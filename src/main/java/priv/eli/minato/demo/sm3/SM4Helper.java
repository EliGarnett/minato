package priv.eli.minato.demo.sm3;

/**
 * SM4Utils 使用不方便，为了避免对其它应用又影响增加了一个工具类
 * 
 * @author wanght
 *
 */
public class SM4Helper {

	public static byte[] encryptData_ECB(byte[] plainContent, String secretKey) throws Exception {

		byte[] keyBytes = secretKey.getBytes("utf-8");
		return encryptData_ECB(plainContent, keyBytes);
	}

	public static byte[] encryptData_ECB(byte[] plainContent, byte[] keyBytes) throws Exception {
		SM4_Context ctx = new SM4_Context();
		ctx.isPadding = true;
		ctx.mode = SM4.SM4_ENCRYPT;
		SM4 sm4 = new SM4();
		sm4.sm4_setkey_enc(ctx, keyBytes);
		byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainContent);
		return encrypted;
	}

	public static byte[] decryptData_ECB(byte[] cipherContent, String secretKey) throws Exception {
		byte[] keyBytes;
		keyBytes = secretKey.getBytes("utf-8");
		return decryptData_ECB(cipherContent, keyBytes);
	}

	public static byte[] decryptData_ECB(byte[] cipherContent, byte[] keyBytes) throws Exception {
		SM4_Context ctx = new SM4_Context();
		ctx.isPadding = true;
		ctx.mode = SM4.SM4_DECRYPT;
		SM4 sm4 = new SM4();
		sm4.sm4_setkey_dec(ctx, keyBytes);
		byte[] decrypted = sm4.sm4_crypt_ecb(ctx, cipherContent);
		return decrypted;
	}

}

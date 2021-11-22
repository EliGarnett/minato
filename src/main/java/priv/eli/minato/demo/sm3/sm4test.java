/**
 * 
 */
package priv.eli.minato.demo.sm3;

/**
 * <p>
 * <b>服务中心名称:</b>
 * </p>
 * <p>
 * <b>服务名称:</b>
 * </p>
 * <p>
 * <b>使用场景:</b>
 * </p>
 * </br>
 * </br>
 * <p>
 * <b>功能描述:</b>
 * </p>
 * </br>
 * <p>
 * <b>注意事项:</b>
 * </p>
 * </br>
 * <p>
 * <b>版权:</b>税友软件集团股份有限公司
 * </p>
 * <p>
 * <b>创建时间:</b>2020年4月14日下午3:34:57
 * </p>
 * <p>
 * <b>作者:</b>lmyue
 * </p>
 * <p>
 * <b>修改历史记录:</b>
 * </p>
 * ====================================================================<br>
 * 维护单：</br>
 * 修改日期：</br>
 * 修改人：</br>
 * 修改内容和修改原因：</br>
 * </br>
 **/
public class sm4test {
	public static void main(String[] args) {
		try {
			byte[] sss = SM4Helper.encryptData_ECB("中文aa22".getBytes(), "WB-ZHRH-FPYJ-SM4");
			String data = Base64Sy.encodeToString(sss);
			System.out.println(data);
			sss = Base64Sy.decode(data);
			byte[] jmhStr = SM4Helper.decryptData_ECB(sss, "WB-ZHRH-FPYJ-SM4");
			System.out.println(new String(jmhStr, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



package priv.eli.minato.demo.sm3;

import java.util.UUID;

public class m3test {

    public static void main(String[] args) {

        String pwd = "*#763@0";
        String tradeSeq = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        System.out.println(tradeSeq);
        String requestPwd = "";
        String tempPwd = "";
        try {
            String pwdtranseq = pwd + "#" + tradeSeq;
            byte[] tempByte = SM3Util.hash(pwdtranseq.getBytes());
            tempPwd = Base64Sy.encodeToString(tempByte);
            requestPwd = tempPwd;
            System.out.println(requestPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

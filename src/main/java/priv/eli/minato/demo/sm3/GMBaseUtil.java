package priv.eli.minato.demo.sm3;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class GMBaseUtil {
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
}

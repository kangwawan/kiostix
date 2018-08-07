package com.birumerah.kiostix.encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class SHA256Encoder implements PasswordEncoder {

	public SHA256Encoder() {
	}


	public String encode(CharSequence rawPassword)  {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] hash = digest.digest(rawPassword.toString().getBytes(StandardCharsets.UTF_8));
		
		return new String(Hex.encode(hash));
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String digested = encode(rawPassword.toString());
		return digested.equals(encodedPassword);
	}


	private static final int DEFAULT_ITERATIONS = 1024;

}

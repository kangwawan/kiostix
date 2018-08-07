package com.birumerah.kiostix.encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HMAC_SHA256Encoder {
	public String encode (String message, String secret){
		String hash = "";
		try {

		    Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		    SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		    sha256_HMAC.init(secret_key);

		    hash = Hex.encodeHexString(sha256_HMAC.doFinal(message.getBytes()));

		    System.out.println(hash);
		} catch (Exception e){
		     System.out.println("Error");
		}
		
		return hash;
	}

}

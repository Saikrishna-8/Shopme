package com.sai;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurePasswordGenerator {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String rawPass="Fassak@08";
		
		String encodedPass=encoder.encode(rawPass);
		System.out.println(encodedPass);
	}

}

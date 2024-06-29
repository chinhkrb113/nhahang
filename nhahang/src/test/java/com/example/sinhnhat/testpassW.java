package com.example.sinhnhat;

import org.springframework.security.crypto.password.PasswordEncoder;


public class testpassW {

    public PasswordEncoder encoder;

    public void main(String args[]) {
		String value = "123456";
        String Pass = bcryptHashing(value);
		System.out.println(Pass);
	}

    public String bcryptHashing(String password) {
		return encoder.encode(password);
	}
}
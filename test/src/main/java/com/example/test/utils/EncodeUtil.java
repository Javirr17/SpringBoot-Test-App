package com.example.test.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class EncodeUtil {

    public static String encode(String text, String cryptAlg) {

        Map encoders = new HashMap<>();
        encoders.put(cryptAlg, new BCryptPasswordEncoder());
        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(cryptAlg, encoders);
        return passwordEncoder.encode(text);

    }
}

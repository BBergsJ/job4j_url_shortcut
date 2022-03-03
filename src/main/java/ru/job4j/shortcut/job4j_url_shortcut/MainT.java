package ru.job4j.shortcut.job4j_url_shortcut;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainT {
    private BCryptPasswordEncoder encoder;

    public static void main(String[] args) {

        String usn = "e=iLAS";
        String psw = "b@l<LY";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.printf(encoder.encode(psw));
    }
}

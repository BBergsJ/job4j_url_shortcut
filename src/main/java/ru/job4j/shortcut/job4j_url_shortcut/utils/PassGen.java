package ru.job4j.shortcut.job4j_url_shortcut.utils;

import java.security.SecureRandom;

public class PassGen {

    public static String generate() {
        return new SecureRandom().ints(6, 33, 122).collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}

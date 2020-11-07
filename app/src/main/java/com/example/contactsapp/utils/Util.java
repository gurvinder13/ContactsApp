package com.example.contactsapp.utils;

import android.util.Patterns;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {

    public static boolean isValidName(String name) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        return patron.matcher(name).matches();
    }

    public static boolean isValidPhone(String phone) {
        return Patterns.PHONE.matcher(phone).matches();
    }

}

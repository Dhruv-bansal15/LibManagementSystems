package com.library.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Helper {
    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
}

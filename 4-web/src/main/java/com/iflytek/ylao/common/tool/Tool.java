package com.iflytek.ylao.common.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tool {

    public static String dateFormatter(String input){

        String output = "";

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy年M月d日");

        try {
            Date date = inputFormat.parse(input);
            output = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return output;
    }
}

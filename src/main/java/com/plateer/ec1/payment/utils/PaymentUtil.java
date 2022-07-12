package com.plateer.ec1.payment.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PaymentUtil {

    public static String getNewPayNo(){
        return appendString("C", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")));
    }

    public static String appendString(String ...strs){
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str);
        }
        return builder.toString();
    }

}

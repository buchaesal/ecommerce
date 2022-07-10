package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.PayInfo;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InicisFactory {

    private static final String MID = "INIpayTest";
    private static final String API_KEY = "ItEQKi3rY7uvDS8l";
    private static final String API_URL = "https://iniapi.inicis.com/api/v1/formpay";

    public static MultiValueMap<String, String> inicisVirtualAccountRequest(OrderInfo orderInfo, PayInfo payInfo) {

        LocalDateTime todayNow = LocalDateTime.now();

        String type = "Pay";
        String paymethod = "Vacct";
        String timestamp = todayNow.format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
        String clientIp = clientIp();
        String moid = orderInfo.getOrdNo();
        String price = String.valueOf(payInfo.getPayAmount());

        // INIAPIKey+type+paymethod+timestamp+clientIp+mid+moid+price
        String hashData = hashSha512(appendString(API_KEY, type, paymethod, timestamp, clientIp, MID, moid, price));

        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();
        result.add("type", type);
        result.add("paymethod", paymethod);
        result.add("timestamp", timestamp);
        result.add("clientIp", clientIp);
        result.add("mid", MID);
        result.add("url", "");
        result.add("moid", moid); // 가맹점 주문번호
        result.add("goodName", orderInfo.getGoodName());
        result.add("buyerName", orderInfo.getBuyerName());
        result.add("buyerEmail", orderInfo.getBuyerEmail());
        result.add("price", price);
        result.add("currency", "WON");
        result.add("bankCode", payInfo.getBankCode());

        LocalDateTime onedayAfter = todayNow.plusHours(24);

        result.add("dtInput", onedayAfter.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        result.add("tmInput", onedayAfter.format(DateTimeFormatter.ofPattern("hhmm")));
        result.add("nmInput", payInfo.getDepositorName());
        result.add("hashData", hashData);

        return result;

    }

    private static String clientIp() {
        String clientIp;
        try {
            clientIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return clientIp;
    }

    private static String hashSha512(String shaBaseStr) {
        String result;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(shaBaseStr.getBytes(StandardCharsets.UTF_8));
            result = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("can not be happened");
        }
        return result;
    }

    private static String appendString(String ...strs){
        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str);
        }
        return builder.toString();
    }

    public static String getApiUrl(){
        return API_URL;
    }


}

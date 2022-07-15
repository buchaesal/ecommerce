package com.plateer.ec1.payment.factory;

import com.plateer.ec1.payment.utils.PaymentUtil;
import com.plateer.ec1.payment.vo.OrderInfo;
import com.plateer.ec1.payment.vo.OriginalOrder;
import com.plateer.ec1.payment.vo.PayInfo;
import com.plateer.ec1.payment.vo.req.PaymentCancelRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class InicisFactory {

    private static final String MID = "INIpayTest";
    private static final String API_KEY = "ItEQKi3rY7uvDS8l";
    private static final String IV = "HYb3yQ4f65QL89==";
    private static final String API_URL = "https://iniapi.inicis.com/api/v1/formpay";
    private static final String CANCEL_API_URL = "https://iniapi.inicis.com/api/v1/refund";

    public static MultiValueMap<String, String> inicisVirtualAccountRequest(OrderInfo orderInfo, PayInfo payInfo) {

        LocalDateTime todayNow = LocalDateTime.now();

        String type = "Pay";
        String paymethod = "Vacct";
        String timestamp = getTimestamp();
        String clientIp = clientIp();
        String moid = orderInfo.getOrdNo();
        String price = String.valueOf(payInfo.getPayAmount());

        // INIAPIKey+type+paymethod+timestamp+clientIp+mid+moid+price
        String hashData = hashSha512(PaymentUtil.appendString(API_KEY, type, paymethod, timestamp, clientIp, MID, moid, price));

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

    public static MultiValueMap<String, String> allRefundVirtualAccount(PaymentCancelRequest request, OriginalOrder originalOrder){

        String type= "Refund";
        MultiValueMap<String, String> resultMap = commonRefundVirtualAccount(request, originalOrder);
        resultMap.add("type", type);

        return resultMap;

    }

    public static MultiValueMap<String, String> partialRefundVirtualAccount(PaymentCancelRequest request, OriginalOrder originalOrder){

        String type = "PartialRefund";
        MultiValueMap<String, String> resultMap = commonRefundVirtualAccount(request, originalOrder);
        resultMap.add("type", type);

        return resultMap;

    }

    private static MultiValueMap<String, String> commonRefundVirtualAccount(PaymentCancelRequest request, OriginalOrder originalOrder){

        MultiValueMap<String, String> result = new LinkedMultiValueMap<>();

        result.add("paymethod", "Vacct");
        result.add("timestamp", getTimestamp());
        result.add("clientIp", clientIp());
        result.add("mid", MID);
        result.add("tid", originalOrder.getTrsnId());
        result.add("msg", "none");
        result.add("refundAcctNum", encAES(originalOrder.getVrAcct()));
        result.add("refundBankCode", originalOrder.getVrBnkCd());
        result.add("refundAcctName", originalOrder.getVrAcctNm());

        return result;

    }

    private static String getTimestamp(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
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

    private static String encAES(String str) {

        String result;

        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(API_KEY.getBytes(), "AES");
            IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            result = Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e){
            throw new RuntimeException("enc AES fail");
        }

        return result;

    }


    public static String getApiUrl(){
        return API_URL;
    }

    public static String getCancelApiUrl(){
        return CANCEL_API_URL;
    }

}

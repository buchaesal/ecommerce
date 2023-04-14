package com.plateer.ec1.payment.controller;

import com.plateer.ec1.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("inicis")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping(path = "deposit")
    public String completeDeposit(@RequestParam Map<String, String> result) {
        if("0200".equals(result.get("type_msg"))){
            paymentService.completeDeposit(result);
            return "OK";
        }
        return "";
    }

}

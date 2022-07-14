package com.plateer.ec1.payment.controller;

import com.plateer.ec1.payment.service.PayService;
import com.plateer.ec1.payment.vo.req.ChangeDepositCompleteRequest;
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

    private final PayService payService;

    @PostMapping(path = "deposit")
    public void completeDeposit(@RequestParam Map<String, String> result) {
        payService.completeDeposit(new ChangeDepositCompleteRequest(result));
    }

}

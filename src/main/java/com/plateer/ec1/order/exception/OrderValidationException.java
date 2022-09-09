package com.plateer.ec1.order.exception;

public class OrderValidationException extends RuntimeException{
    public OrderValidationException(String msg){
        super(msg);
    }
}

package com.example.order_jpa.exception;

public class NoEnoughStockException extends Exception {
    //all or nothing, 만들어지다가 말거나 하면 안됨
    public NoEnoughStockException(String s) {
        super(s);
    }
}

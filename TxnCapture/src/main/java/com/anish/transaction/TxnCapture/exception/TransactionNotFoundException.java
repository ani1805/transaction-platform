package com.anish.transaction.TxnCapture.exception;

public class TransactionNotFoundException extends RuntimeException {

    public TransactionNotFoundException(String message){
        super(message);
    }
}

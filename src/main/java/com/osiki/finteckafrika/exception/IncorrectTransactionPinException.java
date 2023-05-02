package com.osiki.finteckafrika.exception;

public class IncorrectTransactionPinException extends RuntimeException{
    public IncorrectTransactionPinException(String message) {
        super(message);
    }
}

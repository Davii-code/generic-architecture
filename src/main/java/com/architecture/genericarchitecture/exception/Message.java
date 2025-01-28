package com.architecture.genericarchitecture.exception;

import com.architecture.genericarchitecture.enums.ApiErrorEnum;
import com.architecture.genericarchitecture.enums.MessageType;
import lombok.Data;

@Data
public class Message {
    private String message;

    private String code;
    private MessageType type;
    private Object[] params;

    public Message(ApiErrorEnum messageCode, Object... params) {
        this.type = messageCode.getType();
        this.code = messageCode.getCode();
        this.params = params;
    }
}

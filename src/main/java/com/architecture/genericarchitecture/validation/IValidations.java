package com.architecture.genericarchitecture.validation;

import com.architecture.genericarchitecture.enums.ValidationActionsEnum;
import com.architecture.genericarchitecture.exception.Message;

import java.util.List;

public interface IValidations<MODEL> {
    void validate(MODEL data, ValidationActionsEnum action, List<Message> messagesToThrow);
}

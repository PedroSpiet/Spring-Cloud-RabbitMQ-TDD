package com.spiet.people.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmailError extends RuntimeException{

    public EmailError(String err) {
        super(err);
    }
}

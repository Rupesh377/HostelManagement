package com.rupesh.HostelManagement.ExceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value = HttpStatus.CONFLICT)
public class HostelAlreadyExistsException extends RuntimeException{
        public HostelAlreadyExistsException(String message){
            super(message);
        }
}
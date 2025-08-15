package com.rupesh.HostelManagement.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

 @ResponseStatus(value = HttpStatus.CONFLICT)
 public class StudentAlreadyExistsException extends RuntimeException{

     public StudentAlreadyExistsException(String message){
         super(message);
     }
 }

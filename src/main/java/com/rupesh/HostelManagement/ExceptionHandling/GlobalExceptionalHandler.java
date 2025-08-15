package com.rupesh.HostelManagement.ExceptionHandling;

import com.rupesh.HostelManagement.DTO.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> handleEmployeeAlreadyExistsException(StudentAlreadyExistsException ex ,
                                                                                     WebRequest webRequest){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(webRequest.getDescription(false),
                HttpStatus.CONFLICT , ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionResponseDTO);
    }

    //For any other exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGlobalException(Exception ex ,
                                                                      WebRequest webRequest){
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionResponseDTO);
    }
}

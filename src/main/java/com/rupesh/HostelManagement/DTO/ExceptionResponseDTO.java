package com.rupesh.HostelManagement.DTO;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ExceptionResponseDTO {

    private String apiPath;
    private HttpStatus statuscode;
    private String errorMessage;
    private LocalDateTime errortime;

    public ExceptionResponseDTO(String apiPath, HttpStatus statuscode, String errorMessage, LocalDateTime errortime) {
        this.apiPath = apiPath;
        this.statuscode = statuscode;
        this.errorMessage = errorMessage;
        this.errortime = errortime;
    }

    public ExceptionResponseDTO() {
    }

}


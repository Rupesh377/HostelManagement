package com.rupesh.HostelManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HostelDTO {

    private int id;
    private String hostel;
    private List<StudentDTO> student;
}
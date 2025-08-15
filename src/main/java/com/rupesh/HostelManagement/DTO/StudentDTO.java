package com.rupesh.HostelManagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private int id;
    private String enrollment_no;
    private String name;
    private String Room_Bed;
    private String hostel;
}

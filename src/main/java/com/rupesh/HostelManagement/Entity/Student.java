package com.rupesh.HostelManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Enrollment no." , nullable = false)
    private String enrollment_no;
    private String name;

    @Column(name = "Room_Bed" , nullable=false)
    private String Room_Bed;

    @ManyToOne
    @JoinColumn(name = "hostel_id" , nullable = false)
    private Hostel hostel;
}

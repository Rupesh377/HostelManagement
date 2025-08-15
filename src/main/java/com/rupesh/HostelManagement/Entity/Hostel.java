package com.rupesh.HostelManagement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hostel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Hostel_name" ,nullable = false , unique = true)
    private String hostel;

    @OneToMany(mappedBy = "hostel" ,cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Student>students;
}

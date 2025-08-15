package com.rupesh.HostelManagement.Repository;

import com.rupesh.HostelManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student , Integer> {
    Optional<Student> findByName(String name);
}

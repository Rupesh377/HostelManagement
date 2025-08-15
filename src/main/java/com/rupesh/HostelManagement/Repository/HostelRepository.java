package com.rupesh.HostelManagement.Repository;

import com.rupesh.HostelManagement.Entity.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HostelRepository extends JpaRepository<Hostel , Integer> {
  Optional<Hostel> findByHostel(String hostel);
}

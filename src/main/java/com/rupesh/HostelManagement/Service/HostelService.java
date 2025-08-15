package com.rupesh.HostelManagement.Service;

import com.rupesh.HostelManagement.DTO.HostelDTO;
import com.rupesh.HostelManagement.DTO.StudentDTO;
import com.rupesh.HostelManagement.Entity.Hostel;
import com.rupesh.HostelManagement.Entity.Student;
import com.rupesh.HostelManagement.ExceptionHandling.StudentAlreadyExistsException;
import com.rupesh.HostelManagement.Repository.HostelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
    public class HostelService {

        private final HostelRepository hostelRepository;
        private final ModelMapper modelMapper;

//        public HostelService(HostelRepository hostelRepository, ModelMapper modelMapper) {
//            this.hostelRepository = hostelRepository;
//            this.modelMapper = modelMapper;
//        }

        public List<HostelDTO> GelAllHostelDetails() {
            List<HostelDTO> hostelDTOS = hostelRepository.findAll().stream()
                    .map(hostel -> modelMapper.map(hostel, HostelDTO.class)).toList();
            return hostelDTOS;
        }

    public HostelDTO GetHostelById(int id) {
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));
        return modelMapper.map(hostel, HostelDTO.class);
    }

    public HostelDTO CreateNewHostel(HostelDTO hostelDTO) {
        Optional<Hostel> hostel=hostelRepository.findByHostel(hostelDTO.getHostel());
        if(hostel.isPresent()) {
            throw new StudentAlreadyExistsException("Student" + hostelDTO.getHostel() + " already exists");
        }
        Hostel hostel1=modelMapper.map(hostelDTO , Hostel.class);
        return modelMapper.map(hostelRepository.save(hostel1) , HostelDTO.class);
    }

    public HostelDTO UpdateHostelName(int id, HostelDTO hostelDTO) {
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));

        hostel.setHostel(hostelDTO.getHostel());

        Hostel updatedHostel = hostelRepository.save(hostel);
        return modelMapper.map(updatedHostel, HostelDTO.class);
    }
}

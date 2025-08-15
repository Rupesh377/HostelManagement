package com.rupesh.HostelManagement.Service;

import com.rupesh.HostelManagement.DTO.StudentDTO;
import com.rupesh.HostelManagement.Entity.Hostel;
import com.rupesh.HostelManagement.Entity.Student;
import com.rupesh.HostelManagement.ExceptionHandling.StudentAlreadyExistsException;
import com.rupesh.HostelManagement.Repository.HostelRepository;
import com.rupesh.HostelManagement.Repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final HostelRepository hostelRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, HostelRepository hostelRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.hostelRepository = hostelRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getAllStudents() {
        List<StudentDTO> studentDTOList = studentRepository.findAll().stream()
                .map(student -> modelMapper.map(student, StudentDTO.class)).toList();
        return studentDTOList;
    }

    public StudentDTO getStudentById(int id) {
        Student student=studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student Not Found With Id"+ id ));
        return modelMapper.map(student , StudentDTO.class);
    }
    public StudentDTO CreateNewStudent(StudentDTO studentDTO) {
        // Check if student already exists by name
        Optional<Student> existingStudent = studentRepository.findByName(studentDTO.getName());
        if (existingStudent.isPresent()) {
            throw new StudentAlreadyExistsException(
                    "Student " + studentDTO.getName() + " already exists"
            );
        }

        // Check if hostel exists, else create it
        Hostel hostel = hostelRepository.findByHostel(studentDTO.getHostel())
                .orElseGet(() -> {
                    Hostel newHostel = new Hostel();
                    newHostel.setHostel(studentDTO.getHostel());
                    return hostelRepository.save(newHostel);
                });

        // Map DTO → Entity
        Student student = modelMapper.map(studentDTO, Student.class);

        // Attach persistent hostel
        student.setHostel(hostel);

        // Save student
        Student savedStudent = studentRepository.save(student);

        // Map Entity → DTO
        StudentDTO responseDTO = modelMapper.map(savedStudent, StudentDTO.class);
        responseDTO.setHostel(savedStudent.getHostel().getHostel());

        return responseDTO;
    }

//        public StudentDTO CreateNewStudent(StudentDTO studentDTO) {
//        Optional<Student> student=studentRepository.findByName(studentDTO.getName());
//        if(student.isPresent()) {
//            throw new StudentAlreadyExistsException("Student" + studentDTO.getName() + " already exists");
//        }
//        Student student1=modelMapper.map(studentDTO , Student.class);
//        return modelMapper.map(studentRepository.save(student1) , StudentDTO.class);
//    }

    public String DeleteStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return "Deleted Student";
        }
        else throw new StudentAlreadyExistsException("Student With Id: " + id + " not exists");
//        studentRepository.deleteById(id);
//        return "Deleted Student";
    }

    public StudentDTO updateStudentById(int id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setEnrollment_no(studentDTO.getEnrollment_no());
        student.setName(studentDTO.getName());
        student.setRoom_Bed(studentDTO.getRoom_Bed());

//        if (studentDTO.getHostel() != null) {
//            Optional<Hostel> hostel = HostelRepository.findByHostel(studentDTO.getHostel());
//            hostel.ifPresent(student::setHostel);
//        }

        Student updatedStudent = studentRepository.save(student);

        StudentDTO updatedDTO = modelMapper.map(updatedStudent, StudentDTO.class);
        updatedDTO.setHostel(updatedStudent.getHostel().getHostel());

        return updatedDTO;
    }
}

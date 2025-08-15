package com.rupesh.HostelManagement.Controller;

import com.rupesh.HostelManagement.DTO.HostelDTO;
import com.rupesh.HostelManagement.Service.HostelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hostel")
public class HostelController {

    private final HostelService hostelService;

    public HostelController(HostelService hostelService) {
        this.hostelService = hostelService;
    }

    @GetMapping(path = "/getAll")
    public List<HostelDTO> GetAllHotel(){
        return hostelService.GelAllHostelDetails();
    }

    @GetMapping(path = "/get/{id}")
    public HostelDTO GetHostel(@PathVariable("id") int id){
        return hostelService.GetHostelById(id);
    }

    @PostMapping("/create")
    public HostelDTO CreateHostel(@RequestBody HostelDTO hostelDTO){
        return hostelService.CreateNewHostel(hostelDTO);
    }

    @PutMapping(path = "/update/{id}")
    public HostelDTO UpdateHostel(@PathVariable int id , @RequestBody HostelDTO hostelDTO){
        return hostelService.UpdateHostelName(id,hostelDTO);
    }
}

package com.example.tailorShop.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.Workstation;
import com.example.tailorShop.repository.WorkstationRepository;

@Service 
public class WorkstationService {
    @Autowired 
    private WorkstationRepository WorkstationRepo;

    public String save (Workstation workstation){
        WorkstationRepo.save(workstation);
        return "Workstation saved successfully";
    }

    public List<Workstation> findAll(){
        return WorkstationRepo.findAll();
    }
    
    public Optional<Workstation> findById(UUID id){
        return WorkstationRepo.findById(id);
    }

    public String update (UUID id, Workstation details){
        Optional<Workstation> result = WorkstationRepo.findById(id);

        if (result.isEmpty()){
            return "The workstation with that id does not exist";
        }

        Workstation workstation = result.get();
        workstation.setStationNumber(details.getStationNumber());
        workstation.setName(details.getName());
        WorkstationRepo.save(workstation);

        return "Workstation updated successfully";
    }

    public String delete (UUID id){
        if(!WorkstationRepo.existsById(id)){
           return "The workstation with that id does not exist";
        }

        WorkstationRepo.deleteById(id);
        return "Workstation deleted successfully";
    }
}

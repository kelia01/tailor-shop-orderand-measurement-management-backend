package com.example.tailorShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.Tailor;
import com.example.tailorShop.repository.TailorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class TailorService {
    @Autowired 
    private TailorRepository tailorRepo;
    
    public String save(Tailor tailor) {
        tailorRepo.save(tailor);
        return "Tailor saved successfully";
    }

    public List<Tailor> findAll() {
        return tailorRepo.findAll();
    }

    public Optional<Tailor> findById(UUID id) {
        return tailorRepo.findById(id);
    }

    public String update(UUID id, Tailor details) {
        Optional<Tailor> result = tailorRepo.findById(id);

        if (result.isEmpty()) {
            return "The tailor with that id does not exist";
        }

        Tailor tailor = result.get();
        tailor.setFirstName(details.getFirstName());
        tailor.setLastName(details.getLastName());
        tailor.setPhone(details.getPhone());
        tailor.setWorkstation(details.getWorkstation());
        tailor.setGarmentTypes(details.getGarmentTypes());

        tailorRepo.save(tailor);
        return "Tailor updated successfully";
    }

    public String delete(UUID id) {
        if (!tailorRepo.existsById(id)) {
            return "The tailor with that id does not exist";
        }

        tailorRepo.deleteById(id);
        return "Tailor deleted successfully";
    }

    public List<Tailor> findByGarmentTypeName(String name){
        return tailorRepo.findByGarmentTypeName(name);
    }

    public boolean existsById(UUID id){
        return tailorRepo.existsById(id);
    }
}

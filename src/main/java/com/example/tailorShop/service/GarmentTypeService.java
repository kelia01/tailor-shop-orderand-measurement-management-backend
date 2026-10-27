package com.example.tailorShop.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.GarmentType;
import com.example.tailorShop.repository.GarmentTypeRepository;

@Service 
public class GarmentTypeService {
    @Autowired
    private GarmentTypeRepository GarmentTypeRepo;
    
    public String save (GarmentType garmentType){
        GarmentTypeRepo.save(garmentType);
        return "Garment type saved successfully";
    }

    public List<GarmentType> findAll(){
        return GarmentTypeRepo.findAll();
    }

    public Optional<GarmentType> findById(UUID id){
        return GarmentTypeRepo.findById(id);
    }
    public String update (UUID id, GarmentType details){
        Optional<GarmentType> result = GarmentTypeRepo.findById(id);

        if (result.isEmpty()){
            return "The garment type with that id does not exist";
        }
        GarmentType garmentType = result.get();
        garmentType.setName(details.getName());
        GarmentTypeRepo.save(garmentType);

        return "Garment type updated successfully";
    }

    public String delete (UUID id){
        if (!GarmentTypeRepo.existsById(id)){
            return "The garment type with that id does not exist";
        }

        GarmentTypeRepo.deleteById(id);
        return "Garment type deleted successfully";
    }

    public List<GarmentType> findUnusedGarmentTypes() {
    return GarmentTypeRepo.findUnusedGarmentTypes();
}
}


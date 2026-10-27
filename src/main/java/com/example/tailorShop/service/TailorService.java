package com.example.tailorShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.GarmentType;
import com.example.tailorShop.domain.Tailor;
import com.example.tailorShop.domain.Workstation;
import com.example.tailorShop.repository.GarmentTypeRepository;
import com.example.tailorShop.repository.TailorRepository;
import com.example.tailorShop.repository.WorkstationRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service 
public class TailorService {
    @Autowired 
    private TailorRepository tailorRepo;

    @Autowired 
    private GarmentTypeRepository garmentTypeRepo;

    @Autowired 
    private WorkstationRepository workstationRepo;
    
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

    public String addGarmentType(UUID tailorId, UUID garmentTypeId){
        Tailor exists = tailorRepo.findById(tailorId).orElse(null);
        Optional<GarmentType> existing = garmentTypeRepo.findById(garmentTypeId);

        if(exists == null ){
            return "The tailor with that id does not exist";
        }
        if(existing == null ){
            return "That garment type does not exist";
        }
        if(tailorRepo.existsByIdAndGarmentTypes_Id(tailorId, garmentTypeId)){
            return "The tailor already has that garment type";
        }

        exists.getGarmentTypes().add(existing.get());
        tailorRepo.save(exists);

        return "Garment type added to the tailor";
    }

    public String changeWorkstation(UUID tailorId, int stationNumber){
        Tailor exists = tailorRepo.findById(tailorId).orElse(null);
        Workstation existing = workstationRepo.findByStationNumber(stationNumber).orElse(null);
        boolean existsByStationNumber = tailorRepo.existsByWorkstation_StationNumberAndIdNot(stationNumber, tailorId);

        if(exists == null){
            return "The tailor with that id does not exist";
        }
        if(existing == null){
            return "The workstation with that stationNumber does not exist";
        }
        if(existsByStationNumber){
            return "That workstation is taken by another tailor";
        }

        exists.setWorkstation(existing);
        tailorRepo.save(exists);
        return "Workstation changed successfully";

    }
}

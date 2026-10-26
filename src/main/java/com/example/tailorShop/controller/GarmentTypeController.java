package com.example.tailorShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tailorShop.domain.GarmentType;
import com.example.tailorShop.service.GarmentTypeService;

import java.util.Optional;
import java.util.UUID;

@RestController 
@RequestMapping ("garment-types")
public class GarmentTypeController {
    @Autowired
    private GarmentTypeService GarmentTypeServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody GarmentType garmentType) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(GarmentTypeServ.save(garmentType));
    }

    @GetMapping ("/all")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(GarmentTypeServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
    Optional<GarmentType> result = GarmentTypeServ.findById(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The garment type with that id does not exist");
        }
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody GarmentType details) {

        String result = GarmentTypeServ.update(id, details);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        String result = GarmentTypeServ.delete(id);

        if(result.contains("does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }


}

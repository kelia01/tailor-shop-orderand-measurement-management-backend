package com.example.tailorShop.controller;

import com.example.tailorShop.domain.Tailor;
import com.example.tailorShop.service.TailorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController 
@RequestMapping ("tailors")
public class TailorController {
    
    @Autowired
    private TailorService tailorServ;
    
    @PostMapping ("/save")
    public ResponseEntity<?> save(@RequestBody Tailor tailor){
        return ResponseEntity.status(HttpStatus.CREATED).body(tailorServ.save(tailor));
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(tailorServ.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Tailor result = tailorServ.findById(id).orElse(null);
        
        if (result != null) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(result);
        }
    }

    @GetMapping ("/by-garment-type")
    public ResponseEntity<?> findByGarmentTypeName (@RequestParam String name){
        List<Tailor> result = tailorServ.findByGarmentTypeName(name);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody Tailor details) {

        String result = tailorServ.update(id, details);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PatchMapping ("/{tailorId}/garment-types/{garmentTypeId}")
    public ResponseEntity<?> addGarmentType( @PathVariable UUID tailorId,
        @PathVariable UUID garmentTypeId){
            String result = tailorServ.addGarmentType(tailorId, garmentTypeId);

    if (result.equals("The tailor with that id does not exist")
            || result.equals("That garment type does not exist")) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    if (result.equals("The tailor already has that garment type")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String result = tailorServ.delete(id);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @PatchMapping ("/{tailorId}/workstation")
    public ResponseEntity<?> changeWorkstation (@PathVariable UUID tailorId, @RequestParam int stationNumber){
        String result = tailorServ.changeWorkstation(tailorId, stationNumber);

        if (result.equals("The tailor with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        if (result.equals("The workstation with that stationNumber does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        if (result.equals("That workstation is taken by another tailor")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        return ResponseEntity.ok(result);
    }


}

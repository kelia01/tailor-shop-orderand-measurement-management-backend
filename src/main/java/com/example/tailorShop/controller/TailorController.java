package com.example.tailorShop.controller;

import com.example.tailorShop.domain.Tailor;
import com.example.tailorShop.service.TailorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String result = tailorServ.delete(id);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}

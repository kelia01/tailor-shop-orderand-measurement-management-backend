package com.example.tailorShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tailorShop.domain.Workstation;
import com.example.tailorShop.service.WorkstationService;

import java.util.UUID;

@RestController
@RequestMapping ("workstations")
public class WorkstationController {
    @Autowired
    private WorkstationService workstationServ;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Workstation workstation) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(workstationServ.save(workstation));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(workstationServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
    Workstation result = workstationServ.findById(id).orElse(null);

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
            @RequestBody Workstation details) {

        String result = workstationServ.update(id, details);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String result = workstationServ.delete(id);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}

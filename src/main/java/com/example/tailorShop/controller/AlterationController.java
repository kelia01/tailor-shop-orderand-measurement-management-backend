package com.example.tailorShop.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tailorShop.domain.Alteration;
import com.example.tailorShop.service.AlterationService;

@RestController 
@RequestMapping ("alterations")
public class AlterationController {

    @Autowired 
    private AlterationService alterationServ;
    
    @PostMapping ("/save")
    public ResponseEntity<?> save(@RequestParam UUID tailoringOrderId, @RequestBody Alteration details){
        String result = alterationServ.save(tailoringOrderId, details);

        if (result.equals( "The tailoring order with that id does not exist")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        if (result.equals( "Alterations are only for READY tailoring orders")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping ("/customer/{customerCode}")
    public ResponseEntity<?> findByCustomerCode(@PathVariable String customerCode){
        return ResponseEntity.ok(alterationServ.findByCustomerCode(customerCode));
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String result = alterationServ.delete(id);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }
}

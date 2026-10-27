package com.example.tailorShop.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tailorShop.domain.Customer;
import com.example.tailorShop.service.CustomerService;
import com.example.tailorShop.service.TailorService;

@RestController 
@RequestMapping ("customers")
public class CustomerController {

    @Autowired 
    private CustomerService customerServ;

    
    @Autowired 
    private TailorService tailorServ;

    @PostMapping ("/save")
    public ResponseEntity<?> save(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerServ.save(customer));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(customerServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
    Customer result = customerServ.findById(id).orElse(null);

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
            @RequestBody Customer details) {

        String result = customerServ.update(id, details);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String result = customerServ.delete(id);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping ("/by-last-name")
    public ResponseEntity<?> findByLastName(@RequestParam String lastName){
        List<Customer> result = customerServ.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);

        return ResponseEntity.ok(result);
    }

    @GetMapping ("/of-tailor/{tailorId}")
    public ResponseEntity<?> findCustomersOfTailor(@PathVariable UUID tailorId){
        boolean exists = tailorServ.existsById(tailorId);
        if(!exists){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The tailor with that id does not exist");
        } else {
            return ResponseEntity.ok(customerServ.findCustomersOfTailor(tailorId));
        }


    }

}

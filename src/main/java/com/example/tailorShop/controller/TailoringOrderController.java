package com.example.tailorShop.controller;

import java.time.LocalDate;
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
import com.example.tailorShop.domain.OrderStatus;
import com.example.tailorShop.domain.TailoringOrder;
import com.example.tailorShop.service.TailoringOrderService;

@RestController 
@RequestMapping ("tailoring-orders")
public class TailoringOrderController {
    
    @Autowired
    private TailoringOrderService tailoringOrderServ;
    
    @PostMapping ("/save")
    public ResponseEntity<?> save(@RequestBody TailoringOrder tailoringOrder){
        return ResponseEntity.status(HttpStatus.CREATED).body(tailoringOrderServ.save(tailoringOrder));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(tailoringOrderServ.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
    TailoringOrder result = tailoringOrderServ.findById(id).orElse(null);

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
            @RequestBody TailoringOrder details) {

        String result = tailoringOrderServ.update(id, details);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        String result = tailoringOrderServ.delete(id);

        if (result.contains("does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping ("/by-status")
    public ResponseEntity<?> findByOrderStatusOrderByOrderDateAsc(@RequestParam OrderStatus orderStatus){
        List<TailoringOrder> result = tailoringOrderServ.findByOrderStatusOrderByOrderDateAsc(orderStatus);

        return ResponseEntity.ok(result);
    }

    @GetMapping ("/between")
    public ResponseEntity<?> findByDateBetween(String start, String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        List<TailoringOrder> result = tailoringOrderServ.findByOrderDateBetween(startDate, endDate);
        return ResponseEntity.ok(result);
    }
}


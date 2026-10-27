package com.example.tailorShop.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        String result = tailoringOrderServ.save(tailoringOrder);

        if (result.equals("Tailoring order date cannot be in the past")){
            return ResponseEntity.badRequest().body(result);
        }

        if (result.equals(
            "Tailor already has an order to start on that date")) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
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
        if(result.contains("That tailoring order has alterations, delete them first")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping ("/by-status")
    public ResponseEntity<?> findByOrderStatusOrderByOrderDateAsc(@RequestParam OrderStatus orderStatus){
        List<TailoringOrder> result = tailoringOrderServ.findByOrderStatusOrderByOrderDateAsc(orderStatus);

        return ResponseEntity.ok(result);
    }

    @GetMapping ("/between")
    public ResponseEntity<?> findByDateBetween(@RequestParam String start, @RequestParam String end){
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        List<TailoringOrder> result = tailoringOrderServ.findByOrderDateBetween(startDate, endDate);
        return ResponseEntity.ok(result);
    }

    @PatchMapping ("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable UUID id, @RequestParam OrderStatus status){
        String result = tailoringOrderServ.updateOrderStatus(id, status);

        if (result.equals(
                "The tailoring order with that id does not exist")) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }

        if (result.startsWith("Cannot change status")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

}


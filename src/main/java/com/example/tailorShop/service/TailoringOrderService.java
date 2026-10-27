package com.example.tailorShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.OrderStatus;
import com.example.tailorShop.domain.TailoringOrder;
import com.example.tailorShop.repository.AlterationRepository;
import com.example.tailorShop.repository.TailoringOrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TailoringOrderService {
    @Autowired
    private TailoringOrderRepository tailoringOrderRepo;

    @Autowired
    private AlterationRepository alterationRepo;

    public String save(TailoringOrder order) {
        if (order.getOrderDate().isBefore(LocalDate.now())) {
            return "Tailoring order date cannot be in the past";
        }

        if(tailoringOrderRepo.existsByTailor_IdAndOrderDateAndStatusNot(order.getTailor().getId(), order.getOrderDate(), OrderStatus.CANCELLED)){
            return "Tailor already has an order to start on that date";
        }

        order.setStatus(OrderStatus.PLACED);
        tailoringOrderRepo.save(order);

        return "Tailoring order saved successfully";
    }

    public List<TailoringOrder> findAll() {
        return tailoringOrderRepo.findAll();
    }

    public Optional<TailoringOrder> findById(UUID id) {
        return tailoringOrderRepo.findById(id);
    }

    public String update(UUID id, TailoringOrder details) {
        Optional<TailoringOrder> result = tailoringOrderRepo.findById(id);

        if (result.isEmpty()) {
            return "The tailoring order with that id does not exist";
        }

        TailoringOrder order = result.get();
        order.setOrderDate(details.getOrderDate());
        order.setGarmentDescription(details.getGarmentDescription());
        order.setChestCm(details.getChestCm());
        order.setCustomer(details.getCustomer());
        order.setTailor(details.getTailor());

        // the order status shouldn't be changed in the general update endpoint
        tailoringOrderRepo.save(order);

        return "Tailoring order updated successfully";
    }
    public String delete(UUID id) {
        TailoringOrder result = tailoringOrderRepo.findById(id).orElse(null);

        if (!tailoringOrderRepo.existsById(id)) {
            return "The tailoring order with that id does not exist";
        }

        if(alterationRepo.existsByTailoringOrder(result)){
            return "That tailoring order has alterations, delete them first";
        }

        tailoringOrderRepo.deleteById(id);
        return "Tailoring order deleted successfully";
    }

    public List<TailoringOrder> findByOrderStatusOrderByOrderDateAsc(OrderStatus orderStatus){
        return tailoringOrderRepo.findByOrderStatusOrderByOrderDateAsc(orderStatus);
    }

    public List<TailoringOrder> findByOrderDateBetween(LocalDate start, LocalDate end){
        return tailoringOrderRepo.findByOrderDateBetween(start, end);
    }

    public String updateOrderStatus(UUID orderId, OrderStatus newStatus){
        Optional<TailoringOrder> existing = tailoringOrderRepo.findById(orderId);

        if(existing == null ){
            return "The tailoring order with that id does not exist";
        }

        TailoringOrder order = existing.get();
        OrderStatus current = order.getStatus();

        boolean valid = (current == OrderStatus.PLACED && 
        (newStatus == OrderStatus.CANCELLED || newStatus == OrderStatus.STITCHING) )||
        (current == OrderStatus.STITCHING && 
        (newStatus == OrderStatus.READY || newStatus == OrderStatus.CANCELLED));

        if(!valid){
            return "Cannot change status from" + current + "to" + newStatus;
        }

        order.setStatus(newStatus);
        tailoringOrderRepo.save(order);

        return "Tailoring order status updated successfully";
    }

}

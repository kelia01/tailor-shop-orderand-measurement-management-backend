package com.example.tailorShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.TailoringOrder;
import com.example.tailorShop.repository.TailoringOrderRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TailoringOrderService {
    @Autowired
    private TailoringOrderRepository tailoringOrderRepo;

    public String save(TailoringOrder order) {
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
        if (!tailoringOrderRepo.existsById(id)) {
            return "The tailoring order with that id does not exist";
        }

        tailoringOrderRepo.deleteById(id);
        return "Tailoring order deleted successfully";
    }

}

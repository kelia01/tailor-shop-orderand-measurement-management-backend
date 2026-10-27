package com.example.tailorShop.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.Alteration;
import com.example.tailorShop.domain.OrderStatus;
import com.example.tailorShop.domain.TailoringOrder;
import com.example.tailorShop.repository.AlterationRepository;
import com.example.tailorShop.repository.TailoringOrderRepository;

@Service 
public class AlterationService {

    @Autowired 
    private TailoringOrderRepository tailoringOrderRepo;

    @Autowired 
    private AlterationRepository alterationRepo;
    
    public String save (UUID tailoringOrderId, Alteration details){
        
        TailoringOrder result = tailoringOrderRepo.findById(tailoringOrderId).orElse(null);

        if(result == null){
            return "The tailoring order with that id does not exist";
        }
        if (result.getStatus() != OrderStatus.READY){
            return "Alterations are only for READY tailoring orders";
        }

        details.setTailoringOrder(result);
        details.setFittingDate(LocalDate.now());
        alterationRepo.save(details);

        return "Alteration saved successfully";
    }
}

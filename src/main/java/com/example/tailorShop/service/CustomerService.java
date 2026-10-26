package com.example.tailorShop.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tailorShop.domain.Customer;
import com.example.tailorShop.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired 
    private CustomerRepository customerRepo;

    public String save(Customer customer) {
        customerRepo.save(customer);
        return "Customer saved successfully";
    }

    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Optional<Customer> findById(UUID id) {
        return customerRepo.findById(id);
    }

    public String update(UUID id, Customer details) {
        Optional<Customer> result = customerRepo.findById(id);

        if (result.isEmpty()) {
            return "The customer with that id does not exist";
        }

        Customer customer = result.get();
        customer.setFirstName(details.getFirstName());
        customer.setLastName(details.getLastName());
        customer.setCustomerCode(details.getCustomerCode());
        customer.setPhone(details.getPhone());

        customerRepo.save(customer);
        return "Customer updated successfully";
    }

    public String delete(UUID id) {
        if (!customerRepo.existsById(id)) {
            return "The customer with that id does not exist";
        }

        customerRepo.deleteById(id);
        return "Customer deleted successfully";
    }

    public List<Customer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName){
        return customerRepo.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }
}

package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path="/customer")
    public String addNewUser(@RequestParam String fname, @RequestParam String lname) {
        Customer n = new Customer(fname, lname);
        customerRepository.save(n);
        return "saved";
    }

    @GetMapping(path="/customer")
    public Iterable<Customer> getAllUsers() {
        return customerRepository.findAll();
    }

    @PutMapping(path="/customer")
    public String updateUser(@RequestParam String iid, @RequestParam String fname, @RequestParam String lname) {
        Long lid = null;
        try {
            lid = Long.parseLong(iid);
        } catch (Exception e) {
            return "id error";
        }
        Customer n = customerRepository.getOne(lid);
        n.setFirstName(fname);
        n.setLastName(lname);
        customerRepository.save(n);
        return "updated";
    }

    @DeleteMapping(path="/customer")
    public String deleteUser(@RequestParam String iid) {
        Long lid = null;
        try {
            lid = Long.parseLong(iid);
        } catch(Exception e) {
            return "deleted";
        }
        customerRepository.deleteById(lid);
        return "deleted";
    }


}

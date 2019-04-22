package matjez.rentacarapp.controllers;

import matjez.rentacarapp.models.Customer;
import matjez.rentacarapp.models.dtos.CustomerDto;
import matjez.rentacarapp.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers(){

        return customerService.getCustomers();
    }

    @GetMapping("/dto/customers")
    public List<CustomerDto> getCustomersDto(@RequestParam(value ="customerName", required = false)
                                                         String customerName) {
        if(customerName != null){
            return customerService.getCustomersByCustomersName(customerName);
        }
        return customerService.getCustomersDto();
    }

    @PostMapping("/dto/customers")
    public Customer addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }

    @PutMapping("/dto/customers")
    public void updateCustomer(@RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
    }

    @DeleteMapping("/dto/customers/{customerSurname}")
    public void deleteCustomer(@PathVariable String customerSurname){
        customerService.deleteCustomer(customerSurname);
    }
}

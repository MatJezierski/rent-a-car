package matjez.rentacarapp.services;

import matjez.rentacarapp.mappers.CustomerMapper;
import matjez.rentacarapp.models.Customer;
import matjez.rentacarapp.models.dtos.CustomerDto;
import matjez.rentacarapp.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public List<CustomerDto> getCustomersDto(){
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper :: map)
                .collect(Collectors.toList());
    }

    public Customer addCustomer(CustomerDto customerDto){
        return customerRepository.save(customerMapper.reversedMap(customerDto));
    }

    public void updateCustomer(CustomerDto customerDto){
        customerRepository
                .findCustomerByCustomerSurname(customerDto.getSurname())
                .ifPresent(c -> {
                    c.setName(customerDto.getName());
                    c.setSurname(customerDto.getSurname());
                    c.setAge(customerDto.getAge());
                    c.setAddress(customerDto.getAddress());

                    customerRepository.save(c);
                });
    }

    public void deleteCustomer(String customerSurname){
        customerRepository.deleteCustomerByCustomerSurname(customerSurname);
    }

    public List<CustomerDto> getCustomersByCustomersName(String customerName){

        return customerRepository.findCustomersByCustomersName(customerName)
                .stream()
                .map(customerMapper::map)
                .collect(Collectors.toList());
    }
}

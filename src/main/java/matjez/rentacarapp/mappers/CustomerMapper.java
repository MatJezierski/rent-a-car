package matjez.rentacarapp.mappers;

import matjez.rentacarapp.commons.Mapper;
import matjez.rentacarapp.models.Customer;
import matjez.rentacarapp.models.dtos.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    @Override
    public CustomerDto map(Customer from) {
        return CustomerDto
                .builder()
                .name(from.getName())
                .surname(from.getSurname())
                .age(from.getAge())
                .address(from.getAddress())
                .build();
    }

    @Override
    public Customer reversedMap(CustomerDto to) {
        return Customer
                .builder()
                .name(to.getName())
                .surname(to.getSurname())
                .age(to.getAge())
                .address(to.getAddress())
                .build();
    }
}

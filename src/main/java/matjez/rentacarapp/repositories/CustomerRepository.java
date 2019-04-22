package matjez.rentacarapp.repositories;

import matjez.rentacarapp.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select * from customer where surname = ?1", nativeQuery = true)
    Optional<Customer> findCustomerByCustomerSurname(String customerSurname);

    @Transactional
    @Modifying
    @Query("delete from Customer cu where cu.surname = ?1")
    void deleteCustomerByCustomerSurname(String customerSurname);

    @Query("select cu from Customer cu where cu.name = ?1")
    List<Customer> findCustomersByCustomersName(String customerName);

}

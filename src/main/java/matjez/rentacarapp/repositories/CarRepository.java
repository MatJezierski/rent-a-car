package matjez.rentacarapp.repositories;

import matjez.rentacarapp.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    @Query(value = "select * from cars where car_model = ?1", nativeQuery = true)     // zapytanie HQL
    //@Query("select c from Car c where c.carModel = ?1") //zapytanie JPQL; numery po znaku "?" - argumenty naszej funkcji, począwszy od lewej strony
    Optional<Car> findCarByCarModel(String carModel);


    @Transactional
    @Modifying
    @Query("select c from Car c where c.carModel = ?1")
    void deleteCarByCarModel(String carModel);

}

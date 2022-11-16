package uz.sitelabs.car.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<EntCar, Long> {

    @Query("select e from EntCar e ORDER BY e.id ASC")
    List<EntCar> findAllCars();
}

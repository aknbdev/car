package uz.sitelabs.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.sitelabs.car.model.VerifyToken;

import java.util.Optional;

@Repository
public interface VerifyTokenRepository extends JpaRepository<VerifyToken, Long> {

    @Query("SELECT c FROM VerifyToken c WHERE c.token = :token")
    Optional<VerifyToken> findByToken(@Param("token") String token);
}

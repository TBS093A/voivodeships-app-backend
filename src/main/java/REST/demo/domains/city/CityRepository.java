package REST.demo.domains.city;

import REST.demo.domains.province.Province;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
    Page<City> findAllByNameIsNotNull(Pageable pagaeble);
    Page<City> findAllByProvince(Pageable pageable, Province province);
    Optional<City> findById(Long id);
}

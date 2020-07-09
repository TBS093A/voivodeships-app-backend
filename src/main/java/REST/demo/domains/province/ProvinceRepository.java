package REST.demo.domains.province;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long>{
    Page<Province> findAllByNameIsNotNull(Pageable pagaeble);
    Optional<Province> findById(Long id);
}

package luismis.mx.ris.Pay.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PayRepository extends JpaRepository<Pay, Integer> {
}

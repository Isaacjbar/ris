package luismis.mx.ris.Product.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface ProductRepository extends JpaRepository<Product, Integer> {
}

package luismis.mx.ris.Product.Controller;

import luismis.mx.ris.Product.Model.Product;
import luismis.mx.ris.Product.Model.ProductDTO;
import luismis.mx.ris.Product.Model.ProductRepository;
import luismis.mx.ris.Util.Response.Message;
import luismis.mx.ris.Util.Enum.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Transactional
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            logger.warn("No se encontraron productos registrados");
            return new ResponseEntity<>(new Message(null, "No hay productos disponibles", TypesResponse.WARNING), HttpStatus.OK);
        }
        logger.info("La búsqueda de productos se realizó correctamente");
        return new ResponseEntity<>(new Message(products, "Listado de productos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(ProductDTO dto) {
        if (dto.getName() != null && dto.getName().length() > 100) {
            logger.warn("El nombre excede el límite de caracteres permitidos");
            return new ResponseEntity<>(new Message(null, "El nombre excede el número de caracteres permitidos", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setStatus("active");
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        productRepository.save(product);
        logger.info("El producto fue registrado correctamente");
        return new ResponseEntity<>(new Message(product, "El producto fue registrado correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(ProductDTO dto) {
        if (dto.getProductId() == null) {
            logger.error("El ID del producto es nulo");
            return new ResponseEntity<>(new Message(null, "El ID del producto no puede ser nulo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return productRepository.findById(dto.getProductId())
                .map(product -> {
                    if (dto.getName() != null && dto.getName().length() <= 100) {
                        product.setName(dto.getName());
                    }
                    if (dto.getDescription() != null) {
                        product.setDescription(dto.getDescription());
                    }
                    if (dto.getPrice() != null) {
                        product.setPrice(dto.getPrice());
                    }
                    if (dto.getStock() > 0) {
                        product.setStock(dto.getStock());
                    }
                    if (dto.getStatus() != null && dto.getStatus().length() <= 20) {
                        product.setStatus(dto.getStatus());
                    }
                    product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

                    productRepository.save(product);
                    logger.info("El producto fue actualizado correctamente");
                    return new ResponseEntity<>(new Message(product, "El producto fue actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.error("El producto con ID {} no existe", dto.getProductId());
                    return new ResponseEntity<>(new Message(null, "El producto no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
                });
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(ProductDTO dto) {
        if (dto.getProductId() == null) {
            logger.error("El ID del producto es nulo");
            return new ResponseEntity<>(new Message(null, "El ID del producto no puede ser nulo", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        return productRepository.findById(dto.getProductId())
                .map(product -> {
                    product.setStatus(product.getStatus().equals("active") ? "inactive" : "active");
                    product.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    productRepository.save(product);
                    logger.info("El estado del producto fue actualizado correctamente");
                    return new ResponseEntity<>(new Message(product, "El estado del producto fue actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.error("El producto con ID {} no existe", dto.getProductId());
                    return new ResponseEntity<>(new Message(null, "El producto no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
                });
    }
}

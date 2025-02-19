package luismis.mx.ris.Product.Controller;

import luismis.mx.ris.Product.Model.ProductDTO;
import luismis.mx.ris.Util.Response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveProduct(@Validated(ProductDTO.Registrar.class) @RequestBody ProductDTO dto) {
        return productService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateProduct(@Validated(ProductDTO.Modificar.class) @RequestBody ProductDTO dto) {
        return productService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeProductStatus(@Validated(ProductDTO.CambiarEstado.class) @RequestBody ProductDTO dto) {
        return productService.changeStatus(dto);
    }
}

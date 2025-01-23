package luismis.mx.ris.Product.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    public interface Registrar {}
    public interface Modificar {}
    public interface CambiarEstado {}

    @NotNull(groups = {Modificar.class, CambiarEstado.class}, message = "El ID del producto no puede ser nulo")
    private Long productId;

    @NotBlank(groups = {Registrar.class, Modificar.class}, message = "El nombre del producto no puede estar vacío")
    @Size(max = 100, groups = {Registrar.class, Modificar.class}, message = "El nombre del producto no puede exceder 100 caracteres")
    private String name;

    @Size(max = 500, groups = {Registrar.class, Modificar.class}, message = "La descripción del producto no puede exceder 500 caracteres")
    private String description;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El precio no puede ser nulo")
    private Long price;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El stock no puede ser nulo")
    private int stock;

    @NotBlank(groups = {Registrar.class, Modificar.class}, message = "El estado no puede estar vacío")
    @Size(max = 20, groups = {Registrar.class, Modificar.class}, message = "El estado no puede exceder 20 caracteres")
    private String status;

    public Integer getProductId() {
        return Math.toIntExact(productId);
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

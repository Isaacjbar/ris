package luismis.mx.ris.Pay_Details.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pay_DetailsDTO {

    public interface Registrar {}
    public interface Modificar {}
    public interface CambiarEstado {}

    @NotNull(groups = {Modificar.class, CambiarEstado.class}, message = "El ID del detalle de pago no puede ser nulo")
    private Long payDetailsId;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El ID del pago no puede ser nulo")
    private Long payId;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El ID del producto no puede ser nulo")
    private Long productId;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "La cantidad no puede ser nula")
    private int quantity;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El subtotal no puede ser nulo")
    private Long subtotal;

    @NotBlank(groups = {Registrar.class, Modificar.class}, message = "El estado no puede estar vacío")
    @Size(max = 20, groups = {Registrar.class, Modificar.class}, message = "El estado no puede exceder 20 caracteres")
    private String status;

    public Long getPayDetailsId() {
        return payDetailsId;
    }

    public void setPayDetailsId(Long payDetailsId) {
        this.payDetailsId = payDetailsId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Long subtotal) {
        this.subtotal = subtotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

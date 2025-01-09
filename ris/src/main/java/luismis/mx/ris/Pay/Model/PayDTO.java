package luismis.mx.ris.Pay.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PayDTO {

    public interface Registrar {}
    public interface Modificar {}
    public interface CambiarEstado {}

    @NotNull(groups = {Modificar.class, CambiarEstado.class}, message = "El ID del pago no puede ser nulo")
    private Long payId;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El ID del usuario no puede ser nulo")
    private Long userId;

    @NotNull(groups = {Registrar.class, Modificar.class}, message = "El monto total no puede ser nulo")
    private Long totalAmount;

    @NotBlank(groups = {Registrar.class, Modificar.class}, message = "El estado no puede estar vacío")
    @Size(max = 20, groups = {Registrar.class, Modificar.class}, message = "El estado no puede exceder 20 caracteres")
    private String status;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

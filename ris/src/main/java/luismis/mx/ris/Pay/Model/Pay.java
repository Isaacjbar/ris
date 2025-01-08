package luismis.mx.ris.Pay.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Pay {
    private Long payId;              // ID del pago
    private Long userId;             // ID del usuario (relación con User)
    private BigDecimal totalAmount;  // Monto total del pago
    private LocalDateTime paymentDate; // Fecha del pago
    private String status;           // Estado: pending, completed, failed, deleted
    private LocalDateTime createdAt; // Fecha de creación
    private LocalDateTime updatedAt; // Fecha de última actualización
    private LocalDateTime deletedAt; // Fecha de eliminación (opcional)
}

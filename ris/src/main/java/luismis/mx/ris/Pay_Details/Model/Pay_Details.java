package luismis.mx.ris.Pay_Details.Model;


import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Pay_Details {
    private Long payDetailsId;       // ID del detalle de pago
    private Long payId;              // ID del pago (relación con Pay)
    private Long productId;          // ID del producto (relación con Product)
    private int quantity;            // Cantidad del producto
    private BigDecimal subtotal;     // Subtotal para este producto
    private String status;           // Estado: active, inactive, deleted
    private LocalDateTime createdAt; // Fecha de creación
    private LocalDateTime updatedAt; // Fecha de última actualización
    private LocalDateTime deletedAt; // Fecha de eliminación (opcional)
}

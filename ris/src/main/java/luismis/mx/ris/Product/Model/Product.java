package luismis.mx.ris.Product.Model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long productId;          // ID del producto
    private String name;             // Nombre del producto
    private String description;      // Descripción del producto
    private BigDecimal price;        // Precio del producto
    private int stock;               // Cantidad disponible
    private String status;           // Estado: active, inactive, deleted
    private LocalDateTime createdAt; // Fecha de creación
    private LocalDateTime updatedAt; // Fecha de última actualización
    private LocalDateTime deletedAt; // Fecha de eliminación (opcional)
}

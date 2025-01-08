package luismis.mx.ris.User.Model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long userId;             // ID del usuario
    private String username;         // Nombre de usuario
    private String email;            // Correo electrónico
    private String passwordHash;     // Contraseña en formato hash
    private String status;           // Estado: active, inactive, deleted
    private LocalDateTime createdAt; // Fecha de creación
    private LocalDateTime updatedAt; // Fecha de última actualización
    private LocalDateTime deletedAt; // Fecha de eliminación (opcional)
}


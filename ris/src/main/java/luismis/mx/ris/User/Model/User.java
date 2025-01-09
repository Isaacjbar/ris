package luismis.mx.ris.User.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automáticamente el ID en la base de datos
    @Column(name = "user_id") // Mapeo de la columna en la base de datos
    private Long userId;

    @Column(name = "username", nullable = false, length = 50) // Configura la columna y sus restricciones
    private String username;

    @Column(name = "email", nullable = false, unique = true) // Define restricciones adicionales
    private String email;

    @Column(name = "password_hash", nullable = false) // Nombre de la columna en la base de datos
    private String passwordHash;

    @Column(name = "status", nullable = false) // Especifica la columna para el estado del usuario
    private String status;

    @Column(name = "created_at", updatable = false) // Marca la fecha de creación
    private LocalDateTime createdAt;

    @Column(name = "updated_at") // Fecha de última actualización
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at") // Fecha de eliminación lógica (si aplica)
    private LocalDateTime deletedAt;

    @PrePersist // Antes de guardar, establece valores por defecto
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = (this.status == null) ? "active" : this.status;
    }

    @PreUpdate // Antes de actualizar, actualiza el valor de `updatedAt`
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User(Long userId, String username, String email, String passwordHash, String status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public User(String username, String email, String passwordHash, String status, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public User() {
    }
}

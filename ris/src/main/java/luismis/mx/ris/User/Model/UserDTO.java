package luismis.mx.ris.User.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    public interface Registrar {}
    public interface Modificar {}
    public interface CambiarEstado {}

    @NotNull(groups = {Modificar.class, CambiarEstado.class}, message = "El ID del usuario no puede ser nulo")
    private Long userId;

    @NotBlank(groups = {Registrar.class, Modificar.class}, message = "El nombre de usuario no puede estar vacío")
    @Size(max = 50, groups = {Registrar.class, Modificar.class}, message = "El nombre de usuario no puede exceder 50 caracteres")
    private String username;

    @NotBlank(groups = {Registrar.class, Modificar.class}, message = "El correo electrónico no puede estar vacío")
    @Email(groups = {Registrar.class, Modificar.class}, message = "Debe proporcionar un correo electrónico válido")
    private String email;

    @NotBlank(groups = {Registrar.class}, message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 100, groups = {Registrar.class}, message = "La contraseña debe tener entre 8 y 100 caracteres")
    private String passwordHash;

    @NotNull(groups = {CambiarEstado.class}, message = "El estado no puede ser nulo")
    @Size(max = 20, groups = {Modificar.class}, message = "El estado no puede exceder 20 caracteres")
    private String status;

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

    public UserDTO(Long userId, String username, String email, String passwordHash, String status) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
    }

    public UserDTO(String username, String email, String passwordHash, String status) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
    }

    public UserDTO() {
    }
}

package luismis.mx.ris.User.Model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


/**
 * DTO para la transferencia de datos de usuarios.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull(groups = {Modificar.class, CambiarEstado.class}, message = "El estado no puede ser nulo")
    @Size(max = 20, groups = {Modificar.class}, message = "El estado no puede exceder 20 caracteres")
    private String status;
}

package luismis.mx.ris.User.Controller;

import luismis.mx.ris.User.Model.UserDTO;
import luismis.mx.ris.User.Controller.UserService;
import luismis.mx.ris.Util.Response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserController (UserService userService){
        this.userService = userService;
    };

    @GetMapping("/all")
    public ResponseEntity<Message> getAllUsers() {
        return userService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveUser(@Validated(UserDTO.Registrar.class) @RequestBody UserDTO dto) {
        return userService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateUser(@Validated(UserDTO.Modificar.class) @RequestBody UserDTO dto) {
        return userService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeUserStatus(@Validated(UserDTO.CambiarEstado.class) @RequestBody UserDTO dto) {
        return userService.changeStatus(dto);
    }
}
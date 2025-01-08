package luismis.mx.ris.User.Controller;

import luismis.mx.ris.User.Model.User;
import luismis.mx.ris.User.Model.UserDTO;
import luismis.mx.ris.User.Model.UserRepository;
import luismis.mx.ris.Util.Response.Message;
import luismis.mx.ris.Util.Enum.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<User> users = userRepository.findAll();
        logger.info("La búsqueda de usuarios ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(users, "Listado de usuarios", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(UserDTO dto) {
        if (dto.getUsername().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre de usuario excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPasswordHash());
        user.setStatus("active");
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        logger.info("El registro del usuario ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(user, "El usuario se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(UserDTO dto) {
        return userRepository.findById(dto.getUserId())
                .map(user -> {
                    if (dto.getUsername() != null) {
                        if (dto.getUsername().length() > 50) {
                            return new ResponseEntity<>(new Message("El nombre de usuario excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
                        }
                        user.setUsername(dto.getUsername());
                    }
                    if (dto.getEmail() != null) {
                        user.setEmail(dto.getEmail());
                    }
                    if (dto.getPasswordHash() != null) {
                        user.setPasswordHash(dto.getPasswordHash());
                    }
                    user.setUpdatedAt(LocalDateTime.now());
                    userRepository.save(user);
                    logger.info("La actualización del usuario ha sido realizada correctamente");
                    return new ResponseEntity<>(new Message(user, "El usuario se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND));
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(UserDTO dto) {
        return userRepository.findById(dto.getUserId())
                .map(user -> {
                    user.setStatus(user.getStatus().equals("active") ? "inactive" : "active");
                    user.setUpdatedAt(LocalDateTime.now());
                    userRepository.save(user);
                    logger.info("La actualización del estado del usuario ha sido realizada correctamente");
                    return new ResponseEntity<>(new Message(user, "El estado del usuario se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new Message("El usuario no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND));
    }
}

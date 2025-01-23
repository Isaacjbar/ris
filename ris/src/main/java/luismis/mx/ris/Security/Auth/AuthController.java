package luismis.mx.ris.Security.Auth;


import luismis.mx.ris.Security.Auth.Dto.AuthRequest;
import luismis.mx.ris.Security.Auth.Dto.AuthResponse;
import luismis.mx.ris.Security.Jwt.JwtUtil;
import luismis.mx.ris.Security.UserDetailsServiceImpl;
import luismis.mx.ris.User.Model.User;
import luismis.mx.ris.User.Model.UserRepository;
import luismis.mx.ris.Util.Enum.TypesResponse;
import luismis.mx.ris.Util.Response.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UserRepository userRepository, AuthService authService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        if (userOptional.isEmpty()) {
            return new AuthResponse("", 0L, "Usuario no encontrado", 0);
        }

        User user = userOptional.get();
        if (Objects.equals(user.getStatus(), "inactive")) {
            return new AuthResponse("", 0L, "El usuario está inactivo", 0);
        }

        if (!Objects.equals(authRequest.getPassword(), user.getPasswordHash())) {
            return new AuthResponse("", 0L, "Contraseña incorrecta", 0);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails, user.getUserId());

        if (authService.isTokenInvalid(jwt)) {
            return new AuthResponse("", 0L, "Sesión expirada", 0);
        }

        long expirationTime = jwtUtil.getExpirationTime();

        return new AuthResponse(jwt, user.getUserId(), user.getEmail(), expirationTime);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Message> logout(@RequestHeader(value = "Authorization", required = false) String token) throws Exception {
        if (token == null || token.trim().isEmpty()) {
            return new ResponseEntity<>(new Message("Hubo un error", TypesResponse.ERROR), HttpStatus.UNAUTHORIZED);
        }
        if (!token.startsWith("Bearer ")) {
            return new ResponseEntity<>(new Message("Hubo un error", TypesResponse.ERROR), HttpStatus.UNAUTHORIZED);
        }
        if (authService.isTokenInvalid(token)) {
            throw new Exception("Token inválido");
        }

        String jwt = token.substring(7);
        authService.invalidateToken(jwt);

        return new ResponseEntity<>(new Message("Logout exitoso", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
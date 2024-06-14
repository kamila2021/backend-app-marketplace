package com.noseque.services;

import com.noseque.dto.RegistrationRequest;
import com.noseque.entities.Rol;
import com.noseque.entities.Token;
import com.noseque.entities.Usuario;
import com.noseque.repositories.RoleRepository;
import com.noseque.repositories.TokenRepository;
import com.noseque.repositories.UsuarioRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthenticationService {



    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;
    private final TokenRepository tokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository userRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;


    public void register(RegistrationRequest request) throws MessagingException {
        log.info("Paso por aca -> AuthenticationService");
        Rol userRole = new Rol();

        try {
            userRole = roleRepository.findRoleByName("USUARIO");
            Usuario user = new Usuario();
            user.setUsername(request.getUsername());
            user.setFirstname(request.getFirstname());
            user.setLastname(request.getLastname());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setTelephone(request.getTelephone());
            user.setAccountLocked(false);
            user.setEnabled(true);
            user.setRoles(new HashSet<>(List.of(userRole)));
            usuarioRepository.save(user);
        }catch(Exception e){
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }
    }



    public void sendValidationEmail(Usuario user) throws MessagingException {
        try {
            var newToken = generateAndSaveToken(user);
            emailService.sendEmail(
                    user.getUsername(),
                    user.fullname(),
                    newToken,
                    "Account activation"
            );
        } catch(Exception e) {
            throw new IllegalArgumentException("Error: " + e.getMessage());
        }

    }
    private String generateAndSaveToken(Usuario user) {

        String generatedToken= generateActivationCode(6);

        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        user.setResetPasswordToken(generatedToken);
        userRepository.save(user);
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String characters="0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++ ){
            int randomIndex = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

//    public boolean validateToken(String token) throws TokenExpiredException {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(this.secret.getBytes());
//            JWTVerifier verifier = JWT.require(algorithm).build();
//            verifier.verify(token);
//            return true;
//        } catch (Exception ex) {
//            log.error("Invalid JWT signature");
//            return false;
//        } catch (TokenExpiredException tex) {
//            log.error("Expired token");
//            return false;
//        }
//    }

}
package com.noseque.resolvers;

import com.noseque.services.AuthenticationService;
import com.noseque.dto.RegistrationRequest;
import com.noseque.services.UserDetailsServiceImpl;
import com.noseque.entities.Usuario;
import jakarta.mail.MessagingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private  UserDetailsServiceImpl userService;

    @QueryMapping
    public ResponseEntity<?> register(
            @RequestBody RegistrationRequest request) throws MessagingException {
        log.info("Pasa por aqui -> AuthenticationController");
        this.authService.register(request);
        return ResponseEntity.accepted().build();
    }
    @MutationMapping
    public String addNewUser(@RequestBody Usuario usuario
    ) {
        return userService.addUser(usuario);
    }
    @QueryMapping
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public String adminProfile() {
        return "Welcome to Admin Profile";
    }
}

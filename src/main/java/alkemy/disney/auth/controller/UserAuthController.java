package alkemy.disney.auth.controller;

import alkemy.disney.auth.dto.AuthenticationRequest;
import alkemy.disney.auth.dto.AuthenticationResponse;
import alkemy.disney.auth.dto.UserDTO;
import alkemy.disney.auth.service.UserDetailsCustomService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
@Validated
public class UserAuthController {

    private UserDetailsCustomService userDetailsCustomService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthController(UserDetailsCustomService userDetailsCustomService,
                              PasswordEncoder passwordEncoder) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody UserDTO userDTO) {
        userDetailsCustomService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @NotNull
                                                        AuthenticationRequest authRequest) {
        return ResponseEntity.ok(userDetailsCustomService.login(authRequest));
    }

}

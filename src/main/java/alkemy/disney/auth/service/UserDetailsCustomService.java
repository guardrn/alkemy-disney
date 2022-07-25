package alkemy.disney.auth.service;

import alkemy.disney.auth.dto.AuthenticationRequest;
import alkemy.disney.auth.dto.AuthenticationResponse;
import alkemy.disney.auth.dto.UserDTO;
import alkemy.disney.auth.entity.UserEntity;
import alkemy.disney.auth.repository.UserRepository;
import alkemy.disney.auth.util.JwtUtils;
import alkemy.disney.service.EmailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    private EmailService emailService;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @Autowired
    public UserDetailsCustomService(EmailService emailService,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    AuthenticationManager authenticationManager,
                                    JwtUtils jwtUtils) {
        this.emailService = emailService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public boolean register(@NotNull UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        String password = passwordEncoder.encode(userDTO.getPassword());
        userEntity.setPassword(password);
        userRepository.save(userEntity);
        if (userEntity != null) {
            emailService.sendWelcomeTo(userDTO.getUsername());
        }
        return userEntity != null;
    }

    public AuthenticationResponse login(@NotNull AuthenticationRequest authRequest)
            throws UsernameNotFoundException {
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Incorrect Username or Password", ex);
        }
        final String jwt = jwtUtils.generateToken(userDetails);
        return new AuthenticationResponse(jwt);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or Password not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

}

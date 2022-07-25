package alkemy.disney.auth.filter;

import alkemy.disney.auth.service.UserDetailsCustomService;
import alkemy.disney.auth.util.JwtUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private UserDetailsCustomService userDetailsCustomService;

    private JwtUtils jwtUtils;

    @Autowired
    public JwtRequestFilter(@Lazy UserDetailsCustomService userDetailsCustomService, JwtUtils jwtUtils) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String userName = null;
        String jwt = null;
        if ((authorizationHeader != null) && (authorizationHeader.startsWith("Bearer "))) {
            jwt = authorizationHeader.substring(7);
            userName = jwtUtils.extractUserName(jwt);
        }
        if ((userName != null) && (SecurityContextHolder.getContext().getAuthentication() == null)) {
            UserDetails userDetails = userDetailsCustomService.loadUserByUsername(userName);
            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null,
                                userDetails.getAuthorities());
                authRequest.setDetails(authRequest);
                SecurityContextHolder.getContext().setAuthentication(authRequest);
            }
        }
        filterChain.doFilter(request, response);
    }

}

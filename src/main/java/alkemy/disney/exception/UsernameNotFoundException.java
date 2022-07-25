package alkemy.disney.exception;

import javax.security.sasl.AuthenticationException;

public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException(String message) {
        super(message);
    }

}

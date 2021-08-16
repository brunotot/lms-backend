package tvz.btot.zavrsni.infrastructure.errorhandling;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class ApiException extends ProblemException {
	@Serial
    private static final long serialVersionUID = 1L;

	public ApiException(HttpStatus status, String title, String details, Throwable cause) {
        super(new Problem(status.value(), title, details), cause);
    }

    public ApiException(HttpStatus status, String title, String details) {
        super(new Problem(status.value(), title, details), null);
    }
}
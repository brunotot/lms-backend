package tvz.btot.zavrsni.infrastructure.errorhandling;

import java.io.Serial;
import java.util.Objects;

public abstract class ProblemException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;

	private final Problem problem;

	protected ProblemException(Problem problem, Throwable cause) {
        super(cause);
        Objects.requireNonNull(problem, "Problem must not be null");
		this.problem = problem;
		if (cause != null) {
			this.problem.setStacktraceLines(cause.getStackTrace());
		}
    }
	
	public Problem getProblem() {
		return this.problem;
	}
}
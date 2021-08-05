package tvz.btot.zavrsni.infrastructure.errorhandling;

import java.io.Serial;
import java.util.Objects;

public abstract class ProblemException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 1L;
	private static final String PROBLEM_REQUIRE_NON_NULL_MESSAGE = "Problem must not be null";
	
	private final Problem problem;

	protected ProblemException(Problem problem, Throwable cause) {
        super(cause);
        Objects.requireNonNull(problem, PROBLEM_REQUIRE_NON_NULL_MESSAGE);
		this.problem = problem;
		if (cause != null) {
			this.problem.setStacktraceLines(cause.getStackTrace());
		}
    }
	
	public Problem getProblem() {
		return this.problem;
	}

}
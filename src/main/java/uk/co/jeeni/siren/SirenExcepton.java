package uk.co.jeeni.siren;

public class SirenExcepton extends RuntimeException {

	private static final long serialVersionUID = -4497545047388500666L;

	public SirenExcepton(String message, Throwable cause) {
		super(message, cause);
	}

	public SirenExcepton(String message) {
		super(message);
	}
}

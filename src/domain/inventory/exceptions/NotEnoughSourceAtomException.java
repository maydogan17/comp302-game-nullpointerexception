package domain.inventory.exceptions;

@SuppressWarnings("serial")
public class NotEnoughSourceAtomException extends Exception {
	String errorName;

	public NotEnoughSourceAtomException(String message) {
		this.errorName = message;
		System.out.println(message);
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}
}

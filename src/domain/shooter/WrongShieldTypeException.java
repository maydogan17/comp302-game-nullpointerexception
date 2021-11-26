package domain.shooter;

@SuppressWarnings("serial")
public class WrongShieldTypeException extends Exception {

	String errorM;
	
	public WrongShieldTypeException(String m) {
		setErrorM(m);
		System.out.println(m);
	}
	
	public String getErrorM() {
		return errorM;
	}

	public void setErrorM(String errorM) {
		this.errorM = errorM;
	}
}

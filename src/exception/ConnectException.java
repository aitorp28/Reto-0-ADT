package exception;

/**
 * Custom controller for Connect type SQL Exceptions
 * @author Enaitz Izagirre
 */
public class ConnectException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Generation of the customized Exception
     * @param message
     */
    public ConnectException(String message) {
		super(message);
	}
	
}

package exception;

/**
 * Custom controller for Read type SQL Exceptions
 * @author Enaitz Izagirre
 */
public class ReadException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Generation of the customized Exception
     * @param message
     */
    public ReadException(String message) {
		super(message);
	}

	
	
}

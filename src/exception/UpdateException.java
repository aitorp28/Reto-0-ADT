package exception;

/**
 * Custom controller for Update type SQL Exceptions
 * @author Enaitz Izagirre
 */
public class UpdateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Generation of the customized Exception
     * @param message
     */
    public UpdateException(String message) {
		super(message);
	}

	
	
}

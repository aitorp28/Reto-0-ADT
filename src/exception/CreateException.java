package exception;

/**
 * Custom controller for Create type SQL Exceptions
 * @author Enaitz Izagirre
 */
public class CreateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Generation of the customized Exception
     * @param message
     */
    public CreateException(String message) {
		super(message);
	}

	
	
}

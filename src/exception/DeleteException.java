package exception;

/**
 * Custom controller for Delete type SQL Exceptions
 * @author Enaitz Izagirre
 */
public class DeleteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * Generation of the customized Exception
     * @param message
     */
    public DeleteException(String message) {
		super(message);
	}

	
	
}

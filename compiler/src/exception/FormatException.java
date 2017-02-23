package exception;
// FormatException.java

import org.omg.CORBA.UserException;

/**
 * Team 3
 * @author Héctor J. Vásquez
 * @author René Egremy
 * @author Luis Cruz
 * @version 1.0
 */

public class FormatException extends UserException {
	public static final long serialVersionUID = 1551515L;


	/**
	 * Principal constructor.
	 */
	public FormatException() {
		super("Unknown");
	}

	/**
	 * The reason which this Exception was thrown.
	 * @param reason
	 */
	public FormatException(String reason) {
		super(reason);
	}

	@Override
	public void printStackTrace() {// From Exception
		super.printStackTrace();
		System.out.println(this);
	}

	@Override
	public String toString() {// From Object
		return super.toString().replace("exception.FormatException", "Razón");
	}
}

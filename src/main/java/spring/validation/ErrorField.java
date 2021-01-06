package spring.validation;

public class ErrorField {
	
	String field;
	String message;
	
	/**
	 * @param field
	 * @param message
	 */
	public ErrorField(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}

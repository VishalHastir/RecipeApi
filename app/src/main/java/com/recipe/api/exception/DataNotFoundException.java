package com.recipe.api.exception;

import org.springframework.http.HttpStatus;

/**
 * The type Data not found exception.
 *
 * @author Vishal
 */
public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus status = HttpStatus.NOT_FOUND;

	/**
	 * Instantiates a new Data not found exception.
	 */
	public DataNotFoundException() {
		super();
	}

	/**
	 * Instantiates a new Data not found exception.
	 *
	 * @param message the message
	 */
	public DataNotFoundException(String message) {
		super(message);
		this.status = status;
	}

	/**
	 * Instantiates a new Data not found exception.
	 *
	 * @param message the message
	 * @param cause   the cause
	 */
	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);
		this.status = status;
	}

	/**
	 * Gets status.
	 *
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}
}

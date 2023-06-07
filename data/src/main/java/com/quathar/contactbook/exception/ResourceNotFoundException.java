package com.quathar.contactbook.exception;

/**
 * <h1>ResourceNotFoundException</h1>
 *
 * @since 2023-03-30
 * @version 1.0
 * @author Q
 */
public class ResourceNotFoundException extends RuntimeException {

    // <<-CONSTRUCTORS->>
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}

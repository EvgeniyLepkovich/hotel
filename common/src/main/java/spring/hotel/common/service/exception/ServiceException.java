package spring.hotel.common.service.exception;

/**
 * Created by Yayheniy_Lepkovich on 10/31/2016.
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

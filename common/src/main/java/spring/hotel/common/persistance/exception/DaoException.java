package spring.hotel.common.persistance.exception;

/**
 * Created by Yayheniy_Lepkovich on 10/31/2016.
 */
public class DaoException extends Exception {
    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}

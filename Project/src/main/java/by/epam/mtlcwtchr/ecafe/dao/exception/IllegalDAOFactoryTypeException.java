package by.epam.mtlcwtchr.ecafe.dao.exception;

public class IllegalDAOFactoryTypeException extends IllegalStateException {
    public IllegalDAOFactoryTypeException() {
        super();
    }
    public IllegalDAOFactoryTypeException(String s) {
        super(s);
    }
    public IllegalDAOFactoryTypeException(String message, Throwable cause) {
        super(message, cause);
    }
    public IllegalDAOFactoryTypeException(Throwable cause) {
        super(cause);
    }
}

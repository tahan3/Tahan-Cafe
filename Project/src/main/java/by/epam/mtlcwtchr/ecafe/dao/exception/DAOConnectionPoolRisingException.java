package by.epam.mtlcwtchr.ecafe.dao.exception;

public class DAOConnectionPoolRisingException extends DAOException {
    public DAOConnectionPoolRisingException() {
        super();
    }
    public DAOConnectionPoolRisingException(String message) {
        super(message);
    }
    public DAOConnectionPoolRisingException(String message, Throwable cause) {
        super(message, cause);
    }
    public DAOConnectionPoolRisingException(Throwable cause) {
        super(cause);
    }
}

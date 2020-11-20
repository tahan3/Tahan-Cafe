package by.epam.mtlcwtchr.ecafe.dao.exception;

public class DAOPreparedStatementBuilderException extends DAOException {
    public DAOPreparedStatementBuilderException() {
        super();
    }
    public DAOPreparedStatementBuilderException(String message) {
        super(message);
    }
    public DAOPreparedStatementBuilderException(String message, Throwable cause) {
        super(message, cause);
    }
    public DAOPreparedStatementBuilderException(Throwable cause) {
        super(cause);
    }
}

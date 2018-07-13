package beans.transaction.exceptions;

public class LimitExceedException extends RuntimeException {
    public LimitExceedException(String message){
        super(message);
    }
}

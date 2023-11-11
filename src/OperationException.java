// Represents an exception thrown by a 'SafeOperation' object.
//
public class OperationException extends Exception {

    // TODO: refine class definition (if needed).
    private String errorMessage;

    public OperationException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.errorMessage;
    }

    @Override
    public String toString() {
        return this.errorMessage;
    }
}

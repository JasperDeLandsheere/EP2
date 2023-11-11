// Represents a checked exception thrown by a 'SafeFactory' object.
//
public class FactoryException extends Exception {

    // TODO: refine class definition (if needed).
    private String errorMessage;

    public FactoryException(String errorMessage){
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

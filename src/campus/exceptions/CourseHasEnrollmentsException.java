package campus.exceptions;

public class CourseHasEnrollmentsException extends RuntimeException {
    public CourseHasEnrollmentsException(String message) {
        super(message);
    }
}
package kr.megaptera.assignment.exceptions;

public class PostNotFound extends RuntimeException{

    public PostNotFound() {
        super();
    }

    public PostNotFound(String message) {
        super(message);
    }

    public PostNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

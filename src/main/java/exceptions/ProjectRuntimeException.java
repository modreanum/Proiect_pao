package exceptions;

public abstract class ProjectRuntimeException extends RuntimeException {
    public ProjectRuntimeException(String s) {
        super(s);
    }
}

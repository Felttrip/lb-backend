package lb_backend;

public class NoteNotFoundException extends Exception {
    public NoteNotFoundException(String message) {
        super(message);
    }
}

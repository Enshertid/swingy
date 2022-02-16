package swingy.utils.exceptions;

public class GameWonException extends Exception{
    public  GameWonException() {
        super();
    }

    public GameWonException(String message) {
        super(message);
    }
}

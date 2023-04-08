package ip_app;

public class myException extends Exception {

    public myException(String message) {
        super("\u001B[31m" + message + "\u001B[0m");
    }

    public static class Input_Variable_Exception extends myException{
        public Input_Variable_Exception()
        {
            super("Błąd zmiennej wejsciowej");
        }
    }

}
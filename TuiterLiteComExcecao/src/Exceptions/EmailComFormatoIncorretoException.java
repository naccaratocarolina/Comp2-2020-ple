package Exceptions;

public class EmailComFormatoIncorretoException extends Exception {
    public String getMessage() {
        return "O email informado não possui o formato válido.\n" +
                "Insira um email da forma: fulano@fulano.com\n";
    }
}

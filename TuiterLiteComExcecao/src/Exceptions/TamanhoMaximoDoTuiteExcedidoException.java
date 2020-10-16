package Exceptions;

public class TamanhoMaximoDoTuiteExcedidoException extends Exception {
    public String getMessage() {
        return "Tamanho máximo permitido do tuite foi excedito!!\n";
    }
}

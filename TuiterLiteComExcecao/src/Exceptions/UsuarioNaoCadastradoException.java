package Exceptions;

public class UsuarioNaoCadastradoException extends Exception {
    public String getMessage() {
        return "Esse usuário nao esta registrado na plataforma!!!\n";
    }
}

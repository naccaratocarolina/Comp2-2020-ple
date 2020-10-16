import Exceptions.*;

public class Principal {
    public static void main(String[] args)
            throws UsuarioNaoCadastradoException, TamanhoMaximoDoTuiteExcedidoException, EmailComFormatoIncorretoException {
        TuiterLite tuiterLite = new TuiterLite();
        try {
            Usuario usuario = tuiterLite.cadastrarUsuario("Fulaninho", "fulaninho");
        } catch (EmailComFormatoIncorretoException e) {
            System.out.println(e.getMessage());
        }

        Usuario fulano = tuiterLite.cadastrarUsuario("Fulaninho", "fulaninho@fulaninho.com");
        try {
            tuiterLite.tuitarAlgo(fulano, "Texto do tuite");
        } catch (UsuarioNaoCadastradoException | TamanhoMaximoDoTuiteExcedidoException e) {
            System.out.println(e.getMessage());
        }
    }
}

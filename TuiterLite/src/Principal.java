import javax.print.DocFlavor;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Principal {
    public static void main(String[] args) {
        TuiterLite tuiterLite = new TuiterLite();
        Usuario usuario = new Usuario("Fulano", "fulano@fulano.com");
        tuiterLite.cadastrarUsuario("Fulano", "fulano@fulano.com");

        tuiterLite.getHashtagsDoTuiter();
        //tuiterLite.frequenciaDeHashtags();
        tuiterLite.tuitarAlgo(usuario, "#blabla asdasdas #outra!");
        tuiterLite.tuitarAlgo(usuario, "#blabla");
        tuiterLite.tuitarAlgo(usuario, "#outra #outra #outra");
        System.out.println(tuiterLite.getHashtagMaisComum());
    }
}

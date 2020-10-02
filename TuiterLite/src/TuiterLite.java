import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite<T> {

    public static final int TAMANHO_MAXIMO_TUITES = 120;

    private ArrayList<String> hashtagsDoTuiter;
    private Map<String, Integer> frequenciaDeHashtag;
    private ArrayList<Usuario> usuariosCadastrados;

    public TuiterLite() {
        this.hashtagsDoTuiter = new ArrayList<String>();
        this.frequenciaDeHashtag = new HashMap<String, Integer>();
        this.usuariosCadastrados = new ArrayList<Usuario>();
    }

    public ArrayList<String> getHashtagsDoTuiter() {
        return hashtagsDoTuiter;
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        Usuario novoUsuario = new Usuario(nome, email);
        this.usuariosCadastrados.add(novoUsuario);
        return novoUsuario;
    }

    /**
     * Tuíta algo, retornando o objeto Tuíte criado.
     * Se o tamanho do texto exceder o limite pré-definido, não faz nada e retorna null.
     * Se o usuário não estiver cadastrado, não faz nada e retorna null.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if(this.verificaSeUsuarioEhCadastrado(usuario)) {
            Tuite novoTuite = new Tuite(usuario, texto);
            novoTuite.setHashtags(this.registraHashtags(novoTuite));
            return novoTuite;
        }
        return null;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        Map.Entry<String, Integer> hashtagMaisComum = this.frequenciaDeHashtags().entrySet().iterator().next();
        for(Map.Entry<String, Integer> hashtag : this.frequenciaDeHashtag.entrySet()) {
            if(hashtag.getValue() > hashtagMaisComum.getValue()) hashtagMaisComum = hashtag;
        }
        return hashtagMaisComum.getKey();
    }

    private boolean verificaSeUsuarioEhCadastrado(Usuario usuario) {
        for (Usuario usuarioCadastrado : this.usuariosCadastrados) {
            if(usuarioCadastrado.equals(usuario)) return true;
        }
        return false;
    }

    private Map<String, Integer> frequenciaDeHashtags() {
        for(String hashtag : this.hashtagsDoTuiter) {
            Integer contador = frequenciaDeHashtag.get(hashtag);
            if(contador == null) contador = 0;
            contador++;
            frequenciaDeHashtag.put(hashtag, contador);
        }
        return frequenciaDeHashtag;
    }

    private ArrayList<String> registraHashtags(Tuite tuite) {
        ArrayList<String> hashtag = new ArrayList<String>();
        String textoDoTuite = tuite.getTexto();
        Pattern padrao = Pattern.compile("(#\\w+)\\b");
        Matcher combinador = padrao.matcher(textoDoTuite);
        while (combinador.find()) {
            hashtag.add(combinador.group(1));
            this.hashtagsDoTuiter.add(combinador.group(1));
        }
        return hashtag;
    }
}

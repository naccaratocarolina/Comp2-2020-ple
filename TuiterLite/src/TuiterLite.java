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

    //Array que ira armazenar todas as hashtags ja utilizadas na plataforma
    private final ArrayList<String> hashtagsDoTuiter;

    //Map que ira armazenar as hashtags utilizadas (key) e a frequencia em que a mesma foi utilizada (value)
    //Portanto, eh um HashMap com hashtag=frequencia
    private final Map<String, Integer> frequenciaDeHashtag;

    //Array que armazena todos os usuarios cadastrados na plataforma
    private final ArrayList<Usuario> usuariosCadastrados;

    /**
     * Construtor de TuiterLite.
     */
    public TuiterLite() {
        //Inicializa o array de hashtags do Tuiter
        this.hashtagsDoTuiter = new ArrayList<String>();

        //Inicializa o HashMap com a frequencia de cada hashtag
        this.frequenciaDeHashtag = new HashMap<String, Integer>();

        //Inicializa o array de usuarios cadastrados
        this.usuariosCadastrados = new ArrayList<Usuario>();
    }

    /**
     * Getter de hashtagsDoTuiter.
     *
     * @return array de todas as hashtags utilizadas no Tuiter
     */
    public ArrayList<String> getHashtagsDoTuiter() {
        return hashtagsDoTuiter;
    }

    /**
     * Getter de getFrequenciaDeHashtag.
     *
     * @return HashMap com a frequencia em que cada hashtag foi utilizada na plataforma
     */
    public Map<String, Integer> getFrequenciaDeHashtag() {
        return frequenciaDeHashtag;
    }

    /**
     * Getter de usuariosCadastrados.
     *
     * @return todos os usuarios cadastrados na plataforma
     */
    public ArrayList<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
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
        if(this.verificaSeUsuarioEhCadastrado(usuario) && texto.length() <= TAMANHO_MAXIMO_TUITES) {
            Tuite novoTuite = new Tuite(usuario, texto);
            novoTuite.setHashtags(this.registraHashtags(novoTuite));
            usuario.incrementaNumeroDeTuitesPostados();
            usuario.alteraTipoDeUsuario();
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

    /**
     * Metodo que verifica se um usuario eh registrado na plataforma
     * checando se o array dado esta contido no array de usuarios cadastrados.
     *
     * @param usuario
     * @return
     */
    private boolean verificaSeUsuarioEhCadastrado(Usuario usuario) {
        return this.usuariosCadastrados.contains(usuario);
    }

    /**
     * Funcao auxiliar que verifica a frequencia de cada hashtag utilizada na plataforma.
     * A funcao realiza um for loop que percorre o ArrayList que contem
     * todas as hashtags utilizadas no site (incluindo repeticoes).
     * Esse metodo basicamente conta quantas vezes determinada hashtag aparece, incrementa esse contador
     * e constroi um HashMap com hashtag (value) = frequencia (key).
     *
     * @return map de frequencia de hashtags
     */
    private Map<String, Integer> frequenciaDeHashtags() {
        for(String hashtag : this.hashtagsDoTuiter) {
            Integer contador = frequenciaDeHashtag.get(hashtag);
            if(contador == null) contador = 0;
            contador++;
            frequenciaDeHashtag.put(hashtag, contador);
        }
        return frequenciaDeHashtag;
    }

    /**
     * Funcao auxiliar que "registra" as hashtags.
     * A funcao percorre o texto do tuite dado procurando pelas hashtags (toda string seguida de #).
     * Utiliza regex para identificar as hashtags e as armazena. Alem disso, tambem adiciona as hashtags
     * encontradas no array de hashtagsDoTuiter para que seja possivel realizar a verificacao da hashtag mais comum.
     *
     * @param tuite determinado tuite
     * @return um ArrayList com todas as hashtags utilizadas no texto do tuite dadosetHashtags
     */
    private ArrayList<String> registraHashtags(Tuite tuite) {
        ArrayList<String> hashtag = new ArrayList<String>();
        String textoDoTuite = tuite.getTexto();
        Pattern padrao = Pattern.compile("(#\\w+)\\b");
        Matcher comparador = padrao.matcher(textoDoTuite);
        while (comparador.find()) {
            hashtag.add(comparador.group(1));
            this.hashtagsDoTuiter.add(comparador.group(1));
        }
        return hashtag;
    }
}

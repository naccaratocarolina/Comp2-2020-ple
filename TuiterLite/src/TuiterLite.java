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
    public final Map<String, Integer> frequenciaDeHashtag;

    //Map que armazena os usuarios cadastrados na plataforma e seus respectivos emails
    private final Map<Usuario, String> usuariosCadastrados;

    /**
     * Construtor de TuiterLite.
     */
    public TuiterLite() {
        //Inicializa o array de hashtags do Tuiter
        this.hashtagsDoTuiter = new ArrayList<String>();

        //Inicializa o HashMap com a frequencia de cada hashtag
        this.frequenciaDeHashtag = new HashMap<String, Integer>();

        this.usuariosCadastrados = new HashMap<Usuario, String>();
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
    public Map<Usuario, String> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o email informado já estiver em uso, não faz nada e retorna null.
     *
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        if(this.usuariosCadastrados.containsValue(email)) return null;
        Usuario novoUsuario = new Usuario(nome, email);
        this.usuariosCadastrados.put(novoUsuario, email);
        return novoUsuario;
    }

    /**
     * Essa funcao recebe um usuario e faz o mesmo tuitar o texto recebido.
     * Para que essa funcao possa ser chamada, o usuario deve ser cadastrado na plataforma e escrever um texto com um
     * numero maximo de caracteres. Se essas condicoes nao forem atendidas, o tuite nao eh criado e retorna null.
     * Caso contrario, um novo tuite eh criado, tendo como referencia o usuario e texto informados. Alem disso, a funcao
     * "registra" as possiveis hashtags utilizadas no texto dado no atributo hashtags do novo tuite. A funcao tambem
     * incrementa o numero de tuites postados por esse usuario e sempre chama a funcao que altera o tipo de usuario, pois
     * a classificacao de usuarios na plataforma eh em funcao da quantidade de tuites postados. Portanto, eh necessario
     * que haja essa verificacao sempre que um novo tuite eh postado. E, por fim, retorna o tuite recem postado.
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if(!this.verificaSeUsuarioEhCadastrado(usuario) || texto.length() > TAMANHO_MAXIMO_TUITES) return null;
        Tuite novoTuite = new Tuite(usuario, texto);
        novoTuite.setHashtags(this.registraHashtags(novoTuite));
        usuario.incrementaNumeroDeTuitesPostados();
        usuario.alteraTipoDeUsuario();
        return novoTuite;
    }

    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser "registradas" automaticamente para que este método possa funcionar.
     * Percorremos o Hashmap e sempre comparamos a hashtag da iteracao atual com o que esta salvo em hashtagMaisComum.
     * Se o valor armazenado em hashtagMaisComum for null, significa que ainda nao realizamos nenhuma iteracao e,
     * portanto, a hashtag do for eh temporariamente a mais comum. Sendo assim, armazenamos essa hashtag na variavel
     * local auxiliar hashtagMaisComum. As demais iteracoes, sempre comparamos o value associado a hashtag da iteracao
     * atual (ou seja, a frequencia da mesma), com o value associado a hashtagMaisComum. Se a frequencia dessa hashtag
     * for maior que a frequencia da hashtag salva em hashtagMaisComum, significa que a mesma eh temporariamente a mais
     * comum e a armazenamos em hashtagMaisComum. Esse processo ocorre ate que o HashMap frequenciaDeHashtags seja
     * percorrido por completo. Ao fim, teremos encontrado a hashtag mais frequente da plataforma.
     *
     * @return A hashtag mais utilizada da plataforma
     */

    public String getHashtagMaisComum() {
        Map.Entry<String, Integer> hashtagMaisComum = null;
        for (Map.Entry<String, Integer> hashtag : this.frequenciaDeHashtags().entrySet()) {
            if (hashtagMaisComum == null || hashtag.getValue().compareTo(hashtagMaisComum.getValue()) > 0)
                hashtagMaisComum = hashtag;
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
        return this.usuariosCadastrados.containsKey(usuario);
    }

    /**
     * Funcao auxiliar que "registra" as hashtags.
     * A funcao percorre o texto do tuite dado procurando pelas hashtags (toda string seguida de #).
     * Utiliza regex para identificar um padrao (# + uma palavra + espaco) e analiza o texto do tuite em funcao disso.
     * Enquanto o comparador encontra hashtags no padrao desejado, as adiciona no array local de hashtags do tuite dado.
     * Alem disso, tambem adiciona as hashtags encontradas no array de hashtagsDoTuiter para que seja possivel realizar
     * a verificacao da hashtag mais comum.
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
            //Armazena as hashtags do texto na variavel local
            hashtag.add(comparador.group(1));
            //Adiciona essas hashtags ao array geral de hashtags
            this.hashtagsDoTuiter.add(comparador.group(1));
        }
        return hashtag;
    }
    /**
     * Funcao auxiliar que verifica a frequencia de cada hashtag utilizada na plataforma.
     * A funcao realiza um for loop que percorre item por item do array de hashtags do TuiterLite (contem  repeticoes).
     * A cada iteracao, a variavel contador recebe o value referente a hashtag, ou seja, a sua frequencia.
     * Se a variavel contador for igual a null, significa que a hashtag ainda nao foi registrada no array de frequencia.
     * Sendo assim, inicializamos o valor do contador como zero. As demais iteracoes, a variavel contador eh sempre incrementada.
     * Dessa forma, no final das iteracoes, construimos um array com a hashtag e a frequencia em que a mesma foi utilizada.
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
}

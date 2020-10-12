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

    //Maximo de caracteres por tuite
    public static final int TAMANHO_MAXIMO_TUITES = 120;

    //Array que ira armazenar todas as hashtags ja utilizadas na plataforma (sem repeticoes)
    private final List<String> hashtags;

    //Array que caminha junto com this.hashtags e armazena a frequencia de determinada hashtag
    private final List<Integer> contadorDeHashtags;

    //Array que armazena os usuarios cadastrados na plataforma
    private final List<Usuario> usuariosCadastrados;

    /**
     * Construtor de TuiterLite.
     */
    public TuiterLite() {
        //Inicializa o array de hashtags do Tuiter
        this.hashtags = new ArrayList<String>();

        //Inicializa o array que armazena a frequencia de cada hashtag
        this.contadorDeHashtags = new ArrayList<Integer>();

        //Inicializa o array que armazena os usuarios cadastrados
        this.usuariosCadastrados = new ArrayList<Usuario>();
    }

    /**
     * Getter de hashtags.
     *
     * @return hashtags array de hashtags da plataforma (sem repetioes)
     */
    public List<String> getHashtags() {
        return hashtags;
    }

    /**
     * Getter de contadorDeHashtags.
     *
     * @return contadorDeHashtags array de inteiros que armazena a frequencia que cada hashtag aparece na plataforma
     */
    public List<Integer> getContadorDeHashtags() {
        return contadorDeHashtags;
    }

    /**
     * Getter de usuariosCadastrados.
     *
     * @return usuariosCadastrados array contendo todos os usuarios cadastrados na plataforma
     */
    public List<Usuario> getUsuariosCadastrados() {
        return usuariosCadastrados;
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * Se o usuario informado já for cadastrado, não faz nada e retorna null.
     *
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {
        Usuario novoUsuario = new Usuario(nome, email);
        if(verificaSeUsuarioEhCadastrado(novoUsuario)) return null;
        this.usuariosCadastrados.add(novoUsuario);
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
     * Essa funcao acessa o array que armazena a frequencia das hashtags e
     * computa o maior valor armazenado nesse array. Como o array de frequencia
     * (contadorDeHashtags) e o array de hashtags (hashtags) caminham juntos,
     * podemos usar o index do maior inteiro armazenado no array de frequencia para
     * descobrir a hashtag mais comum correspondente (pois os index são os mesmos).
     *
     * @return A hashtag mais utilizada da plataforma
     */

    public String getHashtagMaisComum() {
        Integer contadorHashtagMaisComum = Collections.max(this.contadorDeHashtags);
        int posicao = this.contadorDeHashtags.indexOf(contadorHashtagMaisComum);
        return this.hashtags.get(posicao);
    }

    /**
     * Metodo que verifica se um usuario eh registrado na plataforma
     * checando se o array dado esta contido no array de usuarios cadastrados.
     *
     * @param usuario
     * @return true se o usuario dado ja estiver contido no array; false caso contrario
     */
    private boolean verificaSeUsuarioEhCadastrado(Usuario usuario) {
        return this.usuariosCadastrados.contains(usuario);
    }

    /**
     * Funcao auxiliar que "registra" as hashtags.
     * A funcao percorre o texto do tuite dado procurando pelas hashtags (toda string seguida de #).
     * Utiliza regex para identificar um padrao (# + uma palavra + espaco) e analiza o texto do tuite em funcao disso.
     * Enquanto o comparador encontra hashtags no padrao desejado, essa funcao, portanto, realiza duas tarefas: Retorna
     * uma variavel local do tipo ArrayList que armazena as hashtags do tuite dado. Isso será essencial para registrar
     * tais hashtags no atributo hashtags de cada tuite. Isso é feito na funcao tuitarAlgo. Além disso, chama a funcao
     * frequenciaDeHashtags que registra a frequencia em que cada hashtag aparece na plataforma.
     *
     * @param tuite determinado tuite
     * @return um ArrayList com todas as hashtags utilizadas no texto do tuite dadosetHashtags
     */
    private ArrayList<String> registraHashtags(Tuite tuite) {
        ArrayList<String> hashtagsLocaisDoTuite = new ArrayList<String>();
        String textoDoTuite = tuite.getTexto();
        Pattern padrao = Pattern.compile("(#\\w+)\\b");
        Matcher comparador = padrao.matcher(textoDoTuite);

        while (comparador.find()) {
            this.frequenciaDeHashtags(comparador.group(1));
            hashtagsLocaisDoTuite.add(comparador.group(1));
        }

        return hashtagsLocaisDoTuite;
    }

    /**
     * Funcao auxiliar que registra a frequencia em que as hashtags aparecem na plataforma.
     * Isso é feito atraves de dois arrays que caminham juntos: hashtags e contadorDeHashtags.
     * Ou seja, o numero na posicao 1 de contadorDeHashtags representa a frequencia em que a
     * hashtag na posicao 1 do array de hashtags aparece, a por ai vai. Como o array de hashtags
     * esta limitado a registrar cada hashtag apenas uma vez, podemos definir duas tarefas para
     * a funcao frequenciaDeHashtags realizar: Se a hashtag recebida não estiver contida no array
     * de hashtags, a adiciona no mesmo e contabiliza o seu contador. Caso contrario, percorre o
     * array de hashtags procurando pelo indice da hashtag dada na chamada da funcao. Uma vez encontrado
     * esse indice, incrementa o contador contido na posicao desse indice.
     *
     * @param hashtag determinada hashtag da plataforma
     */
    private void frequenciaDeHashtags(String hashtag) {
        int contador = 0;
        //Se a hashtag ja estiver registrada no array de hashtags, apenas incrementa o seu contador
        if(this.hashtags.contains(hashtag)) {
            for(String hashtagDoTuiter : this.hashtags) {
                if(hashtagDoTuiter.equals(hashtag)) {
                    int posicao = this.hashtags.indexOf(hashtagDoTuiter);
                    int contadorDaHashtag = this.contadorDeHashtags.get(posicao);
                    this.contadorDeHashtags.set(posicao, ++contadorDaHashtag);
                }
            }
        }
        //Se a hashtag ainda nao estiver registrada, incrementa o seu contador e a adiciona no array de hashtags
        else {
            this.hashtags.add(hashtag);
            this.contadorDeHashtags.add(++contador);
        }
    }
}

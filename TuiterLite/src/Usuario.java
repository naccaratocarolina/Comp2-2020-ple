import java.awt.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {

    public static final int MIN_TUITES_SENIOR = 200;
    public static final int MIN_TUITES_NINJA = 1000;

    //Email do Usuario
    private final String email;

    //Nome do Usuario
    private String nome;

    //Imagem do Usuario
    private Image foto;

    // Pode ser INICIANTE, SENIOR ou NINJA
    private NivelUsuario nivel;

    //Numero de tuites postados pelo Usuario
    private int numeroDeTuitesPostados;

    /**
     * Construtor de Usuario.
     *
     * @param nome nome do usuario
     * @param email email do usuario
     */
    public Usuario(String nome, String email) {
        //Faz a validacao dos emails
        if(verificaEmail(email)) this.email = email;
        //Caso o email inserido nao seja valido, cria um usuario vazio
        else {
            this.email = null;
            this.nome = null;
            this.nivel = null;
        }
        this.nome = nome;
        this.nivel = NivelUsuario.INICIANTE;
        this.numeroDeTuitesPostados = 0;
    }

    /**
     * Getter de email.
     *
     * @return email do usuario
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter de nome.
     *
     * @return
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Getter de nivel.
     *
     * @return nivel de usuario na plataforma
     */
    public NivelUsuario getNivel() {
        return nivel;
    }

    /**
     * Setter de nivel.
     * Pode ser INICIANTE, SENIOR ou NINJA
     *
     * @param nivel nivel do usuario na plataforma
     */
    public void setNivel(NivelUsuario nivel) {
        this.nivel = nivel;
    }

    /**
     * Getter de Foto.
     *
     * @return foto do usuario
     */
    public Image getFoto() {
        return this.foto;
    }

    /**
     * Setter de Foto.
     *
     * @param foto do usuario
     */
    public void setFoto(Image foto) {
        this.foto = foto;
    }

    /**
     * Getter de numeroDeTuitesPostados.
     *
     * @return numero de tuites postados pelo usuario
     */
    public int getNumeroDeTuitesPostados() {
        return numeroDeTuitesPostados;
    }

    /**
     * Setter de numeroDeTuitesPostados.
     *
     * @param numeroDeTuitesPostados numero de tuites postados pelo usuario
     */
    public void setNumeroDeTuitesPostados(int numeroDeTuitesPostados) {
        this.numeroDeTuitesPostados = numeroDeTuitesPostados;
    }

    /**
     * Funcao auxiliar que incrementa o numero de tuites postados sempre que um tuite eh postado.
     */
    public void incrementaNumeroDeTuitesPostados() {
        this.numeroDeTuitesPostados++;
    }

    /**
     * Realiza a verificacao do formato do email usando regex.
     * O formato aceito eh: fulano@fulano.com
     *
     * @param email email do usuario
     * @return true se o email dado tem o formato desejado; false caso contrario
     */
    private boolean verificaEmail(String email) {
        String regras = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regras);
    }

    /**
     * Funcao auxiliar que altera o tipo de usuario em funcao do numero de tuites postados.
     * Essa funcao eh chamada sempre que um novo tuite eh postado, para que sempre haja essa verificacao.
     */
    public void alteraTipoDeUsuario() {
        if(this.getNumeroDeTuitesPostados() >= MIN_TUITES_SENIOR && this.getNumeroDeTuitesPostados() < MIN_TUITES_NINJA)
            this.setNivel(NivelUsuario.SENIOR);
        else if(this.getNumeroDeTuitesPostados() >= MIN_TUITES_NINJA)
            this.setNivel(NivelUsuario.NINJA);
    }

    /**
     * Override do metodo equals para Usuario.
     * Dois usuarios sao considerados iguais se possuirem o mesmo email.
     *
     * @param object objeto que se deseja comparar (esperamos que seja do tipo Usuario)
     * @return true se forem iguais, ou seja, possuirem o mesmo email; false, caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Usuario)) return false;
        Usuario usuario = (Usuario) object;
        return this.email == usuario.getEmail();
    }

    /**
     * Override do hashCode em funcao da nova regra do metodo equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

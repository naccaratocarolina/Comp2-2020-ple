import java.util.ArrayList;

public class Tuite<T> {

    //Autor do tuite
    private final Usuario autor;

    //Texto do tuite
    private final String texto;

    //Array com todas as hashtags utilizadas no tuite (this)
    private ArrayList<String> hashtags;

    //Anexo que se deseja adicionar ao tuite
    private T anexo;

    /**
     * Construtor de Tuite.
     *
     * @param autor autor do tuite
     * @param texto texto do tuite
     */
    public Tuite(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
    }

    /**
     * Getter de autor.
     *
     * @return autor do tuite
     */
    public Usuario getAutor() {
        return this.autor;
    }

    /**
     * Getter de texto.
     *
     * @return texto do tuite
     */
    public String getTexto() {
        return this.texto;
    }

    /**
     * Getter de hashtags.
     *
     * @return ArrayList com todas as hashtags utilizadas no texto do tuite
     */
    public ArrayList<String> getHashtags() {
        return this.hashtags;
    }

    /**
     * Getter de anexo.
     *
     * @return anexo do tuite
     */
    public Object getAnexo() {
        return this.anexo;
    }

    /**
     * Setter de Hashtags.
     *
     * @param hashtags ArrayList com todas as hashtags utilizadas no texto do tuite dado
     */
    public void setHashtags(ArrayList<String> hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * Anexa algo ao tuite.
     *
     * @param anexo conteudo que se deseja anexar
     */
    public void anexarAlgo(T anexo) {
        this.anexo = anexo;
    }
}
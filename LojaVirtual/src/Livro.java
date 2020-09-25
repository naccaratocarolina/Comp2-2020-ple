public class Livro extends Produto {
    //Titulo do livro
    public final String titulo;

    //Autor do livro
    public final String autor;

    //Ano de publicacao do livro
    public int anoDePublicacao;

    //Numero de paginas do livro
    public int numDePaginas;

    /**
     * Construtor de Livro.
     *
     * @param pesoEmGramas
     * @param precoEmReais
     * @param categoria
     * @param quantEmEstoque
     * @param titulo
     * @param autor
     * @param anoDePublicacao
     * @param numDePaginas
     */
    public Livro(int pesoEmGramas, float precoEmReais, String categoria, int quantEmEstoque,
                 String titulo, String autor, int anoDePublicacao, int numDePaginas) {
        super(pesoEmGramas, precoEmReais, categoria, quantEmEstoque);
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.numDePaginas = numDePaginas;
    }

    /**
     * Getter de titulo.
     *
     * @return titulo do livro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Getter de titulo.
     *
     * @return titulo do livro
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Getter de anoDePublicacao.
     *
     * @return anoDePublicacao
     */
    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    /**
     * Setter de anoDePublicacao.
     *
     * @param anoDePublicacao ano de publicacao do livro
     */
    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    /**
     * Getter numDePaginas.
     *
     * @return numDePaginas numero de paginas do livro
     */
    public int getNumDePaginas() {
        return numDePaginas;
    }

    /**
     * Setter de numDePaginas.
     *
     * @param numDePaginas numero de paginas do livro
     */
    public void setNumDePaginas(int numDePaginas) {
        this.numDePaginas = numDePaginas;
    }
}

public class Livro extends Produto {
    public final String titulo;
    public final String autor;
    public int anoDePublicacao;
    public int numDePaginas;

    public Livro(int pesoEmGramas, float precoEmReais, String categoria, int quantEmEstoque,
                 String titulo, String autor, int anoDePublicacao, int numDePaginas) {
        super(pesoEmGramas, precoEmReais, categoria, quantEmEstoque);
        this.titulo = titulo;
        this.autor = autor;
        this.anoDePublicacao = anoDePublicacao;
        this.numDePaginas = numDePaginas;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public int getNumDePaginas() {
        return numDePaginas;
    }

    public void setNumDePaginas(int numDePaginas) {
        this.numDePaginas = numDePaginas;
    }
}

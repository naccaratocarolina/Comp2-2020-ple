public class Roupa extends Produto {
    //Tamanho da roupa
    public final char tamanho;

    //Cor da roupa
    private String cor;

    /**
     * Construtor de Roupa.
     *
     * @param pesoEmGramas
     * @param precoEmReais
     * @param categoria
     * @param quantEmEstoque
     * @param tamanho
     * @param cor
     */
    public Roupa(int pesoEmGramas, float precoEmReais, String categoria,
                 int quantEmEstoque, char tamanho, String cor) {
        super(pesoEmGramas, precoEmReais, categoria, quantEmEstoque);
        this.tamanho = tamanho;
        this.cor = cor;
    }

    /**
     * Getter de tamanho.
     *
     * @return tamanho da roupa
     */
    public char getTamanho() {
        return tamanho;
    }

    /**
     * Getter de cor.
     *
     * @return cor da roupa
     */
    public String getCor() {
        return cor;
    }

    /**
     * Setter de cor.
     *
     * @param cor cor da roupa
     */
    public void setCor(String cor) {
        this.cor = cor;
    }
}

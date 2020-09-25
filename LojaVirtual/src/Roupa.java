public class Roupa extends Produto {
    public final char tamanho;
    private String cor;

    public Roupa(int pesoEmGramas, float precoEmReais, String categoria,
                 int quantEmEstoque, char tamanho, String cor) {
        super(pesoEmGramas, precoEmReais, categoria, quantEmEstoque);
        this.tamanho = tamanho;
        this.cor = cor;
    }

    public char getTamanho() {
        return tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}

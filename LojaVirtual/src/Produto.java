import java.util.Objects;

public class Produto {
    //Variavel auxiliar para garantir o autoIncrement do id
    private static long idAutoIncrement = 1;

    //Identificador unico de cada produto
    protected final long id;

    //Peso em gramas
    private int pesoEmGramas;

    //Preco unitario em reais desse produto
    private float precoEmReais;

    //Categoria do produto
    private String categoria;

    //Quantidade em estoque desse produto
    private int quantEmEstoque;

    /**
     * Construtor de Produto.
     *
     * @param pesoEmGramas
     * @param precoEmReais
     * @param categoria
     * @param quantEmEstoque
     */
    public Produto(int pesoEmGramas, float precoEmReais, String categoria, int quantEmEstoque) {
        this.id = idAutoIncrement++;
        this.pesoEmGramas = pesoEmGramas;
        this.precoEmReais = precoEmReais;
        this.categoria = categoria;
        this.quantEmEstoque = quantEmEstoque;
    }

    /**
     * Getter de id.
     *
     * @return identificador do produto
     */
    public long getId() {
        return id;
    }

    /**
     * Getter de getPesoEmGramas.
     *
     * @return peso em gramas do produto
     */
    public int getPesoEmGramas() {
        return pesoEmGramas;
    }

    /**
     * Setter de getPesoEmGramas.
     *
     * @param pesoEmGramas peso em gramas do produto
     */
    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }

    /**
     * Getter de precoEmReais.
     *
     * @return preco unitario em reais do produto
     */
    public float getPrecoEmReais() {
        return precoEmReais;
    }

    /**
     * Setter de setPrecoEmReais.
     *
     * @param precoEmReais preco unitario em reais do produto
     */
    public void setPrecoEmReais(float precoEmReais) {
        this.precoEmReais = precoEmReais;
    }

    /**
     * Getter de categoria.
     *
     * @return categoria do produto
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Setter de categoria.
     *
     * @param categoria categoria do produto
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Getter de quantEmEstoque.
     *
     * @return quantidade disponivel em estoque do produto
     */
    public int getQuantEmEstoque() {
        return quantEmEstoque;
    }

    /**
     * Setter de quantEmEstoque.
     *
     * @param quantEmEstoque quantidade disponivel em estoque do produto
     */
    public void setQuantEmEstoque(int quantEmEstoque) {
        this.quantEmEstoque = quantEmEstoque;
    }

    /**
     * Override do metodo equals para Produto.
     * Dois produtos sao considerados iguais se possuirem o mesmo identificador unico.
     *
     * @param object do tipo Produto, ou suas subclasses (Livro && Roupa)
     * @return true se os ids forem iguais, false caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Produto)) return false;
        Produto produto = (Produto) object;
        return this.getId() == produto.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Override do metodo toString para Produto.
     * Este método formata uma string com todas as informacoes essenciais de Produto.
     *
     * @return
     */
    @Override
    public String toString() {
        return  "ID do produto: " + this.getId() +
                "\nPreço unitário em Reais: " + this.getPrecoEmReais();
    }
}

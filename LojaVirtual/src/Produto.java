public class Produto {
    private static long idAutoIncrement = 1;
    protected final long id;
    private int pesoEmGramas;
    private float precoEmReais;
    private String categoria;
    private int quantEmEstoque;

    public Produto(int pesoEmGramas, float precoEmReais, String categoria, int quantEmEstoque) {
        this.id = idAutoIncrement++;
        this.pesoEmGramas = pesoEmGramas;
        this.precoEmReais = precoEmReais;
        this.categoria = categoria;
        this.quantEmEstoque = quantEmEstoque;
    }

    public long getId() {
        return id;
    }

    public int getPesoEmGramas() {
        return pesoEmGramas;
    }

    public void setPesoEmGramas(int pesoEmGramas) {
        this.pesoEmGramas = pesoEmGramas;
    }

    public static long getIdAutoIncrement() {
        return idAutoIncrement;
    }

    public static void setIdAutoIncrement(long idAutoIncrement) {
        Produto.idAutoIncrement = idAutoIncrement;
    }

    public float getPrecoEmReais() {
        return precoEmReais;
    }

    public void setPrecoEmReais(float precoEmReais) {
        this.precoEmReais = precoEmReais;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantEmEstoque() {
        return quantEmEstoque;
    }

    public void setQuantEmEstoque(int quantEmEstoque) {
        this.quantEmEstoque = quantEmEstoque;
    }
}

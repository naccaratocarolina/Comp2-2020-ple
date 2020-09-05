public class Titular {
    //nome do titular
    private String nome;

    //senha numerica do cartao
    private final int senhaCartao;

    //senha da Intranet do banco
    private final String senhaIntranet;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSenhaCartao() {
        return senhaCartao;
    }

    public String getSenhaIntranet() {
        return senhaIntranet;
    }
}

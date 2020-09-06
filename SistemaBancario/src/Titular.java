public class Titular {
    //nome do titular
    private String nome;

    //CPF do titular
    private final long cpf;

    //senha numerica do cartao
    private final int senhaCartao;

    //senha da Intranet do banco
    private String senhaIntranet;

    //Construtor do Titular da Conta
    public Titular(String nome, long cpf, int senhaCartao) {
        //inicializando atts obrigatorios para criar um Titular
        this.nome = nome;
        this.cpf = cpf;
        this.senhaCartao = senhaCartao;

        //inicializando valores defaults de outros atts
        this.senhaIntranet = String.valueOf(this.cpf);
    }

    public String getNome() {
        return nome;
    }

    public long getCpf() {
        return cpf;
    }

    public int getSenhaCartao() {
        return senhaCartao;
    }

    public String getSenhaIntranet() {
        return senhaIntranet;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenhaIntranet(String senhaIntranet) {
        this.senhaIntranet = senhaIntranet;
    }
}

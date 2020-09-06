import static java.lang.String.format;

public class Conta {
    //o limite da conta
    public final static int LIMITE = 100;

    //numero da conta bancaria
    private final long numero;

    //saldo da conta
    private float saldo;

    //array de Strings com operacoes da conta
    private String[] operacoes;
    private int quantOperacoes;

    //Agencia a qual a conta pertence
    private Agencia agencia;

    //Titular da conta
    private Titular titular;

    //Gerente da conta
    private Gerente gerente;

    //Construtor de Conta
    public Conta(long numero, Agencia agencia, Titular titular) {
        //inicializando atts obrigatorios
        this.numero = numero;
        this.agencia = agencia;
        this.titular = titular;

        //inicializando valores defaults de outros atts
        this.saldo = 0;
        this.operacoes = new String[10];
        this.quantOperacoes = 0;
        this.gerente = agencia.getGerenteGeral();
    }

    public long getNumero() {
        return numero;
    }

    public float getSaldo() {
        return saldo;
    }

    public String[] getOperacoes() {
        return operacoes;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public boolean verificaSenhaCartao(int senhaDigitada) {
        return this.titular.getSenhaCartao() == senhaDigitada;
    }

    public boolean verificaSenhaIntranet(String senhaDigitada) {
        return this.titular.getSenhaIntranet().equals(senhaDigitada);
    }

    public void alteraSenhaIntranet(String senhaDigitada, String novaSenha) {
        if(verificaSenhaIntranet(senhaDigitada)) {
            this.titular.setSenhaIntranet(novaSenha);
        }
    }

    public void sacar(int senhaDigitada, float valor) {
        if(this.saldo - valor > -LIMITE) {
            if (verificaSenhaCartao(senhaDigitada)) {
                this.saldo -= valor;
                String novaOperacao = String.format("%s realizou um saque de R$%.2f", this.titular.getNome(), valor);
                this.operacoes[this.quantOperacoes++] = novaOperacao;
            }
        }
    }

    public void depositar(int senhaDigitada, Conta contaDestino, float valor) {
        if(verificaSenhaCartao(senhaDigitada)) {
            //se o deposito for para si mesmo
            if(this == contaDestino) {
                this.saldo += valor;
                String novaOperacao = String.format(this.titular.getNome() + " depositou R$%.2f em sua conta de numero %d",
                        valor, this.getNumero());
                this.operacoes[this.quantOperacoes++] = novaOperacao;
            }

            //se for um deposito entre contas
            else {
                contaDestino.saldo += valor;
                String novaOperacao = String.format(this.titular.getNome() + " depositou R$%.2f na conta de %s de numero %d",
                        valor, contaDestino.getTitular().getNome(), contaDestino.getNumero());
                this.operacoes[this.quantOperacoes++] = novaOperacao;
            }
        }
    }

    public void transferir(int senhaDigitada, Conta contaDestino, float valor) {
        if(this.saldo - valor > -LIMITE) {
            if (verificaSenhaCartao(senhaDigitada)) {
                this.saldo -= valor;
                this.depositar(senhaDigitada, contaDestino, valor);
                //registra operacao na conta de quem esta realizando a mesma
                String novaOperacaoContaTitular = String.format(
                        "%s transferiu R$%.2f para a conta de %s de numero %d",
                        this.titular.getNome(), valor, contaDestino.getTitular().getNome(), contaDestino.getNumero());
                this.operacoes[this.quantOperacoes++] = novaOperacaoContaTitular;

                //registra operacao na conta destino da transferencia
                String novaOperacaoContaDestino = String.format("%s recebeu uma transferencia de %s no valor de %.2f",
                        contaDestino.getTitular().getNome(), this.titular.getNome(), valor);
                contaDestino.operacoes[contaDestino.quantOperacoes++] = novaOperacaoContaDestino;
            }
        }
    }

    public String historicoOperacoesBancarias(String senhaDigitada) {
        String historico = "";

        String novaOperacao = String.format("Saldo final de R$%.2f da conta de numero %d", this.getSaldo(), this.getNumero());
        this.operacoes[this.quantOperacoes++] = novaOperacao;

        if(verificaSenhaIntranet(senhaDigitada)) {
            for(int i=0; i<this.quantOperacoes; i++) {
                historico = historico + this.operacoes[i] + "\n";
            }
        }

        return historico;
    }
}

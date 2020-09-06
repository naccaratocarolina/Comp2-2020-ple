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

    public boolean verificaSenha(int senhaDigitada) {
        return this.titular.getSenhaCartao() == senhaDigitada;
    }

    public void sacar(int senhaDigitada, float valor) {
        if(this.saldo - valor > -LIMITE) {
            if (verificaSenha(senhaDigitada)) {
                this.saldo -= valor;
                String novaOperacao = String.format("Saque: R$%.2f", valor);
                this.operacoes[this.quantOperacoes++] = novaOperacao;
            }
        }
    }

    public void receberDeposito(float valor) {
        depositar(valor, this.numero);
    }

    public void depositar(float valor, long numeroContaDestino) {
        this.saldo += valor;
        String novaOperacao = String.format("Deposito na conta %d: R$%.2f", numeroContaDestino, valor);
        this.operacoes[this.quantOperacoes++] = novaOperacao;
    }

    public void transferir(int senhaDigitada, Conta contaDestino, float valor) {
        if(this.saldo - valor > -LIMITE) {
            if (verificaSenha(senhaDigitada)) {
                this.saldo -= valor;
                contaDestino.depositar(valor, contaDestino.getNumero());
                String novaOperacao = String.format(
                        "Transferencia efetuada para a conta %d de %s: R$%.2f",
                        contaDestino.getNumero(), this.titular.getNome(), valor);
                this.operacoes[this.quantOperacoes++] = novaOperacao;
            }
        }
    }
}

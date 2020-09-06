public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Meu Banco");
        Agencia agencia = new Agencia();
        Titular ana = new Titular("Ana", 1006218749, 1820);
        Gerente gerente = new Gerente("João");
        agencia.setGerenteGeral(gerente);
        Conta conta = banco.cadastrarConta(agencia, ana);
        System.out.println(conta.getSaldo());
    }
}

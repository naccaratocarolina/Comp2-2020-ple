public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Meu Banco");

        Gerente mariana = banco.cadastrarGerente("Mariana");
        Gerente roberta = banco.cadastrarGerente("Roberta");
        Gerente thiago = banco.cadastrarGerente("Thiago");

        int codigo1 = 654;
        Agencia agencia1 = banco.cadastrarAgencia(codigo1, mariana);
        int codigo2 = 789;
        Agencia agencia2 = banco.cadastrarAgencia(codigo2, thiago);

        long cpfJoana = 301553982;
        int senhaCartaoJoana = 9301;
        Titular joana = banco.cadastrarTitular("Joana", cpfJoana, senhaCartaoJoana);

        long cpfGabriel = 664882301;
        int senhaCartaoGabriel = 4510;
        Titular gabriel = banco.cadastrarTitular("Gabriel", cpfGabriel, senhaCartaoGabriel);

        Conta contaJoana = banco.cadastrarConta(agencia1, joana);
        Conta contaGabriel = banco.cadastrarConta(agencia2, gabriel);

        contaJoana.depositar(senhaCartaoJoana, contaJoana, 1000);
        contaJoana.depositar(senhaCartaoJoana, contaGabriel, 420);
        contaJoana.transferir(senhaCartaoJoana, contaGabriel, 80);
        contaGabriel.sacar(senhaCartaoGabriel, 311);
        contaJoana.pagamento(String.valueOf(cpfJoana), 127);

        System.out.println(contaJoana.historicoOperacoesBancarias("301553982"));
        System.out.println(contaGabriel.historicoOperacoesBancarias("664882301"));
    }
}

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BancoTest {
    private static final int DELTA = 0;

    private Banco banco;

    private Conta contaAna;
    private Conta contaEduardo;

    private Agencia agencia1;
    private int codigo1;
    private Agencia agencia2;
    private int codigo2;

    private Titular ana;
    private long cpfAna;
    private int senhaCartaoAna;

    private Titular eduardo;
    private long cpfEduardo;
    private int senhaCartaoEduardo;

    private Gerente gabriella;
    private Gerente marcelo;

    @Before
    public void setUp() {
        //criar o banco
        banco = new Banco("Meu Banco");

        //cadastrar gerentes
        gabriella = banco.cadastrarGerente("Gabriella");
        marcelo = banco.cadastrarGerente("Marcelo");

        //cadastrar agencia
        codigo1 = 123;
        agencia1 = banco.cadastrarAgencia(codigo1, gabriella);
        codigo2 = 987;
        agencia2 = banco.cadastrarAgencia(codigo2, marcelo);

        //cadastrar titulares
        cpfAna = 1479820654;
        senhaCartaoAna = 6981;
        ana = banco.cadastrarTitular("Ana", cpfAna, senhaCartaoAna);
        cpfEduardo = 1556206771;
        senhaCartaoEduardo = 4271;
        eduardo = banco.cadastrarTitular("Eduardo", cpfEduardo, senhaCartaoEduardo);

        //criando as contas
        contaAna = banco.cadastrarConta(agencia1, ana);
        contaEduardo = banco.cadastrarConta(agencia1, eduardo);
    }

    @Test
    public void testarCadastroAgencia() {

    }
}
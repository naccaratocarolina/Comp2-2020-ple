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
    public void testarCodigoAgencia() {
        Agencia novaAgencia = new Agencia();
        assertNotNull(novaAgencia);
        novaAgencia.setCodigo(1);
        assertEquals(0, novaAgencia.getCodigo());
        novaAgencia.setCodigo(111111);
        assertEquals(0, novaAgencia.getCodigo());
        novaAgencia.setCodigo(333);
        assertEquals(333, novaAgencia.getCodigo());
    }

    @Test
    public void testarCadastroAgencia() {
        assertEquals(2, banco.getQuantAgencias());
        Agencia novaAgencia = banco.cadastrarAgencia(654, marcelo);
        assertNotNull(novaAgencia);
        assertEquals(3, banco.getQuantAgencias());
        assertEquals(marcelo, novaAgencia.getGerenteGeral());
        assertEquals(gabriella, agencia1.getGerenteGeral());
    }

    @Test
    /**
     * Regra da geracao de um numero de conta
     * 3 primeiros digitos - Codigo da agencia
     * quarto digito - Identificador do titular + 1
     * quinto digito - Identificador do gerente + 1
     */
    public void testarGeracaoNumeroConta() {
        Titular barbara = banco.cadastrarTitular("Barbara", 11111111, 1234);
        Titular suzana = banco.cadastrarTitular("Suzana", 222222222, 6549);
        Agencia agencia3 = banco.cadastrarAgencia(999, marcelo);
        assertEquals(4, banco.getQuantTitulares());

        //Numero gerado esperado: 123 (codigo da agencia1) + 3 (id do titular, barbara) + 1 (id da gerente da agencia1)
        assertEquals(12331, banco.gerarNumeroConta(agencia1, barbara));

        //Numero gerado esperado: 999 (codigo da agencia3) + 4 (id do titular, suzana) + 2 (id da gerente da agencia3)
        assertEquals(99942, banco.gerarNumeroConta(agencia3, suzana));
    }

    @Test
    public void testarCadastroConta() {
        assertEquals(0, contaAna.getSaldo(), DELTA);
        assertEquals(agencia1.getGerenteGeral(), contaAna.getGerente());
        assertEquals(ana, contaAna.getTitular());
    }

    @Test
    public void testarAlterarSenhaIntranet() {
        //primeiro acesso a intranet do banco, senha = cpf
        assertEquals(String.valueOf(cpfAna), contaAna.getTitular().getSenhaIntranet());
        contaAna.alteraSenhaIntranet(String.valueOf(cpfAna), "novasenha");
        assertEquals("novasenha", contaAna.getTitular().getSenhaIntranet());
        contaAna.depositar(senhaCartaoAna, contaAna, 500);
        contaAna.sacar(senhaCartaoAna, 120);
        assertNotNull(contaAna.historicoOperacoesBancarias("novasenha"));
    }

    @Test
    public void testarOperacoesBancarias() {
        assertTrue(contaAna.verificaSenhaCartao(senhaCartaoAna));
        assertFalse(contaAna.verificaSenhaCartao(senhaCartaoEduardo));

        contaAna.depositar(senhaCartaoAna, contaAna, 500);
        assertEquals(500, contaAna.getSaldo(), DELTA);

        contaAna.depositar(senhaCartaoAna, contaEduardo, 120);
        assertEquals(500, contaAna.getSaldo(), DELTA);
        assertEquals(120, contaEduardo.getSaldo(), DELTA);

        contaAna.sacar(senhaCartaoAna, 60);
        assertEquals(440, contaAna.getSaldo(), DELTA);

        contaAna.transferir(senhaCartaoAna, contaEduardo, 145);
        assertEquals(295, contaAna.getSaldo(), DELTA);
        assertEquals(265, contaEduardo.getSaldo(), DELTA);
    }

    @Test
    public void testarSaqueComSenhaIncorreta() {
        contaAna.sacar(senhaCartaoEduardo, 20);
        assertEquals(0, contaAna.getSaldo(), DELTA);

        contaAna.sacar(senhaCartaoAna, 100);
        assertEquals(0, contaAna.getSaldo(), DELTA);

        contaAna.sacar(senhaCartaoAna, 10);
        assertEquals(-10, contaAna.getSaldo(), DELTA);
    }

    @Test
    public void testarTransferenciaSemFundos() {
        contaAna.transferir(senhaCartaoAna, contaEduardo, 500);
        assertEquals(0, contaAna.getSaldo(), DELTA);
    }
}
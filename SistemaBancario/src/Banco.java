public class Banco {
    //nome do banco
    private String nome;

    //array de agencias do banco
    private Agencia[] agencias;
    private int quantAgencias;

    //array de contas do banco
    private Conta[] contas;
    private int quantContas;

    //array de gerentes do banco
    private Gerente[] gerentes;
    private int quantGerentes;

    //array de titulares do banco
    private Titular[] titulares;
    private int quantTitulares;

    public Banco(String nome) {
        this.nome = nome;

        //inicializando os arrays
        this.agencias = new Agencia[10];
        this.quantAgencias = 0;
        this.contas = new Conta[10];
        this.quantContas = 0;
        this.gerentes = new Gerente[10];
        this.quantGerentes = 0;
        this.titulares = new Titular[10];
        this.quantTitulares = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Agencia cadastrarAgencia(int codigo, Gerente gerente) {
        Agencia novaAgencia = new Agencia();
        novaAgencia.setCodigo(codigo);
        novaAgencia.setGerenteGeral(gerente);
        this.agencias[this.quantAgencias++] = novaAgencia;
        return novaAgencia;
    }

    public Conta cadastrarConta(Agencia agencia, Titular titular) {
        //long numero = gerarNumeroConta(agencia, titular);
        long numero = gerarNumeroConta(agencia, titular);
        Conta novaConta = new Conta(numero, agencia, titular);
        this.contas[this.quantContas++] = novaConta;
        return novaConta;
    }

    /**
     * A regra de criacao do numero de uma conta eh uma composicao de
     * codigo da agencia, identificador do titular, identificador do gerente
     * @param agencia
     * @param titular
     */
    public long gerarNumeroConta(Agencia agencia, Titular titular) {
        long numeroConta;
        int idTitular = 0;
        int idGerente = 0;

        //Pega o identificador do titular
        for(int i=0; i<this.titulares.length; i++) {
            if(this.titulares[i] == titular) {
                idTitular = i+1;
            }
        }

        //Pega o identificador do gerente
        for(int j=0; j<this.gerentes.length; j++) {
            if(this.gerentes[j] == agencia.getGerenteGeral()) {
                idGerente = j+1;
            }
        }

        //gera o numero da conta de acordo com a regra
        numeroConta = agencia.getCodigo()*100 + idTitular*10 + idGerente;
        return numeroConta;
    }

    public Gerente cadastrarGerente(String nome) {
        Gerente novoGerente = new Gerente(nome);
        this.gerentes[this.quantGerentes++] = novoGerente;
        return novoGerente;
    }

    public Titular cadastrarTitular(String nome, long cpf, int senhaCartao) {
        Titular novoTitular = new Titular(nome, cpf, senhaCartao);
        this.titulares[this.quantTitulares++] = novoTitular;
        return novoTitular;
    }

    public int getQuantAgencias() {
        return quantAgencias;
    }

    public int getQuantContas() {
        return quantContas;
    }

    public int getQuantGerentes() {
        return quantGerentes;
    }

    public int getQuantTitulares() {
        return quantTitulares;
    }
}

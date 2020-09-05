public class Agencia {
    //nome da agencia
    private String nome;

    //numero da agencia
    private final long numero;

    //gerente geral dessa agencia
    private Gerente gerenteGeral;

    /**
     * Setter de gerente geral da Agencia
     * @param gerente
     */
    public void setGerenteGeral(Gerente gerente) {
        this.gerenteGeral = gerente;
    }

    /**
     * Getter de gerente geral da Agencia
     * @return gerenteGeral
     */
    public Gerente getGerenteGeral() {
        return this.gerenteGeral;
    }
}

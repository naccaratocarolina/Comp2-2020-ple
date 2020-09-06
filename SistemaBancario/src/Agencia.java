public class Agencia {
    //codigo da agencia
    private int codigo;

    //gerente geral dessa agencia
    private Gerente gerenteGeral;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        //obriga que o numero digitado tenha somente 3 digitos
        if(codigo<100 || codigo>1000) {
            return;
        }
        this.codigo = codigo;
    }

    public void setGerenteGeral(Gerente gerenteGeral) {
        this.gerenteGeral = gerenteGeral;
    }

    public Gerente getGerenteGeral() {
        return this.gerenteGeral;
    }
}

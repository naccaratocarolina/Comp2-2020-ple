import java.util.Objects;

public class Fracao {

    private int numerador;
    public int denominador;
    public boolean positiva;
    private double dizima;
    private int sinal;

    /**
     * Construtor.
     * O sinal da fração é passado no parâmetro específico.
     *
     * @param numerador O numerador (inteiro não-negativo)
     * @param denominador O denominador (inteiro positivo)
     * @param positiva se true, a fração será positiva; caso contrário, será negativa
     */
    public Fracao(int numerador, int denominador, boolean positiva)
    {
        this.numerador = numerador;
        this.denominador = denominador;
        this.positiva = positiva;
        this.dizima = (double) numerador/denominador;
    }

    /**
     * Getter de numerador
     *
     * @return retorna o numerador da fracao
     */
    public int getNumerador()
    {
        return numerador;
    }

    /**
     * Setter de numerador
     *
     * @param numerador
     */
    public void setNumerador(int numerador)
    {
        this.numerador = numerador;
    }

    /**
     * Getter de denominador
     *
     * @return retorna o denominador da fracao
     */
    public int getDenominador()
    {
        return denominador;
    }

    /**
     * Getter de positiva
     *
     * @return retorna um valor booleano armazenado no atributo positiva
     */
    public boolean isPositiva()
    {
        return positiva;
    }

    /**
     * Getter de sinal
     *
     * @return retorna 1 se positiva for true, -1 caso contrario
     */
    public int getSinal()
    {
        return sinal;
    }

    /**
     * Setter de sinal
     * Armazena 1 no att sinal se positiva for true, -1 caso contrario
     *
     * @param positiva
     */
    public void setSinal(boolean positiva)
    {
        if(positiva) this.sinal = 1;
        else this.sinal = -1;
    }

    /**
     * @return um double com o valor numérico desta fração
     */
    public double getValorNumerico()
    {
        return (double) this.numerador/this.denominador;
    }

    private boolean ehDizimaSimples()
    {
        return true;
    }

    private int getPeriodo()
    {
        return 1;
    }

    private int getDigito(int numero, int i)
    {
        String num = String.valueOf(numero);
        return Character.digit(num.charAt(i),10);
    }

    private String getDecimal(String numero, int i) {
        return String.valueOf(numero.charAt(i));
    }

    public void printa() {
        System.out.println(this.getPeriodo());
        System.out.println(this.getDigito(54321,0));
    }

    private double obtemParteDecimal(double dizima)
    {
        return dizima - Math.floor(dizima);
    }

    private int getNumDigitosPeriodo()
    {
        return 1;
    }

    private int transformaEmDizimaSimples()
    {
        return 1;
    }

    /**
     * Retorna uma fração que é equivalente a esta fração (this),
     * e que é irredutível (numerador e denominador primos entre si)
     *
     * @return uma fração irredutível equivalente a esta
     */
    public Fracao getFracaoGeratriz()
    {
        int numDigitosPeriodo = 1;
        int numeradorGeratriz = (this.numerador/this.denominador)*10*numDigitosPeriodo;
        int denominadorGeratriz = (10*numDigitosPeriodo) - 1;
        return new Fracao(numeradorGeratriz, denominadorGeratriz, this.positiva);
    }

    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!(object instanceof Fracao)) return false;
        Fracao fracao = (Fracao) object;
        return numerador == fracao.numerador &&
                denominador == fracao.denominador &&
                positiva == fracao.positiva;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(numerador, denominador, positiva);
    }
}

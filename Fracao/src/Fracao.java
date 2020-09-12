import java.util.Objects;

public class Fracao {

    public int numerador;
    public int denominador;
    public boolean positiva;
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

    /**
     * Funcao que trunca uma dizima infinita.
     *
     * @param numerador
     * @param denominador
     * @param precisao
     * @return retorna a dizima truncada com a precisao dada
     */
    public double truncaDizima(int numerador, int denominador, int precisao) {
        double n = (double) (numerador * (Math.pow(10, precisao)));
        double divisaoTruncada = (double) Math.floor(n/denominador);
        return (double) (divisaoTruncada/(Math.pow(10, precisao)));
    }

    /**
     * Recebe um double e extrai as suas n casas decimais.
     *
     * @param numero
     * @return retorna um inteiro correspondente as casas decimais do double dado
     */
    public String obtemParteDecimal(double numero)
    {
        String num = String.valueOf(numero);
        return num.substring(2);
    }

    /**
     * Retorna uma fração que é equivalente a esta fração (this),
     * e que é irredutível (numerador e denominador primos entre si)
     *
     * @return uma fração irredutível equivalente a esta
     */
    public Fracao getFracaoGeratriz()
    {
        int mdc = this.mdc(this.numerador, this.denominador);
        if(mdc == 1) return this;
        return new Fracao(this.numerador/mdc, this.denominador/mdc, positiva);
    }

    /**
     * Calcula o MDC entre dois numeros de forma recursiva usando Euclides.
     *
     * @param a um numero inteiro qualquer
     * @param b um numero inteiro qualquer
     * @return o maior divisor comum entre os dois numeros
     */
    public int mdc(int a, int b)
    {
        if (a % b == 0) return b;
        return mdc(b, a % b);
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

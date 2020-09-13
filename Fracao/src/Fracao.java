import java.util.Objects;

public class Fracao
{

    private final int numerador;
    private final int denominador;
    private boolean positiva;
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
        this.sinal = positiva? 1:-1;
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
     * Getter de denominador
     *
     * @return retorna o denominador da fracao
     */
    public int getDenominador()
    {
        return denominador;
    }

    /**
     * Getter de sinal
     *
     * @return retorna 1 se this for positiva, -1 caso contrario
     */
    public int getSinal()
    {
        return sinal;
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
     * Funcao que verifica fracoes nulas de acordo com seu numerador.
     *
     * @return true se this for uma fracao nula, false caso contrario
     */
    public boolean isFracaoNula()
    {
        return this.numerador == 0;
    }

    /**
     * Funcao que retorna o valor numerico da fracao dada, considerando seu sinal.
     *
     * @return um double com o valor numérico desta fração
     */
    public double getValorNumerico()
    {
        if(this.numerador == 0) return 0;
        return this.sinal*((double) this.numerador/ (double) this.denominador);
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
        return new Fracao(this.numerador/mdc, this.denominador/mdc, this.positiva);
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

    /**
     * Funcao que formata o display da fracao this
     *
     * @return String formatada
     */
    public String formataFracao()
    {
        return String.format("%d / %d", this.getSinal() * this.getNumerador(), this.getDenominador());
    }

    /**
     * Override de equals.
     * Estabelecendo a nova regra de comparacao de fracoes.
     * Duas fracoes sao consideradas iguais se o numerador, denominador e sinal da sua funcao geratriz forem iguais.
     *
     * @param object recebe um objeto generico
     * @return true se as funcoes forem iguais, false caso contrario
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (!(object instanceof Fracao)) return false;
        Fracao fracao = (Fracao) object;

        //Compara funcoes nulas e corrige seu sinal
        if(this.isFracaoNula() && fracao.isFracaoNula())
        {
            this.positiva = false;
            fracao.positiva = false;
            return true;
        }

        //Compara as funcoes de acordo com a nova regra
        return this.getFracaoGeratriz().getNumerador() == fracao.getFracaoGeratriz().getNumerador() &&
                this.getFracaoGeratriz().getDenominador() == fracao.getFracaoGeratriz().getDenominador() &&
                this.getFracaoGeratriz().isPositiva() == fracao.getFracaoGeratriz().isPositiva();
    }

    /**
     * Override de hashCode.
     * @return
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(numerador, denominador, positiva);
    }
}

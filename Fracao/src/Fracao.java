import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fracao {

    private int numerador;
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
     * Funcao que retorna o periodo de uma dizima infinita.
     *
     * @return o periodo de uma dizima
     */
    public int getPeriodo()
    {
        String parteDecimal = obtemParteDecimal(truncaDizima(numerador, denominador, 16));
        int periodo = (getDecimal(parteDecimal, 0));
        for(int i=0; i<parteDecimal.length()-1; i++)
        {
            if(periodo != getDecimal(parteDecimal, i+1)) {
                periodo = (int) ((periodo*10) + getDecimal(parteDecimal, i+1));
            }
            else break;
        }
        return periodo;
    }

    public int getPeriodo2()
    {
        String parteDecimal = obtemParteDecimal(truncaDizima(numerador, denominador, 16));
        String periodo = null;
        for(int i=0; i<parteDecimal.length()-1; i++)
        {
            if(i == 0) periodo = String.valueOf(parteDecimal.charAt(0));
            else if(i == 1 && parteDecimal.charAt(0) != parteDecimal.charAt(1)) periodo = parteDecimal.substring(0,2);
            else if(i == 2 && parteDecimal.substring(0,i) != parteDecimal.substring(i,i*2)) periodo = parteDecimal.substring(0,i+1);
            else break;
        }
        return Integer.parseInt(periodo);
    }

    /**
     * Funcao auxiliar que recebe um numero em forma de String e retorna o caractere na posicao i.
     *
     * @param numero
     * @param i
     * @return retorna o inteiro correspondente a posicao i da String dada
     */
    private int getDecimal(String numero, int i) {
        return Integer.parseInt(String.valueOf(numero.charAt(i)));
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
     * Funcao que conta a quantidade de digitos que do periodo da d
     *
     * @return quantidade de digitos do periodo de uma dizima
     */
    private int getNumDigitosPeriodo()
    {
        int periodo = this.getPeriodo();
        String numeroDeDigitos = String.valueOf(periodo);
        return numeroDeDigitos.length();
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

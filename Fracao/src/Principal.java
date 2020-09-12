public class Principal {
    public static void main(String[] args) {
        Fracao fracao = new Fracao(128,56, true);
        System.out.println(fracao.getNumerador());
        System.out.println(fracao.getDenominador());
        Fracao fracaoGeratriz = fracao.getFracaoGeratriz();
        System.out.println(fracaoGeratriz.getNumerador());
        System.out.println(fracaoGeratriz.getDenominador());
    }
}
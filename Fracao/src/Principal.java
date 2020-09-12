public class Principal {
    public static void main(String[] args) {
        Fracao fracao = new Fracao(29,90, true);
        System.out.println(fracao.getNumerador());
        System.out.println(fracao.getDenominador());
        Fracao fracaoGeratriz = fracao.getFracaoGeratriz();
        System.out.println(fracaoGeratriz.getNumerador());
        System.out.println(fracaoGeratriz.getDenominador());

        System.out.println(fracao.truncaDizima(fracao.getNumerador(), fracao.getDenominador(), 16));
        String num = String.valueOf(fracao.truncaDizima(fracao.getNumerador(), fracao.getDenominador(), 16));
        System.out.println(fracao.obtemParteDecimal(fracao.truncaDizima(fracao.getNumerador(), fracao.getDenominador(), 16)));
        System.out.println(fracao.getPeriodo2());
        String string = "abcdefgh";
        System.out.println(string.substring(0,2));
        System.out.println(string.substring(2,4));
    }
}
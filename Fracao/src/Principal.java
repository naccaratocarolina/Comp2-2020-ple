public class Principal
{
    public static void main(String[] args)
    {
        Fracao fracao1 = new Fracao(10,56, false);
        Fracao fracaoGeratriz = fracao1.getFracaoGeratriz();
        System.out.println(fracao1.formataFracao());
        System.out.println(fracaoGeratriz.formataFracao());
    }
}
public class Principal {
    public static void main(String[] args) {
        TuiterLite tuiterLite = new TuiterLite();
        Usuario usuario = new Usuario("Fulano", "blabla@email.com");
        tuiterLite.cadastrarUsuario("Fulano", "blabla@email.com");

        tuiterLite.getHashtagsDoTuiter();

        tuiterLite.tuitarAlgo(usuario, "#blabla asdasdas #outra!");
        tuiterLite.tuitarAlgo(usuario, "#blabla");
        System.out.println(tuiterLite.tuitarAlgo(usuario, "#outra #outra #outra"));
        Tuite tuite = new Tuite(usuario, "#blabla asdasdas #outra! #blabla");
        //System.out.println(tuiterLite.getHashtagMaisComum());
        System.out.println(usuario.getNumeroDeTuitesPostados());
    }
}
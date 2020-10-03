public class Principal {
    public static void main(String[] args) {
        TuiterLite tuiterLite = new TuiterLite();
        Usuario usuario = new Usuario("Fulano", "blabla@email.com");
        tuiterLite.cadastrarUsuario("Fulano", "blabla@email.com");

        tuiterLite.getHashtagsDoTuiter();

        //System.out.println(tuiterLite.registraHashtags(usuario, "#blabla asdasdas #outra! #blabla #blabla"));
        System.out.println(tuiterLite.tuitarAlgo(usuario, "#blabla asdasdas #outra! #blabla #blabla"));
        //System.out.println(tuiterLite.tuitarAlgo(usuario, "#outra #outra #outra"));
        //Tuite tuite = new Tuite(usuario, "#blabla asdasdas #outra! #blabla #blabla");
        System.out.println(tuiterLite.getHashtagMaisComum());
        System.out.println(tuiterLite.frequenciaDeHashtag);
        //System.out.println(usuario.getNumeroDeTuitesPostados());*/
    }
}
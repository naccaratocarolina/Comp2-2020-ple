import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class AlbumTest {
    private Album album;
    private final int TOTAL_FIGURINHAS = 200;
    private final int QUANT_FIGURINHAS_POR_PACOTE = 3;

    private Random random = new Random();

    /**
     * Método setUp que será chamado antes de cada teste ser executado.
     * Usaremos ele para criar um objeto Album.
     */
    @Before
    public void setUp() {
        album = new Album(TOTAL_FIGURINHAS, QUANT_FIGURINHAS_POR_PACOTE);
    }

    /**
     * Testa o recebimento de um pacotinho qualquer e preenchimento dessas figurinhas no album.
     */
    @Test
    public void testarRecebimentoPacotinhoQualquer() {
        Figurinha[] novoPacotinho = criarPacotinho(null);
        album.receberNovoPacotinho(novoPacotinho);
        assertEquals(1, album.getQuantDePacotinhosComprados());
        assertEquals(QUANT_FIGURINHAS_POR_PACOTE,
                album.getQuantFigurinhasColadas() + album.getQuantFigurinhasRepetidas());
        for(int i=0; i<QUANT_FIGURINHAS_POR_PACOTE; i++) {
            assertTrue(album.possuiFigurinhaColada(novoPacotinho[i]));
        }
    }

    /**
     * Testa o recebimento de figurinhas repetidas.
     */
    @Test
    public void testarRecebimentoFigurinhaRepedita() {
        int[] posicoes = new int[] {1, 1, 1};
        Figurinha[] primeiroPacotinho = criarPacotinho(posicoes);

        album.receberNovoPacotinho(primeiroPacotinho);
        assertEquals(1, album.getQuantFigurinhasColadas());
        assertEquals(QUANT_FIGURINHAS_POR_PACOTE - 1, album.getQuantFigurinhasRepetidas());
        assertTrue(album.possuiFigurinhaRepetida(1));
        assertTrue(album.possuiFigurinhaRepetida(primeiroPacotinho[0]));
        assertTrue(album.possuiFigurinhaRepetida(new Figurinha(1, "qualquerURL")));

        posicoes = new int[] {10, 23, 1};
        Figurinha[] segundoPacotinho = criarPacotinho(posicoes);

        album.receberNovoPacotinho(segundoPacotinho);
        assertEquals(3, album.getQuantFigurinhasColadas());
        assertEquals(3, album.getQuantFigurinhasRepetidas());
        assertTrue(album.possuiFigurinhaColada(10));
        assertTrue(album.possuiFigurinhaColada(23));
        assertFalse(album.possuiFigurinhaRepetida(10));
        assertFalse(album.possuiFigurinhaRepetida(23));
        assertTrue(album.possuiFigurinhaRepetida(1));

        assertEquals(2, album.getQuantDePacotinhosComprados());
    }

    /**
     * Testa o preenchimento automatido do album.
     * Se o album estiver com pelo menos 90% das figurinhas "coladas", esperamos que,
     * ao chamar a funcao encomendarFigurinhasRestantes(), o album esteja completo.
     */
    @Test
    public void testarPreenchimentoAutomaticoDasUltimasFigurinhas() {
        //Album ainda esta vazio - esperamos que nada aconteca
        album.encomendarFigurinhasRestantes();
        assertEquals(0, album.getQuantFigurinhasColadas());

        //Preenchemos 90% do album
        while (album.getQuantFigurinhasColadas() <
        TOTAL_FIGURINHAS * Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR) {
            Figurinha[] pacotinho = criarPacotinho(null);
            album.receberNovoPacotinho(pacotinho);
        }

        //Com o album 90% preenchido, encomendamos o restante das figurinhas
        //e esperamos que o album esteja completo
        album.encomendarFigurinhasRestantes();
        assertEquals(TOTAL_FIGURINHAS, album.getQuantFigurinhasColadas());
    }

    /**
     * Funcao que cria um pacotinho com a quantidade de figurinhas pre definidas.
     *
     * @param posicoesDesejadas array que contem as posicoes das figurinhas a serem criadas no pacotinho
     * @return o pacotinho recem criado
     */
    private Figurinha[] criarPacotinho(int[] posicoesDesejadas) {
        Figurinha[] novoPacotinho = new Figurinha[QUANT_FIGURINHAS_POR_PACOTE];
        for (int i = 0; i < QUANT_FIGURINHAS_POR_PACOTE; i++) {
            int posicaoDaFigurinha = posicoesDesejadas == null ? escolherPosicaoAleatoria() :
                    posicoesDesejadas[i];
            Figurinha figurinha = new Figurinha(posicaoDaFigurinha,
                    String.format("http://urlFakeDaFigurinha%d.jpg", posicaoDaFigurinha));
            novoPacotinho[i] = figurinha;
        }
        return novoPacotinho;
    }

    /**
     * Funcao que aleatoriza um inteiro.
     *
     * @return um inteiro aleatorio dentro do range do numero total de figurinhas + 1
     */
    private int escolherPosicaoAleatoria() {
        return random.nextInt(TOTAL_FIGURINHAS) + 1;
    }
}
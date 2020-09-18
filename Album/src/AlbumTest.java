import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class AlbumTest {
    private Album album;
    private final int TOTAL_FIGURINHAS = 200;
    private final int QUANT_FIGURINHAS_POR_PACOTE = 3;

    private Random random = new Random();

    @Before
    public void setUp() {
        album = new Album(TOTAL_FIGURINHAS, QUANT_FIGURINHAS_POR_PACOTE);
    }

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

    @Test
    public void testarRecebimentoFigurinhaRepedita() {
        int[] posicoes = new int[] {1, 1, 1};
        Figurinha[] primeiroPacotinho = criarPacotinho(posicoes);

        album.receberNovoPacotinho(primeiroPacotinho);
        assertEquals(1, album.getQuantFigurinhasColadas());
        assertEquals(QUANT_FIGURINHAS_POR_PACOTE - 1, album.getQuantFigurinhasRepetidas());
        assertTrue(album.possuiFigurinhaRepetida(1));
        assertTrue(album.possuiFigurinhaRepetida(primeiroPacotinho[0]));
        assertTrue(album.possuiFigurinhaRepetida(new Figurinha(1)));

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

    @Test
    public void testarPreenchimentoAutomaticoDasUltimasFigurinhas() {
        //Album ainda esta vazio - esperamos que nada aconteca
        album.encomendarFigurinhasRestantes();
        assertEquals(0, album.getQuantFigurinhasColadas());

        //Preenchemos 90% do album
        while (album.getQuantFigurinhasColadas() <=
        TOTAL_FIGURINHAS * Album.PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR) {
            Figurinha[] pacotinho = criarPacotinho(null);
            album.receberNovoPacotinho(pacotinho);
        }

        //Com o album 90% preenchido, encomendamos o restante das figurinhas
        //e esperamos que o album esteja completo
        album.encomendarFigurinhasRestantes();
        assertEquals(TOTAL_FIGURINHAS, album.getQuantFigurinhasColadas());
    }

    private Figurinha[] criarPacotinho(int[] posicoesDesejadas) {
        Figurinha[] novoPacotinho = new Figurinha[QUANT_FIGURINHAS_POR_PACOTE];
        for (int i = 0; i < QUANT_FIGURINHAS_POR_PACOTE; i++) {
            int posicaoDaFigurinha = posicoesDesejadas == null ? escolherPosicaoAleatoria() :
                    posicoesDesejadas[i];
            Figurinha figurinha = new Figurinha(posicaoDaFigurinha);
            novoPacotinho[i] = figurinha;
        }
        return novoPacotinho;
    }

    private int escolherPosicaoAleatoria() {
        return random.nextInt(TOTAL_FIGURINHAS) + 1;
    }
}
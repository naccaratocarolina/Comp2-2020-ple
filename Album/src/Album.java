import java.util.ArrayList;
import java.util.Objects;

public class Album {
    public static final float PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR = 0.9f;

    //Atributos de Album
    public int totalFigurinhas;
    public int quantFigurinhasPorPacotinho;

    //Array de figurinhas do album
    public Figurinha[] figurinhas;

    //Array de figurinhas repetidas
    private Figurinha[] figurinhasRepetidas;
    public int quantFigurinhasRepetidas;

    //Variavel que vai armazenar a quantidade de pacotinhos comprados
    public int quantDePacotinhosComprados;

    public Album(int totalFigurinhas, int quantFigurinhasPorPacotinho) {
        //Inicializa atributos passados no construtor
        this.totalFigurinhas = totalFigurinhas;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;

        //Inicializa um array vazio com o tamanho total de figurinhas do album
        this.figurinhas = new Figurinha[totalFigurinhas + 1];

        //Inicializa um array vazio de figurinhas repetidas com o tamanho total de figurinhas do album
        this.figurinhasRepetidas = new Figurinha[totalFigurinhas + 1];
        this.quantFigurinhasRepetidas = 0;

        //Quantidade inicial de pacotinhos eh zero
        this.quantFigurinhasPorPacotinho = 0;
    }

    public Figurinha[] getFigurinhas() {
        return figurinhas;
    }

    public int getTotalFigurinhas() {
        return totalFigurinhas;
    }

    public int getQuantFigurinhasPorPacotinho() {
        return quantFigurinhasPorPacotinho;
    }

    public int getQuantDePacotinhosComprados() {
        return quantDePacotinhosComprados;
    }

    public int getQuantFigurinhasColadas() {
        int quantFigurinhasColadas = 0;
        for(int i=0; i<this.totalFigurinhas; i++) {
            if(this.figurinhas[i] != null) quantFigurinhasColadas++;
        }
        return quantFigurinhasColadas;
    }

    public int getQuantFigurinhasRestantes() {
        return this.totalFigurinhas - this.getQuantFigurinhasColadas();
    }

    public int getQuantFigurinhasRepetidas() {
        return quantFigurinhasRepetidas;
    }

    /**
     * Método que recebe o número pré-definido de figurinhas por pacotinho e as sorteia aleatoriamente.
     */
    public void receberNovoPacotinho(Figurinha[] pacotinho) {
        //incrementa a variavel que armazena a quant de pacotinhos comprados
        this.quantDePacotinhosComprados++;
        //for each pacotinho as figurinha
        for(Figurinha figurinha : pacotinho) {
            this.colaFigurinhaNoAlbum(figurinha);
        }
    }

    public void colaFigurinhaNoAlbum(Figurinha figurinha) {
        //se a posicao da figurinha estiver vazia, cola a figurinha no album
        if(!this.possuiFigurinhaColada(figurinha.getPosicao())) {
            this.figurinhas[figurinha.getPosicao()] = figurinha;
        }
        //caso contrario, adiciona a mesma no array de figurinhas repetidas
        else {
            this.armazenaFigurinhaRepetida(figurinha);
        }
    }

    public void armazenaFigurinhaRepetida(Figurinha figurinha) {
        this.figurinhasRepetidas[figurinha.getPosicao()] = figurinha;
        this.quantFigurinhasRepetidas++;
    }

    public boolean possuiFigurinhaColada(int posicao) {
        return this.figurinhas[posicao] != null;
    }

    public boolean possuiFigurinhaColada(Figurinha figurinha) {  // overload
        return this.possuiFigurinhaColada(figurinha.getPosicao());
    }

    //isso pode dar problema
    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.figurinhasRepetidas[posicao] != null;
    }

    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {  // overload
        return this.possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    public float getPorcentagemDoAlbumPreenchida() {
        return (float) this.getQuantFigurinhasColadas()/ (float) this.totalFigurinhas;
    }

    public void encomendarFigurinhasRestantes() {
        if(this.getPorcentagemDoAlbumPreenchida() >= PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR) {
            for(int i=0; i<this.totalFigurinhas; i++) {
                if(this.figurinhas[i] == null) {
                    Figurinha figurinhaQueFalta = new Figurinha(i);
                    this.colaFigurinhaNoAlbum(figurinhaQueFalta);
                }
            }
        }
    }
}
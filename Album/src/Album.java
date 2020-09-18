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

    /**
     * Getter de figurinhas.
     *
     * @return array de figurinhas do album
     */
    public Figurinha[] getFigurinhas() {
        return figurinhas;
    }

    /**
     * Getter de totalFigurinhas.
     *
     * @return total de figurinhas do album
     */
    public int getTotalFigurinhas() {
        return totalFigurinhas;
    }

    /**
     * Getter de quantFigurinhasPorPacotinho.
     *
     * @return quantidade de figurinhas por pacotinho
     */
    public int getQuantFigurinhasPorPacotinho() {
        return quantFigurinhasPorPacotinho;
    }

    /**
     * Retorna a quantidade de pacotinhos comprados por determinado usuario.
     *
     * @return quantidade de pacotinhos comprados
     */
    public int getQuantDePacotinhosComprados() {
        return quantDePacotinhosComprados;
    }

    /**
     * Percorre o array de figurinhas do album e conta quais posicoes estao preenchidas.
     *
     * @return quantidade de figurinhas coladas
     */
    public int getQuantFigurinhasColadas() {
        int quantFigurinhasColadas = 0;
        for(int i=0; i<this.totalFigurinhas; i++) {
            if(this.figurinhas[i] != null) quantFigurinhasColadas++;
        }
        return quantFigurinhasColadas;
    }

    /**
     * Verifica quantas figurinhas faltam para o album estar completo.
     *
     * @return quantidade de figurinhas restantes
     */
    public int getQuantFigurinhasRestantes() {
        return this.totalFigurinhas - this.getQuantFigurinhasColadas();
    }

    /**
     * Getter de quantFigurinhasRepetidas.
     *
     * @return quantidade de figurinhas repetidas
     */
    public int getQuantFigurinhasRepetidas() {
        return quantFigurinhasRepetidas;
    }

    /**
     * Recebe um pacotinho de figurinhas e as "cola" no album.
     *
     * @param pacotinho de figurinhas
     */
    public void receberNovoPacotinho(Figurinha[] pacotinho) {
        //incrementa a variavel que armazena a quant de pacotinhos comprados
        this.quantDePacotinhosComprados++;
        //for each pacotinho as figurinha
        for(Figurinha figurinha : pacotinho) {
            this.colaFigurinhaNoAlbum(figurinha);
        }
    }

    /**
     * Recebe uma figurinha e verifica se a mesma ja foi "colada" no album.
     * Se a posicao dessa figurinha no array de figurinhas estiver vazia, "cola" essa figurinha no album.
     * Caso contrario, significa que a figurinha ja foi "colada". E, sendo assim, a armazena no array de
     * figurinhas repetidas.
     *
     * @param figurinha
     */
    private void colaFigurinhaNoAlbum(Figurinha figurinha) {
        //se a posicao da figurinha estiver vazia, cola a figurinha no album
        if(!this.possuiFigurinhaColada(figurinha.getPosicao())) {
            this.figurinhas[figurinha.getPosicao()] = figurinha;
        }
        //caso contrario, adiciona a mesma no array de figurinhas repetidas
        else {
            this.armazenaFigurinhaRepetida(figurinha);
        }
    }

    /**
     * Método auxiliar que armazena uma figurinha repetida no array de figurinhas repetidas.
     *
     * @param figurinhaRepetida figurinha repetida
     */
    private void armazenaFigurinhaRepetida(Figurinha figurinhaRepetida) {
        this.figurinhasRepetidas[figurinhaRepetida.getPosicao()] = figurinhaRepetida;
        this.quantFigurinhasRepetidas++;
    }

    /**
     * Verifica se a figurinha na posicao dada ja foi colada.
     *
     * @param posicao da figurinha
     * @return true caso a figurinha na posicao dada ja tenha sido colada, false caso contrario
     */
    public boolean possuiFigurinhaColada(int posicao) {
        return this.figurinhas[posicao] != null;
    }

    /**
     * Overload de possuiFigurinhaColada(int posicao)
     * Verifica se a figurinha dada ja foi colada.
     * @param figurinha
     * @return true caso a figurinha dada ja tenha sido colada, false caso contrario
     */
    public boolean possuiFigurinhaColada(Figurinha figurinha) {
        return this.possuiFigurinhaColada(figurinha.getPosicao());
    }

    /**
     * Verifica se a figurinha na posicao dada eh repetida.
     *
     * @param posicao da figurinha
     * @return true caso a figurinha na posicao dada seja repetida, false caso contrario
     */
    public boolean possuiFigurinhaRepetida(int posicao) {
        return this.figurinhasRepetidas[posicao] != null;
    }

    /**
     * Overload
     * Verifica se a figurinha dada eh repetida.
     *
     * @param figurinha
     * @return true caso a figurinha dada seja repetida, false caso contrario
     */
    public boolean possuiFigurinhaRepetida(Figurinha figurinha) {
        return this.possuiFigurinhaRepetida(figurinha.getPosicao());
    }

    /**
     * Calcula a porcentagem de figurinhas preenchidas de um album.
     *
     * @return o percentual de figurinhas "coladas" no album
     */
    private float getPorcentagemDoAlbumPreenchida() {
        return (float) this.getQuantFigurinhasColadas()/ (float) this.totalFigurinhas;
    }

    /**
     * Método que preenche as figurinhas restantes.
     * Esse método só será chamado se o album possuir pelo menos 90% de figurinhas "coladas".
     */
    public void encomendarFigurinhasRestantes() {
        if(this.getPorcentagemDoAlbumPreenchida() >= PREENCHIMENTO_MINIMO_PARA_PERMITIR_AUTO_COMPLETAR) {
            for(int i=0; i<this.totalFigurinhas; i++) {
                if(this.figurinhas[i] == null) {
                    Figurinha figurinhaQueFalta = new Figurinha(i,
                            String.format("http://urlFakeDaFigurinha%d.jpg", i));
                    this.colaFigurinhaNoAlbum(figurinhaQueFalta);
                }
            }
        }
    }
}
import java.util.Objects;

public class Figurinha {
    public int posicao;
    public String urlImagem;

    /**
     * Construtor de Figurinha.
     *
     * @param posicao que a figurinha ocupa no album
     * @param urlImagem correspondente a figurinha
     */
    public Figurinha(int posicao, String urlImagem) {
        this.posicao = posicao;
        this.urlImagem = urlImagem;
    }

    /**
     * Getter para posicao.
     *
     * @return posicao que a figurinha ocupa
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     * Setter de posicao.
     *
     * @param posicao da figurinha
     */
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     * Getter de urlImagem.
     *
     * @return url da imagem correspondente a figurinha
     */
    public String getUrlImagem() {
        return urlImagem;
    }

    /**
     * Setter de urlImagem.
     *
     * @param urlImagem da figurinha
     */
    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    /**
     * Override do metodo equals.
     * Duas figurinhas sao iguais se ocuparem a mesma posicao no album, independente da url de sua imagem.
     *
     * @param object, que sera uma figurinha
     * @return true se as posicoes de ambas figurinhas forem iguais, false caso contrario
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Figurinha)) return false;
        Figurinha figurinha = (Figurinha) object;
        return this.getPosicao() == figurinha.getPosicao();
    }
}

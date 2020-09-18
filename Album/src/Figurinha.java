import java.util.Objects;

public class Figurinha {
    public int posicao;
    public String urlImagem;

    public Figurinha(int posicao) {
        this.posicao = posicao;
        this.urlImagem = String.format("http://urlFakeDaFigurinha%d.jpg", posicao));
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Figurinha)) return false;
        Figurinha figurinha = (Figurinha) object;
        return this.getPosicao() == figurinha.getPosicao();
    }
}

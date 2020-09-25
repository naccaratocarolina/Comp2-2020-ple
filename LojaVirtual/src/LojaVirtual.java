import java.util.ArrayList;

public class LojaVirtual {
    private ArrayList<Produto> produtosDoEstoque;
    private int tamanhoEstoque;

    public LojaVirtual() {
        this.produtosDoEstoque = new ArrayList<Produto>();
        this.tamanhoEstoque = 0;
    }

    public ArrayList<Produto> getProdutosDoEstoque() {
        return produtosDoEstoque;
    }

    public void setProdutosDoEstoque(ArrayList<Produto> produtosDoEstoque) {
        this.produtosDoEstoque = produtosDoEstoque;
    }

    public int getTamanhoEstoque() {
        return tamanhoEstoque;
    }

    public void setTamanhoEstoque(int tamanhoEstoque) {
        this.tamanhoEstoque = tamanhoEstoque;
    }

    public void incluirProdutoNoEstoque(Produto produto, int quantidade) {
        if(produto.getQuantEmEstoque() >= quantidade) {
            if(!verificaProdutoNoEstoque(produto)) {
                this.produtosDoEstoque.add(produto);
            }
            this.atualizaEstoque("+", produto, quantidade);
        }
    }

    private boolean verificaProdutoNoEstoque(Produto produto) {
        for(Produto itemNoEstoque : this.produtosDoEstoque) {
            if(itemNoEstoque == produto) return true;
        }
        return false;
    }

    private void atualizaEstoque(String acao, Produto produto, int quantidade) {
        switch (acao) {
            case "+":
                produto.setQuantEmEstoque(produto.getQuantEmEstoque() + quantidade);
                this.tamanhoEstoque += quantidade;
                break;
            case "-":
                produto.setQuantEmEstoque(produto.getQuantEmEstoque() - quantidade);
                this.tamanhoEstoque -= quantidade;
                break;
        }
    }

    public String efetuarVenda(Produto produto, int quantidade) {
        if(verificaProdutoNoEstoque(produto) && produto.getQuantEmEstoque() >= quantidade) {

        }
        return "";
    }

    private void receberPagamento() {
        
    }
}

import java.util.ArrayList;

public class LojaVirtual {
    private ArrayList<Produto> produtosDoEstoque;
    private int tamanhoEstoque;
    private float totalValorVendas;

    private ArrayList<String> vendas;

    public LojaVirtual() {
        this.produtosDoEstoque = new ArrayList<Produto>();
        this.vendas = new ArrayList<String>();
        //Adicionando a Header do Historico de Vendas
        String header = String.format("Id - Preco Unitário - Quantidade vendida - Valor total da venda");
        this.vendas.add(header);
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

    public float getTotalValorVendas() {
        return totalValorVendas;
    }

    public void setTotalValorVendas(float totalValorVendas) {
        this.totalValorVendas = totalValorVendas;
    }

    public void incluirProdutoNoEstoque(Produto produto, int quantidade) {
        //Caso o produto nao esteja registrado no estoque, o registra
        if(!verificaProdutoNoEstoque(produto)) {
            this.produtosDoEstoque.add(produto);
        }
        //Atualiza o estoque
        this.atualizaEstoque("+", produto, quantidade);
    }

    public String efetuarVenda(Produto produto, int quantidade) {
        if(verificaProdutoNoEstoque(produto) && produto.getQuantEmEstoque() >= quantidade &&
                this.receberPagamento(produto.getPrecoEmReais() * quantidade)) {
            //atualiza o estoque
            this.atualizaEstoque("-", produto, quantidade);
            //gera o recibo da compra
            return this.gerarRecibo(produto, quantidade);
        }
        return "";
    }

    public String printarHistoricoDeVendas() {
        StringBuilder historicoDeVendas = new StringBuilder();
        //Caso nenhuma venda tenha sido realizada ainda

        if(this.vendas.size() == 0) return historicoDeVendas.toString();
        //Percorre o array de vendas e armazena em historidoDeVendas
        for (String venda : this.vendas) historicoDeVendas.append(venda).append("\n");
        return historicoDeVendas.toString();
    }

    private boolean verificaProdutoNoEstoque(Produto produto) {
        for(Produto itemNoEstoque : this.produtosDoEstoque) {
            if(itemNoEstoque.getId() == produto.getId()) return true;
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

    private boolean receberPagamento(float valor) {
        this.totalValorVendas += valor;
        return true;
    }

    private String gerarRecibo(Produto produto, int quantidade) {
        String novaVenda = String.format(produto.getId() + " - " + produto.getPrecoEmReais() + " - " +
                quantidade + " - " + produto.getPrecoEmReais() * quantidade);
        this.vendas.add(novaVenda);
        return novaVenda;
    }
}
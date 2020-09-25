import java.util.ArrayList;

public class LojaVirtual {
    private String nomeDaLoja;

    //Array que armazena os produtos do estoque
    private ArrayList<Produto> produtosDoEstoque;

    //Quantidade de produtos no estoque
    private int tamanhoEstoque;

    //Variavel que armazena o valor total da venda de x quantidades de um produto
    private float totalValorVendas;

    //Array que armazena todas as vendas realizadas por uma Loja Virtual
    private ArrayList<String> vendas;

    /**
     * Construtor de LojaVirtual.
     */
    public LojaVirtual(String nomeDaLoja) {
        this.nomeDaLoja = nomeDaLoja;
        this.produtosDoEstoque = new ArrayList<Produto>();
        this.vendas = new ArrayList<String>();
        //Adicionando a Header do Historico de Vendas
        String header = String.format("Id - Preco Unitário - Quantidade vendida - Valor total da venda");
        this.vendas.add(header);
    }

    /**
     * Getter de produtosDoEstoque
     *
     * @return array de produtos registrados no estoque
     */
    public ArrayList<Produto> getProdutosDoEstoque() {
        return produtosDoEstoque;
    }

    /**
     * Setter de produtosDoEstoque.
     *
     * @param produtosDoEstoque array de produtos registrados no estoque
     */
    public void setProdutosDoEstoque(ArrayList<Produto> produtosDoEstoque) {
        this.produtosDoEstoque = produtosDoEstoque;
    }

    /**
     * Getter de tamanhoEstoque.
     *
     * @return quantidade total de produtos no estoque
     */
    public int getTamanhoEstoque() {
        return tamanhoEstoque;
    }

    /**
     * Setter de tamanhoEstoque.
     *
     * @param tamanhoEstoque quantidade total de produtos no estoque
     */
    public void setTamanhoEstoque(int tamanhoEstoque) {
        this.tamanhoEstoque = tamanhoEstoque;
    }

    /**
     * Getter de totalValorVendas.
     *
     * @return valor total da venda de x quantidades de um produto
     */
    public float getTotalValorVendas() {
        return totalValorVendas;
    }

    /**
     * Setter para totalValorVendas.
     *
     * @param totalValorVendas valor total da venda de x quantidades de um produto
     */
    public void setTotalValorVendas(float totalValorVendas) {
        this.totalValorVendas = totalValorVendas;
    }

    /**
     * Funcao que recebe um produto (que pode ser do tipo Produto, Livro ou Roupa)
     * e a quantidade desse produto a ser adicionada no estoque.
     * Caso o produto ainda nao tenha sido registrado no estoque, o faz.
     * Em seguida, atualiza o estoque com a quantidade adicionada.
     *
     * @param produto produto a ser adicionado no estoque
     * @param quantidade quantidade desse produto
     */
    public void incluirProdutoNoEstoque(Produto produto, int quantidade) {
        //Caso o produto nao esteja registrado no estoque, o registra
        if(!verificaProdutoNoEstoque(produto)) {
            this.produtosDoEstoque.add(produto);
        }
        //Atualiza o estoque
        this.atualizaEstoque("+", produto, quantidade);
    }

    /**
     * Para que uma venda seja efetuada, a funcao primeiro verifica se o produto dado
     * esta registrado no estoque, se a loja possui a quantidade a ser vendida em estoque e
     * se o pagamento foi recebido com sucesso.
     * Isso garantido, a funcao efetuarVenda atualiza o estoque e gera um recibo para a compra.
     *
     * @param produto produto a ser vendido
     * @param quantidade quantidade desse produto a ser vendido
     * @return o recibo da compra, se as condicoes forem atendidas, uma string vazia caso contrario
     */
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

    /**
     * A funcao printarHistoricoDeVendas formata o array de vendas, do qual armazena todas
     * as vendas realizadas pela loja, em uma string.
     *
     * @return string com o historico de vendas da loja
     */
    public String printarHistoricoDeVendas() {
        StringBuilder historicoDeVendas = new StringBuilder();
        //Caso nenhuma venda tenha sido realizada ainda

        if(this.vendas.size() == 0) return historicoDeVendas.toString();
        //Percorre o array de vendas e armazena em historidoDeVendas
        for (String venda : this.vendas) historicoDeVendas.append(venda).append("\n");
        return historicoDeVendas.toString();
    }

    /**
     * Verifica se o produto dado esta contido no estoque.
     *
     * @param produto produto que se deseja verificar
     * @return true se o produto dado ja estiver registrado no estoque, false caso contrario
     */
    private boolean verificaProdutoNoEstoque(Produto produto) {
        for(Produto itemNoEstoque : this.produtosDoEstoque) {
            if(itemNoEstoque.getId() == produto.getId()) return true;
        }
        return false;
    }

    /**
     * Funcao que atualiza o estoque dada a acao que se deseja realizar.
     * Se a funcao for chamada com "+", entende-se que se deseja acrescentar dada quantidade ao estoque.
     * Analogamente, se acao for igual a "-", o estoque eh decrementado de quantidade.
     *
     * @param acao "+" para adicionar a quantidade ao estoque, "-" para decrementar dada quantidade do estoque
     * @param produto produto que esta sendo acrescentado ou vendido
     * @param quantidade quantidade do produto
     */
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

    /**
     * Funcao que recebe o valor total da compra e incrementa a variavel totalValorVendas.
     *
     * @param valor valor da compra
     * @return true, que indica que o pagamento foi realizado com sucesso
     */
    private boolean receberPagamento(float valor) {
        this.totalValorVendas += valor;
        return true;
    }

    /**
     * Funcao que retorna o recibo de uma compra, dado o produto e a quantidade do mesmo.
     *
     * @param produto produto que foi comprado
     * @param quantidade quantidade do produto que foi comprado
     * @return o recibo da compra, indicando o id do produto, preco unitario, quantidade vendida e valor total da compra
     */
    private String gerarRecibo(Produto produto, int quantidade) {
        String novaVenda = String.format(produto.getId() + " - " + produto.getPrecoEmReais() + " - " +
                quantidade + " - " + produto.getPrecoEmReais() * quantidade);
        this.vendas.add(novaVenda);
        return novaVenda;
    }
}
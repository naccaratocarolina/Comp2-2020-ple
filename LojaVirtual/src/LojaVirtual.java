import java.util.ArrayList;

public class LojaVirtual {
    //Nome da Loja
    private final String nomeDaLoja;

    //Array que armazena os produtos do estoque
    private ArrayList<Produto> produtosDoEstoque;

    //Quantidade de produtos no estoque
    private int tamanhoEstoque;

    //Variavel que armazena o valor total da venda de x quantidades de um produto
    private float totalValorVenda;

    //Array que armazena todas as vendas realizadas por uma Loja Virtual
    private ArrayList<String> reciboDasVendas;

    /**
     * Construtor de LojaVirtual.
     */
    public LojaVirtual(String nomeDaLoja) {
        this.nomeDaLoja = nomeDaLoja;
        this.produtosDoEstoque = new ArrayList<Produto>();
        this.reciboDasVendas = new ArrayList<String>();
    }

    /**
     * Getter de nomeDaLoja.
     *
     * @return nome da loja virtual
     */
    public String getNomeDaLoja() {
        return nomeDaLoja;
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
     * Getter de reciboDasVendas.
     *
     * @return array que armazena o recibo das vendas realizadas
     */
    public ArrayList<String> getReciboDasVendas() {
        return reciboDasVendas;
    }

    /**
     * Setter de reciboDasVendas.
     *
     * @param reciboDasVendas array que armazena o recibo das vendas realizadas
     */
    public void setReciboDasVendas(ArrayList<String> reciboDasVendas) {
        this.reciboDasVendas = reciboDasVendas;
    }

    /**
     * Getter de totalValorVenda.
     *
     * @return valor total da venda de x quantidades vendida de um produto
     */
    public float getTotalValorVenda() {
        return totalValorVenda;
    }

    /**
     * Setter de totalValorVenda.
     *
     * @param totalValorVenda valor total da venda de x quantidades vendida de um produto
     */
    public void setTotalValorVenda(float totalValorVenda) {
        this.totalValorVenda = totalValorVenda;
    }

    /**
     * Funcao que verifica a quantidade de dado produto em estoque.
     *
     * @param produto
     * @return quantidade total disponivel em estoque deste produto
     */
    private int getQuantDoProdutoNoEstoque(Produto produto) {
        //Se o produto estiver "registrado" no array de produtos em estoque
        if(this.verificaProdutoNoEstoque(produto)) return produto.getQuantEmEstoque();
        //Caso contrario, lancar uma excecao
        return 0;
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
        if(verificaProdutoNoEstoque(produto) && getQuantDoProdutoNoEstoque(produto) >= quantidade &&
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

        if(this.reciboDasVendas.size() == 0) return historicoDeVendas.toString().toString();
        //Percorre o array de vendas e armazena em historidoDeVendas
        for (String venda : this.reciboDasVendas) historicoDeVendas.append(venda).append("\n");
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
            if(itemNoEstoque.equals(produto)) return true;
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
     * Funcao que recebe o valor total da compra e incrementa a variavel totalValorVenda.
     *
     * @param valor valor da compra
     * @return true, que indica que o pagamento foi realizado com sucesso
     */
    private boolean receberPagamento(float valor) {
        this.totalValorVenda += valor;
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
        String novaVenda = produto.toString() + "\nQuantidade vendida: " +
                quantidade + "\nValor total da compra: " + produto.getPrecoEmReais() * quantidade;
        this.reciboDasVendas.add(novaVenda);
        return novaVenda;
    }
}
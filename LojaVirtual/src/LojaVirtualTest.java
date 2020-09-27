import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class LojaVirtualTest {
    private static final int FLOAT_DELTA = 0;

    //Inicializando a loja virtual
    private LojaVirtual loja;

    //Inicializando o produto e seus atributos
    private Produto produto;
    private float precoProduto;
    private int quantEmEstoqueProduto;

    //Inicializando o livro e seus atributos
    private Livro livro;
    private float precoLivro;
    private int quantEmEstoqueLivro;

    /**
     * Metodo setUp que sera executado antes de cada funcao de teste.
     */
    @Before
    public void setUp() {
        //Criando a Loja Virtual
        loja = new LojaVirtual("Minha Loja Virtual");

        //Criando o Produto
        quantEmEstoqueProduto = 5;
        precoProduto = 10;
        produto = new Produto(10, precoProduto, "Categoria 1", quantEmEstoqueProduto);

        //Criando o Livro
        quantEmEstoqueLivro = 10;
        precoLivro = 50;
        livro = new Livro(20, precoLivro, "Categoria 2", quantEmEstoqueLivro, "Titulo", "Autor", 2020, 500);
    }

    /**
     * Testa a inclusao de dada quantidade de um produto no estoque.
     */
    @Test
    public void testarIncluirProdutoNoEstoque() {
        //Quantidade inicial no estoque eh zero
        assertEquals("Esperamos que assim que criarmos uma loja, o seu estoque esteja vazio.",
                0, loja.getTamanhoEstoque());

        //Incluir os produtos no estoque
        loja.incluirProdutoNoEstoque(produto, quantEmEstoqueProduto);
        loja.incluirProdutoNoEstoque(livro, quantEmEstoqueLivro);

        //Total da quantidade de produtos tem que ser igual ao output da funcao que verifica o estoque
        assertEquals("O tamanho do estoque deve ser o somatorio da quantidade de produtos que foram adicionados.",
                quantEmEstoqueProduto + quantEmEstoqueLivro, loja.getTamanhoEstoque());

        loja.incluirProdutoNoEstoque(livro, 2);
        assertEquals("Se o produto ja estiver registrado no estoque, esperamos que ele nao seja acrescentado no array de produtos no estoque.",
                2, loja.getProdutosDoEstoque().size());

        assertEquals("Apos adicionarmos mais produtos no estoque, esperamos que a quantidade em estoque desse produto seja incrementada.",
                2*quantEmEstoqueLivro + 2, livro.getQuantEmEstoque());
    }

    /**
     * Testa uma venda com sucesso.
     */
    @Test
    public void testarEfetuarVenda() {
        loja.incluirProdutoNoEstoque(livro, quantEmEstoqueLivro);
        loja.efetuarVenda(livro, 2);
        assertEquals("O valor total da venda deve ser a quantidade vezes o preco unitario do produto.",
                2 * precoLivro, loja.getTotalValorVenda(), FLOAT_DELTA);
        assertEquals("Apos a venda, o tamanho disponivel no estoque desse produto deve ser decrementado da quantidade vendida.",
                quantEmEstoqueLivro - 2, loja.getTamanhoEstoque());
        assertEquals("O formato do recibo retornado pela funcao deve ter um formato especifico.",
                geraReciboFormatado(livro, 2), loja.efetuarVenda(livro, 2));
    }

    /**
     * Funcao que testa a tentativa de venda de um produto do qual a quantidade informada nao contem no estoque.
     */
    @Test
    public void testarVendaDeProdutoForaDoEstoque() {
        loja.incluirProdutoNoEstoque(livro, 3);
        loja.efetuarVenda(livro, 100);
        assertEquals("Como desejamos vender uma quantidade nao disponivel no estoque, esperamos que nada aconteca.",
                0, loja.getTotalValorVenda(), FLOAT_DELTA);
        assertEquals("Como desejamos vender uma quantidade nao disponivel no estoque, esperamos que a funcao retorne uma string vazia.",
                "", loja.efetuarVenda(livro, 100));
    }

    /**
     * Funcao que verifica se o historico de uma sequencia de vendas está sendo printado corretamente.
     */
    @Test
    public void testarPrintarHistoricoDeVendas() {
        loja.incluirProdutoNoEstoque(livro, quantEmEstoqueLivro);
        loja.incluirProdutoNoEstoque(produto, quantEmEstoqueProduto);
        loja.efetuarVenda(livro, quantEmEstoqueLivro);
        loja.efetuarVenda(produto, quantEmEstoqueProduto);
        assertEquals("Esperamos que a funcao printe o historico de vendas no formato correto, especificado na funcao gerarReciboFormatado.",
                geraReciboFormatado(livro, quantEmEstoqueLivro) + "\n" + geraReciboFormatado(produto, quantEmEstoqueProduto) + "\n",
                loja.printarHistoricoDeVendas());
    }

    /**
     * Funcao que testa a nova regra (override) da funcao Equals de Produto.
     */
    @Test
    public void testarEqualsDeProduto() {
        Livro novoLivro = new Livro(20, precoLivro, "Categoria 2", quantEmEstoqueLivro, "Titulo", "Autor", 2020, 500);
        assertNotEquals("Dois produtos sao iguais somente se possuirem o mesmo ID, independente dos outros atributos.",
                novoLivro, livro);
    }

    /**
     * Funcao que testa a nova regra (override) da funcao ToString de Produto
     */
    @Test
    public void testarToStringDeProduto() {
        assertEquals("Esperamos que o toString da classe Produto e suas subclasses tenham um formato especifico.",
                "ID do produto: " + livro.getId() + "\nPreço unitário em Reais: " + livro.getPrecoEmReais(),
                livro.toString());
    }

    /**
     * Funcao auxiliar que cria um recibo da forma que esperamos que ele seja retornado pela LojaVirtual.
     * A regra do recibo eh a seguinte: (exemplo)
     * ID do produto: 1
     * Preço unitário em Reais: 10
     * Quantidade vendida: 2
     * Valor total da compra: 10*2
     *
     * @param produto produto que esta sendo vendido
     * @param quantidade quantidade desse produto
     * @return recibo formatado com informacoes da compra do produto
     */
    private String geraReciboFormatado(Produto produto, int quantidade) {
        return  "ID do produto: " + produto.getId() +
                "\nPreço unitário em Reais: " + produto.getPrecoEmReais() +
                "\nQuantidade vendida: " + quantidade +
                "\nValor total da compra: " + produto.getPrecoEmReais() * quantidade;
    }
}
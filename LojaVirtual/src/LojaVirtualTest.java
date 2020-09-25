import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LojaVirtualTest {
    private static final int FLOAT_DELTA = 0;

    //Inicializando a loja virtual
    private LojaVirtual loja;

    //Inicializando o produto e seus atributos
    private Produto produto;
    private float precoProduto;
    private String categoriaProduto;
    private int quantEmEstoqueProduto;

    //Inicializando o livro e seus atributos
    private Livro livro;
    private int pesoLivro;
    private float precoLivro;
    private String categoriaLivro;
    private int quantEmEstoqueLivro;
    private String titulo;
    private String autor;
    private int anoDePublicacao;
    private int numDePaginas;

    @Before
    public void setUp() {
        //Criando a Loja Virtual
        loja = new LojaVirtual();

        //Criando o Produto
        int pesoProduto = 10;
        precoProduto = 10;
        categoriaProduto = "Categoria 1";
        quantEmEstoqueProduto = 5;
        produto = new Produto(pesoProduto, precoProduto, categoriaProduto, quantEmEstoqueProduto);

        //Criando o Livro
        pesoLivro = 20;
        precoLivro = 50;
        categoriaLivro = "Categoria 2";
        quantEmEstoqueLivro = 10;
        titulo = "Titulo";
        autor = "Autor";
        anoDePublicacao = 2020;
        numDePaginas = 500;
        livro = new Livro(pesoLivro, precoLivro, categoriaLivro, quantEmEstoqueLivro, titulo, autor, anoDePublicacao, numDePaginas);
    }

    @Test
    public void testarIncluirProdutoNoEstoque() {
        //Quantidade inicial no estoque eh zero
        assertEquals(0, loja.getTamanhoEstoque());

        //Incluir os produtos no estoque
        loja.incluirProdutoNoEstoque(produto, quantEmEstoqueProduto);
        loja.incluirProdutoNoEstoque(livro, quantEmEstoqueLivro);

        //Total da quantidade de produtos tem que ser igual ao output da funcao que verifica o estoque
        assertEquals(quantEmEstoqueProduto + quantEmEstoqueLivro, loja.getTamanhoEstoque());
    }

    @Test
    public void testarEfetuarVenda() {
        loja.incluirProdutoNoEstoque(livro, quantEmEstoqueLivro);
        loja.efetuarVenda(livro, 2);
        assertEquals(2*precoLivro, loja.getTotalValorVendas(), FLOAT_DELTA);
        assertEquals(quantEmEstoqueLivro - 2, loja.getTamanhoEstoque());
    }

    @Test
    public void testarVendaDeProdutoForaDoEstoque() {
        loja.incluirProdutoNoEstoque(livro, 3);
        loja.efetuarVenda(livro, 100);
        assertEquals(0, loja.getTotalValorVendas(), FLOAT_DELTA);
    }

    @Test
    public void testarPrintarHistoricoDeVendas() {
        loja.incluirProdutoNoEstoque(livro, quantEmEstoqueLivro);
        loja.incluirProdutoNoEstoque(produto, quantEmEstoqueProduto);
        loja.efetuarVenda(livro, quantEmEstoqueLivro);
        loja.efetuarVenda(produto, quantEmEstoqueProduto);
        assertEquals(String.format("Id - Preco Unitário - Quantidade vendida - Valor total da venda\n" +
                livro.getId() + " - " + livro.getPrecoEmReais() + " - " + quantEmEstoqueLivro + " - " + livro.getPrecoEmReais() * quantEmEstoqueLivro + "\n" +
                produto.getId() + " - " + produto.getPrecoEmReais() + " - " + quantEmEstoqueProduto + " - " + produto.getPrecoEmReais() * quantEmEstoqueProduto + "\n"),
                loja.printarHistoricoDeVendas());
    }
}
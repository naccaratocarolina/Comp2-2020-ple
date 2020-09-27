public class Principal {
    public static void main(String[] args) {
        LojaVirtual loja = new LojaVirtual("Minha Loja Virtual");
        Roupa blusa = new Roupa(10, 35, "Roupas femininas", 50, 'M', "preto");
        Roupa calca = new Roupa(50, 120, "Roupas femininas", 6, 'G', "branco");

        loja.incluirProdutoNoEstoque(blusa, 10);
        loja.incluirProdutoNoEstoque(calca, 50);
        loja.efetuarVenda(blusa, 5);
        loja.efetuarVenda(calca, 50);
        System.out.println(loja.printarHistoricoDeVendas());
        System.out.println(blusa.toString());
    }
}

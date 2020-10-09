import java.util.HashSet;
import java.util.List;

public class CalculadorIntersecaoEsperto extends CalculadorIntersecao<Integer> {

    @Override
    public int getQuantidadeElementosEmComum(List<Integer> lista1, List<Integer> lista2) {
        //Cria um HashSet com os elementos da lista1
        HashSet<Integer> hashset1 = new HashSet<Integer>();
        for (int elementoDaLista1 : lista1) hashset1.add(elementoDaLista1);

        //Cria um HashSet que percorre a lista2 e adiciona somente elementos em comum com a lista 1
        HashSet<Integer> hashsetIntersecao = new HashSet<Integer>();
        for(int elementoDaLista2 : lista2) {
            if(hashset1.contains(elementoDaLista2)) hashsetIntersecao.add(elementoDaLista2);
        }

        //Retorna a contagem de elementos do HashSet de Intersecao
        return hashsetIntersecao.size();
    }
}

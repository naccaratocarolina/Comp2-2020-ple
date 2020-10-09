import java.util.HashSet;
import java.util.List;

public class CalculadorIntersecaoEsperto extends CalculadorIntersecao<Integer> {

    @Override
    public int getQuantidadeElementosEmComum(List<Integer> lista1, List<Integer> lista2) {
        HashSet<Integer> hashset1 = new HashSet<Integer>();
        for (int elementoDaLista1 : lista1) hashset1.add(elementoDaLista1);

        HashSet<Integer> hashset2 = new HashSet<Integer>();
        for(int elementoDaLista2 : lista2) {
            if(hashset1.contains(elementoDaLista2)) hashset2.add(elementoDaLista2);
        }

        return hashset2.size();
    }
}

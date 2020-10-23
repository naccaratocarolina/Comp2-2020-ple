import java.io.*;
import java.util.Scanner;

import Excecoes.*;

public class LendoDeArquivo {
    //Nome ou caminho do arquivo que se deseja trabalhar
    String caminhoDoArquivo;

    //Variavel que armazena a quantidade de linhas validas de dado arquivo
    int quantidadeLinhasValidas;

    //Variavel que armazena a quantidade de linhas invalidas de dado arquivo
    int quantidadeLinhasInvalidas;

    /**
     * Getter de caminhoDoArquivo.
     *
     * @return caminhoDoArquivo string com o caminho e nome do arquivo
     */
    public String getCaminhoDoArquivo() {
        return caminhoDoArquivo;
    }

    /**
     * Setter de caminhoDoArquivo.
     *
     * @param caminhoDoArquivo string com o caminho e nome do arquivo
     */
    public void setCaminhoDoArquivo(String caminhoDoArquivo) {
        this.caminhoDoArquivo = caminhoDoArquivo;
    }

    /**
     * Getter de quantidadeLinhasValidas.
     *
     * @return quantidadeLinhasValidas variavel que conta a quantidade de linhas validas de um dado arquivo
     */
    public int getQuantidadeLinhasValidas() {
        return quantidadeLinhasValidas;
    }

    /**
     * Setter de quantidadeLinhasValidas.
     *
     * @param quantidadeLinhasValidas variavel que conta a quantidade de linhas validas de um dado arquivo
     */
    public void setQuantidadeLinhasValidas(int quantidadeLinhasValidas) {
        this.quantidadeLinhasValidas = quantidadeLinhasValidas;
    }

    /**
     * Getter quantidadeLinhasInvalidas.
     *
     * @return quantidadeLinhasInvalidas variavel que conta a quantidade de linhas invalidas de um dado arquivo
     */
    public int getQuantidadeLinhasInvalidas() {
        return quantidadeLinhasInvalidas;
    }

    /**
     * Setter de quantidadeLinhasInvalidas.
     *
     * @param quantidadeLinhasInvalidas variavel que conta a quantidade de linhas invalidas de um dado arquivo
     */
    public void setQuantidadeLinhasInvalidas(int quantidadeLinhasInvalidas) {
        this.quantidadeLinhasInvalidas = quantidadeLinhasInvalidas;
    }

    /**
     * Funcao auxiliar que abre o arquivo e faz tratamento de erro.
     *
     * @param caminhoDoArquivo string com o caminho e nome do arquivo
     * @return arquivo arquivo recem aberto
     * @throws FileNotFoundException excecao que trata de erros ao abrir o arquivo
     */
    private File abreArquivo(String caminhoDoArquivo) throws FileNotFoundException {
        File arquivo = new File(caminhoDoArquivo);
        Scanner scanner = null;

        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo que voce digitou não existe!\nPor favor, tente de novo!\n");
        }

        return arquivo;
    }

    /**
     * Funcao que calcula a media aritmetica das medias contidas em um dado arquivo.
     * Essa funcao utiliza os modulos do Java BufferedReader e FileReader para iterar
     * pelas linhas do arquivo. Esses modulos utilizam como referencia o "\n" no final
     * de cada linha para definir as mesmas. A cada linha, a funcao tenta converter a
     * string contida na mesma para um float. Se o que estiver contido nessa linha nao
     * for um float, a funcao lanca uma excecao de NumberFormatException. Isto feito,
     * verifica se a quantidade de linhas validas eh superior a quantidade de linhas
     * invalidas. Se essa condicao nao for atendida, lanca a excecao ArquivoCorrompidoException.
     *
     * @param caminhoDoArquivo string que armazena o nome do arquivo
     * @return media das notas listadas em um arquivo
     * @throws IOException classe geral de exceções produzidas por operações de I / O com falha ou interrompidas
     * @throws NumberFormatException quando existe uma tentativa de converter uma string para um tipo numerico, mas a mesma nao tem o formato apropriado
     * @throws ArquivoCorrompidoException excecao lancada quando a quantidade de linhas invalidas de um arquivo eh maior do que a quantidade de linhas validas
     */
    public float calculaMedia(String caminhoDoArquivo)
            throws IOException, NumberFormatException, ArquivoCorrompidoException {
        File arquivo = this.abreArquivo(caminhoDoArquivo);
        float somaDasNotas = 0;
        int quantidadeDeLinhasDoArquivo = 0;

        //Itera pelas linhas do arquivo
        BufferedReader iteradorLinhasArquivo = new BufferedReader(new FileReader(arquivo));
        String linhaDoArquivo;
        while ((linhaDoArquivo = iteradorLinhasArquivo.readLine()) != null) {
            try {
                quantidadeDeLinhasDoArquivo++;
                somaDasNotas += Float.parseFloat(linhaDoArquivo);
            } catch (NumberFormatException e) {
                this.quantidadeLinhasInvalidas++;
            }
        }

        //Verifica se a quantidade de linhas validas eh maior que a quantidade de linhas invalidas
        //Caso contrario, lanca a excecao ArquivoCorrompidoException
        this.quantidadeLinhasValidas = quantidadeDeLinhasDoArquivo - this.quantidadeLinhasInvalidas;
        if(this.quantidadeLinhasInvalidas > this.quantidadeLinhasValidas) throw new ArquivoCorrompidoException();

        //Retorna a media aritmetica das notas contidas no arquivo dado
        return somaDasNotas/this.quantidadeLinhasValidas;
    }
}

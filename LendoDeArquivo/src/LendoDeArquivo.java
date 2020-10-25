import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

import Excecoes.*;

public class LendoDeArquivo {
    //Variavel que armazena todas as linhas do arquivo (inclusive linhas invalidas)
    private final ArrayList<String> linhasDoArquivo;

    //Variavel que armazena somente as linhas validas no formato DRE (key) = nota (value)
    private final Map<String, Float> linhasValidas;

    //Variavel que armazena a quantidade de linhas invalidas de um arquivo
    private int quantLinhasInvalidas;

    //Variavel do tipo ResultadosTurma associado ao arquivo que esta sendo lido
    private final ResultadosTurma resultadosTurma;

    public LendoDeArquivo() {
        this.linhasDoArquivo = new ArrayList<String>();
        this.linhasValidas = new HashMap<String, Float>();
        this.resultadosTurma = new ResultadosTurma(this);
    }

    /**
     * Getter de linhasDoArquivo.
     *
     * @return armazena todas as linhas do arquivo (inclusive linhas invalidas)
     */
    public ArrayList<String> getLinhasDoArquivo() {
        return linhasDoArquivo;
    }

    /**
     * Getter de linhasValidas.
     *
     * @return armazena somente as linhas validas no formato DRE (key) = nota (value)
     */
    public Map<String, Float> getLinhasValidas() {
        return linhasValidas;
    }

    /**
     * Getter de resultadosTurma.
     *
     * @return ResultadosTurma associado ao arquivo que esta sendo lido
     */
    public ResultadosTurma getResultadosTurma() {
        return resultadosTurma;
    }

    /**
     * Funcao auxiliar que recebe o nome de um arquivo e salva todas as linhas do mesmo em uma variavel.
     * Utiliza o medoto readAllLines da classe Files para acessar essa informacao.
     *
     * @param nomeDoArquivo caminho do arquivo que se deseja abrir
     * @throws IOException classe geral de exceções produzidas por operações de I / O com falha ou
     * interrompidasclasse geral de exceções produzidas por operações de I / O com falha ou interrompidas
     */
    private void armazenaLinhasArquivo(String nomeDoArquivo) throws IOException {
        Path caminhoDoArquivo = Paths.get(nomeDoArquivo);
        List <String> linhasDoArquivo = Files.readAllLines(caminhoDoArquivo, StandardCharsets.UTF_8);
        this.linhasDoArquivo.addAll(linhasDoArquivo);
    }

    /**
     * Funcao auxiliar que verifica se o arquivo dado esta no formato que esperamos, que eh:
     * DRE do aluno (String com 9 digitos) + espaço + nota do aluno (float).
     * Todas as linhas desse arquivo que nao estiverem nesse formato especifico serao descartadas.
     * Essa verificacao foi feita atraves de excecoes. As linhas que estiverem no formato correto
     * sao adicionadas no array de linhas validas. Sempre que uma linha invalida eh identificada,
     * cai no catch que, por sua vez, incrementa a variavel quantLinhasInvalidas para fazer a
     * verificacao do arquivo corrompido posteriormente.
     */
    private void verificaFormatoArquivo() {
        for(String linhaDoArquivo : this.linhasDoArquivo) {
            try {
                String[] informacoesArquivo = linhaDoArquivo.split(" ");
                String DRE = informacoesArquivo[0];
                if(!(DRE.length() == 9)) throw new DreComFormatoIncorreto();
                Long.parseLong(DRE);
                float nota = Float.parseFloat(informacoesArquivo[1]);
                this.linhasValidas.put(DRE, nota);
            } catch (NumberFormatException | IndexOutOfBoundsException | DreComFormatoIncorreto e) {
                this.quantLinhasInvalidas++;
            }
        }
    }

    /**
     * Funcao auxiliar que verifica se um dado arquivo eh corrompido.
     * Um arquivo eh considerado corrompido se a quantidade de linhas invalidas for maior
     * que a quantidade de linhas validas. Se isso for verdade, lanca a excecao correspondente.
     *
     * @throws ArquivoCorrompidoException excecao lancada quando o arquivo esta corrompido
     */
    private void verificaSeArquivoEhCorrompido() throws ArquivoCorrompidoException {
        int quantidadeLinhasValidas = this.linhasDoArquivo.size() - this.quantLinhasInvalidas;
        if(this.quantLinhasInvalidas > quantidadeLinhasValidas) throw new ArquivoCorrompidoException();
    }

    /**
     * Funcao auxiliar que abre o arquivo e faz tratamento de erro.
     * Para fazer o tratamento de erro, ou seja, garantir que o arquivo dado esta no formato correto
     * e nao esta corrompido, chama as funcoes verificaFormatoArquivo e verificaSeArquivoEhCorrompido.
     * Isto feito, segue o procedimento normal para abrir um arquivo, e lanca a excecao FileNotFoundException
     * se o mesmo nao for encontrado.
     *
     * @param caminhoDoArquivo string com o caminho e nome do arquivo
     * @throws FileNotFoundException excecao que trata de erros ao abrir o arquivo
     * @throws ArquivoCorrompidoException excecao lancada quando a quantidade de linhas
     * invalidas de um arquivo eh maior do que a quantidade de linhas validas
     */
    private void abreArquivo(String caminhoDoArquivo) throws IOException, ArquivoCorrompidoException {
        this.armazenaLinhasArquivo(caminhoDoArquivo);
        this.verificaFormatoArquivo();
        this.verificaSeArquivoEhCorrompido();

        File arquivo = new File(caminhoDoArquivo);
        Scanner scanner = null;

        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException e) {
            System.out.println("O arquivo que voce digitou não existe!\nPor favor, tente de novo!\n");
        }
    }

    /**
     * Funcao que calcula a media da turma, dado o caminho de um arquivo. Esta funcao apenas abre
     * o arquivo digitado pelo usuario e acessa o objeto resultadosTurma correspondente ao arquivo
     * dado. Em seguida, printa os resultados da turma para o usuario.
     *
     * @param caminhoDoArquivo caminho do arquivo com as notas
     * @return resultados da turma, calculado atraves do arquivo dado
     * @throws IOException classe geral de exceções produzidas por operações de I / O com falha ou
     * interrompidasclasse geral de exceções produzidas por operações de I / O com falha ou interrompidas
     * @throws ArquivoCorrompidoException excecao lancada quando a quantidade de linhas
     * invalidas de um arquivo eh maior do que a quantidade de linhas validas
     */
    public ResultadosTurma calculaMedia(String caminhoDoArquivo) throws IOException, ArquivoCorrompidoException {
        this.abreArquivo(caminhoDoArquivo);
        System.out.println(this.resultadosTurma.geraResultadosDaTurma().toString());
        return this.resultadosTurma.geraResultadosDaTurma();
    }
}

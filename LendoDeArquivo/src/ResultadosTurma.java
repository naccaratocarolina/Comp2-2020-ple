import java.lang.reflect.Array;
import java.util.*;

public class ResultadosTurma {
    //Definindo a nota minima para que um aluno seja aprovado
    public static final float NOTA_PARA_SER_APROVADO = 7;

    //Arquivo com as notas dos alunos de determinada turma
    private LendoDeArquivo arquivoDeNotasDaTurma;
    //Informacoes retiradas deste arquivo
    private Map<String, Float> informacoesDoArquivo;

    //Array de alunos de determinada turma
    private final ArrayList<Aluno> alunosDaTurma;

    //Media aritmetica das notas de uma turma
    private float mediaDaTurma;

    //Quantidade de alunos aprovados em uma turma
    private int quantidadeAlunosAprovados;

    //Quantidade de alunos reprovados em uma turma
    private int quantidadeAlunosReprovados;

    //Aluno com a maior nota em uma turma
    private Aluno alunoComMaiorNota;

    public ResultadosTurma(LendoDeArquivo arquivoDeNotasDaTurma) {
        this.arquivoDeNotasDaTurma = arquivoDeNotasDaTurma;
        this.informacoesDoArquivo = this.arquivoDeNotasDaTurma.getLinhasValidas();
        this.alunosDaTurma = new ArrayList<Aluno>();
    }

    /**
     * Getter de alunosDaTurma.
     *
     * @return array de alunos cadastrados em uma turma
     */
    public ArrayList<Aluno> getAlunosDaTurma() {
        return alunosDaTurma;
    }

    /**
     * Getter de mediaDaTurma.
     *
     * @return media das notas dos alunos de uma turma
     */
    public float getMediaDaTurma() {
        return mediaDaTurma;
    }

    /**
     * Setter de mediaDaTurma.
     *
     * @param mediaDaTurma media das notas dos alunos de uma turma
     */
    public void setMediaDaTurma(float mediaDaTurma) {
        this.mediaDaTurma = mediaDaTurma;
    }

    /**
     * Getter de quantidadeAlunosAprovados.
     *
     * @return quantidade de alunos aprovados em uma turma
     */
    public int getQuantidadeAlunosAprovados() {
        return quantidadeAlunosAprovados;
    }

    /**
     * Setter de quantidadeAlunosAprovados.
     *
     * @param quantidadeAlunosAprovados quantidade de alunos aprovados em uma turma
     */
    public void setQuantidadeAlunosAprovados(int quantidadeAlunosAprovados) {
        this.quantidadeAlunosAprovados = quantidadeAlunosAprovados;
    }

    /**
     * Getter de quantidadeAlunosReprovados.
     *
     * @return quantidade de alunos reprovados em uma turma
     */
    public int getQuantidadeAlunosReprovados() {
        return quantidadeAlunosReprovados;
    }

    /**
     * Setter de quantidadeAlunosReprovados.
     *
     * @param quantidadeAlunosReprovados quantidade de alunos reprovados em uma turma
     */
    public void setQuantidadeAlunosReprovados(int quantidadeAlunosReprovados) {
        this.quantidadeAlunosReprovados = quantidadeAlunosReprovados;
    }

    /**
     * Getter de alunoComMaiorNota.
     *
     * @return aluno com a maior nota em uma turma
     */
    public Aluno getAlunoComMaiorNota() {
        return alunoComMaiorNota;
    }

    /**
     * Setter de alunoComMaiorNota.
     *
     * @param alunoComMaiorNota aluno com a maior nota em uma turma
     */
    public void setAlunoComMaiorNota(Aluno alunoComMaiorNota) {
        this.alunoComMaiorNota = alunoComMaiorNota;
    }

    /**
     * Funcao que registra os alunos de uma turma.
     */
    private void registraAlunosDaTurma() {
        for(Map.Entry<String, Float> informacaoAluno : this.informacoesDoArquivo.entrySet()) {
            Aluno novoAluno = new Aluno(informacaoAluno.getKey(), informacaoAluno.getValue());
            this.alunosDaTurma.add(novoAluno);
        }
    }

    /**
     * Funcao que descobre o aluno de maior nota de uma turma.
     *
     * @return aluno com a maior nota em uma turma
     */
    private Aluno descobreAlunoComMaiorNota() {
        for(Aluno aluno : this.alunosDaTurma) {
            if(this.alunoComMaiorNota == null || aluno.getNota() > this.alunoComMaiorNota.getNota())
                this.alunoComMaiorNota = aluno;
        }
        return this.alunoComMaiorNota;
    }

    /**
     * Funcao que calcula a media aritmetica das notas dos alunos de uma turma.
     */
    private void calculaMedia() {
        float somaDasNotas = 0;
        for(Map.Entry<String, Float> informacaoAluno : this.informacoesDoArquivo.entrySet()) {
            somaDasNotas += informacaoAluno.getValue();
        }
        this.setMediaDaTurma(somaDasNotas / alunosDaTurma.size());
    }

    /**
     * Funcao que descobre a quantidade de alunos aprovados e reprovados de uma turma
     * em funcao da nota minima definida em NOTA_PARA_SER_APROVADO.
     */
    private void processaAlunosAprovadosEReprovados() {
        for(Aluno aluno : this.alunosDaTurma) {
            if(aluno.getNota() > NOTA_PARA_SER_APROVADO) {
                this.quantidadeAlunosAprovados++;
                aluno.setAprovado(true);
            }
            else {
                this.quantidadeAlunosReprovados++;
                aluno.setAprovado(false);
            }
        }
    }

    /**
     * Funcao que processo todas as funcoes criadas acima que trabalham informacoes desejadas
     * dos resultados de uma turma.
     *
     * @return o proprio objeto ResultadosTurma
     */
    public ResultadosTurma geraResultadosDaTurma() {
        this.registraAlunosDaTurma();
        this.descobreAlunoComMaiorNota();
        this.calculaMedia();
        this.processaAlunosAprovadosEReprovados();
        return this;
    }

    /**
     * Override de toString da classe ResultadosTurma.
     *
     * @return string formatada com as informacoes mais relevantes a respeito do resultados de uma turma
     */
    @Override
    public String toString() {
        String alunoESuasNotas = "";
        for (Aluno aluno : this.alunosDaTurma) {
            String novaLinha = String.format("DRE do aluno: %s = Nota: %.2f\n", aluno.getDRE(), aluno.getNota());
            alunoESuasNotas += novaLinha;
        }

        return "Resultados da turma: " +
                "\n" + alunoESuasNotas +
                "Média da turma = " + String.format("%.2f", this.getMediaDaTurma()) +
                "\nQuantidade de alunos aprovados = " + quantidadeAlunosAprovados +
                "\nQuantidade de alunos reprovados = " + quantidadeAlunosReprovados +
                "\nDRE do aluno com maior nota = " + this.getAlunoComMaiorNota().getDRE();
    }
}
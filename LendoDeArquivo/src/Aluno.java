public class Aluno {
    //String de 9 digitos que representa o numero unico de identificacao de um aluno
    private final String DRE;

    //Nota que este aluno tirou em uma determinada turma
    private final float nota;

    //Variavel que armazena se este aluno foi aprovado ou nao em uma determinada turma
    public boolean aprovado;

    public Aluno(String DRE, float nota) {
        this.DRE = DRE;
        this.nota = nota;
    }

    /**
     * Getter de DRE.
     *
     * @return numero unico de identificacao de um aluno
     */
    public String getDRE() {
        return DRE;
    }

    /**
     * Getter se nota.
     *
     * @return nota que este aluno tirou em uma determinada turma
     */
    public float getNota() {
        return nota;
    }

    /**
     * Getter de aprovado.
     *
     * @return true se o aluno for aprovado, false caso contrario
     */
    public boolean isAprovado() {
        return aprovado;
    }

    /**
     * Setter de aprovado.
     * @param aprovado variacel que armazena se este aluno foi aprovado ou nao em uma determinada turma
     */
    public void setAprovado(boolean aprovado) {
        this.aprovado = aprovado;
    }
}

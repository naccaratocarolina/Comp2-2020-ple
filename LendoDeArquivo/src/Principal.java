import Excecoes.ArquivoCorrompidoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, NumberFormatException, ArquivoCorrompidoException {
        //Inicializa a variavel da classe LendoDeArquivo
        LendoDeArquivo lendoDeArquivo  = new LendoDeArquivo();

        //Realiza a interacao com o usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do arquivo e aperte ENTER: ");
        String caminhoDoArquivo = scanner.nextLine();

        //Calcula a media
        float media = lendoDeArquivo.calculaMedia(caminhoDoArquivo);

        //Formata a resposta para o usuario
        System.out.println(media);
    }
}

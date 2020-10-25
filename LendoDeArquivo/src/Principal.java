import Excecoes.ArquivoCorrompidoException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, NumberFormatException, ArquivoCorrompidoException {
        //Inicializa a variavel da classe LendoDeArquivo
        LendoDeArquivo lendoDeArquivo  = new LendoDeArquivo();

        //Realiza a interacao com o usuario
        //Se quiser selecionar um arquivo nao corrompido para testes: notasValidas.txt
        //Se quiser selecionar um arquivo corrompido para testes: notasInvalidas.txt
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o nome do arquivo e aperte ENTER: ");
        String caminhoDoArquivo = scanner.nextLine();

        //Calcula a media e printa o resultado para o usuario
        try{
            lendoDeArquivo.calculaMedia(caminhoDoArquivo);
        } catch (FileNotFoundException | NoSuchFileException e) {
            System.out.println("Esse arquivo não existe! Tente novamente.\n");
        } catch (ArquivoCorrompidoException e) {
            System.out.println("O arquivo selecionado está corrompido. Tente novamente.\n");
        }
    }
}

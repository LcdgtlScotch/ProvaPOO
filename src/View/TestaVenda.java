package View;

import Controller.BancoDados;
import java.util.Scanner;

public class TestaVenda {

    public static void main(String[] args) {
        BancoDados bancoDados = new BancoDados();
        Scanner scanner = new Scanner(System.in);
        bancoDados.conecta();
        int opcao;

        if (bancoDados.estaConectado()) {
            do {
                System.out.println("1-Listar ");
                System.out.println("2-Comprar ");
                System.out.println("0-Sair");
                System.out.print("Selecione uma opcao: ");
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1 ->
                        bancoDados.listar();
                    case 2 -> {
                        System.out.print("Qual o id do Produto? ");
                        int item = scanner.nextInt();
                        System.out.print("Quantidade: ");
                        int quant = scanner.nextInt();
                        bancoDados.editar(item, quant);
                    }
                    case 0 -> {
                        break;
                    }
                    default -> {
                    }

                }
            } while (opcao != 0);

        } else {
            System.out.println("Banco N�O OK");
        }
    }
}

import java.util.Scanner;

import controle.Operador;
import controle.Conversor;
import controle.Entrada;
import controle.Validador;
import model.Fila;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("--- Avaliador de expressões matemáticas ---");
		System.out.println("Operadores: + - * / ^   |   Use parênteses e números inteiros");
		System.out.println("Digite '0' para encerrar\n");

		while (true) {
			System.out.print("Expressão: ");
			if (!scan.hasNextLine()) {
				break;
			}
			String expressao = scan.nextLine().trim();

			if (expressao.equalsIgnoreCase("0")) {
				break;
			}

			String erro = Validador.validar(expressao);
			if (erro != null) {
				System.out.println("Expressão inválida: " + erro + "\n");
				continue;
			}

			try {
				Fila<Entrada> posfixa = Conversor.infixaParaPosfixa(expressao);
				System.out.println("Pós-fixa: " + Conversor.posfixaComoTexto(posfixa));
				System.out.println("Valor: " + Operador.formatar(Operador.operarPosfixa(posfixa)) + "\n");
			} catch (ArithmeticException e) {
				System.out.println("Erro de cálculo: " + e.getMessage() + "\n");
			}
		}
		scan.close();
	}
}
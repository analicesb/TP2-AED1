package controle;

import model.Fila;
import model.Pilha;

//Resolve uma expressão pós-fixa usando uma pilha de operandos
public class Operador {

    //Avalia a fila pós-fixa (a fila é consumida)
    public static double operarPosfixa(Fila<Entrada> posfixa) {
        Pilha<Double> pilha = new Pilha<Double>();

        while (!posfixa.isEmpty()) {
            Entrada e = posfixa.desenfileirar();

            if (e.tipo == Entrada.Tipo.NUMERO) {
                pilha.empilhar(e.valor);
            } else {
                double b = pilha.desempilhar(); //segundo operando
                double a = pilha.desempilhar(); //primeiro operando
                pilha.empilhar(calcular(e.simbolo, a, b));
            }
        }
        return pilha.desempilhar();
    }

    private static double calcular(char op, double a, double b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Divisão por zero.");
                }
                return a / b;
            case '^': return Math.pow(a, b);
            default:
                throw new RuntimeException("Operador desconhecido: " + op);
        }
    }


    //Mostra inteiros sem casas decimais (13 em vez de 13.0)
    public static String formatar(double v) {
        if (v == Math.rint(v) && Math.abs(v) < 1e15) {
            return String.valueOf((long) v);
        }
        return String.valueOf(v);
    }
}

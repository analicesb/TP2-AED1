package controle;

import model.Fila;
import model.Pilha;

//Converte uma expressão infixa (já validada) para pós-fixa usando uma pilha de operadores
//A precedência é detectada comparando o operador lido com o operador do topo da pilha
public class Conversor {

    //Transforma o texto em uma fila de entradas em ordem infixa
    public static Fila<Entrada> separar(String expressao) {
        Fila<Entrada> entradas = new Fila<Entrada>();
        int inicio = 0;
        int tamExpressao = expressao.length();
        while (inicio < tamExpressao) {
            char c = expressao.charAt(inicio);
            if (c >= '0' && c <= '9') {
                double valor = 0;

                //Transforma char em double
                while (inicio < tamExpressao && expressao.charAt(inicio) >= '0' && expressao.charAt(inicio) <= '9') {
                    valor = valor * 10 + (expressao.charAt(inicio) - '0');
                    inicio++;
                }
                entradas.enfileirar(Entrada.numero(valor));
                continue;
            }
            if (c == '(') {
                entradas.enfileirar(Entrada.abrePar());
            } else if (c == ')') {
                entradas.enfileirar(Entrada.fechaPar());
            } else if (Validador.ehOperador(c)) {
                entradas.enfileirar(Entrada.operador(c));
            }
            inicio++; //espaços são ignorados
        }
        return entradas;
    }

    //Converte infixa para pós-fixa, retornando a fila de saída
    public static Fila<Entrada> infixaParaPosfixa(String expr) {
        Fila<Entrada> entrada = separar(expr);
        Fila<Entrada> saida = new Fila<Entrada>();
        Pilha<Entrada> operadores = new Pilha<Entrada>();

        while (!entrada.isEmpty()) {
            Entrada e = entrada.desenfileirar();

            switch (e.tipo) {
                case NUMERO:
                    saida.enfileirar(e);
                    break;

                case ABRE_PAR:
                    operadores.empilhar(e);
                    break;

                case FECHA_PAR:
                    //desempilha até achar o '(' correspondente
                    while (operadores.getTopo().tipo != Entrada.Tipo.ABRE_PAR) {
                        saida.enfileirar(operadores.desempilhar());
                    }
                    operadores.desempilhar(); //descarta o '('
                    break;

                case OPERADOR:
                    while (!operadores.isEmpty()
                            && operadores.getTopo().tipo == Entrada.Tipo.OPERADOR
                            && deveDesempilhar(operadores.getTopo(), e)) {
                        saida.enfileirar(operadores.desempilhar());
                    }
                    operadores.empilhar(e);
                    break;
            }
        }

        while (!operadores.isEmpty()) {
            saida.enfileirar(operadores.desempilhar());
        }
        return saida;
    }

    //Esquerda para direita: desempilha se a precedência do topo for >= à do lido
    //Exponenciação (direita para esquerda): só desempilha se a do topo for maior
    private static boolean deveDesempilhar(Entrada topo, Entrada lido) {
        if (topo.precedencia() > lido.precedencia()) {
            return true;
        }
        return topo.precedencia() == lido.precedencia() && !lido.associativoDireita();
    }

    //Texto da fila pós-fixa
    public static String posfixaComoTexto(Fila<Entrada> posfixa) {
        StringBuilder sb = new StringBuilder();
        int n = posfixa.size();
        for (int i = 0; i < n; i++) {
            Entrada e = posfixa.desenfileirar();
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(e.toString());
            posfixa.enfileirar(e); //volta para o final da fila
        }
        return sb.toString();
    }
}

package controle;

//Verifica se uma expressão infixa é sintaticamente válida
//Dois estados: esperando um número (ou "(") ou esperando um operador (ou ")")
public class Validador {

    //Retorna null se a expressão for válida ou a mensagem de erro
    public static String validar(String expressao) {
        if (expressao == null) {
            return "Expressão vazia";
        }
        boolean esperaOperando = true;
        int parAbertos = 0; //parênteses abertos
        int inicio = 0;
        int tamExpressao = expressao.length();

        while (inicio < tamExpressao) {
            char c = expressao.charAt(inicio);

            if (c == ' ' || c == '\t') {
                inicio++;
                continue;
            }

            if (c >= '0' && c <= '9') {
                if (!esperaOperando) {
                    return "Operando inesperado na posição " + (inicio + 1) + ".";
                }
                while (inicio < tamExpressao && expressao.charAt(inicio) >= '0' && expressao.charAt(inicio) <= '9') {
                    inicio++;
                }
                esperaOperando = false;
                continue;
            }

            if (c == '(') {
                if (!esperaOperando) {
                    return "Parêntese de abertura inesperado na posição " + (inicio + 1);
                }
                parAbertos++;
            } else if (c == ')') {
                if (esperaOperando) {
                    return "Parêntese de fechamento inesperado na posição " + (inicio + 1);
                }
                parAbertos--;
                if (parAbertos < 0) {
                    return "Parêntese de fechamento sem abertura na posição " + (inicio + 1);
                }
            } else if (ehOperador(c)) {
                if (esperaOperando) {
                    return "Operador '" + c + "' inesperado na posição " + (inicio + 1);
                }
                esperaOperando = true;
            } else {
                return "Caractere inválido '" + c + "' na posição " + (inicio + 1);
            }
            inicio++;
        }

        if (esperaOperando) {
            return "Expressão incompleta ou vazia";
        }
        if (parAbertos != 0) {
            return "Parênteses não balanceados";
        }
        return null;
    }

    public static boolean ehOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
}
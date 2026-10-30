package controle;

public class Entrada {

    public enum Tipo { NUMERO, OPERADOR, ABRE_PAR, FECHA_PAR }

    public final Tipo tipo;
    public final double valor;
    public final char simbolo;

    private Entrada(Tipo tipo, double valor, char simbolo) {
        this.tipo = tipo;
        this.valor = valor;
        this.simbolo = simbolo;
    }

    public static Entrada numero(double valor) {
        return new Entrada(Tipo.NUMERO, valor, ' ');
    }

    public static Entrada operador(char simbolo) {
        return new Entrada(Tipo.OPERADOR, 0, simbolo);
    }

    public static Entrada abrePar() {
        return new Entrada(Tipo.ABRE_PAR, 0, '(');
    }

    public static Entrada fechaPar() {
        return new Entrada(Tipo.FECHA_PAR, 0, ')');
    }

    // operadores
    public int precedencia() {
        switch (simbolo) {
            case '^': 
            	return 3;
            case '*':
            case '/': 
            	return 2;
            case '+':
            case '-': 
            	return 1;
            default:  return 0;
        }
    }

    // exponenciação 
    public boolean associativoDireita() {
        return simbolo == '^';
    }

    @Override
    public String toString() {
        if (tipo == Tipo.NUMERO) {
            return String.valueOf((long) valor);
        }
        return String.valueOf(simbolo);
    }
}
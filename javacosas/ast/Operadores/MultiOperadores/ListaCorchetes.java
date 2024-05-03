package ast.Operadores.MultiOperadores;
import ast.Operadores.Operador;

public class ListaCorchetes extends Operador {
    public final static String OPSTRING = "ListaCorchetes";
    public ListaCorchetes(int fila, int columna){
        super(fila, columna);
        this.inicializarTipado();
    }

    public String toString() {
        return ListaCorchetes.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicializarTipado'");
    }
}

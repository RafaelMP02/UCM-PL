package ast.Operadores.MultiOperadores;
import ast.Operadores.Operador;

public class ListaLlaves extends Operador {
    public final static String OPSTRING = "ListaLLaves";
    public ListaLlaves(){
        inicializarTipado();
    }

    public String toString() {
        return ListaLlaves.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicializarTipado'");
    }
}

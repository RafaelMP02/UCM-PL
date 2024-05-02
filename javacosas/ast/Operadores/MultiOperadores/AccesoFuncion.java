package ast.Operadores.MultiOperadores;
import ast.Operadores.Operador;

public class AccesoFuncion extends Operador {
    public final static String OPSTRING = "AccesoFunción";
    public AccesoFuncion(){
        this.inicializarTipado();
    }

    public String toString() {
        return AccesoFuncion.OPSTRING;
    }

    @Override
    public void inicializarTipado() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'inicializarTipado'");
    }
}

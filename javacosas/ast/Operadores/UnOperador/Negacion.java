package ast.Operadores.UnOperador;

import ast.KindE;

public class Negacion extends OperadorUn{
    /* Operador decremento */
    public Negacion(){
        exp = KindE.Negacion;
    }

    public String toString() {
        return "Negación";
    }
}

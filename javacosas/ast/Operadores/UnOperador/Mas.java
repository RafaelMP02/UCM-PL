package ast.Operadores.UnOperador;

import ast.KindE;

public class Mas extends OperadorUn{
    /* Operador de números positivos */
    public Mas(){
        exp = KindE.Mas;
    }

    public String toString() {
        return "Más";
    }
}

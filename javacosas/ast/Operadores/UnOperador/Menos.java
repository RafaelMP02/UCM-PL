package ast.Operadores.UnOperador;

import ast.KindE;

public class Menos extends OperadorUn{
    /* Operador de números negativos */
    public Menos(){
        exp = KindE.Menos;
    }

    public String toString() {
        return "Menos";
    }
}

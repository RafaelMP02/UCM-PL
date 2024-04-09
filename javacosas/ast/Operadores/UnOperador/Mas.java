package ast.Operadores.UnOperador;

import ast.KindE;

public class Mas extends OperadorUn{
    public Mas(){
        exp = KindE.Mas;
    }

    public String toString() {
        return "Mas";
    }
}

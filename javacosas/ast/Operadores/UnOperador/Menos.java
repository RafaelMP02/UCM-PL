package ast.Operadores.UnOperador;

import ast.KindE;

public class Menos extends OperadorUn{
    public Menos(){
        exp = KindE.Menos;
    }

    public String toString() {
        return "Menos";
    }
}

package ast.Operadores.UnOperador;

import ast.KindE;

public class Negacion extends OperadorUn{
    public Negacion(){
        exp = KindE.Negacion;
    }

    public String toString() {
        return "Negacion";
    }
}

package ast.Operadores.UnOperador;

import ast.KindE;

public class AccesoPuntero extends OperadorUn{
    public AccesoPuntero(){
        exp = KindE.AccesoPuntero;
    }

    public String toString() {
        return "AccesoPuntero";
    }
}

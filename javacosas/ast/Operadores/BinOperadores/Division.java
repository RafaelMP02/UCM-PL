package ast.Operadores.BinOperadores;

import ast.KindE;

public class Division extends OperadorBin{
    public Division(){
        exp = KindE.Division;
    }

    public String toString() {
        return "Division";
    }
}

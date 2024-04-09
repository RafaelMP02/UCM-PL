package ast.Operadores.BinOperadores;

import ast.KindE;

public class MayorIgual extends OperadorBin{
    public MayorIgual(){
        exp = KindE.MayorIgual;
    }

    public String toString() {
        return "MayorIgual";
    }
}

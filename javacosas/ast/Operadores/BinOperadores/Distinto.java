package ast.Operadores.BinOperadores;

import ast.KindE;

public class Distinto extends OperadorBin{

    public Distinto() {
        exp = KindE.Distinto;
    }

    public String toString() {
        return "Distinto";
    }
}

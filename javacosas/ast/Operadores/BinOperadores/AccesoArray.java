package ast.Operadores.BinOperadores;

import ast.KindE;

public class AccesoArray extends OperadorBin{
    public AccesoArray() {
        exp = KindE.AccesoArray;
    }

    @Override
    public String toString() {
        return "AccesoArray";
    }
}

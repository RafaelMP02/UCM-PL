package ast.Operadores.BinOperadores;

import ast.KindE;

public class Igual extends OperadorBin{
    public Igual(){
        exp = KindE.Igual;
    }

    public String toString() {
        return "Igual";
    }
}

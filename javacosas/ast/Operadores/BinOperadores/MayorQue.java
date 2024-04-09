package ast.Operadores.BinOperadores;

import ast.KindE;

public class MayorQue extends OperadorBin{
    public MayorQue(){
        exp = KindE.MayorQue;
    }

    public String toString() {
        return "MayorQue";
    }
}

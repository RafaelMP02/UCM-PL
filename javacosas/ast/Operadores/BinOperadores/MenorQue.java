package ast.Operadores.BinOperadores;

import ast.KindE;

public class MenorQue extends OperadorBin{
    public MenorQue(){
        exp = KindE.MenorQue;
    }

    public String toString() {
        return "MenorQue";
    }
}

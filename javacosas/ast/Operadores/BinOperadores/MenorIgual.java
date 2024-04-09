package ast.Operadores.BinOperadores;

import ast.KindE;

public class MenorIgual extends OperadorBin{
    public MenorIgual(){
        exp = KindE.MenorIgual;
    }

    public String toString() {
        return "MenorIgual";
    }
}

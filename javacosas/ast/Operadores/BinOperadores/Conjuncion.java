package ast.Operadores.BinOperadores;

import ast.KindE;

public class Conjuncion extends OperadorBin{
    public Conjuncion(){
        exp = KindE.Conjuncion;
    }

    public String toString() {
        return "Conjuncion";
    }
}

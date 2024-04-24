package ast.Operadores.BinOperadores;

import ast.KindE;

public class Disyuncion extends OperadorBin{

    public Disyuncion() {
        exp = KindE.Disyuncion;
    }

    public String toString() {
        return "Disyunción";
    }
}

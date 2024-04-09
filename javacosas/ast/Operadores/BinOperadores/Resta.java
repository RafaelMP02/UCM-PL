package ast.Operadores.BinOperadores;

import ast.KindE;

public class Resta extends OperadorBin{
    public Resta(){
        exp = KindE.Resta;
    }

    public String toString() {
        return "Resta";
    }
}

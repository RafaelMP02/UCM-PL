package ast.Operadores.BinOperadores;

import ast.KindE;

public class Suma extends OperadorBin{
    public Suma(){
        exp = KindE.Suma;
    }

    public String toString() {
        return "Suma";
    }
}

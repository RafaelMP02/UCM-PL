package ast.Operadores.BinOperadores;

import ast.KindE;

public class AccesoCampo extends OperadorBin{
    public AccesoCampo(){
        exp = KindE.AccesoCampo;
    }
    public String toString() {
        return "AccesoCampo";
    }
}

package ast.Operadores.BinOperadores;

import ast.KindE;

public class Producto extends OperadorBin{
    public Producto(){
        exp = KindE.Producto;
    }

    public String toString() {
        return "Producto";
    }
}

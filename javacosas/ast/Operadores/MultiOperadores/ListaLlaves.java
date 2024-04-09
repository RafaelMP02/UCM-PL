package ast.Operadores.MultiOperadores;

import ast.KindE;
import ast.Operadores.Operador;

public class ListaLlaves extends Operador {
    public ListaLlaves(){
        exp = KindE.ListaLLaves;
    }

    public String toString() {
        return "ListaLLaves";
    }
}

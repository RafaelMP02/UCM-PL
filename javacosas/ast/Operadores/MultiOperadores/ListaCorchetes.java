package ast.Operadores.MultiOperadores;

import ast.KindE;
import ast.Operadores.Operador;

public class ListaCorchetes extends Operador {
    public ListaCorchetes(){
        exp = KindE.ListaCorchetes;
    }

    public String toString() {
        return "ListaCorchetes";
    }
}

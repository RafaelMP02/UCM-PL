package ast.Operadores.MultiOperadores;

import ast.KindE;
import ast.Operadores.Operador;

public class AccesoFuncion extends Operador {

    public AccesoFuncion(){
        exp = KindE.AccesoFuncion;
    }

    public String toString() {
        return "AccesoFunción";
    }
}

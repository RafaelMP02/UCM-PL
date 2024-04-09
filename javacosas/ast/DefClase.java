package ast;

import ast.Metaoperadores.Ambito;

public class DefClase extends Definicion{
    Identificador nombre;
    Ambito ambito;

    public DefClase(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "CLASE " + nombre.toString() + ambito.toString();
    }
}

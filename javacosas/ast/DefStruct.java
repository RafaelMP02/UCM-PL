package ast;

import ast.Metaoperadores.Ambito;

public class DefStruct extends Definicion{
    Identificador nombre;
    Ambito ambito;

    public DefStruct(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "STRUCT " + nombre.toString() + ambito.toString();
    }
}

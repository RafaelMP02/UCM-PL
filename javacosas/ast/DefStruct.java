package ast;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;

public class DefStruct extends Definicion {
    /* Se definde el struct, es decir sus atributos. Pero no se instancia. */
    Identificador nombre;
    Ambito ambito;

    public DefStruct(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "STRUCT " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        
    }
}

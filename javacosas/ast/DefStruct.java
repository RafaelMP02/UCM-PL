package ast;

import java.util.Map;
import java.util.Set;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Clase;
import ast.Tipos.NodoTipo;
import ast.Tipos.Struct;
import ast.Tipos.TipoNuevo;
import ast.Vinculacion.Vinculacion;

public class DefStruct implements Definicion  {
    /* Se definde el struct, es decir sus atributos. Pero no se instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos. */
    Identificador nombre;
    Ambito ambito;
    private NodoTipo t;

    public DefStruct(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
        t = new Struct(nombre);
    }

    public String toString() {
        return " DEFSTRUCT " + nombre.toString() + ambito.toString();
    }

    @Override
    public Set<NodoTipo> type() {
        return null; //TODO
    }

    @Override
    public void bind(Vinculacion vinc) {
        ambito.bind(vinc);
        ((Clase) t).setCampos(vinc.cierraBloque()); //Guardan su tabla de símbolos al cerrarla en el nodo tipo
    }
}

package ast;

import java.util.HashMap;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;

public class DefClase implements Definicion, TipoNuevo{
    /* Define la clase, es decir, sus métodos y atributos. Pero no la instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos.*/
    Identificador nombre;
    Ambito ambito;
    boolean descendiendo;
    private HashMap<String, NodoAST> mapaCampos; //Almacena la tabla de símbolos del cuerpo de la clase

    public DefClase(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "CLASE " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        /* 
        Usaremos los nodos de tipo clase para acceder a sus campos. 
        Para ello, insertaremos un entrada especial en el mapa de vinculación que empiece por "clase".
         */
        vinc.insertaId("clase " + nombre.toString(), this);
        vinc.abreBloque();
        ambito.bind(vinc);
        mapaCampos = vinc.cierraBloque(); //Guardan su tabla de símbolos al cerrarla
    }

    @Override
    public HashMap<String, NodoAST> getCampos() {
        return this.mapaCampos;
    }
}

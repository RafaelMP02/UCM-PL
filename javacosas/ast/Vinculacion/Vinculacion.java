package ast.Vinculacion;

import errors.GestionErroresExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ast.NodoAST;
import ast.TipoNuevo;


public class Vinculacion {
    /*
    La tabla guardará pares (String, ASTNode), cuando busquemos un nodo lo haremos
    llamando a buscaId(id.toString()) (se llama al toString de manera automática)
    */
    private List<HashMap<String, NodoAST>> pilaDeTablas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente
    private GestionErroresExp errores;

    public Vinculacion () {
        this.pilaDeTablas = new ArrayList<HashMap<String, NodoAST>>();
        this.errores = new GestionErroresExp();
    }

    /* 
    Los que se encargan de abrir y cerrar ámbitos no son los MetaOperadores "{}", porque hay veces que no abren nuevo ámbito. 
    Ejemplo: una clase declara un ámbito global. Sus funciones y métodos se pueden acceder desde el ámbito al que pertenece la clase. 
    */

    public void abreBloque() {
        /*
        Añadimos una nueva tabla de símbolos porque se ha abierto un bloque.
        */
        pilaDeTablas.add(new HashMap<String, NodoAST>());
    }

    public HashMap<String, NodoAST> cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        */
        return pilaDeTablas.remove(pilaDeTablas.size() - 1);
    }

    public void insertaId(String id, NodoAST puntero) {
        /*
        Añadimos un par (id,puntero) a la tabla de símbolos del ámbito actual
         */
        pilaDeTablas.get(pilaDeTablas.size() - 1).put(id, puntero);
    }
    
    public List<NodoAST> buscaId (String id, int fila, int columna) {
        boolean encontrado = false;
        List<NodoAST> punteros = new ArrayList<>();
        /*
        Buscamos en la pila recorriéndola desde el final hasta el comienzo. 
        No paramos cuando lo encontramos pues podría haber sobrecarga. Devolvemos una lista de punteros.
        Esta función solo se invocará desde un nodo de tipo Identificador cuando no sea una declaración.
        */

        /*
        Mientras no lo hayamos encontrado y queden tablas de símbolos por recorrer, seguimos buscando
        Si ya lo hemos encontrado, no continuamos buscando, porque, por ocultación, las siguientes tablas no son visibles
        */
        for (int i = pilaDeTablas.size()-1; i >= 0 && !encontrado; i--) {
            HashMap<String,NodoAST> mapa = pilaDeTablas.get(i);
            for (HashMap.Entry<String, NodoAST> entry : mapa.entrySet()) {
                //Si existe una declaración con ese identificador, lo vinculamos
                if (entry.getKey().equals(id)) {
                    punteros.add(mapa.get(id));
                    encontrado = true;
                }
                //Si es un nodo de definición de struct o clae, accedemos a los campos de este y buscamos ahí. 
                // Estos nodos  especiales empiezan por "struct " o "clase "
                if (entry.getKey().startsWith("STRUCT ") || entry.getKey().startsWith("CLASE "))
                    if (explorarNodo(id, punteros, ((TipoNuevo) entry.getValue()).getCampos()))
                        encontrado = true;
            }
        }
        //Si no estaba en ninguna tabla, lanzamos error de vinculación
        if (!encontrado) {
            errores.errorVinculacion(fila, columna, id.toString());
        }
        return punteros;
    }


    private boolean explorarNodo (String id, List<NodoAST> punteros, HashMap<String,NodoAST> mapa){
        /* 
        Recorre un mapa de campos de un struct buscando la clave id y vinculándola en caso de que exista.
        Devuelve un booleano indicando si la ha encontrado o no.
        */
        boolean res = false;

        //Recorremos el mapa exactamente igual que en la función buscaId
        for (HashMap.Entry<String, NodoAST> entry : mapa.entrySet()) {
            if (entry.getKey().equals(id)) {
                punteros.add(mapa.get(id));
                res = true;
            }
            if (entry.getKey().startsWith("struct ") || entry.getKey().startsWith("clase "))
                if (explorarNodo(id, punteros, ((TipoNuevo) entry.getValue()).getCampos()))
                    res = true;
        }


        return res;
    }
}

package ast.Vinculacion;

import errors.GestionErroresExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ast.ASTNode;
import ast.Expresiones.Identificador;
import ast.Metaoperadores.CabecerAsig;

public class Vinculacion {
    /*
    La tabla guardará pares (String, ASTNode), cuando busquemos un nodo lo haremos
    llamando a buscaId(id.toString()) (se llama al toString de manera automática)
    */
    private List<HashMap<String, ASTNode>> pilaDeTablas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente
    private GestionErroresExp errores;

    public Vinculacion () {
        this.pilaDeTablas = new ArrayList<HashMap<String, ASTNode>>();
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
        pilaDeTablas.add(new HashMap<String, ASTNode>());
    }

    public HashMap<String, ASTNode> cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        */
        return pilaDeTablas.remove(pilaDeTablas.size() - 1);
    }

    public void insertaId(String id, ASTNode puntero) {
        /*
        Añadimos un par (id,puntero) a la tabla de símbolos del ámbito actual
         */
        pilaDeTablas.get(pilaDeTablas.size() - 1).put(id, puntero);
    }

    public List<ASTNode> buscaId (String id, int fila, int columna) {
        boolean encontrado = false;
        List<ASTNode> punteros = new ArrayList<>();
        /*
        Buscamos en la pila recorriéndola desde el final hasta el comienzo. 
        No paramos cuando lo encontramos pues podría haber sobrecarga. Devolvemos una lista de punteros.
        Esta función solo se invocará desde un nodo de tipo Identificador cuando no sea una declaración.
        */
        for (int i = pilaDeTablas.size()-1; i >= 0; i--) {
            if (pilaDeTablas.get(i).containsKey(id)) {
                punteros.add(pilaDeTablas.get(i).get(id));
                encontrado = true;
            }
            //TODO hay que ver qué hacer con las clases y los structs
        }
        //Si no estaba en ninguna tabla, lanzamos error de vinculación
        if (!encontrado) {
            errores.errorVinculacion(fila, columna, id.toString());
        }
        return punteros;
    }
}

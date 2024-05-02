package ast.Vinculacion;

import errors.GestionErroresExp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ast.NodoAST;
import ast.Declaracion;
import ast.Tipos.TipoNuevo;


public class Vinculacion {
    /*
    La tabla guardará pares (String, Set<NodoAST>), donde el String es el identificador y el set de NodoAST es el conjunto de declaraciones posibles de ese id
    (en el caso de que sea una función puede haber sobrecarga).
    Cuando busquemos un nodo lo haremos llamando a buscaId(id.toString()) (se llama al toString de manera automática).
    */
    private List<Map<String, Set<Declaracion>>> pilaDeTablas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente
    private GestionErroresExp errores;

    public Vinculacion () {
        this.pilaDeTablas = new ArrayList<>();
        this.pilaDeTablas.add(new HashMap<String, Set<Declaracion>>()); //Añadimos la tabla de símbolos inicial
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
        pilaDeTablas.add(new HashMap<String, Set<Declaracion>>());
    }

    public Map<String, Set<Declaracion>> cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        */
        return pilaDeTablas.remove(pilaDeTablas.size() - 1);
    }

    public void insertaId(String id, Declaracion puntero, int fila, int columna) {
        /*
        Intentamos añadir una entrada a la tabla de símbolos del ámbito actual.
         */
        Map<String,Set<Declaracion>> tabla_actual = pilaDeTablas.get(pilaDeTablas.size() - 1);
        //Si ya existe una entrada con ese id...
        if (tabla_actual.containsKey(id)) {
            Set<Declaracion> entrada = tabla_actual.get(id);
            Declaracion primerNodo = entrada.iterator().next();
            //Si cualquiera de los nodos de la entrada o el nodo que se va a insertar no admite sobrecarga, error
            if (!puntero.getTipo().admiteSobrecarga() || !primerNodo.getTipo().admiteSobrecarga())
                errores.errorYaDeclarado(fila, columna, id);
            //En otro caso añadimos un elemento al conjunto de nodos a los que apunta
            else 
                entrada.add(puntero);
        }
        //Si no existe una entrada con ese id, añadimos la entrada
        else 
            tabla_actual.put(id, Collections.singleton(puntero));
    }
    
    public Set<Declaracion> buscaId (String id, int fila, int columna) {
        boolean encontrado = false;
        Set<Declaracion> punteros = new HashSet<Declaracion>();
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
            Map<String,Set<Declaracion>> mapa = pilaDeTablas.get(i);
            if (mapa.containsKey(id))
                punteros = mapa.get(i);
        }
        //Si no estaba en ninguna tabla, lanzamos error de vinculación
        if (!encontrado) {
            errores.errorNoDeclarado(fila, columna, id.toString());
        }
        return punteros;
    }
}

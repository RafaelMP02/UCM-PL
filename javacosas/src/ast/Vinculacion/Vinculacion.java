package ast.Vinculacion;

import errors.GestionErroresExp;
import java_cup.production;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import ast.DecVariable;
import ast.Declaracion;
import ast.Definicion;
import ast.Expresiones.Identificador;
import ast.Tipos.NodoTipo;
import ast.Tipos.Parametrico;
import ast.Tipos.TipoError;
import ast.Tipos.TipoNuevo;


public class Vinculacion {
    /*
    La tabla guardará pares (String, Set<NodoAST>), donde el String es el identificador y el set de NodoAST es el conjunto de declaraciones posibles de ese id
    (en el caso de que sea una función puede haber sobrecarga).
    Cuando busquemos un nodo lo haremos llamando a buscaId(id.toString()) (se llama al toString de manera automática).
    */
    private LinkedList<LinkedList<LinkedHashMap<String, LinkedHashSet<Declaracion>>>> pDPDeTablas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente
    private LinkedList<LinkedList<Definicion>> pDPDeDefinicion;
    private LinkedList<LinkedHashMap<String, TipoNuevo>> pilaDeTipos;
    private GestionErroresExp errores;

    public Vinculacion () {
        this.pDPDeTablas = new LinkedList<>();
        this.pDPDeTablas.add(new LinkedList<LinkedHashMap<String, LinkedHashSet<Declaracion>>>());

        this.pDPDeDefinicion = new LinkedList<>();
        this.pDPDeDefinicion.add(new LinkedList<Definicion>());

        this.pilaDeTipos = new LinkedList<>();
        this.pilaDeTipos.add(new LinkedHashMap<String, TipoNuevo>());

        this.errores = new GestionErroresExp();
    }

    /* 
    Los que se encargan de abrir y cerrar ámbitos no son los MetaOperadores "{}", porque hay veces que no abren nuevo ámbito. 
    Ejemplo: una clase declara un ámbito global. Sus funciones y métodos se pueden acceder desde el ámbito al que pertenece la clase. 
    */
    
    public void abreAmbTNuevo(Definicion def) {
        pDPDeTablas.add(new LinkedList<LinkedHashMap<String, LinkedHashSet<Declaracion>>>());
        pDPDeTablas.getLast().add(pDPDeTablas.getFirst().getFirst());

        pDPDeDefinicion.getLast().add(def);
    } 

    public void cierraAmbTNuevo() {
        pDPDeTablas.removeLast();
        
        pDPDeDefinicion.getLast().removeLast();
    } 

    public void abreAmbFunc() {
        pDPDeTablas.add(new LinkedList<LinkedHashMap<String, LinkedHashSet<Declaracion>>>());
        pDPDeTablas.getLast().add(pDPDeTablas.getFirst().getFirst());
        if(pDPDeDefinicion.getLast().size() > 0 && !pDPDeDefinicion.getLast().isEmpty()) {
            pDPDeTablas.getLast().add(pDPDeDefinicion.getLast().getLast().getDecs());
        }
        pDPDeTablas.getLast().add(new LinkedHashMap<>());

        pDPDeDefinicion.add(new LinkedList<>());
    } 

    public void cierraAmbFunc() {
        pDPDeTablas.removeLast();
        pDPDeDefinicion.removeLast();
    } 

    public void abreBloque() {
        /* Añadimos una nueva tabla de símbolos porque se ha abierto un bloque.*/
        pDPDeTablas.getLast().add(new LinkedHashMap<String, LinkedHashSet<Declaracion>>());
        pilaDeTipos.add(new LinkedHashMap<String, TipoNuevo>());
    }

    public Map<String, LinkedHashSet<Declaracion>> cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        Se devuelve la tabla de símbolo para que cada ámbito la almacene (es útil para binding y tipado).
        */
        pilaDeTipos.removeLast();
        return pDPDeTablas.getLast().removeLast();
    }

    public void insertaId(String id, Declaracion puntero, int fila, int columna) {
        /*
        Intentamos añadir una entrada a la tabla de símbolos del ámbito actual.
         */
        Map<String,LinkedHashSet<Declaracion>> tabla_actual = pDPDeTablas.getLast().getLast();

        //Si ya existe una entrada con ese id...
        if (tabla_actual.containsKey(id)) {
            LinkedHashSet<Declaracion> entrada = tabla_actual.get(id);
            boolean correcto = true; //Variable booleana para controlar si la nueva declaración se puede añadir
            for(Declaracion dec : entrada) {
                //Si ninguno de los dos nodos admite sobrecarga (son dos declaraciones de variables)
                if (!puntero.getTipo().admiteSobrecarga() && !dec.getTipo().admiteSobrecarga()) {
                    errores.errIdYaDec(fila, columna, id);
                    correcto = false;
                    break;
                }
            }
            //Si después de comprobarlo con todas las declaraciones, lo podemos añadir, lo hacemos
            if (correcto)
                entrada.add(puntero);
        }
        
        //Si no existe una entrada con ese id, añadimos la entrada
        else 
            tabla_actual.put(id, new LinkedHashSet<Declaracion>(Arrays.asList(puntero)));
    }
    
    public LinkedHashSet<Declaracion> buscaId (String id, int fila, int columna) {
        boolean encontrado = false;
        boolean varEncontradaBloquePrevio = false;
        LinkedHashSet<Declaracion> punteros = new LinkedHashSet<Declaracion>();
        /*
        Buscamos en la pila recorriéndola desde el final hasta el comienzo. 
        No paramos cuando lo encontramos pues podría haber sobrecarga. Devolvemos una lista de punteros. Sim embargo, si en un bloque
        hemos encontrado una declaración de variable que coincide con el id, no buscaremos más declaraciones de variables en los bloques
        previos (ocultación).
        Esta función solo se invocará desde un nodo de tipo Identificador cuando no sea una declaración.
        */

        /*
        Mientras no lo hayamos encontrado y queden tablas de símbolos por recorrer, seguimos buscando
        Si ya lo hemos encontrado, no continuamos buscando, porque, por ocultación, las siguientes tablas no son visibles
        */
        for (int i = pDPDeTablas.getLast().size()-1; i >= 0; i--) {
            Map<String,LinkedHashSet<Declaracion>> mapa = pDPDeTablas.getLast().get(i);
            boolean varEncontradaBloque = false;
            if (mapa.containsKey(id)) {
                for (Declaracion dec : mapa.get(id)) {
                    if (!dec.getTipo().admiteSobrecarga()) {
                        varEncontradaBloque = true; //Hemos encontrado una declaración de variable en este bloque
                        //Si no lo habíamos encontrado en bloques previos, la añadimos
                        if (!varEncontradaBloquePrevio) 
                            punteros.add(dec);

                    }
                    else    
                        punteros.add(dec);
                }
                encontrado = true;
            }
            varEncontradaBloquePrevio = varEncontradaBloque;
        }
        //Si no estaba en ninguna tabla, lanzamos error de vinculación
        if (!encontrado) {
            errores.errIdNoDec(fila, columna, id.toString());
        }
        return punteros;
    }

    public void insertarTipoNuevo(TipoNuevo tipo, String nombre, int fila, int columna) {
        Map<String,TipoNuevo> mapa = pilaDeTipos.getLast();
        if (mapa.containsKey(nombre)) {
            errores.errTipoYaDef(fila, columna, nombre);
        }
        else {
            mapa.put(nombre, tipo);
        }
    }

    public NodoTipo buscarTipoNuevo(String id, int fila, int columna) {
        boolean encontrado = false;
        NodoTipo tipo = new TipoError();

        for (int i = pilaDeTipos.size()-1; i >= 0 && !encontrado; i--) {
            Map<String,TipoNuevo> mapa = pilaDeTipos.get(i);
            if (mapa.containsKey(id)) {
                tipo = mapa.get(id);
                encontrado = true;
            }
        }
        //Si no estaba en ninguna tabla, lanzamos error de vinculación
        if (!encontrado) {
            errores.errTipoNoDef(fila, columna, id);
        }
        return tipo;
    }

    public void bindParam(LinkedList<DecVariable> argumentosDec, LinkedList<Identificador> argumentosId, List<Parametrico> parametros, String idFuncion, int fila, int columna) {
        /* Crea una nueva declaración con el tipo de parámetros y su identificador, para vincular los usos de los parámetros dentro del ámbito de la función. */
        if (argumentosId.size() != parametros.size())
            errores.errNumArgumentos(idFuncion, fila, columna);

        else {
            if (argumentosId != null) {
                for(int i = 0; i < argumentosId.size(); i++) {
                    Identificador id = argumentosId.get(i);
                    DecVariable dec = new DecVariable(parametros.get(i).getTipo(), id);                    
                    argumentosDec.push(dec);
                    insertaId(id.toString(), dec, id.getFila(), id.getColumna());
                }
            }
        }
    }
}

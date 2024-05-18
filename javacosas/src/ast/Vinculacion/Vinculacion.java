package ast.Vinculacion;

import errors.GestionErroresExp;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import ast.DecVariable;
import ast.Declaracion;
import ast.Expresiones.Identificador;
import ast.Tipos.NodoTipo;
import ast.Tipos.Parametrico;
import ast.Tipos.TipoError;
import ast.Tipos.TipoNuevo;
import ast.Tipos.Tipado.TiposEnum;


public class Vinculacion {
    /*
    La tabla guardará pares (String, Set<NodoAST>), donde el String es el identificador y el set de NodoAST es el conjunto de declaraciones posibles de ese id
    (en el caso de que sea una función puede haber sobrecarga).
    Cuando busquemos un nodo lo haremos llamando a buscaId(id.toString()) (se llama al toString de manera automática).
    */
    private List<Map<String, LinkedHashSet<Declaracion>>> pilaDeTablas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente
    private List<Map<String, TipoNuevo>> pilaDeTipos;
    private GestionErroresExp errores;

    public Vinculacion () {
        this.pilaDeTablas = new LinkedList<>();
        //Añadimos la tabla de símbolos inicial. Es un LinkedHashmap para que se guarden en el orden en que se insertan.
        this.pilaDeTablas.add(new LinkedHashMap<String, LinkedHashSet<Declaracion>>());
        this.pilaDeTipos = new LinkedList<>();
        this.pilaDeTipos.add(new LinkedHashMap<String, TipoNuevo>());
        this.errores = new GestionErroresExp();
    }

    /* 
    Los que se encargan de abrir y cerrar ámbitos no son los MetaOperadores "{}", porque hay veces que no abren nuevo ámbito. 
    Ejemplo: una clase declara un ámbito global. Sus funciones y métodos se pueden acceder desde el ámbito al que pertenece la clase. 
    */

    public void abreBloque() {
        /* Añadimos una nueva tabla de símbolos porque se ha abierto un bloque.*/
        pilaDeTablas.add(new LinkedHashMap<String, LinkedHashSet<Declaracion>>());
        pilaDeTipos.add(new LinkedHashMap<String, TipoNuevo>());
    }

    public Map<String, LinkedHashSet<Declaracion>> cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        Se devuelve la tabla de símbolo para que cada ámbito la almacene (es útil para binding y tipado).
        */
        pilaDeTipos.remove(pilaDeTipos.size() - 1);
        return pilaDeTablas.remove(pilaDeTablas.size() - 1);
    }

    public void insertaId(String id, Declaracion puntero, int fila, int columna) {
        /*
        Intentamos añadir una entrada a la tabla de símbolos del ámbito actual.
         */
        Map<String,LinkedHashSet<Declaracion>> tabla_actual = pilaDeTablas.get(pilaDeTablas.size() - 1);

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
        for (int i = pilaDeTablas.size()-1; i >= 0; i--) {
            Map<String,LinkedHashSet<Declaracion>> mapa = pilaDeTablas.get(i);
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
        Map<String,TipoNuevo> mapa = pilaDeTipos.get(pilaDeTipos.size() - 1);
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
                argumentosDec = new LinkedList<>();                    
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

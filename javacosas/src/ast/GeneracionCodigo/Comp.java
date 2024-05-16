package ast.GeneracionCodigo;

import ast.Declaracion;
import ast.Expresiones.Identificador;
import ast.Tipos.NodoTipo;
import ast.Tipos.TipoNuevo;


import java.util.*;

public class Comp {
    private String ha;

    private List<Map<Declaracion, Integer>> pilaDeDeltas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente

    private List<Map<String,  Map<String, Integer>>> pilaDeTipos; //Es una pila conceptual, lo implementamos como lista porque es más eficiente

    private List<Integer> deltaStack;

    private int PA;

    private int tam;






    public Comp () {
        this.pilaDeDeltas = new LinkedList<>();
        this.pilaDeTipos = new LinkedList<>();
        //Añadimos la tabla de símbolos inicial. Es un LinkedHashmap para que se guard
        // en en el orden en que se insertan.
        this.pilaDeDeltas.add(new LinkedHashMap<Declaracion, Integer>());
        this.PA = 0;
        this.deltaStack = new LinkedList<>();
        this.deltaStack.add(1);
    }

    /*
    Los que se encargan de abrir y cerrar ámbitos no son los MetaOperadores "{}", porque hay veces que no abren nuevo ámbito.
    Ejemplo: una clase declara un ámbito global. Sus funciones y métodos se pueden acceder desde el ámbito al que pertenece la clase.
    */

    public void abreBloque() {
        /* Añadimos una nueva tabla de símbolos porque se ha abierto un bloque.*/
        pilaDeDeltas.add(new LinkedHashMap<Declaracion, Integer>());
        deltaStack.add(1);
        pilaDeTipos.add(new LinkedHashMap<>());
        this.PA = PA + 1;
    }

    public void cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        Se devuelve la tabla de símbolo para que cada ámbito la almacene (es útil para binding y tipado).
        */
        pilaDeDeltas.removeLast();
        deltaStack.removeLast();
        pilaDeTipos.removeLast();
        this.PA = PA - 1;
    }

    public void insertaId(Declaracion dec) {
        /*
        Intentamos añadir una entrada a la tabla de símbolos del ámbito actual.
         */
        pilaDeDeltas.getLast().put(dec, deltaStack.getLast());
        int delta = deltaStack.getLast() + dec.getTam();
        deltaStack.removeLast();
        deltaStack.add(delta);
    }

    public List<Integer> buscaId (Identificador id) {
        Declaracion dec = id.getVinculo();
        Iterator<Map<Declaracion, Integer>> it = ((LinkedList<Map<Declaracion, Integer>>) pilaDeDeltas).descendingIterator();
        boolean encontrado = false;
        Map<Declaracion, Integer> tabla;
        int delta_actual = 0;
        int n = 0;
        while(!encontrado) {
            tabla = it.next();
            if(tabla.containsKey(dec)) {
                encontrado = true;
                delta_actual = tabla.get(dec);
            } else {
                n = n + 1;
            }
        }

        List<Integer> l = new LinkedList<>();
        l.add(4*delta_actual);
        l.add(n);

        return l;

    }

    public void insertarTipoNuevo(TipoNuevo tipo) {
        String nombre = tipo.toString();
        int delta = 0;
        Map<String, Integer> mapa = pilaDeTipos.getLast().get(nombre);
        for(Set<NodoTipo> X: tipo.getTipos()) {
            NodoTipo N = X.iterator().next();
            mapa.put(N.toString(), delta);
            delta = delta + N.getTam();
        }
    }

    public List<Integer> buscaCampo (String id, String campo) {
        Iterator<Map<String,  Map<String, Integer>>> it = ((LinkedList<Map<String,  Map<String, Integer>>>) pilaDeTipos).descendingIterator();
        boolean encontrado = false;
        Map<String, Map<String, Integer>> tabla;
        int delta_actual = 0;
        int n = 0;
        while(!encontrado) {
            tabla = it.next();
            if(tabla.containsKey(id)) {
                encontrado = true;
                delta_actual = tabla.get(id).get(campo);
            } else {
                n = n + 1;
            }
        }
        List<Integer> l = new LinkedList<>();
        l.add(4*delta_actual);
        l.add(n);

        return l;

    }





    public void setS(String ha) {
        this.ha = ha;
    }

    public String getha(){return ha;}

    public void setTam(int tam){this.tam = tam;}

    public int getTam(){return tam;}

}

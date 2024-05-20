package ast.GeneracionCodigo;



import ast.Declaracion;
import ast.Definicion;
import ast.Expresiones.Identificador;
import ast.Tipos.Funcional;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;


import java.util.*;

public class Comp {
    private String ha;


    private LinkedList<Map<Declaracion, Integer>> pilaDeDeltas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente

    private LinkedList<Map<Definicion,  Map<Declaracion, Integer>>> pilaDeTipos; //Es una pila conceptual, lo implementamos como lista porque es más eficiente

    private LinkedList<Integer> MPStack;

    private LinkedList<Definicion> atributos;
    private LinkedHashMap<Declaracion, String> funciones;

    private Map<Declaracion, Integer> globales;

    private  Map<Definicion, Map<Declaracion, Integer>> global_defs;

    private Map<Declaracion, String> local_map;  //Mapeo de parametros barra atributos


    private int delta_actual;

    private int tam;

    private int num_fun;

    private int next_allocator;

    private int SP;

    private int NP;








    public Comp (Map<String, LinkedHashSet<Declaracion>> mapa) {

        this.delta_actual = 0;
        this.globales = new LinkedHashMap<>();
        this.global_defs = new LinkedHashMap<>();
        this.MPStack = new LinkedList<>();
        this.MPStack.add(0);
        this.pilaDeDeltas = new LinkedList<>();
        this.pilaDeDeltas.add(globales);
        this.SP = delta_actual;
        this.next_allocator = 0;
        int num_globals = 1;
        for(String  s: mapa.keySet()){
            for(Declaracion dec: mapa.get(s)){
                if(dec.getTipo().typeToEnum() != Tipado.TiposEnum.FUNCIONAL) {
                    globales.put(dec, delta_actual);
                    this.delta_actual = delta_actual + dec.getTam();
                    this.SP = delta_actual;
                    num_globals = num_globals + 1;
                }
            }
        }
        this.pilaDeTipos = new LinkedList<>();
        this.pilaDeTipos.add(new LinkedHashMap<>());
        this.funciones = new LinkedHashMap<>();
        this.num_fun = 0;
        this.NP = 2000*16*1024-1;
        this.local_map = new LinkedHashMap<>();
        this.atributos = new LinkedList<>();

    }

    public int getSP() {
        return SP;
    }

    /*
    Los que se encargan de abrir y cerrar ámbitos no son los MetaOperadores "{}", porque hay veces que no abren nuevo ámbito.
    Ejemplo: una clase declara un ámbito global. Sus funciones y métodos se pueden acceder desde el ámbito al que pertenece la clase.
    */

    public void abreBloque() {
        /* Añadimos una nueva tabla de símbolos porque se ha abierto un bloque.*/
        this.pilaDeDeltas.add(new LinkedHashMap<Declaracion, Integer>());
        this.MPStack.add(SP);
        this.delta_actual = 1;
        this.pilaDeTipos.add(new LinkedHashMap<>());
    }

    public void cierraBloque() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        */

        pilaDeDeltas.removeLast();
        SP = MPStack.pollLast();
        this.delta_actual =  SP - MPStack.getLast();
        pilaDeTipos.removeLast();
    }

    public void insertaId(Declaracion dec) {
        /*
        Intentamos añadir una entrada a la tabla de símbolos del ámbito actual.
         */
        pilaDeDeltas.getLast().put(dec, delta_actual);
        this.delta_actual = this.delta_actual + dec.getTam();
        this.SP = this.SP + dec.getTam();
    }

    public void insertarFunc(Declaracion fun){
        this.funciones.put(fun, "$fun" + Integer.toString(num_fun));
        num_fun = num_fun + 1;
    }

    public void insertarMain(Declaracion main){
        this.funciones.put(main, "$main");
    }

    public String buscarFun(Declaracion dec){
        return this.funciones.get(dec);
    }


    public Integer buscaId (Identificador id) {
        Declaracion dec = id.getVinculo();
        Iterator<Map<Declaracion, Integer>> it = pilaDeDeltas.descendingIterator();
        Iterator<Integer> it_mp = MPStack.descendingIterator();
        boolean encontrado = false;
        Map<Declaracion, Integer> tabla;
        int delta = -1;
        int MP = -1;
        int add = -10;
        while(!encontrado && it.hasNext()) {
            tabla = it.next();
            MP = it_mp.next();
            if(tabla.containsKey(dec)) {
                encontrado = true;
                delta = tabla.get(dec);
                add = 4*(MP + delta);
            }
        }


        return add;
    }

    public String localId(Identificador id) {
        Declaracion dec = id.getVinculo();
        return local_map.get(dec);
    }

    public void insertarTipoNuevo (Definicion def) {
        int delta = 0;
        Map<Declaracion, Integer> mapa = new LinkedHashMap<>();
        for(String s: def.getTipo().getCampos().keySet()) {
            for(Declaracion X: def.getTipo().getCampos().get(s)) {
                mapa.put(X, delta);
                delta = delta + X.getTam();
            }
        }
        pilaDeTipos.getLast().put(def, mapa);
    }

    public Integer buscaCampo (Definicion id, Declaracion campo) {
        Iterator<Map<Definicion,  Map<Declaracion, Integer>>> it = pilaDeTipos.descendingIterator();
        boolean encontrado = false;
        Map<Definicion , Map<Declaracion, Integer>> tabla;
        int delta = 0;
        while(!encontrado) {
            tabla = it.next();
            if(tabla.containsKey(id)) {
                encontrado = true;
                delta = tabla.get(id).get(campo);
            }
        }


        return 4*delta;

    }

    public void pushAtributos(Definicion def){
        atributos.add(def);
    }

    public void popAtributos(){
        atributos.removeLast();
    }

    public Map<String, LinkedHashSet<Declaracion>> getAtributos(){
        Map<String, LinkedHashSet<Declaracion>> map = new LinkedHashMap<>();
        if(!atributos.isEmpty()) {
            map = atributos.getLast().getTipo().getCampos();
        }
        return map;
    }

    public int setNew(){
        NP = NP - tam;
        return NP;
    }








    public void setS(String ha) {
        this.ha = ha;
    }

    public String getha(){return ha;}

    public void setTam(int tam){this.tam = tam;}

    public int getTam(){return tam;}

    public void abreFun() {
        /* Añadimos una nueva tabla de símbolos porque se ha abierto un bloque.*/

    }

    public void cierraFun() {
        /*
        Eliminamos la tabla de símbolos de ese ámbito (el último) porque se ha cerrado el bloque.
        Se devuelve la tabla de símbolo para que cada ámbito la almacene (es útil para binding y tipado).
        */
        pilaDeDeltas.removeLast();
        MPStack.removeLast();
    }

    public void setLocalMap(Map<Declaracion,String> local_map){
        this.local_map = local_map;
    }

    public void clearLocalMap(){
        this.local_map.clear();
    }





}

package ast.Tipos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ast.Declaracion;
import ast.Instruccion;
import ast.Programa;
import ast.Expresiones.E;
import ast.Expresiones.Identificador;
import ast.Expresiones.Num;
import ast.Metaoperadores.CabecerAsig;
import ast.Operadores.UnOperador.*;
import errors.GestionErroresExp;
//import ast.Operadores.MultiOperadores.*;
import ast.Operadores.BinOperadores.*;


public class Tipado {
    public enum TiposEnum {
    //Tipos del lenguaje
    ENTERO, BOOLEANO, ARRAY, PUNTERO, FUNCIONAL, STRUCT, CLASE, VOID, T, //Tipo genérico
    //Tipo de retorno de instrucciones. Lo utilizaremos para comprobar que todas las instrucciones de una clase o un struct son declaraciones o definiciones
    ASIGNACION, DECVARIABLE, DECFUNCION, DEFCLASE, DEFSTRUCT, RETURN, OTRA_INSTRUCCION, //No campo es para aquellas instrucciones que no son campos ni de structs ni de clases.

    ERROR; 
    } 
    public static final Set<TiposEnum> TODOS = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.FUNCIONAL, TiposEnum.VOID, TiposEnum.ASIGNACION, TiposEnum.DECVARIABLE, TiposEnum.DECFUNCION, TiposEnum.DEFCLASE, TiposEnum.DEFSTRUCT, TiposEnum.RETURN, TiposEnum.ERROR);
    public static final Set<TiposEnum> TIPOS_EXPRESIONES = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.FUNCIONAL);
    public static final Set<TiposEnum> TIPOS_ARGUMENTOS_FUNCION = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.STRUCT, TiposEnum.CLASE);
    public static final Set<TiposEnum> TIPOS_PUNTERO = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY);
    public static final Set<TiposEnum> TIPOS_RETURN = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.VOID);
    public static final Set<TiposEnum> TIPOS_COMPARABLES = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO);
    public static final Set<TiposEnum> TIPOS_CONTENIDO_ARRAY = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.ARRAY);
    public static final Set<TiposEnum> TIPOS_ATRIBUTOS = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE);
    public static final Set<TiposEnum> TIPOS_ENTRADA_SALIDA = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO);
    public static final Set<TiposEnum> TIPOS_INSTR_STRUCTS = Set.of(TiposEnum.DECVARIABLE, TiposEnum.DEFSTRUCT, TiposEnum.ERROR);
    public static final Set<TiposEnum> TIPOS_INSTR_CLASES = Set.of(TiposEnum.DECVARIABLE, TiposEnum.DEFCLASE, TiposEnum.DEFSTRUCT, TiposEnum.DECFUNCION,  TiposEnum.ERROR);
    public static final Set<TiposEnum> TIPOS_INSTR_FUNC = Set.of(TiposEnum.ASIGNACION, TiposEnum.DECVARIABLE, TiposEnum.DEFCLASE, TiposEnum.DEFSTRUCT, TiposEnum.DECFUNCION, TiposEnum.OTRA_INSTRUCCION, TiposEnum.RETURN,TiposEnum.ERROR);
    public static final Set<TiposEnum> TIPOS_INSTR_FUNC_VOID = Set.of(TiposEnum.ASIGNACION, TiposEnum.DECVARIABLE, TiposEnum.DEFCLASE, TiposEnum.DEFSTRUCT, TiposEnum.DECFUNCION, TiposEnum.OTRA_INSTRUCCION, TiposEnum.ERROR);
    public static final Set<TiposEnum> TIPOS_NUEVOS = Set.of(TiposEnum.STRUCT, TiposEnum.CLASE);
    public static final Set<TiposEnum> TIPOS_ASIGNACIONES = Set.of(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.DECVARIABLE);
    public static final Set<TiposEnum> TIPOS_PARAMETRIZABLES  = Set.of(TiposEnum.ARRAY, TiposEnum.PUNTERO);

    private static NodoTipo match2Tipos(NodoTipo t1, NodoTipo t2) {
        /* Comprueba si dos tipos son idénticos. Si lo son, devuelve su tipo, si no, devuelve error*/
        TiposEnum tEnum1 = t1.typeToEnum();
        TiposEnum tEnum2 = t2.typeToEnum();
        //Si el segundo tipo es genérico, devolvemos el primer tipo
        if (tEnum2.equals(TiposEnum.T))
            return t1;
        //Si no son del mismo tipo o uno de los dos es un error de tipo, devolvemos un tipo error
        else if(!tEnum1.equals(tEnum2) || tEnum1.equals(TiposEnum.ERROR))
            return new TipoError();
        
        
        //El tipo del resultado se inicializa a tipo error. Si el matching es exitoso, se cambiará su valor al tipo correspondiente.
        NodoTipo tRes = new TipoError();
        NodoTipo tMatch; //Variable auxiliar
        switch (tEnum1) {
            /* Para los tipos no básicos, el matching es un poco más profundo */
            case ARRAY:
                Array tArr1 = (Array) t1;
                Array tArr2 = (Array) t2;
                if (tArr2.getN() > 0 && tArr1.getN() != tArr2.getN())
                    errores.errTamArray(tArr1.getN(), t1.getFila(), t1.getColumna());
                //Hacemos match de los elementos del array
                tMatch = match2Tipos(tArr1.getTipo(), tArr2.getTipo());
                //Si no es un tipo error, devolvemos un array del tipo
                if (!tMatch.typeToEnum().equals(TiposEnum.ERROR))
                    tRes =  new Array(tMatch);
            break;
            case PUNTERO:
                //Hacemos match del elemento del puntero
                tMatch = match2Tipos(t1.getTipo(), t2.getTipo());
                //Si no es un tipo error, devolvemos un puntero del tipo
                if (!tMatch.typeToEnum().equals(TiposEnum.ERROR))
                    tRes =  new Puntero(tMatch);
            break;
            case STRUCT:
            case CLASE:
                TipoNuevo tn1 = (TipoNuevo) t1;
                TipoNuevo tn2 = (TipoNuevo) t2;

                
                //Si los nombres coinciden, o alguno es un tipo nuevo auxiliar (tienen un id por defecto)
                if (tn1.getId().equals(TipoNuevo.DEFAULT_ID) || tn2.getId().equals(TipoNuevo.DEFAULT_ID) || tn1.getId().equals(tn2.getId())) {
                    List<Set<NodoTipo>> tipado1 = tn1.getTipos();
                    List<Set<NodoTipo>> tipado2 = tn2.getTipos();
                    boolean hayMatch = true;
                    if (tipado2 != null) {
                        //Por cada atributo, miramos a ver si coinciden
                        if (tipado1.size() != tipado2.size())
                            hayMatch = false;
                        else {
                            for (int i = 0; i < tipado1.size() && hayMatch; i++) {
                                Set<NodoTipo> tAtributo1 = tipado1.get(i);
                                Set<NodoTipo> tAtributo2 = tipado2.get(i);
                                //Si la intersección de los dos conjuntos es vacía, no hay match para ese atributo
                                if (match2Conjuntos(tAtributo1, tAtributo2).size() == 0)
                                    hayMatch = false;
                            }
                        }
                    }  
                    if (hayMatch)
                        tRes =  t1;               
                    
                }
                
            break;
            case FUNCIONAL:
                NodoTipo tRetorno1 = ((Funcional) t1).getTipoRetorno();
                NodoTipo tRetorno2 = ((Funcional) t2).getTipoRetorno();

                    List<Parametrico> parametros1 = ((Funcional) t1).getParametros();
                    List<Parametrico> parametros2 = ((Funcional) t2).getParametros();
                    if (parametros2 == null)
                        tRes = t1;
                    else if (!match2Tipos(tRetorno1, tRetorno2).typeToEnum().equals(TiposEnum.ERROR) && parametros1.size() == parametros2.size()  ) {
                        boolean funIgual = true;
                        for (int i = 0; i < parametros1.size() && funIgual; i++) {
                            NodoTipo tParam1 = parametros1.get(i).getTipo();
                            NodoTipo tParam2 = parametros2.get(i).getTipo();        
                            if (match2Tipos(tParam1, tParam2).typeToEnum().equals(TiposEnum.ERROR))
                                funIgual = false;
                        }
                        if (funIgual)
                            tRes = t1;
                    }

            break;

            //Tipos básicos
            default:
                tRes =  t1;
            break;
        }

        return tRes;
    }

    public static Set<NodoTipo> match2Conjuntos (Set<NodoTipo> conjunto1, Set<NodoTipo> conjunto2) {
        /* Devuelve un conjunto con la intersección de ambos (descartando los tipo error) */
        Set<NodoTipo> res = new LinkedHashSet<>();
        for (NodoTipo t1 : conjunto1)
            for (NodoTipo t2 : conjunto2) {
                NodoTipo match = match2Tipos(t1, t2);
                if (!match.typeToEnum().equals(TiposEnum.ERROR))
                    res.add(t1);
            }

        return res;
    }

    public static boolean esTipoEsperado (NodoTipo tipo, Set<NodoTipo> tiposEsperados) {
        boolean correcto = false;
            for (NodoTipo tEsperado : tiposEsperados)
                if (match2Tipos(tipo, tEsperado).typeToEnum().equals(tipo.typeToEnum()))
                    correcto = true;

        return correcto;
    }

    private static boolean esConjuntoError(Set<NodoTipo> conjuntoTipos) {
        //Comprueba si un conjunto tiene algún tipo factible (algo que no sea un error)
        boolean esError = true;
        for (NodoTipo tipo : conjuntoTipos) {
            if (!tipo.typeToEnum().equals(TiposEnum.ERROR))
                esError = false;
        }

        return esError;
    }

    //EXPRESIONES BÁSICAS
    
    public static Set<NodoTipo> matchTipoEsperado(NodoTipo tipo, Set<NodoTipo> tiposEsperados, int fila, int columna) {
        //Si ha habido un error de tipado previamente, lo devolvemos
        if(esConjuntoError(tiposEsperados))
            return tiposEsperados;
        //Si no, comprobamos si es el tipo esperado
        else if (esTipoEsperado(tipo, tiposEsperados))
            return new LinkedHashSet<>(Arrays.asList(tipo));
        //Si no lo es, damos error
        else {
            errores.errTipadoExp(tiposEsperados, fila, columna);
            return conjuntoError();
        }
    }

    //EXPRESIONES CON OPERADOR UNARIO

    public static Set<NodoTipo> matchEUn(Set<NodoTipo> tiposEsperados, E expresion, OperadorUn op) {
        /* Comprueba que los tipos del operador unario encajan con los del operando y devuelve un conjunto con el NodoTipo esperado */
        String opString = op.toString();
        int fila = op.getFila();
        int columna = op.getColumna();
        List<Set<NodoTipo>> tOperador = op.getTipado();     // Tipado del operador
        Set<NodoTipo> tEsperadosOpnd = conjuntoError();     // Tipos esperados para el operando
        Set<NodoTipo> tResOperador = enumToTipo(TODOS);     // Tipos factibles de devolución
        Set<NodoTipo> tipadoMatch = conjuntoError();        // Tipos que se devuelven en el return
        
        //El tipo de devolución y el tipo esperado en el operando vendrán según el operador
        switch(opString) {
            // tPuntero* -> tPuntero
            case AccesoPuntero.OPSTRING:                 
                tEsperadosOpnd = new HashSet<>();
                for (NodoTipo tipo : tiposEsperados) {
                    tEsperadosOpnd.add (new Puntero(tipo));
                }
                tResOperador = tOperador.get(1);
                //Tipamos la expresión del operando
                Set<NodoTipo> tResOperando = expresion.type(tEsperadosOpnd);
                if (!esConjuntoError(tResOperando)) {
                    tipadoMatch = new LinkedHashSet<>();
                    //El tipado de devolución será el contenido del tipado del puntero
                    for (NodoTipo tipo : tResOperando) {
                        if (!tipo.typeToEnum().equals(TiposEnum.ERROR))
                            tipadoMatch.add(((Puntero) tipo).getTipo());
                    }
                }
            break;
            // boolean -> boolean
            // int -> int
            default:
                tEsperadosOpnd = tOperador.get(0);
                tResOperador = tOperador.get(1);
                //Tipamos la expresión del operando. Este tipado será el tipado de devolución.
                tipadoMatch = expresion.type(tEsperadosOpnd);          
            break;
            
        }        
        //Si se epera un tipo de devolución imposible para este operador, detectamos el error y lo devolvemos
        if(match2Conjuntos(tResOperador, tiposEsperados).size() == 0) {
            errores.errTipadoExp(tiposEsperados, fila, columna);  
            tipadoMatch = conjuntoError();
        }
        //Ambigüedad
        if (tipadoMatch.size() > 1) {
            errores.errExpAmbigua(fila, columna);  
            tipadoMatch = conjuntoError();
        }
        return tipadoMatch;
    }

    //EXPRESIONES CON OPERADOR BINARIO

    public static Set<NodoTipo> matchEBin(Set<NodoTipo> tiposEsperados, E opnd1, E opnd2, OperadorBin op) {
        /* Comprueba que los tipos del operador binario encajan con los de los operandos y devuelve un conjunto con el NodoTipo esperado */
        String opString = op.toString();
        int fila = op.getFila();
        int columna = op.getColumna();
        List<Set<NodoTipo>> tOperador = op.getTipado();     // Tipado del operador
        Set<NodoTipo> tResOperando1 = conjuntoError();      // Tipo resultado del primer operando
        Set<NodoTipo> tEsperadosOpnd1 = conjuntoError();    // Tipo esperado el primer operando
        Set<NodoTipo> tResOperando2 = conjuntoError();      // Tipo resultado del segundo operando
        Set<NodoTipo> tEsperadosOpnd2 = conjuntoError();    // Tipo esperado el segundo operando
        Set<NodoTipo> tResOperador = enumToTipo(TODOS);     // Tipo de devolución del operando
        Set<NodoTipo> tipadoMatch = conjuntoError();        // Tipos que se devuelven en el return

        //El tipo de devolución y los tipos esperados en los operandos vendrán según el operador
        switch(opString) {

            // array[t] -> int -> t
            case AccesoArray.OPSTRING:

                tEsperadosOpnd1 = new HashSet<>();
                for (NodoTipo tEsperado : tiposEsperados) {
                    tEsperadosOpnd1.add( new Array(tEsperado));
                }
                tEsperadosOpnd2 = tOperador.get(1);
                tResOperador = tOperador.get(2);
                tResOperando1 = opnd1.type(tEsperadosOpnd1);
                tResOperando2 = opnd2.type(tEsperadosOpnd2);
                //Si ninguno de los dos devuelve error de tipado
                if (!esConjuntoError(tResOperando1) && !esConjuntoError(tEsperadosOpnd2)) {
                    tipadoMatch = new LinkedHashSet<>();
                    //El tipado de devolución será el contenido del tipado del puntero
                    for (NodoTipo tipo : tResOperando1) {
                        if (!tipo.typeToEnum().equals(TiposEnum.ERROR))
                            tipadoMatch.add(tipo.getTipo());
                    }
                }
            break;

            // tStruct -> id -> tAtributo
            // tClase -> id -> tAtributo
            // tClase -> id -> Funcional
            case AccesoCampo.OPSTRING: 

                //Tipamos la clase o struct
                tResOperando1 = opnd1.type(enumToTipo(TIPOS_NUEVOS));
                //Como es una clase o un struct, solo pueden haber devuelto el tipo correcto o un tipo error. En todo caso solo hay un elemento en el conjunto resultado.
                if(!esConjuntoError(tResOperando1)) {
                    tipadoMatch = new LinkedHashSet<>();
                    //Ahora buscamos el opnd2 en el mapa de campos del operando 1
                    NodoTipo tOpnd1 = tResOperando1.iterator().next(); //Cogemos el primero porque solo puede haber uno
                    Map<String,LinkedHashSet<Declaracion>> mapaCampos = ((TipoNuevo) tOpnd1).getCampos();
                    Identificador idOpnd2 = (Identificador) opnd2;
                    //Si no está, lanzamos error y lo devolvemos hacia arriba
                    if(!mapaCampos.containsKey(idOpnd2.toString())) {
                        errores.errorNoHayCampo(idOpnd2.toString(), op.getFila(), op.getColumna());
                    }
                    else {
                        Set<Declaracion> vinculosId = new LinkedHashSet<>();
                        for(Declaracion vinculo : mapaCampos.get(idOpnd2.toString())) {
                            NodoTipo tCampo = vinculo.getTipo();

                            //Por cada declaración en esa entrada del mapa, la añadimos al conjunto de matches si coincide con el tipo esperado
                            if(esTipoEsperado(tCampo, tiposEsperados)) {
                                vinculosId.add(vinculo);
                                tipadoMatch.add(tCampo);
                            }
                        }
                        //Si no hay ninguna, lanzamos error y lo devolvemos
                        if(vinculosId.size() == 0) {
                            errores.errTipadoExp(tiposEsperados, idOpnd2.getFila(), idOpnd2.getColumna());
                            return conjuntoError();
                        }
                        else {
                            idOpnd2.setVinculos(vinculosId);
                        }
                    }
                }
            break;

            // int -> int -> int    o     boolean -> boolean -> boolean
            case Disyuncion.OPSTRING: 
            case Conjuncion.OPSTRING: 
            case Division.OPSTRING: 
            case Producto.OPSTRING: 
            case Suma.OPSTRING: 
            case Resta.OPSTRING: 
            // tComparable -> tComparable -> boolean 
            case Igual.OPSTRING:
            case Distinto.OPSTRING:
            case MenorQue.OPSTRING:
            case MenorIgual.OPSTRING:
            case MayorQue.OPSTRING:
            case MayorIgual.OPSTRING:

                tEsperadosOpnd1 = tOperador.get(0);
                tEsperadosOpnd2 = tOperador.get(1);
                tResOperador = tOperador.get(2);
                tResOperando1 = opnd1.type(tEsperadosOpnd1);
                tResOperando2 = opnd2.type(tEsperadosOpnd2);
                //Sacamos los tipos iguales en ambos conjuntos (que no sean tipo error)
                Set<NodoTipo> tCompatibles = match2Conjuntos(tResOperador, tResOperando2);
                //Si hay al menos uno, lo devolveremos
                if(tCompatibles.size() > 0)
                    tipadoMatch = tCompatibles;
            break;
        }
        
        //Si se epera un tipo de devolución imposible para este operador, detectamos el error y lo devolvemos
        if(match2Conjuntos(tResOperador, tiposEsperados).size() == 0) {
            errores.errTipadoExp(tiposEsperados, fila, columna);  
            tipadoMatch = conjuntoError();
        }
        //Ambigüedad
        if (tipadoMatch.size() > 1) {
            errores.errExpAmbigua(fila, columna);  
            tipadoMatch = conjuntoError();
        }
        return tipadoMatch;
    }

    //DEFINICIÓN DE CLASES Y STRUCTS

    public static List<Set<NodoTipo>> matchDefTipoNuevo(Set<NodoTipo> tAmbito, String id, int fila, int columna) {
        /* Devolvemos una lista ordenada con los tipos en el orden de la declaración de las variables. */

        List<Set<NodoTipo>> tiposResultado = new ArrayList<Set<NodoTipo>>();
        for (NodoTipo tipo : tAmbito) {
            if(tipo.typeToEnum().equals(TiposEnum.DECVARIABLE))
                //Obtenemos el tipo de la declaración y lo añadimos a la lista de tipos
                tiposResultado.add(new LinkedHashSet<>(Arrays.asList(((TInstruccion) tipo).getTipo())));  
        }

        return tiposResultado;
    }

    //ASIGNACIONES

    public static Set<NodoTipo> matchAsig(Set<NodoTipo> tiposEsperados, CabecerAsig cabecera, E exp, int fila, int columna) {
        /* 
        Comprueba si el tipo del operando izquierdo es una declaración, en cuyo caso la instrucción se considera una declaración con asignación 
        y se devuelve el tipo de la cabecera (tipo declaración). En otro caso, la instrucción es una asignación simple y se devuelve este tipo.
         */
        //Si el operando izquierdo es una expresión válida, tipamos el operando izquierdo. Si no, lanzamos error
        Set<NodoTipo> tCabecera = cabecera.type(Tipado.enumToTipo(Tipado.TIPOS_ASIGNACIONES));
        NodoTipo tAsignacion = new TInstruccion(TiposEnum.ASIGNACION);
        boolean esDeclaracion = false;
        boolean asignable = cabecera.esAsignable();
         //Si el operando izquierdo no es asignable, devolvemos un error y no tipamos la expresión izquierda
         for (NodoTipo tipo : tCabecera) {
            TiposEnum tEnum = tipo.typeToEnum();
            if (tEnum.equals(TiposEnum.DECVARIABLE)) {
                esDeclaracion = true;
                tAsignacion = tipo;
                break;
            }
        }

        Set<NodoTipo> tRes = Set.of(tAsignacion);
        
        //Si es una asignación con declaración, devolveremos una declaración
        if (esDeclaracion)
            tRes = tCabecera;            

        //Si el operando izquierdo es una expresión asignable, tipamos el operando derecho
        if (asignable) {       
            if (esDeclaracion)
                exp.type(Set.of(((Declaracion) cabecera).getTipo()));  
            else
                exp.type(tCabecera);            
        }
        //Si no, error de asignación
        else
            errores.errAsignacion(fila, columna);

        return tRes;
        
    }
    
   
    //LISTAS

    public static Set<NodoTipo> matchListaL(Set<NodoTipo> tiposEsperados, List<E> elementos, int fila, int columna) {
        /* 
        Comprueba que los elementos de una lista de llaves son correctos (son tipos válidos para atributos de una clase o struct).
        Devuelve un tipo Struct y un tipo Clase con una lista campos de atributos de los elementos si son correctos.
         */
        //Vemos si algún tipo de los esperados es tipo struct o clase (tipos factibles para una lista de llaves)
        Set<NodoTipo> tRes = new LinkedHashSet<>();
        for (NodoTipo tEsperado : tiposEsperados) 
            //Si alguno es factible, hacemos el match
            if (TIPOS_NUEVOS.contains(tEsperado.typeToEnum())) {
                List<Set<NodoTipo>> tiposAtributos = ((TipoNuevo) tEsperado).getTipos();
                //Si hay el mismo número de atributos en el tipo struct o clase que número de elementos en la lista de llaves, los tipamos
                if (elementos.size() == tiposAtributos.size()) {
                    List<Set<NodoTipo>> tiposElemenos = new ArrayList<Set<NodoTipo>>();
                    for (int i = 0; i < elementos.size(); i++)                        
                        tiposElemenos.add(elementos.get(i).type(tiposAtributos.get(i)));

                    //Después de tiparlos, comprobamos si hacen match el tipo esperado y el nuevo tipo
                    NodoTipo tListaLLaves;
                    if (tEsperado.typeToEnum().equals(TiposEnum.CLASE))
                        tListaLLaves = new Clase(tiposElemenos);
                    else 
                        tListaLLaves = new Struct(tiposElemenos);
                    if (!match2Tipos(tEsperado, tListaLLaves).typeToEnum().equals(TiposEnum.ERROR))
                        tRes.add(tListaLLaves);
                }
            }
        //Si no hay ningún match, devolvemos error
        if (tRes.size() == 0)
            tRes = conjuntoError();
        return tRes;
    }

    public static Set<NodoTipo> matchListaC(Set<NodoTipo> tiposEsperados, LinkedList<E> elementos, int fila, int columna) {
        /* 
        Comprueba que los elementos de una lista de corchetes son correctos (son tipos válidos para elemntos de un array y todos del mismo tipo)
        Devuelve un tipo Array con el contenido de los elementos si son correctos.
         */

        //Comprobamos si entre los tipos esperados hay algún tipo array. Si lo hay, añadimos a tContenidoArray el tipo esperado del array 
        Set<NodoTipo> tEsperadosContenido = new LinkedHashSet<>();
        int nElems = elementos.size();
        for (NodoTipo tipo : tiposEsperados)
            //Si alguno de los tipos esperados es tipo array
            if (tipo.typeToEnum().equals(TiposEnum.ARRAY)) {
                NodoTipo tContenidoArray = tipo;
                //Comprobamos recursivamente el tipo del contenido del array hasta llegar a un tipo no parametrizable
                while(Tipado.TIPOS_PARAMETRIZABLES.contains(tContenidoArray.typeToEnum())) {
                    tContenidoArray = tContenidoArray.getTipo();
                }
                //Si ese tipo no es erróneo, y coincide en tamaño con el número de elementos, añadimos el match como tipo esperado posible (del contenido del array)
                if (!tContenidoArray.typeToEnum().equals(TiposEnum.ERROR) && ((Array) tipo).getN() == nElems)
                    tEsperadosContenido.add(tipo.getTipo());
            }

        //Si no hay ningún match, error de tipado
        if (tEsperadosContenido.size() == 0) {
            errores.errTipadoExp(tiposEsperados, fila, columna);
            return conjuntoError();
        }

        //Vamos a construir el tipo array de la lista corchetes (comprobando que es factible)
        Num nArray = new Num(String.valueOf(nElems)); //Número de elementos del array
        //Si hay algún elemento, comprobamos que los elementos estén bien tipados
        if(nElems == 0)
            return Set.of(new Array(nArray));

        Set<NodoTipo> tPrimerElem = elementos.get(0).type(tEsperadosContenido);
        LinkedHashSet<NodoTipo> tiposFactibles = new LinkedHashSet<>(); //tipos factibles para el contenido del array
        for (NodoTipo tipo : tPrimerElem) {
            //Por cada tipo correcto del tipado del primer elemento, lo añadimos a la lista de tipos factibles
            if (!tipo.typeToEnum().equals(TiposEnum.ERROR)) {
                tiposFactibles.add(tipo);
            }
        }
        for (int i = 1; i < elementos.size(); i++) {
            //Lo tipamos esperando alguno de los tipos esperados para el contenido del array
            E elemento = elementos.get(i);
            Set<NodoTipo> tipadoElem = elemento.type(tEsperadosContenido);
            //Hacemos la intersección del tipado de este elemento con el tipado de los anteriores
            for (NodoTipo tFactible : tiposFactibles) {
                boolean esFactible = false;
                for(NodoTipo tElem : tipadoElem) {
                    //Si hay match, lo dejamos
                    if (!match2Tipos(tFactible, tElem).typeToEnum().equals(TiposEnum.ERROR)) {
                        esFactible = true;
                        break;
                    }
                }
                if (!esFactible)
                    tiposFactibles.remove(tFactible);
                
            }
        }
        ArrayList<NodoTipo> tArraysFactibles = new ArrayList<>();
        for (NodoTipo tipo : tiposFactibles) {
            tArraysFactibles.add(new Array(tipo, nArray));
        }
        Set<NodoTipo> tRes = new HashSet<NodoTipo>(tArraysFactibles);
        //Devolvemos un nuevo tipo Array de tArray (el tipo de los elementos)
        return tRes;
    }

    //IDENTIFICADORES

    public static Set<NodoTipo> matchIdentificador(Identificador identificador, Set<Declaracion> vinculos, Set<NodoTipo> tiposEsperados) {
        Set<NodoTipo> tipadoMatch = new LinkedHashSet<>();
        Set<Declaracion> vincResultado = new LinkedHashSet<>();
        for (Declaracion dec : vinculos) {
            NodoTipo tDeclaracion = dec.getTipo();
            //Si el vínculo encaja con algún tipo esperado, lo añadimos a los tipos resultado y a los vínculos finales del id
            if (esTipoEsperado(tDeclaracion, tiposEsperados)) {
                tipadoMatch.add(tDeclaracion);
                vincResultado.add(dec);
            }
        }
        identificador.setVinculos(vincResultado);

        //No encaja con el tipo solicitado
        if (tipadoMatch.size() == 0) {
            errores.errTipadoExp(tiposEsperados, identificador.getFila(), identificador.getColumna());
            tipadoMatch = conjuntoError();
        }
        //Ambigüedad
        if (tipadoMatch.size() > 1) {
            errores.errExpAmbigua(identificador.getFila(), identificador.getColumna());  
            tipadoMatch = conjuntoError();
        }
        return tipadoMatch;
    }

    //FUNCIONES

    public static Set<NodoTipo> matchExpFunc(Set<NodoTipo> tiposEsperados, E idFuncion, List<E> parametros, int fila, int columna) {
        /* Comprueba que los tipos de los parámetros son tipos válidos y que encajan con los tipos de alguna definición de la función */

        //Tipamos los parámetros
        List<Parametrico> tipadoParametros = new LinkedList<Parametrico>();
        for (E parametro: parametros) {
            Set<NodoTipo> tParametrosEsperados = Tipado.enumToTipo(Tipado.TIPOS_ARGUMENTOS_FUNCION);
            Set<NodoTipo> tParametro = parametro.type(tParametrosEsperados);
            if (tParametro.size() > 1) {
                tipadoParametros.add(new TipoError());
                errores.errExpAmbigua(parametro.getFila(), parametro.getColumna());
            }
            else if (tParametro.size() == 0) {
                tipadoParametros.add(new TipoError());
                errores.errTipadoExp(tParametrosEsperados, parametro.getFila(), parametro.getColumna());
            }
            else {
                tipadoParametros.add(tParametro.iterator().next());
            }
        }

        LinkedHashSet<NodoTipo> tFuncionalesEsperados = new LinkedHashSet<>();

        //Por cada tipo esperado de devolución factible, creamos un tipo funcional con ese tipo esperado
        for (NodoTipo tEsperado : tiposEsperados) {
            if (TIPOS_RETURN.contains(tEsperado.typeToEnum()))
                tFuncionalesEsperados.add(new Funcional(tEsperado, tipadoParametros));
        }

        if (tFuncionalesEsperados.size() == 0) {
            errores.errTRetornoFuncion(tiposEsperados, fila, columna);
            return conjuntoError();
        }
        else
            return idFuncion.type(tFuncionalesEsperados);
    }
    //INSTRUCCIÓN
    public static Set<NodoTipo> matchInstr(Instruccion inst, Programa prog, Set<NodoTipo> tiposEsperados) {
        Set<NodoTipo> tipadoInst = inst.type(tiposEsperados);
        Set<NodoTipo> tipadoProg = prog.type(tiposEsperados);

        //Si no es una instrucción esperada, error
        if (match2Conjuntos(tipadoInst, tiposEsperados).size() == 0) {
            errores.errInstrNoValida(inst.getFila(), inst.getColumna());
            tipadoInst = conjuntoError();
        }

        return unirConjuntos(tipadoInst, tipadoProg);
    }
    //AMBITO

    public static void matchAmbito(Map<String, LinkedHashSet<Declaracion>> mapa, Set<NodoTipo> tPrograma) {
        /* Comprueba que en un ámbito no haya dos declaraciones de funciones que provoquen ambigüedad */

        //Por cada entrada comprobamos que no hay dos declaraciones con el mismo tipo
        for (Map.Entry<String, LinkedHashSet<Declaracion>> entrada : mapa.entrySet()) {
            ArrayList<Declaracion> listaDec = new ArrayList<Declaracion>(entrada.getValue());
            if (listaDec.size() > 1) {
                for (int i = 0; i < listaDec.size() - 1; i++) {
                    Declaracion dec1 = listaDec.get(i);
                    for (int j = i+1; j < listaDec.size(); j++) {
                        Declaracion dec2 = listaDec.get(j);
                        //Si dos declaraciones tienen mismo tipo (el match no devuelve error) deben ser dos funciones iguales.
                        if (!match2Tipos(dec1.getTipo(), dec2.getTipo()).typeToEnum().equals(TiposEnum.ERROR)) {
                            errores.errFuncionYaDeclarada(dec2.toString(), dec2.getFila(), dec2.getColumna());
                            break;
                        }
                    }
                }
            }

        }
    }

    //DECLARACIÓN

    public static Set<NodoTipo> matchDec(NodoTipo t, Set<NodoTipo> tiposEsperados, int fila, int columna) {
        TiposEnum tEnum = t.typeToEnum();
        Set<NodoTipo> tRes = conjuntoError();
        
        if (tEnum.equals(TiposEnum.FUNCIONAL)){
            tRes = new LinkedHashSet<>(Arrays.asList(new TInstruccion(TiposEnum.DECFUNCION, t, fila, columna)));
        }
        else {
            tRes = new LinkedHashSet<>(Arrays.asList(new TInstruccion(TiposEnum.DECVARIABLE, t, fila, columna)));
        }
        return tRes;
    }

    /* ERRORES */

    private static GestionErroresExp errores = new GestionErroresExp();

    public static Set<NodoTipo> conjuntoError() {
        return new LinkedHashSet<>(Arrays.asList(new TipoError()));
    }
   

    public static Set<NodoTipo> enumToTipo(Set<TiposEnum> enums) {
        Set<NodoTipo> res = new HashSet<>();
        for (TiposEnum enumerado : enums) {
            switch (enumerado) {
                case ENTERO: 
                    res.add(new Entero());
                break;
                case BOOLEANO: 
                    res.add(new Booleano());
                break;
                case ARRAY: 
                    res.add(new Array());
                break;
                case PUNTERO: 
                    res.add(new Puntero());
                break;
                case FUNCIONAL: 
                    res.add(new Funcional());
                break;
                case STRUCT: 
                    res.add(new Struct());
                break;
                case CLASE: 
                    res.add(new Clase());
                break;
                case VOID: 
                    res.add(new Void());
                break;
                case T: 
                    res.add(new TipoGenerico());
                break;
                case ERROR:
                    res.add(new TipoError());
                default:
                    res.add(new TInstruccion(enumerado));
                break;
            }
        }
        return res;
    }

    public static Set<NodoTipo> unirConjuntos(Set<NodoTipo> conjunto1, Set<NodoTipo> conjunto2) {
        LinkedHashSet<NodoTipo> listaTipos = new LinkedHashSet<>();
        for (NodoTipo tipo : conjunto1) {
            listaTipos.add(tipo);
        }
        for (NodoTipo tipo : conjunto2) {
            listaTipos.add(tipo);
        }
        return listaTipos;
    }

        
}

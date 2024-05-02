package ast.Tipos;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ast.Declaracion;
import ast.Expresiones.E;
import ast.Operadores.UnOperador.*;
import errors.GestionErroresExp;
import ast.Operadores.MultiOperadores.*;
import ast.Operadores.BinOperadores.*;


public class Tipado {
    public enum TiposEnum {
    ENTERO, BOOLEANO, ARRAY, PUNTERO, FUNCIONAL, STRUCT, CLASE,  LISTLAL, VOID, LISTAC, ERROR,
    
    ;
    } 
    public static final Set<TiposEnum> TODOS = EnumSet.allOf(TiposEnum.class);
    public static final Set<TiposEnum> TIPOS_PUNTERO = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY));
    public static final Set<TiposEnum> TIPOS_RETURN = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.VOID));
    public static final Set<TiposEnum> TIPOS_COMPARABLES = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO)); 
    public static final Set<TiposEnum> TIPOS_CONTENIDO_ARRAY = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE));
    public static final Set<TiposEnum> TIPOS_ATRIBUTOS = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE)); 
    public static final Set<TiposEnum> TIPOS_IMPRIMIBLES = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO));

    private static GestionErroresExp errores = new GestionErroresExp();

    private static void comprobarErrores(Set<NodoTipo> tiposResultado, Set<List<TiposEnum>> tipado, int fila, int columna) {
        //Si no hay ningún match
        if (tiposResultado.isEmpty()) {
            Tipado.errores.errorTipado(tipado, fila, columna); 
            tiposResultado = new HashSet<>(Collections.singleton(new TipoError()));
        }
        else {
            //Tomamos un tipo cualquiera del conjunto de resultados
            NodoTipo tipo = tiposResultado.iterator().next();
            //Si hay más de un match y no es un tipo funcional, es ambiguo
            if (tiposResultado.size() > 1 && !tipo.typeToEnum().equals(TiposEnum.FUNCIONAL)){
                Tipado.errores.errorAmbiguo(tipado, fila, columna); 
                tiposResultado = new HashSet<>(Collections.singleton(new TipoError()));
                /* La comprobación de ambigüedad de funciones con mismo tipado se realizará al invocar la función */
            }
        }
    }


    public static Set<NodoTipo> matchEUn(Set<NodoTipo> tOpnd, OperadorUn operador) {
        /* Comprueba que los tipos del operador encajan con los del operando y devuelve un conjunto con el NodoTipo esperado */

        if(tOpnd.isEmpty())
            return conjuntoError();
        

        Set<NodoTipo> tiposResultado = new HashSet<NodoTipo>();
        Set<List<TiposEnum>> tipadoExpresion = operador.getTipado();
        //Hacemos match de los tipos posibles del operando con los tipos posibles del operador
        for (NodoTipo t : tOpnd) {
            if(t.typeToEnum().equals(TiposEnum.ERROR))
                return conjuntoError();
            for (List<TiposEnum> listaTipos : tipadoExpresion) {
                //Si el tipo del operando coincide con el tipo del parámetro del operador unario, añadimos el match
                if (listaTipos.get(0).equals(t.typeToEnum())) 
                    //El tipo de devolución vendrá según el operador
                    switch(operador.toString()) {
                        case AccesoPuntero.OPSTRING: 
                            tiposResultado.add(((Puntero) t).getTipo());
                        break;
                        case Mas.OPSTRING: 
                        case MasMas.OPSTRING: 
                        case Menos.OPSTRING: 
                        case MenosMenos.OPSTRING: 
                        case Negacion.OPSTRING: 
                            tiposResultado.add(t);
                        break;
                    }
            }
        }
        comprobarErrores(tiposResultado, tipadoExpresion, operador.getFila(), operador.getColumna());
        return tiposResultado;
    }

    public static Set<NodoTipo> matchEBin(Set<NodoTipo> tOpnd1, Set<NodoTipo> tOpnd2, OperadorBin operador, String idOp2) {
        /* Comprueba que los tipos del operador encajan con los de los operandos y devuelve un conjunto con el NodoTipo esperado */
        if(tOpnd1.isEmpty())
            return conjuntoError();
        
        Set<NodoTipo> tiposResultado = new HashSet<NodoTipo>();
        Set<List<TiposEnum>> tipadoExpresion = operador.getTipado();
        //Hacemos match de los tipos posibles del operando con los tipos posibles del operador
        for (NodoTipo t1 : tOpnd1) {
            if(t1.typeToEnum().equals(TiposEnum.ERROR))
                return conjuntoError();
            for (List<TiposEnum> listaTipos : tipadoExpresion) {
                TiposEnum enum1 = t1.typeToEnum();
                //Si el tipo del primer operando coincide con el tipo del primer parámetro del operador binario...
                if (listaTipos.get(0).equals(enum1)) 
                    //El tipo de devolución vendrá según el operador
                    switch(operador.toString()) {
                        case AccesoArray.OPSTRING:
                            for (NodoTipo t2 : tOpnd2) {
                                if(t2.typeToEnum().equals(TiposEnum.ERROR))
                                    return conjuntoError();
                                //...y el del segundo operando coincide con el del segundo parámetro (int)
                                if (listaTipos.get(1).equals(t2.typeToEnum()))
                                    tiposResultado.add(((Array) t1).getTipo());
                            }
                        break;
                        case AccesoCampo.OPSTRING: 
                            Map<String,Set<Declaracion>> mapaCampos = ((TipoNuevo) t1).getCampos();
                            if(!mapaCampos.containsKey(idOp2))
                                errores.errorNoHayCampo(idOp2, operador.getFila(), operador.getColumna());
                            else {
                                for(Declaracion vinculo : mapaCampos.get(idOp2)) {
                                    NodoTipo tCampo = vinculo.getTipo();
                                    if(tCampo.typeToEnum().equals(TiposEnum.ERROR))
                                        return conjuntoError();
                                    //Por cada declaración en esa entrada del mapa, la añadimos al conjunto de matches si coincide con el tipo del segundo parámetro
                                    //(pueden ser varias por sobrecarga de funciones)
                                    if (listaTipos.get(1).equals(tCampo.typeToEnum()))
                                        tiposResultado.add(tCampo);
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
                            //...y el del segundo operando coincide con el del segundo parámetro (int o boolean)
                            for (NodoTipo t2 : tOpnd2) {
                                if(t2.typeToEnum().equals(TiposEnum.ERROR))
                                    return conjuntoError();
                                if (listaTipos.get(1).equals(t2.typeToEnum()))
                                    tiposResultado.add(t1);
                            }
                        break;
                        // tComparable -> tComparable -> boolean 
                        case Igual.OPSTRING:
                        case Distinto.OPSTRING:
                        case MenorQue.OPSTRING:
                        case MenorIgual.OPSTRING:
                        case MayorQue.OPSTRING:
                        case MayorIgual.OPSTRING:
                            
                            //...y el segundo operando es del mismo tipo tComparable
                            for (NodoTipo t2 : tOpnd2) {
                                if(t2.typeToEnum().equals(TiposEnum.ERROR))
                                    return conjuntoError();
                                if(enum1.equals(t2.typeToEnum()))
                                    tiposResultado.add(new Booleano());
                            }
                        break;
                    }
            }
        }
        comprobarErrores(tiposResultado, tipadoExpresion, operador.getFila(), operador.getColumna());
        return tiposResultado;
    }
    public static Set<NodoTipo> matchFuncion(Set<NodoTipo> tFuncion, List<Set<NodoTipo>> tParametros) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchFuncion'");
    }
    public static Set<NodoTipo> matchECampo(Set<NodoTipo> type, Set<NodoTipo> type2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchECampo'");
    }
    public static void matchAsig(Set<NodoTipo> type, Set<NodoTipo> type2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchAsig'");
    }
    public static NodoTipo matchAtributo(Set<NodoTipo> tipos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchAtributo'");
    }
    public static NodoTipo matchElemArray(Set<NodoTipo> tipos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchElemArray'");
    }
    public static void matchEBool(E cond) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchEBool'");
    }
    public static void matchReturn(E valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchReturn'");
    }
    public static void matchENum(E cond) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matchENum'");
    }

    public static Set<NodoTipo> conjuntoError() {
        return Collections.singleton(new TipoError());
    }
}

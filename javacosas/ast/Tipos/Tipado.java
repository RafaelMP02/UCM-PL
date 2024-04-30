package ast.Tipos;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Tipado {
    public enum TiposEnum {
    ENTERO, BOOLEANO, ARRAY, PUNTERO, FUNCIONAL, STRUCT, CLASE, VOID, T /* tipo general por si se acepta cualquier tipo FIXME */
    } 
    public static  final Set<TiposEnum> TODOS = EnumSet.allOf(TiposEnum.class);
    public static final Set<TiposEnum> TIPOS_PUNTERO = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY));
    public static final Set<TiposEnum> TIPOS_RES_FUNCIONES = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.ARRAY, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.VOID));
    public static  final Set<TiposEnum> TIPOS_COMPARABLES = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO)); 
    public static final Set<TiposEnum> TIPOS_CONTENIDO_ARRAY = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE));
    public static final Set<TiposEnum> TIPOS_CON_CAMPOS = new HashSet<>(Arrays.asList(TiposEnum.STRUCT, TiposEnum.CLASE));
    public static final Set<TiposEnum> TIPOS_CAMPOS = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO, TiposEnum.STRUCT, TiposEnum.CLASE, TiposEnum.FUNCIONAL)); 
    public static final Set<TiposEnum> TIPOS_IMPRIMIBLES = new HashSet<>(Arrays.asList(TiposEnum.ENTERO, TiposEnum.BOOLEANO, TiposEnum.PUNTERO));
}

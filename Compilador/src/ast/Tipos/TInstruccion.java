package ast.Tipos;

import ast.Tipos.Tipado.TiposEnum;

public class TInstruccion extends TipoParametrizable {
    /* Tipos de las instrucciones. Si es una declaración o una definición de una función debe tener el tipo de la variable/función. */
    private TiposEnum tEnum;

    public TInstruccion(TiposEnum tEnum) { //Si no es una declaración o una definición, el tipo es nulo.
        super(null);
        this.tEnum = tEnum;
    }
    public TInstruccion(TiposEnum tEnum, NodoTipo t, int fila, int columna) {
        super(t, fila, columna);
        this.tEnum = tEnum;
    }

    public TInstruccion(TiposEnum tEnum, NodoTipo t) {
        super(t);
        this.tEnum = tEnum;
    }

    @Override
    public TiposEnum typeToEnum() {
        return tEnum;
    }

    @Override
    public String toString() {
        switch (tEnum){
            case ASIGNACION: return "ASIGNACIÓN";
            case DECVARIABLE: return "DECVARIABLE";
            case DECFUNCION: return "DECFUNCION";
            case DEFCLASE: return "DEFCLASE";
            case DEFSTRUCT: return "DEFSTRUCT";
            case RETURN: return "RETURN";
            case ERROR: return "INSTRUCCIÓN ERRÓNEA";
            default: return "INSTRUCCIÓN";
        }
    }

}
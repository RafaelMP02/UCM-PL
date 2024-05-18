package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.Puntero;

public class NullPtr extends EBasica{
    private final static String NULL_STRING = "NULL";
    public NullPtr(int fila, int columna) {
        super(fila, columna);
        this.tipo = new Puntero();
        this.expString = NULL_STRING;
        exp = KindE.CONST;
    }

    @Override
    public String codeE(Comp hcon) {
        //return "i32.const 0\n";
        return null; //TODO
    }
}

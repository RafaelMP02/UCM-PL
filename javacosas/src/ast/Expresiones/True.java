package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.Booleano;

public class True extends EBasica{
    private final static String TRUE_STRING = "TRUE";
    public True(int fila, int columna) {
        super(fila, columna);
        this.tipo = new Booleano();
        this.expString = TRUE_STRING;
        exp = KindE.CONST;
    }

    @Override
    public String codeE(Comp hcon) {
        return "i32.const 0\n";
    }

}


package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.Booleano;

public class False extends EBasica{
    private final static String FALSE_STRING = "FALSE";
    public False(int fila, int columna) {
        super(fila, columna);
        this.tipo = new Booleano();
        this.expString = FALSE_STRING;
        exp = KindE.CONST;
    }

    @Override
    public String codeE(Comp hcon) {
        return "i32.const 1\n";
    }
}

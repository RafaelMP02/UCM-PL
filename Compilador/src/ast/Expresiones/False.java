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
        return "i32.const 0\n";
    }

    @Override
    public String codeB(Comp hcon){
        StringBuilder s = new StringBuilder();
        s.append("local.get $i\n").append(this.codeE(hcon)).append("i32.store\n");
        s.append("local.get $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("local.set $i\n");

        return s.toString();
    }
}

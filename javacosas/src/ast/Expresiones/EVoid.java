package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.Void;


public class EVoid extends EBasica {

    public EVoid(int fila, int columna) {
        super(fila, columna);
        this.tipo = new Void();
    }

    @Override
    public String codeE(Comp hcon) {
        return "";
    }

    public String toString() {
        return " ";
    }

}



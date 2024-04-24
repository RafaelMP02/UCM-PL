package ast.Expresiones;

import ast.KindE;

public class Identificador extends E {
    private String v;
    public Identificador(String v) {
        this.v = v;
    }
    public String num() {return v;}
    public KindE kind() {return KindE.Iden;}
    public String toString() {return v;}
}


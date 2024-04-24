package ast.Tipos;

import ast.Vinculacion.Vinculacion;

public class Booleano extends Tipo{
    public Booleano(){}
    public String toString() {
        return "BOOL";
    }
    @Override
    public void bind(Vinculacion vinc) {}
}

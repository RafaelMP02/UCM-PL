package ast.Tipos;

import ast.Vinculacion.Vinculacion;

public class Entero extends Tipo{
    public Entero(){}

    public String toString() {
        return "ENTERO";
    }

    @Override
    public void bind(Vinculacion vinc) {}
}

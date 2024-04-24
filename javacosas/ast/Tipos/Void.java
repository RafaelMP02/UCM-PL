package ast.Tipos;

import ast.Vinculacion.Vinculacion;

public class Void extends Tipo{
    public Void(){}

    public String toString(){
        return "VOID";
    }

    @Override
    public void bind(Vinculacion vinc) {}
}

package ast.Tipos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.Tipos.Tipado.TiposEnum;

public class Void implements NodoTipo{
    public Void(){
    }

    public String toString(){
        return "VOID";
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.VOID;
    }
}

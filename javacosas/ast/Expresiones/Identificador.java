package ast.Expresiones;

import java.util.HashSet;
import java.util.Set;

import ast.Tipos.NodoTipo;
import ast.Declaracion;
import ast.LocatedNode;
import ast.Vinculacion.Vinculacion;

public class Identificador extends LocatedNode implements E {
    private String v;
    private Set<Declaracion> vinculos;

    public Identificador(String v, int fila, int columna) {
        super(fila, columna);
        this.v = v;
    }
    public String num() {return v;}
    public String toString() {return v;}

    public void bind(Vinculacion vinc) {
        /* Los identificadores se encargan de buscarse en la tabla de símbolos. Solo se llamará a id.bind() cuando no sea una declaración de el id. */
        vinculos = vinc.buscaId(v, fila, columna);
    }
    @Override
    public Set<NodoTipo> type() {
        /* Devolvemos los tipos del conjunto de nodos a los que está vinculado el id. Si es un id de campo, no se a vinculado, habrá que hacerlo ahora. */
        Set<NodoTipo> tipado = new HashSet<>();

        for(Declaracion vinculo : vinculos) {
            tipado.add(vinculo.getTipo());
        }

        return tipado;
    }
}


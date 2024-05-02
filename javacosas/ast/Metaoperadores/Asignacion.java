package ast.Metaoperadores;

import ast.Expresiones.E;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

import java.util.Set;

import ast.Instruccion;
import ast.TiposDeNodos;

public class Asignacion implements Instruccion  {
    private CabecerAsig cabecera;
    private E exp;

    public Asignacion(CabecerAsig c, E e) {
        this.cabecera = c;
        this.exp = e;
    }
    public TiposDeNodos nodeKind() {
        return TiposDeNodos.ASIGNACION;
    }

    public String toString() {
        return cabecera.toString() + " := " + exp.toString();
    }
    @Override
    public void bind(Vinculacion vinc) {
        exp.bind(vinc); //Vinculamos la segunda expresión primero para que no se use el elemento declarado en su asignación
        cabecera.bind(vinc);
    }
    @Override
    public Set<NodoTipo> type() {
        Tipado.matchAsig(exp.type(), cabecera.type());
        return null;
    }
}

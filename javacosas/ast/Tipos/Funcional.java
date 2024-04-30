package ast.Tipos;

import java.util.LinkedList;

import ast.Tipos.Tipado.TiposEnum;

public class Funcional extends NodoTipo{
    NodoTipo retorno;
    LinkedList<Parametrico> parametros;

    public Funcional(NodoTipo retorno, LinkedList<Parametrico> parametros){
        super(TiposEnum.FUNCIONAL);
        this.retorno = retorno;
        this.parametros = parametros;
    }

    public String toString() {
        return retorno.toString() + parametros.toString();
    }

}

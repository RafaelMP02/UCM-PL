package ast.Tipos;

import java.util.LinkedList;

public class Funcional extends Tipo{
    Tipo retorno;
    LinkedList<Parametrico> parametros;

    public Funcional(Tipo retorno, LinkedList<Parametrico> parametros){
        this.retorno = retorno;
        this.parametros = parametros;
    }

    public String toString() {
        return retorno.toString() + parametros.toString();
    }

}

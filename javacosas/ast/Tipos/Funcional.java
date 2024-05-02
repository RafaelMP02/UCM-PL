package ast.Tipos;

import java.util.LinkedList;
import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Funcional extends TipoParametrizable{
    LinkedList<Parametrico> parametros;
    List<TiposEnum> tipado;

    public Funcional(NodoTipo retorno, LinkedList<Parametrico> parametros){
        super(retorno);
        this.parametros = parametros;
    }

    public String toString() {
        return tipo.toString() + parametros.toString();
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.FUNCIONAL;
    }

    @Override 
    public boolean admiteSobrecarga() {
        return true;
    }
}

package ast.Tipos;

import java.util.Map;
import java.util.Set;

import ast.Declaracion;
import ast.Expresiones.Identificador;
import ast.Tipos.Tipado.TiposEnum;

public class Clase implements TipoNuevo{
    Identificador iden;
    private Map<String, Set<Declaracion>> mapaCampos; //Almacena la tabla de símbolos del cuerpo de la clase

    public Clase(Identificador iden) {
        this.iden = iden;
    }
    public String toString() {
        return "CLASE " + iden.toString();
    }

    @Override
    public void setCampos(Map<String,Set<Declaracion>> mapa) {
        this.mapaCampos = mapa;
    }

    @Override
    public Map<String, Set<Declaracion>> getCampos() {
        return this.mapaCampos;
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.CLASE;
    }
}

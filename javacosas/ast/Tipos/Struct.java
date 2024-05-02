package ast.Tipos;

import java.util.Map;
import java.util.Set;

import ast.Declaracion;
import ast.Expresiones.Identificador;
import ast.Tipos.Tipado.TiposEnum;

public class Struct implements TipoNuevo{
    Identificador iden;
    private Map<String, Set<Declaracion>> mapaCampos;

    public Struct(Identificador iden) {
        this.iden = iden;
    }
    public String toString() {
        return "STRUCT " + iden.toString();
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
        return TiposEnum.STRUCT;
    }
}

package ast.Expresiones;

import ast.KindE;
import ast.Vinculacion.Vinculacion;

public class ECampo implements E{ // FIXME esto hay que cambiarlo para que sea para clases también? y que se pueda acceder al campo de un campo
    private E objeto;

    private Identificador campo;

    public ECampo(E o, Identificador campo) {
        this.objeto = o;
        this.campo = campo;
    }

    public KindE kind() {
        return KindE.AccesoCampo;
    }
    public String toString() {
        return objeto.toString() + "." + campo.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        // TODO aquí?
    }
}

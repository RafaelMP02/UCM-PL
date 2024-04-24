package ast;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;

public class DefClase extends Definicion{
    /* Define la clase, es decir, sus métodos y atributos. Pero no la instancia. */
    Identificador nombre;
    Ambito ambito;

    public DefClase(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "CLASE " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        // TODO ??
    }
}

package ast;

import java.util.Set;

import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Tipos.TipoNuevo;
import ast.Vinculacion.Vinculacion;

public class DecVariable extends Declaracion  {
    /* Declara una variable de cualquier tipo (incluso funcional) */

    public DecVariable(NodoTipo t, Identificador id){
        super(t, id);
    }

    @Override
    public String toString() {
        return " " + tipo.toString() + " " + id.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        
        //Si es un tipo clase o struct vemos si se ha definido
        if (Tipado.TIPOS_NUEVOS.contains(tipo.typeToEnum()))
            this.setTipo(vinc.buscarTipoNuevo(((TipoNuevo) tipo).toString(), tipo.getFila(), tipo.getColumna()));

        vinc.insertaId(id.toString(), this, id.getFila(), id.getColumna());

    }

    public void setTipo(NodoTipo tipo) {
        this.tipo = tipo;
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        /* Devolvemos el tipo de declaración que es (DECVARIABLE o DECFUNCION) para el tipado de la definición de clases y structs */
        return Tipado.matchDec(tipo, tiposEsperados, this.getFila(), this.getColumna());
    }

    @Override
    public String codeI(Comp hcon){
        hcon.insertaId(this);
        return "";
    }
}

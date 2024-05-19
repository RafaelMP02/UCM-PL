package ast;

import java.util.Set;

import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Tipos.TipoNuevo;
import ast.Vinculacion.Vinculacion;

public class DecVariable extends Declaracion  {
    /* Declara una variable de cualquier tipo  */
    protected Definicion def;

    public DecVariable(NodoTipo t, Identificador id){
        super(t, id); this.def = null;
    }

    @Override
    public String toString() {
        return " " + tipo.toString() + " " + id.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        
        //Si es un tipo clase o struct vemos si se ha definido
        if (Tipado.TIPOS_NUEVOS.contains(tipo.typeToEnum())) {
            this.setTipo(vinc.buscarTipoNuevo(((TipoNuevo) tipo).getId(), tipo.getFila(), tipo.getColumna()));
            this.def = ((TipoNuevo)this.tipo).getDef();
        }

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
        StringBuilder s = new StringBuilder();
        s.append("get_global $SP\n");
        s.append("i32.const ").append(4*tipo.getTam()).append("\n");
        s.append("i32.add\n");
        s.append("set_global $SP\n");
        return s.toString();
    }

    public Definicion getDef(){
        return def;
    }
}

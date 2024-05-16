package ast.Expresiones;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Declaracion;
import ast.Vinculacion.Vinculacion;

public class Identificador extends E {
    private String v;

    private boolean copy;
    private Set<Declaracion> vinculos;

    public Identificador(String v, int fila, int columna) {
        super(fila, columna);
        this.v = v;
        asignable = true;
        copy = false;
    }

    public Identificador(String v) { //Para crear Identificadores auxiliares
        super(-1, -1);
        this.v = v;
    }
    public String num() {return v;}
    public String toString() {return v;}

    public void bind(Vinculacion vinc) {
        /* Los identificadores se encargan de buscarse en la tabla de símbolos. Solo se llamará a id.bind() cuando no sea una declaración de el id. */
        vinculos = vinc.buscaId(v, fila, columna);
    }

    public void setVinculos(Set<Declaracion> vinculosId) {
        this.vinculos = vinculosId;
    }

    public Declaracion getVinculo(){
        Iterator<Declaracion> iterator = vinculos.iterator();
        return iterator.next();
    }

    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        /* Devolvemos los tipos del conjunto de nodos a los que está vinculado el id. Si es un id de campo, no se ha vinculado, habrá que hacerlo ahora. */
        return Tipado.matchIdentificador(this, vinculos, tiposEsperados);
    }

    @Override
    public String codeE(Comp hcon) {
        return this.codeD(hcon) + "\n i32.load\n";
    }

    public void setCopy(){this.copy = true;}

    public boolean getCopy(){return copy;}

    @Override
    public String codeD(Comp hcon) {
        String s = "";
        List<Integer> l = hcon.buscaId(this);
        s = s + Integer.toString(l.get(0)) + "\n";
        s = s + "get_global MP\n";
        s = s + "set_global MP_a\n";
        for(int i = 0; i < l.get(l.size()-1); i++) {
            s = s + "get_global MP_a\n";
            s = s + "i32.load\n";
            s = s + "set_global MP_a\n";
        }
        s = s + "get_global MP_a\n";
        s = s + "i32.add\n";
        hcon.setTam(vinculos.iterator().next().getTam());

        return s;
    }
}


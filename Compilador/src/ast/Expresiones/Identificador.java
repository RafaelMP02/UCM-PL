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
        this.exp = KindE.CONST;
    }

    public Identificador(String v) { //Para crear Identificadores auxiliares
        super(-1, -1);
        this.v = v;
        this.exp = KindE.CONST;
        asignable = true;
        copy = false;
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
        Set<NodoTipo> a = Tipado.matchIdentificador(this, vinculos, tiposEsperados);
        if(a != null){
            Iterator<NodoTipo> it = a.iterator();
            if(it.hasNext()) {
                tipo = it.next();
            }
        }

        return a;
    }

    @Override
    public String codeE(Comp hcon) {
        return this.codeD(hcon) + "(i32.load)\n";
    }

    public void setCopy(){this.copy = true;}

    public boolean getCopy(){return copy;}

    @Override
    public String codeD(Comp hcon) {
        StringBuilder s = new StringBuilder();
        int c = hcon.buscaId(this);
        if(c < -1) {
            s.append("local.get ").append(hcon.localId(this)).append("\n");
        } else {
            s.append("i32.const ").append(c).append("\n");
        }
        hcon.setTam(vinculos.iterator().next().getTam());

        return s.toString();
    }

    @Override
    public String codeB(Comp hcon){
        StringBuilder s = new StringBuilder();
        s.append(this.codeD(hcon)).append("local.get $i\n").append("i32.const ").append(4*tipo.getTam()).append("\n");
        s.append("call $copy_memory\n");
        s.append("local.get $i\n").append("i32.const ").append(4*tipo.getTam()).append("\n").append("i32.add\n").append("local.set $i\n");

        return s.toString();
    }
}


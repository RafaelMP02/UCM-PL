package ast;

import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.NodoTipo;
import ast.Tipos.Puntero;
import ast.Tipos.Tipado;

public abstract class Declaracion implements Instruccion, CabecerAsig {
    protected NodoTipo tipo;
    protected Identificador id;

    public Declaracion(NodoTipo t, Identificador id) {
        this.tipo = t;
        this.id = id;
    }

    public int getFila() {
        return this.tipo.getFila();
    }

    public int getColumna() {
        return this.tipo.getColumna();
    }

    public NodoTipo getTipo() {
        return this.tipo;
    }

    public Identificador getId() {
        return this.id;
    }

    @Override
    public boolean esAsignable() {
        return true;
    }

    public String codeD(Comp hcon){
        if(tipo.typeToEnum() == Tipado.TiposEnum.PUNTERO) {
            hcon.setTam(((Puntero)tipo).getTipo().getTam());
        }
        hcon.insertaId(this);
        return "";
    }

    public int getTam(){
        return tipo.getTam();
    }

    @Override
    public NodoTipo tip(){return tipo;}

}

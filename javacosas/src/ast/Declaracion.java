package ast;

import ast.Expresiones.Identificador;
import ast.GeneracionCodigo.Comp;
import ast.Metaoperadores.CabecerAsig;
import ast.Tipos.NodoTipo;
import ast.Tipos.Puntero;
import ast.Tipos.Tipado;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

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

    @Override
    public void recoleccionAtributos(LinkedHashMap<String,LinkedHashSet<Declaracion>> mapa_actual) {
        if(mapa_actual.containsValue(id.toString())) {
            mapa_actual.get(id.toString()).add((this));
        } else {
            mapa_actual.put(id.toString(), new LinkedHashSet<>());
            mapa_actual.get(id.toString()).add((this));
        }
    }

}

package ast.Tipos;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ast.Declaracion;
import ast.Definicion;
import ast.Expresiones.Identificador;

public abstract class TipoNuevo implements NodoTipo{
    /* Clase abstracta para aquellos nodos que crean tipos (clases y structs) */
    protected Identificador iden;

    protected Definicion def;
    protected Map<String, LinkedHashSet<Declaracion>> mapaCampos; //Almacena la tabla de símbolos del cuerpo de la clase
    protected List<Set<NodoTipo>> tiposAtributos; //Lista de tipos de los atributos
    protected final static String DEFAULT_ID = "NombreTipo";

    protected int tam;

    public TipoNuevo() {

    }

    public void setCampos(Map<String,LinkedHashSet<Declaracion>> mapa, Definicion def) {
        this.mapaCampos = mapa;
        this.def = def;
    }

    public Map<String, LinkedHashSet<Declaracion>> getCampos() {
        return this.mapaCampos;
    }

   
    public void setTiposAtributos(List<Set<NodoTipo>> lista) {
        this.tiposAtributos = lista;
        tam = 0;
        for(Set<NodoTipo> X: tiposAtributos) {
            NodoTipo N = X.iterator().next();
            tam = tam + N.getTam();
        }
    }

    public List<Set<NodoTipo>>  getTipos() {
        return this.tiposAtributos;
    }

    public String getId() {
        return this.iden.toString();
    }

    public int getTam(){
        return tam;
    }

    public Definicion getDef(){
        return def;
    }


}

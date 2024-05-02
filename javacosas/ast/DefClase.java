package ast;

import java.util.Set;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Tipos.Clase;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;

public class DefClase implements Definicion{
    /* Define la clase, es decir, sus métodos y atributos. Pero no la instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos.*/
    Identificador nombre;
    Ambito ambito;
    boolean descendiendo;
    private NodoTipo t;

    public DefClase(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
        t = new Clase(nombre);
    }

    public String toString() {
        return " DEFCLASE " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        ambito.bind(vinc);
        ((Clase) t).setCampos(vinc.cierraBloque()); //Guardan su tabla de símbolos al cerrarla en el nodo tipo
    }

    @Override
    public Set<NodoTipo> type() {
        Set<NodoTipo> tAmbito = ambito.type();

        for (NodoTipo tipo : tAmbito) {
            if (!Tipado.TIPOS_ATRIBUTOS.contains(tAmbito) )
        }
    }

}

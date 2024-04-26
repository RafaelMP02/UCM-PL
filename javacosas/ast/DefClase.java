package ast;

import java.util.HashMap;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;

public class DefClase extends Definicion{
    /* Define la clase, es decir, sus métodos y atributos. Pero no la instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos.*/
    Identificador nombre;
    Ambito ambito;
    boolean descendiendo;
    private HashMap<String, ASTNode> cuerpo; //Almacena la tabla de símbolos del cuerpo de la clase

    public DefClase(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "CLASE " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.insertaId(nombre.toString(), this);
        vinc.abreBloque();
        ambito.bind(vinc);
        cuerpo = vinc.cierraBloque();
    }
}

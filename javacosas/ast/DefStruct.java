package ast;

import java.util.HashMap;

import ast.Expresiones.Identificador;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;

public class DefStruct extends Definicion {
    /* Se definde el struct, es decir sus atributos. Pero no se instancia. 
    Los ámbitos de las claves son globales, así que  no apilan una nueva tabla de símbolos. */
    Identificador nombre;
    Ambito ambito;
    private HashMap<String, ASTNode> cuerpo;

    public DefStruct(Identificador nombre, Ambito ambito) {
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public String toString() {
        return "STRUCT " + nombre.toString() + ambito.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.insertaId(nombre.toString(), this);
        vinc.abreBloque();
        ambito.bind(vinc);
        cuerpo = vinc.cierraBloque();
    }
}

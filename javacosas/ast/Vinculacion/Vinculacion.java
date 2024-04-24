package ast.Vinculacion;

import errors.GestionErroresExp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import ast.ASTNode;
import ast.Expresiones.Identificador;

public class Vinculacion {
    /*
    La tabla guardará pares (String, ASTNode), cuando busquemos un nodo lo haremos
    llamando a buscaId(id.toString()) (se llama al toString de manera automática)
    */
    private List<HashMap<Identificador, ASTNode>> pilaDeTablas; //Es una pila conceptual, lo implementamos como lista porque es más eficiente
    private GestionErroresExp errores;

    public Vinculacion () {
        this.pilaDeTablas = new ArrayList<HashMap<Identificador, ASTNode>>();
        this.errores = new GestionErroresExp();
    }

    public void abreBloque() {
        pilaDeTablas.add(new HashMap<Identificador, ASTNode>());
    }

    public void cierraBloque() {
        /*
        Eliminas la tabal de símbolos de ese ámbito (el último) porque se ha cerrado
         */
        pilaDeTablas.remove(pilaDeTablas.size() - 1);
    }

    public void insertaId(Identificador id, ASTNode puntero) {
        /*
        Añadimos un par (id,puntero) a la tabla de símbolos del ámbito actual
         */
        pilaDeTablas.get(pilaDeTablas.size() - 1).put(id, puntero);
    }

    public List<ASTNode> buscaId (Identificador id, int fila, int columna) {
        boolean encontrado = false;
        List<ASTNode> punteros = new ArrayList<>();
        /*
        Buscamos en la pila recorriéndola desde el final hasta el comienzo. 
        No paramos cuando lo encontramos pues podría haber sobrecarga. Devolvemos una lista de punteros.
        */
        for (int i = pilaDeTablas.size()-1; i >= 0; i--) {
            if (pilaDeTablas.get(i).containsKey(id)) {
                punteros.add(pilaDeTablas.get(i).get(id));
                encontrado = true;
            }
        }
        //Si no estaba en ninguna tabla, lanzamos error
        if (!encontrado) {
            errores.errorVinculacion(fila, columna, id.toString());
        }
        return punteros;
    }
}

package ast.FuncionesPrimitivas;

import ast.Expresiones.E;
import ast.Metaoperadores.Ambito;
import ast.Vinculacion.Vinculacion;
import ast.LocatedNode;
import ast.NodeKind;
import ast.Programa;

public class Switch extends LocatedNode implements Programa {
    private E cond;
    private Case caso;

    private Ambito ambito;

    private Programa programa;

    public Switch(E cond, Ambito amb, Programa p, Case caso, int fila, int columna) {
        super(fila, columna);
        this.cond = cond;
        this.ambito = amb;
        this.programa = p;
        this.caso = caso;
    }

    public NodeKind nodeKind() {
        return NodeKind.SWITCH;
    }


    public String toString() {
        if(cond != null) {
            if (caso != null) {
                return "SWITCH(" + cond.toString() + "){" + caso.toString() + "DEFAULT" + ambito.toString() + "}" + programa.toString();
            } else {
                return "SWITCH(" + cond.toString() + "){" + "DEFAULT" + ambito.toString() + "}" + programa.toString();
            }
        } else {
             return "SWITCH(#ERROR#)" + programa.toString();
        }
    }

    @Override
    public void bind(Vinculacion vinc) {
        cond.bind(vinc);

        vinc.abreBloque();
        caso.bind(vinc);    //Ámbitos de los posibles "case"
        ambito.bind(vinc);  //Ámbito del "default"
        vinc.cierraBloque();

        programa.bind(vinc);
    }
}

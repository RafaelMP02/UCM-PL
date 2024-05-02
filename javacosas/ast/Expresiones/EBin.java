package ast.Expresiones;
import java.util.Set;

import ast.Operadores.BinOperadores.OperadorBin;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;


public class EBin implements E {
    /* Expresión que incluye un operador binario como atributo */
   private E opnd1;
   private E opnd2;
   private OperadorBin operador;
   public EBin(E opnd1, E opnd2, OperadorBin operador) {
     this.opnd1 = opnd1;
     this.opnd2 = opnd2;
     this.operador = operador;
   }
   public E opnd1() {return opnd1;}
   public E opnd2() {return opnd2;}

   public OperadorBin operador() {return operador;}


    public String toString() {
        return operador.toString() + "(" + opnd1.toString() + " , " + opnd2.toString() + ")";
    }

    @Override
    public void bind(Vinculacion vinc) {
        opnd1.bind(vinc);
        opnd2.bind(vinc);
    }

    @Override
    public Set<NodoTipo> type() {
        /* Se le pasa el toString del operando dos para el caso de que sea un acceso a campo.
        Necesitaremos buscar el id en la tabla de símbolos de la clase o el struct.*/
        return Tipado.matchEBin(opnd1.type(), opnd2.type(), operador, opnd2.toString());
    }
}

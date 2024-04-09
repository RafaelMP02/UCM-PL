package ast.Expresiones;
import ast.KindE;
import ast.Operadores.BinOperadores.OperadorBin;


public class EBin extends E {
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

    public KindE kind() {
        return operador.kind();
    }

    public String toString() {
        return operador.toString() + "(" + opnd1.toString() + " , " + opnd2.toString() + ")";
    }
}

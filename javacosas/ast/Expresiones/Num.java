package ast.Expresiones;

import ast.KindE;
import ast.LocatedNode;
import ast.Vinculacion.Vinculacion;

public class Num extends LocatedNode implements E{
  private String v;
  public Num(String v, int fila, int columna) {
    super(fila, columna);
   this.v = v;   
  }
  public String num() {return v;}
  public KindE kind() {return KindE.Num;}
  public String toString() {return v;}

  @Override
  public void bind(Vinculacion vinc) {}  
}

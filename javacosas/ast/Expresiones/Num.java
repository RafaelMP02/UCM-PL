package ast.Expresiones;

import ast.LocatedNode;

public class Num extends LocatedNode implements E{
  private String v;
  public Num(String v, int fila, int columna) {
    super(fila, columna);
   this.v = v;   
  }
  public String num() {return v;}
  public String toString() {return v;}

}

package ast.Expresiones;

import ast.KindE;

public class Num extends E{
  private String v;
  public Num(String v) {
   this.v = v;   
  }
  public String num() {return v;}
  public KindE kind() {return KindE.Num;}
  public String toString() {return v;}  
}

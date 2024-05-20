package ast.Expresiones;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.Entero;

public class Num extends EBasica{
  
  public Num(String v, int fila, int columna) {
    super(fila, columna);
    this.tipo = new Entero();
    this.expString = v;
    exp = KindE.CONST;
  }

  public Num(String v) {
    super();
    this.expString = v;
  }

  public Num() {
    super(-1,-1);
    this.expString = "-1";
  }

  @Override
  public String codeE(Comp hcon) {
    return "i32.const " + expString + "\n";
  }

  public int num() {return Integer.parseInt(expString);}
}

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


  @Override
  public String codeB(Comp hcon){
    StringBuilder s = new StringBuilder();
    s.append("local.get $i\n").append(this.codeE(hcon)).append("i32.store\n");
    s.append("local.get $i\n").append("i32.const ").append(4).append("\n").append("i32.add\n").append("local.set $i\n");

    return s.toString();
  }
}

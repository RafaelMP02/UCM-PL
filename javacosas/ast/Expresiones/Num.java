package ast.Expresiones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.LocatedNode;
import ast.Tipos.Booleano;
import ast.Tipos.Entero;
import ast.Tipos.NodoTipo;

public class Num extends LocatedNode implements E{
  private String v;
  public Num(String v, int fila, int columna) {
    super(fila, columna);
   this.v = v;   
  }
  public String num() {return v;}
  public String toString() {return v;}

  @Override
    public Set<NodoTipo> type() {
        return new HashSet<NodoTipo>(Collections.singleton(new Entero()));
    }
}

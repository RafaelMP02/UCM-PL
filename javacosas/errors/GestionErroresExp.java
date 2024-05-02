package errors;

import java.util.List;
import java.util.Set;

import alex.UnidadLexica;
import ast.Tipos.Tipado.TiposEnum;

public class GestionErroresExp {
  public void errorLexico(int fila, int columna, String lexema) {
     System.out.println("ERROR fila "+fila+" columna "+columna+": Caracter inesperado: "+lexema); 
     //System.exit(1);
   }  
  public void errorSintactico(UnidadLexica unidadLexica) {
     if (unidadLexica.lexema() != null) {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado \""+unidadLexica.lexema()+"\"");
     } else {
       System.out.println("ERROR fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+": Elemento inesperado");
     }
  }

  public void errorNoDeclarado(int fila, int columna, String lexema) {
    System.out.println("ERROR fila "+fila+" columna "+columna+": Identificador no declarado: "+lexema); 
  }

  public void errorYaDeclarado(int fila, int columna, String lexema) {
    System.out.println("ERROR fila "+fila+" columna "+columna+": Identificador ya declarado: "+lexema); 
  }
public void errorTipado(Set<List<TiposEnum>> tOperador, int fila, int columna) {
  System.out.println("ERROR fila "+fila+" columna "+columna+": Error de tipado, se esperaba : " ); 
  StringBuilder strBuilder = new StringBuilder();
  for (List<TiposEnum> listaTipos : tOperador) {
    //Imprimimos todos los tipos menos el de devolución
    strBuilder.append(listaTipos.get(0));
    for (int i = 1 ; i < listaTipos.size() - 1; i++) {
      strBuilder.append(" -> " + listaTipos.get(i));
    }
    
  }
  System.out.println(strBuilder);
}
public void errorAmbiguo(Set<List<TiposEnum>> tOperador, int fila, int columna) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'errorAmbiguo'");
}
public void errorNoHayCampo(String idOp2, int fila, int columna) {
  // TODO Auto-generated method stub
  throw new UnsupportedOperationException("Unimplemented method 'errorNoHayCampo'");
} 
}

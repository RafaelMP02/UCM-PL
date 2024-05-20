package errors;


import java.util.Set;

import alex.UnidadLexica;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado.TiposEnum;

public class GestionErroresExp {
  public static int NUM_ERRORES;
  private void addError() {
    NUM_ERRORES++;
  }

  public static void printNumErrores() {
    System.out.println("---\n\nNúmero de errores: " + NUM_ERRORES);
  }

  //LÉXICO

  public void errorLexico(int fila, int columna, String lexema) {
      System.out.println("[Fila " + fila + ", Columna " + columna + "] Caracter inesperado: \""+lexema+"\""); 
      addError();
  }
  
  //SINTÁCTICO

  public void errorSintactico(UnidadLexica unidadLexica) {
      if (unidadLexica.lexema() != null) {
        System.out.println("[Fila "+unidadLexica.fila()+", Columna "+unidadLexica.columna()+"] Elemento inesperado \""+unidadLexica.lexema()+"\"");
      } else {
        System.out.println("[Fila "+unidadLexica.fila()+", Columna "+unidadLexica.columna()+"] Elemento inesperado");
      }
      addError();
  }

  //VINCULACIÓN

  public void errIdYaDec(int fila, int columna, String lexema) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Identificador ya declarado: "+lexema); 
    addError();
  }

  public void errIdNoDec(int fila, int columna, String lexema) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Identificador no declarado: "+lexema); 
    addError();
  }
  
  public void errTipoYaDef(int fila, int columna, String lexema) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Tipo nuevo ya definido con ese nombre: "+lexema); 
    addError();
  }

  public void errTipoNoDef(int fila, int columna, String lexema) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Tipo nuevo no definido: "+lexema); 
    addError();
  }

  public void errNumArgumentos(String idFuncion, int fila, int columna) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Error en el número de argumentos de la función: " + idFuncion); 
    addError();
  }

  public void errNoMain() {
    System.out.println("[ERROR] No se ha declarado la función \"main\"");
    addError();
  }

  public void errMainDuplicado() {
    System.out.println("[ERROR] Se han declarado varias funciones \"main\"");
    addError();
  }

  //TIPADO

  public void errExpAmbigua(int fila, int columna) {
    /* Error de ambigüedad de expresión */
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Expresión ambigua"); 
    addError();
  }

  public void errTipadoExp(Set<NodoTipo> tiposEsperados, int fila, int columna) {
    /* Error de tipado para expresiones */
    System.out.print("[Fila " + fila + ", Columna " + columna + "] Error de tipado. Se esperaba uno de los siguientes tipos: "); 
    System.out.println(tEsperadosToString(tiposEsperados));
    addError();
  } 

  public void errorNoHayCampo(String campo, int fila, int columna) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] No existe el campo: " +  campo); 
    addError();
  }

  public void errTRetornoFuncion(Set<NodoTipo> tiposEsperados, int fila, int columna) {
    /* Piden un tipo de devolución de función imposible */
    System.out.print("[Fila " + fila + ", Columna " + columna + "] Tipo esperado imposible para retorno de función. Se esperaba uno de los siguientes tipos: " ); 
    System.out.println(tEsperadosToString(tiposEsperados));
    addError();
  }
  public void errTipadoDec(Set<NodoTipo> tiposEsperados, int fila, int columna) {
    /* Error de tipado para expresiones */
    System.out.print("[Fila " + fila + ", Columna " + columna + "] Error tipado declaración. Se esperaba uno de los siguientes tipos: "); 
    for (NodoTipo tipo : tiposEsperados)
      //Imprimimos todos los tipos menos el de devolución
      if (!tipo.typeToEnum().equals(TiposEnum.T))
        tiposEsperados.remove(tipo);
      System.out.println(tEsperadosToString(tiposEsperados));
    addError();
  }
  public void errFuncionYaDeclarada(String decString, int fila, int columna) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Error de ambigüedad. Función duplicada: " + decString); 
    addError();
  }
  public void errInstrNoValida(int fila, int columna) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Instrucción no válida"); 
    addError();
  }

  public void errAsignacion(int fila, int columna) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Asignación inválida: Expresión no asignable"); 
    addError();
  }
  public void errTamArray(int n, int fila, int columna) {
    System.out.println("[Fila " + fila + ", Columna " + columna + "] Error en el tamaño del array. Se esperaba: " + n); 
    addError();
  }


  private String tEsperadosToString(Set<NodoTipo> tiposEsperados) {
    StringBuilder strBuilder = new StringBuilder();
    for (NodoTipo tipo : tiposEsperados) {
      //Imprimimos todos los tipos menos el de devolución
      if (!tipo.typeToEnum().equals(TiposEnum.T) && !tipo.typeToEnum().equals(TiposEnum.ERROR))
        strBuilder.append(tipo.toString() + "\t");
    }
    return strBuilder.toString();    
  }


}

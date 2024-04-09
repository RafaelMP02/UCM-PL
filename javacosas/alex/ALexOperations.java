package alex;

import asint.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTiny alex;
  public ALexOperations(AnalizadorLexicoTiny alex) {
   this.alex = alex;   
  }
  public UnidadLexica unidadId() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN,
                                         alex.lexema()); 
  } 
  public UnidadLexica unidadEnt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENT,alex.lexema()); 
  } 
  public UnidadLexica unidadSuma() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAS,"+");
  }

  public UnidadLexica unidadOpMasMas() { return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OPMASMAS,"++"); }

    public UnidadLexica unidadOpMenosMenos() {return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OPMENOSMENOS,"--");  }
  public UnidadLexica unidadResta() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOS,"-"); 
  }  
  public UnidadLexica unidadDiv() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV,"/"); 
  } 
  public UnidadLexica unidadPAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PAP,"("); 
  } 
  public UnidadLexica unidadPCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRE,")"); 
  }
  
  public UnidadLexica unidadCAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CAP,"["); 
  } 
  public UnidadLexica unidadCCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CCIERRE,"]"); 
  } 
  public UnidadLexica unidadLlAp() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLAP,"{"); 
  } 
  public UnidadLexica unidadLlCierre() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLCIERRE,"}"); 
  } 
  public UnidadLexica unidadPComa() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCOMA,";"); 
  } 
  public UnidadLexica unidadComa() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA,","); 
  } 
  


  public UnidadLexica unidadAsig() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ASIG,":="); 
  } 
  public UnidadLexica unidadOpAsterisco() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OPASTERISCO,"*"); 
  } 
  public UnidadLexica unidadOpPunto() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OPPUNTO,"."); 
  } 
  public UnidadLexica unidadOpPasoValor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OPPASOVALOR,"$"); 
  } 
  public UnidadLexica unidadIgual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL,"=="); 
  } 
  public UnidadLexica unidadDistinto() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DISTINTO,"=/="); 
  } 
  public UnidadLexica unidadNegacion() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEGACION,"!"); 
  }
  public UnidadLexica unidadConjuncion() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CONJUNCION,"&&"); 
  } 
  public UnidadLexica unidadDisyuncion() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DISYUNCION,"||"); 
  }
  public UnidadLexica unidadMenorQue() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORQUE,"<"); 
  } 
  public UnidadLexica unidadMayorQue() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORQUE,">"); 
  } 
  public UnidadLexica unidadMenorIgual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORIGUAL,"<="); 
  } 
  public UnidadLexica unidadMayorIgual() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORIGUAL,">="); 
  } 
  public UnidadLexica unidadNew() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NEW,"new"); 
  } 
  public UnidadLexica unidadPrints() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PRINTS,"prints"); 
  } 
  public UnidadLexica unidadScans() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SCANS,"scans"); 
  } 
  
  
  public UnidadLexica unidadWhile() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.WHILE,"while"); 
  } 
  public UnidadLexica unidadFor() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FOR,"for"); 
  } 
  public UnidadLexica unidadSwitch() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SWITCH,"switch"); 
  } 
  public UnidadLexica unidadCase() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CASE,"case"); 
  } 
  public UnidadLexica unidadDefault() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DEFAULT,"default"); 
  } 
  public UnidadLexica unidadReturn() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RETURN,"return"); 
  } 
  public UnidadLexica unidadIf() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IF,"if"); 
  } 
  public UnidadLexica unidadElsif() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSIF,"elsif"); 
  } 
  public UnidadLexica unidadElse() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSE,"else"); 
  } 
  
  
  
  public UnidadLexica unidadClass() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CLASS,"class"); 
  } 
  public UnidadLexica unidadStruct() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STRUCT,"struct"); 
  } 
  public UnidadLexica unidadTypedef() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TYPEDEF,"typdef"); 
  } 
  public UnidadLexica unidadInt() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.INT,"int"); 
  } 
  public UnidadLexica unidadBool() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOL,"bool"); 
  } 
  public UnidadLexica unidadTrue() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TRUE,"true"); 
  } 
  public UnidadLexica unidadFalse() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FALSE,"false"); 
  } 
  
  

  public UnidadLexica unidadEof() {
     return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF,"<EOF>"); 
  }
}

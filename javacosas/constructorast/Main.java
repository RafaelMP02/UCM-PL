package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoExp;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input); //Te devuelve el input en tokens
	 ConstructorASTExp constructorast = new ConstructorASTExp(alex); //Te devuelve el AST a partir de los tokens
	 System.out.println(constructorast.parse().value);	//Para imprimir el árbol
	 //constructorast.bind();
 }
}   
   

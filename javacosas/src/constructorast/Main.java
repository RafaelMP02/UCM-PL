package constructorast;

import java.io.*;

import alex.AnalizadorLexicoExp;
import ast.NodoAST;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;
import ast.raiz;
import java_cup.runtime.Symbol;

public class Main {
	public static void main(String[] args) throws Exception {
		Main.clear(); //Comentar
		Reader input = new InputStreamReader(new FileInputStream(args[0]));

		//Traducir texto a tokens con el analizador léxico
		System.out.println("---\nLexical errors:");
		AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input); //Te devuelve el input en tokens

		//Construir el AST y análisis sintáctico
		System.out.println("---\nSintax errors:");
		ConstructorASTExp constructorast = new ConstructorASTExp(alex); //Te devuelve el AST a partir de los tokens
		NodoAST root = (NodoAST) constructorast.parse().value;
		System.out.println("---\nAST representation: \n" + root);	//Para imprimir el árbol

		//Binding
		Vinculacion vinc = new Vinculacion();
		System.out.println("---\nBinding errors:");
		root.bind(vinc);

		//TIPADO
		System.out.println("---\nType errors:");
		root.type(Tipado.enumToTipo(Tipado.TODOS));

		//Generacion de codigo
		BufferedWriter output = new BufferedWriter(new FileWriter(args[0]+".wat"));
		((raiz)root).compile(output);
	}

	public static void clear() {
		System.out.print("\033[H\033[2J"); 
		System.out.flush(); 
 	}
}   
   

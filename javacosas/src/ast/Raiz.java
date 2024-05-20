package ast;

import ast.GeneracionCodigo.Comp;
import ast.Tipos.NodoTipo;
import ast.Tipos.Tipado;
import ast.Vinculacion.Vinculacion;
import errors.GestionErroresExp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Raiz implements NodoAST{
    Programa programa;

    private Map<String, LinkedHashSet<Declaracion>> mapa;

    private GestionErroresExp errores;



    public  Raiz(Programa programa){
        this.programa = programa;
        this.errores = new GestionErroresExp();
    }

    public GestionErroresExp getErroresExp() {
        return this.errores;
    }
    @Override
    public Set<NodoTipo> type(Set<NodoTipo> tiposEsperados) {
        /* Devuelve el tipado de todas las instrucciones del programa. */
        Set<NodoTipo> tPrograma = programa.type(tiposEsperados);
        Tipado.matchAmbito(mapa, tPrograma); //Comprueba que no hay ambigüedad en las funciones
        return  tPrograma;
    }


    public Map<String, LinkedHashSet<Declaracion>> getMapa(){
        return mapa;
    }

    @Override
    public int getFila() {
        return programa.getFila();
    }

    @Override
    public int getColumna() {
        return programa.getColumna();
    }

    @Override
    public void bind(Vinculacion vinc) {
        vinc.abreBloque();
        programa.bindFunc(vinc);
        programa.bind(vinc);
        mapa = vinc.cierraBloque();
       if(!mapa.containsKey("main")){
           errores.errNoMain();
       } else {
           if(mapa.get("main").size() > 1){
               errores.errMainDuplicado();
           }
       }
    }

    public void setErrores(GestionErroresExp errores) {
        this.errores = errores;
    }

    @Override
    public String codeFunc(Comp hcon) {
        return programa.codeFunc(hcon);
    }

    void copyMem(StringBuilder s){  //para copiar en memoria
        s.append(
                "  (func $copy_memory (param $src i32) (param $dest i32) (param $n i32)\n").append(
                "    (local $i i32)\n").append(
                "    (local.set $i (i32.const 0))\n").append(
                "    (block \n").append(
                "      (loop \n").append(
                "        (br_if 1 (i32.ge_u (local.get $i) (local.get $n)))\n").append(
                "        (local.get $src)\n").append(
                "        (local.get $i)\n").append(
                "        (i32.const 4)\n").append(
                "        (i32.mul)\n").append(
                "        (i32.add)\n").append(
                "        (i32.load)\n").append(
                "        (local.get $dest)\n").append(
                "        (local.get $i)\n").append(
                "        (i32.const 4)\n").append(
                "        (i32.mul)\n").append(
                "        (i32.add)\n").append(
                "        (i32.store)\n").append(
                "        (local.set $i (i32.add (local.get $i) (i32.const 1)))\n").append(
                "        (br 0)\n").append(
                "      )\n").append(
                "    )\n").append(
                "  )\n");
    }

    void init(StringBuilder s){  //para copiar en memoria
        s.append("(module\n").append(
                "(import \"runtime\" \"print\" (func $print (param i32)))\n" ).append(
                "(import \"runtime\" \"read\" (func $read (result i32)))\n" ).append(
                "(import \"runtime\" \"read\" (func $read (type $_sig_ri32)))\n" ).append(
                "(memory 2000)\n" ).append(
                "(global $SP (mut i32) (i32.const 0)) ;; start of stack\n" ).append(
                "(global $MP (mut i32) (i32.const 0)) ;; mark pointer\n" ).append(
                "(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4\n" ).append(
                "(start $main)\n");
    }

    public void compile(BufferedWriter output){
        try {
            StringBuilder s = new StringBuilder();
            Comp hcon = new Comp(mapa);
           init(s);
           copyMem(s);
            s.append(programa.codeFunc(hcon));
            s.append(")\n");
            output.write(s.toString());
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String toString(){return  programa.toString();}
}

package ast.Operadores.UnOperador;

import ast.KindE;

public class MenosMenos extends OperadorUn{
    public MenosMenos(){
        exp = KindE.MenosMenos;
    }

    public String toString() {
        return "MenosMenos";
    }
}

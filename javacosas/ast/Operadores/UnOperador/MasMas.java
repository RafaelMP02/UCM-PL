package ast.Operadores.UnOperador;

import ast.KindE;

public class MasMas extends OperadorUn{
    public MasMas(){
        exp = KindE.MasMas;
    }

    public String toString() {
        return "MasMas";
    }
}

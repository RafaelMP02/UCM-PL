package ast.Operadores.UnOperador;

import ast.KindE;

public class MasMas extends OperadorUn{
    /* Operador incremento */
    public MasMas(){
        exp = KindE.MasMas;
    }

    public String toString() {
        return "MásMás";
    }
}

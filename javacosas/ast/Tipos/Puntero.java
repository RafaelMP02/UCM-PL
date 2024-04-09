package ast.Tipos;

public class Puntero extends Tipo{
    Tipo t;


    public Puntero(Tipo t) {
        this.t = t;
    }

    public String toString() {
        return t.toString() + "*";
    }
}

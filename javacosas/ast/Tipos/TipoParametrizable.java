package ast.Tipos;

public abstract class TipoParametrizable  implements NodoTipo {
    /* Clase abstracta para encapsular los tipos parametrizables:
     * Array<T> : donde T es el tipo del contenido del array
     * Funcional<T>: donde T es el tipo del resultado de la función
     * Puntero<T>: donde T es el tipo de la variable a la que apunta el puntero
     */
    protected NodoTipo tipo;

    public TipoParametrizable(NodoTipo t) {
        this.tipo = t;
    }

    public NodoTipo getTipo() { return this.tipo;}
}

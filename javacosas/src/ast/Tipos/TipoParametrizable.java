package ast.Tipos;

public abstract class TipoParametrizable  implements NodoTipo {
    /* Clase abstracta para encapsular los tipos parametrizables:
     * Array<T> : donde T es el tipo del contenido del array
     * Puntero<T>: donde T es el tipo de la variable a la que apunta el puntero
     */
    protected NodoTipo tipo;
    protected int fila;
    protected int columna;
    protected boolean inicializado;

    protected int tam;

    public TipoParametrizable(NodoTipo t, int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.tipo = t;
        this.inicializado = true;
    }

    public TipoParametrizable(NodoTipo t) {
        this.tipo = t;
        this.inicializado = true; 
    }

    public TipoParametrizable() {
        this.tipo = new TipoGenerico();
        this.fila = -1;
        this.columna = -1;
        this.inicializado = false;
    }

    public NodoTipo getTipo() { return this.tipo;}

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public boolean inicializado() {
        return this.inicializado;
    }

    @Override
    public int getTam(){return tam;}
}

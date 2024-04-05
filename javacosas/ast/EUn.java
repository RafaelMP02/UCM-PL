package ast;

public abstract class EUn extends E {
    private E opnd;

    public EUn(E opnd) {
        this.opnd = opnd;
    }
    public E opnd() {return opnd;}
}
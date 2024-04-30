package ast.Expresiones;

import ast.Vinculacion.Vinculacion;

public class ECampo implements E{ // FIXME esto hay que cambiarlo para que sea para clases también? y que se pueda acceder al campo de un campo
    private E objeto; //Objeto al que pertenece el campo

    private Identificador campo;

    public ECampo(E o, Identificador campo) {
        this.objeto = o;
        this.campo = campo;
    }

    public String toString() {
        return objeto.toString() + "." + campo.toString();
    }

    @Override
    public void bind(Vinculacion vinc) {
        /*
        Vinculación solo se encargará de encontrar si el objeto al que pertenece el campo existe. Esto lo hará recursivamente hasta llegar a un identificador. 
        En el tipado ya identificaremos si cada campo está bien tipado.
        */
        objeto.bind(vinc);
       //FIXME esto no sé si está bien
    }
}

package ast.Tipos;

import java.util.List;
import ast.Tipos.Tipado.TiposEnum;

public class Funcional implements NodoTipo {
    List<Parametrico> parametros;
    List<TiposEnum> tipado;
    NodoTipo tRetorno;

    public Funcional(NodoTipo tipo, List<Parametrico> parametros) {
        this.tRetorno = tipo;
        this.parametros = parametros;
    }

    public Funcional() {
        this.tRetorno = new TipoGenerico();
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (tRetorno != null)
            str.append(tRetorno.toString());
        if(parametros != null)
            str.append(parametros.toString());
        return str.toString();
    }

    public NodoTipo getTipoRetorno() {
        return tRetorno;
    }

    public List<Parametrico> getParametros() {
        return this.parametros;
    }

    @Override
    public TiposEnum typeToEnum() {
        return TiposEnum.FUNCIONAL;
    }

    @Override 
    public boolean admiteSobrecarga() {
        return true;
    }

    @Override
    public int getFila() {
        return this.tRetorno.getFila();
    }

    @Override
    public int getColumna() {
        return this.tRetorno.getColumna();
    }

    @Override
    public int getTam(){
        return 1;
    }
}

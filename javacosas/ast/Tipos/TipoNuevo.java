package ast.Tipos;

import java.util.Map;
import java.util.Set;

import ast.Declaracion;

public interface TipoNuevo extends NodoTipo{
    /* Interfaz para aquellos nodos que crean tipos (clases y structs) */
    public Map<String, Set<Declaracion>> getCampos();
    public void setCampos(Map<String, Set<Declaracion>> mapa);
}

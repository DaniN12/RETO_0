package controller;

import java.util.List;

public interface IController {
    /**
     * Obtiene la ruta del documento asociado a un enunciado.
     *
     * @param idEnunciado El ID del enunciado
     * @return La ruta del documento si existe, de lo contrario null
     */
    String obtenerRutaDocumentoEnunciado(int idEnunciado);

    /**
     * Obtiene las convocatorias en las que se ha utilizado un enunciado.
     *
     * @param idEnunciado El ID del enunciado
     * @return Lista de convocatorias en las que ha sido utilizado el enunciado
     */
    List<String> obtenerConvocatoriasDeEnunciado(int idEnunciado);
}


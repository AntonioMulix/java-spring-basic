package com.example.demo.util;

import lombok.Getter;

/**
 *
 * @author marco-romero
 */
/**
 * Enum Response
 *
 * Esta clase enum define un conjunto de respuestas estándar que se pueden usar
 * en toda la aplicación para manejar la comunicación entre el backend y el
 * frontend de manera consistente.
 *
 * Cada constante del enum representa un tipo de respuesta, con: - key: mensaje
 * amigable o descriptivo que puede mostrarse al usuario o consumirse en el
 * frontend. - code: código numérico que representa el estado de la operación,
 * generalmente mapeado a códigos HTTP.
 *
 * Beneficios de usar este enum: 1. Centraliza los mensajes y códigos de
 * respuesta, evitando duplicación de código. 2. Facilita el mantenimiento: si
 * cambia un mensaje o código, se actualiza en un solo lugar. 3. Permite
 * estandarizar la comunicación entre backend y frontend.
 *
 *
 * Nota: Se pueden agregar nuevas constantes según las necesidades del proyecto,
 * para cubrir diferentes escenarios de respuesta.
 *
 * Autor: Marco Romero Fecha: 27-01-26
 */
@Getter
public enum Response {
    OK("Proceso exitoso.", 200),
    CREATED("Creacion exitosa.", 200),
    UPDATE("Actualizacion exitosa.", 200),
    DELETED("Eliminacion exitosa,", 200),
    BADREQUEST("Error en su petición.", 200),
    NOTFOUND("No existen registros.", 404),
    INTERNALERROR("Error.", 500);

    private String key;
    private int code;

    private Response(String key, int code) {
        this.key = key;
        this.code = code;
    }

}

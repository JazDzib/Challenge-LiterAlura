package com.LiterAlura.LiterAlura.Servicio;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class<T> clase);

}

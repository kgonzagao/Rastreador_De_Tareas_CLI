package com.example.controller;

import java.nio.file.Path;
import java.util.List;

import com.example.model.Tarea;

public interface ITarea {

    public void agregarTarea(Tarea tareaNueva);

    public void editarTarea(Tarea tareaEditar);

    public void eliminarTarea(Tarea tareaEliminar);

    public void guardarTareas(Path URL_FILE);

    public List<Tarea> listaTareas();

}

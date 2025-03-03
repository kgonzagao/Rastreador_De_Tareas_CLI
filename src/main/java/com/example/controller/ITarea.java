package com.example.controller;

import java.util.List;

import com.example.model.Tarea;

public interface ITarea {

    void agregarTarea(Tarea tareaNueva);

    void editarTarea(Tarea tareaEditar);

    void eliminarTarea(Tarea tareaEliminar);

    List<Tarea> Tareas();

}

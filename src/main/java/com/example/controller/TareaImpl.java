package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Tarea;

public class TareaImpl implements ITarea {

    private static final List<Tarea> TAREAS;

    static {
        TAREAS = new ArrayList<>();
        TAREAS.add(new Tarea("Escribir un libro"));
        TAREAS.add(new Tarea("Comprar comida"));
        TAREAS.add(new Tarea("Lavar la ropa"));
        TAREAS.add(new Tarea("Correr"));
    }

    @Override
    public void agregarTarea(Tarea tareaNueva) {
        Optional<Tarea> tareaOpt = TAREAS.stream()
                .filter(tarea -> tarea.getDescription() == tareaNueva.getDescription())
                .findFirst();

        if (tareaOpt.isPresent()) {
            System.out.println("Tarea ya existe");
        }else{
            TAREAS.add(tareaNueva);
        }
    }

    @Override
    public void editarTarea(Tarea tareaEditar) {
        Optional<Tarea> tareaOpt = TAREAS.stream()
                .filter(tarea -> tarea.getIdTarea() == tareaEditar.getIdTarea())
                .findFirst();

        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            if (tareaEditar.getDescription() != null) {
                tarea.setDescription(tareaEditar.getDescription());
            }
            if (tareaEditar.getStatus() != null) {
                tarea.setStatus(tareaEditar.getStatus());
            }
        } else {
            System.out.println("Tarea no encontrada");
        }
    }

    @Override
    public void eliminarTarea(Tarea tareaEliminar) {
        Optional<Tarea> tareaOpt = TAREAS.stream()
                .filter(tarea -> tarea.getIdTarea() == tareaEliminar.getIdTarea())
                .findFirst();

        if (tareaOpt.isPresent()) {
            TAREAS.remove(tareaOpt.get());
        } else {
            System.out.println("Tarea no encontrada");
        }
    }

    @Override
    public List<Tarea> Tareas() {
        return TAREAS;
    }

}

package com.example.test;

import java.nio.file.Path;

import com.example.controller.ITarea;
import com.example.controller.TareaImpl;
import com.example.model.EstadoTarea;
import com.example.model.Tarea;

public class test {
    public static void main(String[] args) {

        final Path ARCHIVO = Path.of("tareas.json");

        ITarea tareaInterfaz= new TareaImpl(ARCHIVO);

        tareaInterfaz.editarTarea(new Tarea(4,"Comer",null));

        tareaInterfaz.editarTarea(new Tarea(4,null, EstadoTarea.HECHO));
        for (Tarea tarea : tareaInterfaz.listaTareas()) {
            System.out.println(tarea);
        }
        
    }
}
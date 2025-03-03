package com.example.test;

import com.example.controller.ITarea;
import com.example.controller.TareaImpl;
import com.example.model.Tarea;

public class test {
    public static void main(String[] args) {
        ITarea TareaInterfas = new TareaImpl();

        for (Tarea Tarea : TareaInterfas.Tareas()) {
            System.out.println(Tarea.escribirTarea());
        }

        System.out.println("--------------------");

        for (Tarea Tarea : TareaInterfas.Tareas()) {
            System.out.println(Tarea);
        }
    }
}
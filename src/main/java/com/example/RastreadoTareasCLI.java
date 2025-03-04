package com.example;

import java.nio.file.Path;

import com.example.controller.ITarea;
import com.example.controller.TareaImpl;
import com.example.model.EstadoTarea;
import com.example.model.Tarea;

public class RastreadoTareasCLI {
    public static void main(String[] args) {
        final Path URL=Path.of("tareas.json");

        ITarea tareaInterfas = new TareaImpl(URL);

        if (args.length < 1) {
            System.out.println("Uso: TareaCLI <comando> [argumentos]");
            return;
        }

        String comando = args[0];

        switch (comando) {
            case "agregar":
                if (args.length < 2) {
                    System.out.println("Uso: TareaCLI agregar <descripcion>");
                    return;
                }
                tareaInterfas.agregarTarea(new Tarea(args[1]));
                break;
            case "actualizar":
                if (args.length < 3) {
                    System.out.println("Uso: TareaCLI actualizar <idTarea> <nueva descripcion>");
                    return;
                }
                tareaInterfas.editarTarea(new Tarea(Integer.parseInt(args[1]),String.valueOf(args[2]),null));
                break;
            case "eliminar":
                if (args.length < 2) {
                    System.out.println("Uso: TareaCLI eliminar <id>");
                    return;
                }
                tareaInterfas.eliminarTarea(new Tarea(Integer.parseInt(args[1]), null, null));
                break;
            case "proceso":
                if (args.length < 2) {
                    System.out.println("Uso: TareaCLI proceso <id>");
                    return;
                }
                tareaInterfas.editarTarea(new Tarea(Integer.parseInt(args[1]), null, EstadoTarea.EN_PROCESO));;
                break;

            case "hecho":
                if (args.length < 2) {
                    System.out.println("Uso: TareaCLI hecho <id>");
                    return;
                }
                tareaInterfas.editarTarea(new Tarea(Integer.parseInt(args[1]), null, EstadoTarea.HECHO));;
                break;

            case "listar":
                if (args.length < 2) {
                    tareaInterfas.listarTareas("todo");
                } else {
                    EstadoTarea estadoTareaFiltro;
                    try {
                        estadoTareaFiltro = EstadoTarea.valueOf(args[1].toUpperCase().replace("-", "_"));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Estado invalido: " + args[1]);
                        return;
                    }
                    tareaInterfas.listarTareas(estadoTareaFiltro.toString());
                }
                break;

            default:
                System.out.println("Comando desconocido: " + comando);
                break;
        }
        tareaInterfas.guardarTareas(URL);
    }
}
package com.example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.model.Tarea;

public class TareaImpl implements ITarea {

    private List<Tarea> tareas;

    public TareaImpl(Path URL_FILE) {
        this.tareas = cargarTareas(URL_FILE);
    }

    private List<Tarea> cargarTareas(Path URL_FILE) {
        List<Tarea> tareasJson = new ArrayList<>();

        if (!Files.exists(URL_FILE)) {
            return tareasJson;
        }
        try {
            String contenidoJson = Files.readString(URL_FILE);
            String[] listaTarea = contenidoJson.replace("[", "")
                    .replace("]", "")
                    .split("},");
            for (String tareaJson : listaTarea) {
                if (!tareaJson.endsWith("}")) {
                    tareaJson = tareaJson + "}";
                    tareasJson.add(Tarea.fromJson(tareaJson));
                } else {
                    tareasJson.add(Tarea.fromJson(tareaJson));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareasJson;
    }

    public void guardarTareas(Path URL_FILE) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tareas.size(); i++) {
            sb.append(tareas.get(i).toJson());
            if (i < tareas.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");

        String contenidoJson = sb.toString();
        try {
            Files.writeString(URL_FILE, contenidoJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listarTareas(String tipo){
        for (Tarea tarea : tareas){
            String estado = tarea.getEstadoTarea().toString().strip();
            if (tipo.equals("todo") || estado.equals(tipo)){
                System.out.println(tarea.toString());
            }
        }
    }

    @Override
    public void agregarTarea(Tarea tareaNueva) {
        Optional<Tarea> tareaOpt = tareas.stream()
                .filter(tarea -> tarea.getDescription().equals(tareaNueva.getDescription()))
                .findFirst();

        if (tareaOpt.isPresent()) {
            System.out.println("La tarea: "+tareaNueva.getDescription()+", ya existe");
        } else {
            tareas.add(tareaNueva);
            System.out.println("La tarea fue agregada correctamente: "+tareaNueva);
        }
    }

    @Override
    public void editarTarea(Tarea tareaEditar) {
        Optional<Tarea> tareaOpt = tareas.stream()
                .filter(tarea -> tarea.getIdTarea() == tareaEditar.getIdTarea())
                .findFirst();

        if (tareaOpt.isPresent()) {
            Tarea tarea = tareaOpt.get();
            if (tareaEditar.getDescription() != null) {
                System.out.println("La tarea: "+tarea.getIdTarea()+" | "+tarea.getDescription()+" | "+tareaEditar.getDescription());
                tarea.setDescription(tareaEditar.getDescription());
            }
            if (tareaEditar.getEstadoTarea() != null) {
                System.out.println("La tarea: "+tarea.getIdTarea()+" | "+tarea.getEstadoTarea()+" | "+tareaEditar.getEstadoTarea());
                tarea.setEstadoTarea(tareaEditar.getEstadoTarea());
            }
        } else {
            System.out.println("La Tarea con id: "+tareaEditar.getIdTarea()+", no existe");
        }
    }

    @Override
    public void eliminarTarea(Tarea tareaEliminar) {
        Optional<Tarea> tareaOpt = tareas.stream()
                .filter(tarea -> tarea.getIdTarea() == tareaEliminar.getIdTarea())
                .findFirst();

        if (tareaOpt.isPresent()) {
            tareas.remove(tareaOpt.get());
            System.out.println("La Tarea con id: "+tareaEliminar.getIdTarea()+", fue eliminada");
        } else {
            System.out.println("La Tarea con id: "+tareaEliminar.getIdTarea()+", no existe");
        }
    }
}

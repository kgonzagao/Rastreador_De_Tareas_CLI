package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarea implements Serializable {

    private static int contadorId = 0;
    private int idTarea;
    private String description;
    private EstadoTarea estadoTarea;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizada;

    private static final DateTimeFormatter formateador = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Tarea(String description) {
        this.idTarea = ++contadorId;
        this.description = description;
        this.estadoTarea = estadoTarea.POR_HACER;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizada = LocalDateTime.now();
    }

    public Tarea(int idTarea, String description, EstadoTarea status) {
        this.idTarea = idTarea;
        this.description = description;
        this.estadoTarea = status;
    }

    public int getIdTarea() {
        return idTarea;
    }

    public String getDescription() {
        return description;
    }

    public EstadoTarea getEstadoTarea() {
        return estadoTarea;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizada() {
        return fechaActualizada;
    }

    public void setDescription(String description) {
        this.description = description;
        this.fechaActualizada = LocalDateTime.now();
    }

    public void setEstadoTarea(EstadoTarea estadoTarea) {
        this.estadoTarea = estadoTarea;
        this.fechaActualizada = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Tarea [idTarea=" + idTarea + ", description=" + description + ", estadoTarea=" + estadoTarea
                + ", fechaCreacion=" + fechaCreacion + ", fechaActualizada=" + fechaActualizada + "]";
    }

    public String toJson() {
        return "{\"idTarea\":\"" + idTarea + "\", \"description\":\"" + description.strip() + "\", \"estadoTarea\":\""
                + estadoTarea.toString() +
                "\", \"fechaCreacion\":\"" + fechaCreacion.format(formateador) + "\", \"fechaActualizada\":\""
                + fechaActualizada.format(formateador) + "\"}";
    }

    public static Tarea fromJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] json1 = json.split(",");

        String id = json1[0].split(":")[1].strip();
        String description = json1[1].split(":")[1].strip();
        String statusString = json1[2].split(":")[1].strip();
        String createdAtStr = json1[3].split("[a-z]:")[1].strip();
        String updatedAtStr = json1[4].split("[a-z]:")[1].strip();

        EstadoTarea status = EstadoTarea.valueOf(statusString.toUpperCase().replace(" ", "_"));

        Tarea task = new Tarea(description);
        task.idTarea = Integer.parseInt(id);
        task.estadoTarea = status;
        task.fechaCreacion = LocalDateTime.parse(createdAtStr, formateador);
        task.fechaActualizada = LocalDateTime.parse(updatedAtStr, formateador);

        if (Integer.parseInt(id) > contadorId) {
            contadorId = Integer.parseInt(id);
        }

        return task;
    }

}

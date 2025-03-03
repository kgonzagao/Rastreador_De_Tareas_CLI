package com.example.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea implements Serializable {

    private static int contadorId = 0;
    private int idTarea;
    private String description;
    private String status;
    private Date fechaCreacion;
    private Date fechaActualizada;

    public Tarea(int idTarea, String description, String status) {
        this.idTarea = idTarea;
        this.description = description;
        this.status = status;
    }

    public Tarea(String description) {
        this.idTarea = ++contadorId;
        this.description = description;
        this.status = "progeso";
        this.fechaCreacion = new Date();
        this.fechaActualizada = this.fechaCreacion;
    }


    public Tarea(int idTarea, String description, String status, Date fechaCreacion, Date fechaActualizada) {
        this.idTarea = idTarea;
        this.description = description;
        this.status = status;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizada = fechaActualizada;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
        this.fechaActualizada = new Date();
    }

    public int getIdTarea() {
        return idTarea;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaActualizada() {
        return fechaActualizada;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return "Tarea [idTarea=" + idTarea + ", description=" + description + ", status=" + status + ", fechaCreacion="
                + formatDate(fechaCreacion) + ", fechaActualizada=" + formatDate(fechaActualizada) + "]";
    }

    public String escribirTarea(){
        return idTarea+","+description+","+status+","+formatDate(fechaCreacion)+","+formatDate(fechaActualizada);
    }
}

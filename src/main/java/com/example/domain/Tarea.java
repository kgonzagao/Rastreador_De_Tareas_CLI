package com.example.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tarea {

    private static int contadorId = 0;
    private int idTarea;
    private String description;
    private String status;
    private Date createdAt;
    private Date updatedAt;

    public Tarea(String description) {
        this.idTarea = ++contadorId;
        this.description = description;
        this.status = "progeso";
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
        this.updatedAt = new Date();
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    private String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return "Tarea [idTarea=" + idTarea + ", description=" + description + ", status=" + status + ", createdAt="
                + formatDate(createdAt) + ", updatedAt=" + formatDate(updatedAt) + "]";
    }
}

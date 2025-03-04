package com.example.model;

public enum EstadoTarea {
    POR_HACER("Por Hacer"),
    EN_PROCESO("En Proceso"),
    HECHO("Hecho");

    private String valorEstado;

    EstadoTarea(String valorEstado) {
        this.valorEstado = valorEstado;
    }

    public String getValorEstado() {
        return valorEstado;
    }

    @Override
    public String toString() {
        return valorEstado;
    }

}

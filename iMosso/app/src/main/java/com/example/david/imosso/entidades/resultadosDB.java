package com.example.david.imosso.entidades;

import java.util.Date;

public class resultadosDB {
    private Integer idTest;
    private Date fechaTest;
    private String nombreTest;
    private String notaTest;

    public resultadosDB(Integer idTest, Date fechaTest, String nombreTest, String notaTest) {
        this.idTest = idTest;
        this.fechaTest = fechaTest;
        this.nombreTest = nombreTest;
        this.notaTest = notaTest;
    }

    public Integer getIdTest() {
        return idTest;
    }

    public void setIdTest(Integer idTest) {
        this.idTest = idTest;
    }

    public Date getFechaTest() {
        return fechaTest;
    }

    public void setFechaTest(Date fechaTest) {
        this.fechaTest = fechaTest;
    }

    public String getNombreTest() {
        return nombreTest;
    }

    public void setNombreTest(String nombreTest) {
        this.nombreTest = nombreTest;
    }

    public String getNotaTest() {
        return notaTest;
    }

    public void setNotaTest(String notaTest) {
        this.notaTest = notaTest;
    }
}

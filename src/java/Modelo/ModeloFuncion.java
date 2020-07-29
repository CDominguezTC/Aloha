
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloFuncion {

    Integer id;
    String nombre;
    String descripcion;
    String codigo_reloj;
    String estado;

    public ModeloFuncion() {
    }

    public ModeloFuncion(Integer id, String nombre, String descripcion, String codigo_reloj, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo_reloj = codigo_reloj;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo_reloj() {
        return codigo_reloj;
    }

    public void setCodigo_reloj(String codigo_reloj) {
        this.codigo_reloj = codigo_reloj;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

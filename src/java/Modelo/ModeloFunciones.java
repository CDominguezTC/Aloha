
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
public class ModeloFunciones {

    int Id;
    String nombre;
    String descripcion;
    String estado;
    String codReloj;

    public ModeloFunciones() {
    }

    public ModeloFunciones(int Id, String nombre, String descripcion, String estado, String codReloj) {
        this.Id = Id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
        this.codReloj = codReloj;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodReloj() {
        return codReloj;
    }

    public void setCodReloj(String codReloj) {
        this.codReloj = codReloj;
    }

}

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
public class ModeloHorarioConsumo {

    int id;
    String codigo;
    String nombre;
    String horainicio;
    String horafin;
    String cantidadconsumos;
    String lunes;
    String martes;
    String miercoles;
    String jueves;
    String viernes;
    String sabado;
    String domingo;
    String festivo;
    ModeloTipoConsumo modeloTipoConsumo;

    public ModeloHorarioConsumo() {
    }

    public ModeloHorarioConsumo(int id, String codigo, String nombre, String horainicio, String horafin, String cantidadconsumos, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo, String festivo, ModeloTipoConsumo modeloTipoConsumo) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.cantidadconsumos = cantidadconsumos;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.festivo = festivo;
        this.modeloTipoConsumo = modeloTipoConsumo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getCantidadconsumos() {
        return cantidadconsumos;
    }

    public void setCantidadconsumos(String cantidadconsumos) {
        this.cantidadconsumos = cantidadconsumos;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    public String getFestivo() {
        return festivo;
    }

    public void setFestivo(String festivo) {
        this.festivo = festivo;
    }

    public ModeloTipoConsumo getModeloTipoConsumo() {
        return modeloTipoConsumo;
    }

    public void setModeloTipoConsumo(ModeloTipoConsumo modeloTipoConsumo) {
        this.modeloTipoConsumo = modeloTipoConsumo;
    }

}

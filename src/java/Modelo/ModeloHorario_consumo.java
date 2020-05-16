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
public class ModeloHorario_consumo {

    Integer id;
    String codigo;
    String nombre;
    String hora_inicio;
    String hora_fin;
    Integer cantidad_consumo;
    String lunes;
    String martes;
    String miercoles;
    String jueves;
    String viernes;
    String sabado;
    String domingo;
    String festivo;
    ModeloTipo_consumo Modelo_tipo_consumo;
    String estado;

    public ModeloHorario_consumo() {
    }

    public ModeloHorario_consumo(Integer id, String codigo, String nombre, String hora_inicio, String hora_fin, Integer cantidad_consumo, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo, String festivo, ModeloTipo_consumo Modelo_tipo_consumo, String estado) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.cantidad_consumo = cantidad_consumo;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
        this.festivo = festivo;
        this.Modelo_tipo_consumo = Modelo_tipo_consumo;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Integer getCantidad_consumo() {
        return cantidad_consumo;
    }

    public void setCantidad_consumo(Integer cantidad_consumo) {
        this.cantidad_consumo = cantidad_consumo;
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

    public ModeloTipo_consumo getModelo_tipo_consumo() {
        return Modelo_tipo_consumo;
    }

    public void setModelo_tipo_consumo(ModeloTipo_consumo Modelo_tipo_consumo) {
        this.Modelo_tipo_consumo = Modelo_tipo_consumo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}

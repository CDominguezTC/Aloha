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
public class ModeloDispositivo {

    Integer id;
    Integer numero;
    String nombre;
    String direccion_ip;
    Integer puerto;
    String modo;
    String ip_controladora;
    Integer puerto_controladora;
    String tipo_lector;
    String activo;
    String serie;
    String licencia;
    String impresora;
    String encabezado_impresion;
    String utiliza_menu;
    String evento;
    String estado;

    public ModeloDispositivo() {
    }

    public ModeloDispositivo(Integer id, Integer numero, String nombre, String direccion_ip, Integer puerto, String modo, String ip_controladora, Integer puerto_controladora, String tipo_lector, String activo, String serie, String licencia, String impresora, String encabezado_impresion, String utiliza_menu, String evento, String estado) {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.direccion_ip = direccion_ip;
        this.puerto = puerto;
        this.modo = modo;
        this.ip_controladora = ip_controladora;
        this.puerto_controladora = puerto_controladora;
        this.tipo_lector = tipo_lector;
        this.activo = activo;
        this.serie = serie;
        this.licencia = licencia;
        this.impresora = impresora;
        this.encabezado_impresion = encabezado_impresion;
        this.utiliza_menu = utiliza_menu;
        this.evento = evento;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion_ip() {
        return direccion_ip;
    }

    public void setDireccion_ip(String direccion_ip) {
        this.direccion_ip = direccion_ip;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getIp_controladora() {
        return ip_controladora;
    }

    public void setIp_controladora(String ip_controladora) {
        this.ip_controladora = ip_controladora;
    }

    public Integer getPuerto_controladora() {
        return puerto_controladora;
    }

    public void setPuerto_controladora(Integer puerto_controladora) {
        this.puerto_controladora = puerto_controladora;
    }

    public String getTipo_lector() {
        return tipo_lector;
    }

    public void setTipo_lector(String tipo_lector) {
        this.tipo_lector = tipo_lector;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }

    public String getEncabezado_impresion() {
        return encabezado_impresion;
    }

    public void setEncabezado_impresion(String encabezado_impresion) {
        this.encabezado_impresion = encabezado_impresion;
    }

    public String getUtiliza_menu() {
        return utiliza_menu;
    }

    public void setUtiliza_menu(String utiliza_menu) {
        this.utiliza_menu = utiliza_menu;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
        
}

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
public class ModeloDispositivos {

    Integer id;
    Integer NumeroDispositivo;
    String Nombre;
    String DireccionIP;
    String Puerto;
    String Modo;
    String IpControladora;
    String PuertoControladora;
    String TipoLector;
    String Activo;
    String Serie;
    String Licencia;
    String Impresora;
    String EncabezadoImpresion;
    String UtilizaMenu;
    String Evento;

    public ModeloDispositivos() {
    }

    public ModeloDispositivos(Integer id, Integer NumeroDispositivo, String Nombre, String DireccionIP, String Puerto, String Modo, String IpControladora, String PuertoControladora, String TipoLector, String Activo, String Serie, String Licencia, String Impresora, String EncabezadoImpresion, String UtilizaMenu, String Evento) {
        this.id = id;
        this.NumeroDispositivo = NumeroDispositivo;
        this.Nombre = Nombre;
        this.DireccionIP = DireccionIP;
        this.Puerto = Puerto;
        this.Modo = Modo;
        this.IpControladora = IpControladora;
        this.PuertoControladora = PuertoControladora;
        this.TipoLector = TipoLector;
        this.Activo = Activo;
        this.Serie = Serie;
        this.Licencia = Licencia;
        this.Impresora = Impresora;
        this.EncabezadoImpresion = EncabezadoImpresion;
        this.UtilizaMenu = UtilizaMenu;
        this.Evento = Evento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroDispositivo() {
        return NumeroDispositivo;
    }

    public void setNumeroDispositivo(Integer NumeroDispositivo) {
        this.NumeroDispositivo = NumeroDispositivo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccionIP() {
        return DireccionIP;
    }

    public void setDireccionIP(String DireccionIP) {
        this.DireccionIP = DireccionIP;
    }

    public String getPuerto() {
        return Puerto;
    }

    public void setPuerto(String Puerto) {
        this.Puerto = Puerto;
    }

    public String getModo() {
        return Modo;
    }

    public void setModo(String Modo) {
        this.Modo = Modo;
    }

    public String getIpControladora() {
        return IpControladora;
    }

    public void setIpControladora(String IpControladora) {
        this.IpControladora = IpControladora;
    }

    public String getPuertoControladora() {
        return PuertoControladora;
    }

    public void setPuertoControladora(String PuertoControladora) {
        this.PuertoControladora = PuertoControladora;
    }

    public String getTipoLector() {
        return TipoLector;
    }

    public void setTipoLector(String TipoLector) {
        this.TipoLector = TipoLector;
    }

    public String getActivo() {
        return Activo;
    }

    public void setActivo(String Activo) {
        this.Activo = Activo;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getLicencia() {
        return Licencia;
    }

    public void setLicencia(String Licencia) {
        this.Licencia = Licencia;
    }

    public String getImpresora() {
        return Impresora;
    }

    public void setImpresora(String Impresora) {
        this.Impresora = Impresora;
    }

    public String getEncabezadoImpresion() {
        return EncabezadoImpresion;
    }

    public void setEncabezadoImpresion(String EncabezadoImpresion) {
        this.EncabezadoImpresion = EncabezadoImpresion;
    }

    public String getUtilizaMenu() {
        return UtilizaMenu;
    }

    public void setUtilizaMenu(String UtilizaMenu) {
        this.UtilizaMenu = UtilizaMenu;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String Evento) {
        this.Evento = Evento;
    }

}

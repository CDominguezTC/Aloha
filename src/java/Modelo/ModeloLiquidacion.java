/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloLiquidacion {

    int id;
    int idEmpleado;
    String Cedula;
    String NombreEmpleado;
    int idGrupoTurno;
    String GrupoTurno;
    int idTurno;
    String Tunro;
    Date FechaInicio;
    String HoraInicio;
    Date FechaFin;
    String HoraFin;
    String HoraDiferencia;
    String HoraDiurnas;
    String HoraNocturnas;
    String HoraExtras;
    String HoraEntradaTardia;
    String HoraSalidaTemprana;
    String HoraDescuento;
    int Bandera;
    ModeloTurnos modeloTurnos;

    public ModeloLiquidacion() {
    }

    public ModeloLiquidacion(int id, int idEmpleado, String Cedula, String NombreEmpleado, int idGrupoTurno, String GrupoTurno, int idTurno, String Tunro, Date FechaInicio, String HoraInicio, Date FechaFin, String HoraFin, String HoraDiferencia, String HoraDiurnas, String HoraNocturnas, String HoraExtras, String HoraEntradaTardia, String HoraSalidaTemprana, String HoraDescuento, int Bandera, ModeloTurnos modeloTurnos) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.Cedula = Cedula;
        this.NombreEmpleado = NombreEmpleado;
        this.idGrupoTurno = idGrupoTurno;
        this.GrupoTurno = GrupoTurno;
        this.idTurno = idTurno;
        this.Tunro = Tunro;
        this.FechaInicio = FechaInicio;
        this.HoraInicio = HoraInicio;
        this.FechaFin = FechaFin;
        this.HoraFin = HoraFin;
        this.HoraDiferencia = HoraDiferencia;
        this.HoraDiurnas = HoraDiurnas;
        this.HoraNocturnas = HoraNocturnas;
        this.HoraExtras = HoraExtras;
        this.HoraEntradaTardia = HoraEntradaTardia;
        this.HoraSalidaTemprana = HoraSalidaTemprana;
        this.HoraDescuento = HoraDescuento;
        this.Bandera = Bandera;
        this.modeloTurnos = modeloTurnos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombreEmpleado() {
        return NombreEmpleado;
    }

    public void setNombreEmpleado(String NombreEmpleado) {
        this.NombreEmpleado = NombreEmpleado;
    }

    public int getIdGrupoTurno() {
        return idGrupoTurno;
    }

    public void setIdGrupoTurno(int idGrupoTurno) {
        this.idGrupoTurno = idGrupoTurno;
    }

    public String getGrupoTurno() {
        return GrupoTurno;
    }

    public void setGrupoTurno(String GrupoTurno) {
        this.GrupoTurno = GrupoTurno;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getTunro() {
        return Tunro;
    }

    public void setTunro(String Tunro) {
        this.Tunro = Tunro;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(String HoraFin) {
        this.HoraFin = HoraFin;
    }

    public String getHoraDiferencia() {
        return HoraDiferencia;
    }

    public void setHoraDiferencia(String HoraDiferencia) {
        this.HoraDiferencia = HoraDiferencia;
    }

    public String getHoraDiurnas() {
        return HoraDiurnas;
    }

    public void setHoraDiurnas(String HoraDiurnas) {
        this.HoraDiurnas = HoraDiurnas;
    }

    public String getHoraNocturnas() {
        return HoraNocturnas;
    }

    public void setHoraNocturnas(String HoraNocturnas) {
        this.HoraNocturnas = HoraNocturnas;
    }

    public String getHoraExtras() {
        return HoraExtras;
    }

    public void setHoraExtras(String HoraExtras) {
        this.HoraExtras = HoraExtras;
    }

    public String getHoraEntradaTardia() {
        return HoraEntradaTardia;
    }

    public void setHoraEntradaTardia(String HoraEntradaTardia) {
        this.HoraEntradaTardia = HoraEntradaTardia;
    }

    public String getHoraSalidaTemprana() {
        return HoraSalidaTemprana;
    }

    public void setHoraSalidaTemprana(String HoraSalidaTemprana) {
        this.HoraSalidaTemprana = HoraSalidaTemprana;
    }

    public String getHoraDescuento() {
        return HoraDescuento;
    }

    public void setHoraDescuento(String HoraDescuento) {
        this.HoraDescuento = HoraDescuento;
    }

    public int getBandera() {
        return Bandera;
    }

    public void setBandera(int Bandera) {
        this.Bandera = Bandera;
    }

    public ModeloTurnos getModeloTurnos() {
        return modeloTurnos;
    }

    public void setModeloTurnos(ModeloTurnos modeloTurnos) {
        this.modeloTurnos = modeloTurnos;
    }

}

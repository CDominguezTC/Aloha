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
public class ModeloCargos {

    int Id;
    ModeloPersonas modeloPersonas;
    String TipoCargo;
    int ValorCargo;
    String FechaInicioCargo;
    String FechaFinCargo;
    String EstadoCargo;

    public ModeloCargos() {
    }

    public ModeloCargos(int Id, ModeloPersonas modeloPersonas, String TipoCargo, int ValorCargo, String FechaInicioCargo, String FechaFinCargo, String EstadoCargo) {
        this.Id = Id;
        this.modeloPersonas = modeloPersonas;
        this.TipoCargo = TipoCargo;
        this.ValorCargo = ValorCargo;
        this.FechaInicioCargo = FechaInicioCargo;
        this.FechaFinCargo = FechaFinCargo;
        this.EstadoCargo = EstadoCargo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public ModeloPersonas getModeloPersonas() {
        return modeloPersonas;
    }

    public void setModeloPersonas(ModeloPersonas modeloPersonas) {
        this.modeloPersonas = modeloPersonas;
    }

    public String getTipoCargo() {
        return TipoCargo;
    }

    public void setTipoCargo(String TipoCargo) {
        this.TipoCargo = TipoCargo;
    }

    public int getValorCargo() {
        return ValorCargo;
    }

    public void setValorCargo(int ValorCargo) {
        this.ValorCargo = ValorCargo;
    }

    public String getFechaInicioCargo() {
        return FechaInicioCargo;
    }

    public void setFechaInicioCargo(String FechaInicioCargo) {
        this.FechaInicioCargo = FechaInicioCargo;
    }

    public String getFechaFinCargo() {
        return FechaFinCargo;
    }

    public void setFechaFinCargo(String FechaFinCargo) {
        this.FechaFinCargo = FechaFinCargo;
    }

    public String getEstadoCargo() {
        return EstadoCargo;
    }

    public void setEstadoCargo(String EstadoCargo) {
        this.EstadoCargo = EstadoCargo;
    }

}

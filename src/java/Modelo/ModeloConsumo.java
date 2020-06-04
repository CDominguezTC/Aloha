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
public class ModeloConsumo {

    int id;
    ModeloPersona modeloPersonas;
    ModeloCargo modeloCargos;
    ModeloCentro_costo modeloCentro_costo;
    ModeloGrupo_consumo modeloGrupo_consumo;
    ModeloHorario_consumo modeloHorario_consumo;
    String Fechaconsumo;
    String diaconsumo;

    public ModeloConsumo() {
    }

    public ModeloConsumo(int id, ModeloPersona modeloPersonas, ModeloCargo modeloCargos, ModeloCentro_costo modeloCentro_costo, ModeloGrupo_consumo modeloGrupo_consumo, ModeloHorario_consumo modeloHorario_consumo, String Fechaconsumo, String diaconsumo) {
        this.id = id;
        this.modeloPersonas = modeloPersonas;
        this.modeloCargos = modeloCargos;
        this.modeloCentro_costo = modeloCentro_costo;
        this.modeloGrupo_consumo = modeloGrupo_consumo;
        this.modeloHorario_consumo = modeloHorario_consumo;
        this.Fechaconsumo = Fechaconsumo;
        this.diaconsumo = diaconsumo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModeloPersona getModeloPersonas() {
        return modeloPersonas;
    }

    public void setModeloPersonas(ModeloPersona modeloPersonas) {
        this.modeloPersonas = modeloPersonas;
    }

    public ModeloCargo getModeloCargos() {
        return modeloCargos;
    }

    public void setModeloCargos(ModeloCargo modeloCargos) {
        this.modeloCargos = modeloCargos;
    }

    public ModeloCentro_costo getModeloCentro_costo() {
        return modeloCentro_costo;
    }

    public void setModeloCentro_costo(ModeloCentro_costo modeloCentro_costo) {
        this.modeloCentro_costo = modeloCentro_costo;
    }

    public ModeloGrupo_consumo getModeloGrupo_consumo() {
        return modeloGrupo_consumo;
    }

    public void setModeloGrupo_consumo(ModeloGrupo_consumo modeloGrupo_consumo) {
        this.modeloGrupo_consumo = modeloGrupo_consumo;
    }

    public ModeloHorario_consumo getModeloHorario_consumo() {
        return modeloHorario_consumo;
    }

    public void setModeloHorario_consumo(ModeloHorario_consumo modeloHorario_consumo) {
        this.modeloHorario_consumo = modeloHorario_consumo;
    }

    public String getFechaconsumo() {
        return Fechaconsumo;
    }

    public void setFechaconsumo(String Fechaconsumo) {
        this.Fechaconsumo = Fechaconsumo;
    }

    public String getDiaconsumo() {
        return diaconsumo;
    }

    public void setDiaconsumo(String diaconsumo) {
        this.diaconsumo = diaconsumo;
    }
    
    
}

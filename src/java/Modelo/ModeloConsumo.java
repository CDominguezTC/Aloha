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
    ModeloPersonas modeloPersonas;
    ModeloCargo modeloCargos;
    String Fechaconsumo;
    String diaconsumo;

    public ModeloConsumo() {
    }

    public ModeloConsumo(int id, ModeloPersonas modeloPersonas, ModeloCargo modeloCargos, String Fechaconsumo, String diaconsumo) {
        this.id = id;
        this.modeloPersonas = modeloPersonas;
        this.modeloCargos = modeloCargos;
        this.Fechaconsumo = Fechaconsumo;
        this.diaconsumo = diaconsumo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ModeloPersonas getModeloPersonas() {
        return modeloPersonas;
    }

    public void setModeloPersonas(ModeloPersonas modeloPersonas) {
        this.modeloPersonas = modeloPersonas;
    }

    public ModeloCargo getModeloCargos() {
        return modeloCargos;
    }

    public void setModeloCargos(ModeloCargo modeloCargos) {
        this.modeloCargos = modeloCargos;
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

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
public class ModeloRetenciones
{
    int Id;
    String Codigo;
    String Nombre;
    String Retencion_Tipo;
    String Retencion_Tasa;
    String Retencion_BaseV;
    String Retencion_BaseC;
    String Retencion_CtaVDB;
    String Retencion_CtaVCR;
    String Retencion_CtaComDB;
    String Retencion_CtaComCR;
    String Retencion_CODIGOFE;

    public ModeloRetenciones() {
    }

    public ModeloRetenciones(int Id, String Codigo, String Nombre, String Retencion_Tipo, String Retencion_Tasa, String Retencion_BaseV, String Retencion_BaseC, String Retencion_CtaVDB, String Retencion_CtaVCR, String Retencion_CtaComDB, String Retencion_CtaComCR, String Retencion_CODIGOFE) {
        this.Id = Id;
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Retencion_Tipo = Retencion_Tipo;
        this.Retencion_Tasa = Retencion_Tasa;
        this.Retencion_BaseV = Retencion_BaseV;
        this.Retencion_BaseC = Retencion_BaseC;
        this.Retencion_CtaVDB = Retencion_CtaVDB;
        this.Retencion_CtaVCR = Retencion_CtaVCR;
        this.Retencion_CtaComDB = Retencion_CtaComDB;
        this.Retencion_CtaComCR = Retencion_CtaComCR;
        this.Retencion_CODIGOFE = Retencion_CODIGOFE;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRetencion_Tipo() {
        return Retencion_Tipo;
    }

    public void setRetencion_Tipo(String Retencion_Tipo) {
        this.Retencion_Tipo = Retencion_Tipo;
    }

    public String getRetencion_Tasa() {
        return Retencion_Tasa;
    }

    public void setRetencion_Tasa(String Retencion_Tasa) {
        this.Retencion_Tasa = Retencion_Tasa;
    }

    public String getRetencion_BaseV() {
        return Retencion_BaseV;
    }

    public void setRetencion_BaseV(String Retencion_BaseV) {
        this.Retencion_BaseV = Retencion_BaseV;
    }

    public String getRetencion_BaseC() {
        return Retencion_BaseC;
    }

    public void setRetencion_BaseC(String Retencion_BaseC) {
        this.Retencion_BaseC = Retencion_BaseC;
    }

    public String getRetencion_CtaVDB() {
        return Retencion_CtaVDB;
    }

    public void setRetencion_CtaVDB(String Retencion_CtaVDB) {
        this.Retencion_CtaVDB = Retencion_CtaVDB;
    }

    public String getRetencion_CtaVCR() {
        return Retencion_CtaVCR;
    }

    public void setRetencion_CtaVCR(String Retencion_CtaVCR) {
        this.Retencion_CtaVCR = Retencion_CtaVCR;
    }

    public String getRetencion_CtaComDB() {
        return Retencion_CtaComDB;
    }

    public void setRetencion_CtaComDB(String Retencion_CtaComDB) {
        this.Retencion_CtaComDB = Retencion_CtaComDB;
    }

    public String getRetencion_CtaComCR() {
        return Retencion_CtaComCR;
    }

    public void setRetencion_CtaComCR(String Retencion_CtaComCR) {
        this.Retencion_CtaComCR = Retencion_CtaComCR;
    }

    public String getRetencion_CODIGOFE() {
        return Retencion_CODIGOFE;
    }

    public void setRetencion_CODIGOFE(String Retencion_CODIGOFE) {
        this.Retencion_CODIGOFE = Retencion_CODIGOFE;
    }
    
    
}

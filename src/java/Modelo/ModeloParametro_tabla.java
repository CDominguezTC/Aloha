/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Diego Fdo Guzman B
 */
public class ModeloParametro_tabla {

    Integer id;
    ModeloEnumeracion Modelo_tabla;
    ModeloEnumeracion Modelo_modulo;
    String nombre_campo_bd;
    String nombre_visible;
    String tipo_campo;
    String tabla_referencia;
    String visualizar;
    String habilitar;
    String obligatorio;
    String lista;
    String estado;

    public ModeloParametro_tabla(Integer id, ModeloEnumeracion Modelo_tabla, ModeloEnumeracion Modelo_modulo, String nombre_campo_bd, String nombre_visible, String tipo_campo, String tabla_referencia, String visualizar, String habilitar, String obligatorio, String lista, String estado) {
        this.id = id;
        this.Modelo_tabla = Modelo_tabla;
        this.Modelo_modulo = Modelo_modulo;
        this.nombre_campo_bd = nombre_campo_bd;
        this.nombre_visible = nombre_visible;
        this.tipo_campo = tipo_campo;
        this.tabla_referencia = tabla_referencia;
        this.visualizar = visualizar;
        this.habilitar = habilitar;
        this.obligatorio = obligatorio;
        this.lista = lista;
        this.estado = estado;
    }

    public ModeloParametro_tabla() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModeloEnumeracion getModelo_tabla() {
        return Modelo_tabla;
    }

    public void setModelo_tabla(ModeloEnumeracion Modelo_tabla) {
        this.Modelo_tabla = Modelo_tabla;
    }

    public ModeloEnumeracion getModelo_modulo() {
        return Modelo_modulo;
    }

    public void setModelo_modulo(ModeloEnumeracion Modelo_modulo) {
        this.Modelo_modulo = Modelo_modulo;
    }

    public String getNombre_campo_bd() {
        return nombre_campo_bd;
    }

    public void setNombre_campo_bd(String nombre_campo_bd) {
        this.nombre_campo_bd = nombre_campo_bd;
    }

    public String getNombre_visible() {
        return nombre_visible;
    }

    public void setNombre_visible(String nombre_visible) {
        this.nombre_visible = nombre_visible;
    }

    public String getTipo_campo() {
        return tipo_campo;
    }

    public void setTipo_campo(String tipo_campo) {
        this.tipo_campo = tipo_campo;
    }

    public String getTabla_referencia() {
        return tabla_referencia;
    }

    public void setTabla_referencia(String tabla_referencia) {
        this.tabla_referencia = tabla_referencia;
    }

    public String getVisualizar() {
        return visualizar;
    }

    public void setVisualizar(String visualizar) {
        this.visualizar = visualizar;
    }

    public String getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(String habilitar) {
        this.habilitar = habilitar;
    }

    public String getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(String obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getLista() {
        return lista;
    }

    public void setLista(String lista) {
        this.lista = lista;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}

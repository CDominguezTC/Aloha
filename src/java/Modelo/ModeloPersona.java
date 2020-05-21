
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Blob;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloPersona {

    Integer id;
    String tipo_identificacion;
    String identificacion;
    String nombres;
    String apellidos;
    String email;
    String direccion;
    String telefono;
    String rh;
    String tipo_persona;
    String recibe_visitas;
    String nombre_eps;
    String nombre_arl;
    String acceso_restringido;
    String observacion;
    String consumo_casino;
    String tarjeta_acceso;
    String codigo_nomina;
    String estado;
    ModeloDependencia Modelo_dependencia;
    ModeloEmpresa Modelo_empresa_seguridad_social;
    ModeloGrupoTurnos Modelo_grupo_horario;
    ModeloTurnos Modelo_turno;
    ModeloDepartamento Modelo_departamento;
    ModeloArea Modelo_area;
    ModeloCiudad Modelo_ciudad;
    ModeloCentro_costo Modelo_centro_costo;
    ModeloCargo Modelo_cargo;
    ModeloEmpresa Modelo_empresa_trabaja;
    ModeloGrupo_consumo Modelo_grupo_consumo;
    
    //datos para imagenes, huellas y template
    LinkedList<ModeloImagen> Lista_Modelo_Imagenes;
    LinkedList<ModeloTemplate> Lista_Modelo_Template;    

    public ModeloPersona() {
    }

    public ModeloPersona(Integer id, String tipo_identificacion, String identificacion, String nombres, String apellidos, String email, String direccion, String telefono, String rh, String tipo_persona, String recibe_visitas, String nombre_eps, String nombre_arl, String acceso_restringido, String observacion, String consumo_casino, String tarjeta_acceso, String codigo_nomina, String estado, ModeloDependencia Modelo_dependencia, ModeloEmpresa Modelo_empresa_seguridad_social, ModeloGrupoTurnos Modelo_grupo_horario, ModeloTurnos Modelo_turno, ModeloDepartamento Modelo_departamento, ModeloArea Modelo_area, ModeloCiudad Modelo_ciudad, ModeloCentro_costo Modelo_centro_costo, ModeloCargo Modelo_cargo, ModeloEmpresa Modelo_empresa_trabaja, ModeloGrupo_consumo Modelo_grupo_consumo, LinkedList<ModeloImagen> Lista_Modelo_Imagenes, LinkedList<ModeloTemplate> Lista_Modelo_Template) {
        this.id = id;
        this.tipo_identificacion = tipo_identificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rh = rh;
        this.tipo_persona = tipo_persona;
        this.recibe_visitas = recibe_visitas;
        this.nombre_eps = nombre_eps;
        this.nombre_arl = nombre_arl;
        this.acceso_restringido = acceso_restringido;
        this.observacion = observacion;
        this.consumo_casino = consumo_casino;
        this.tarjeta_acceso = tarjeta_acceso;
        this.codigo_nomina = codigo_nomina;
        this.estado = estado;
        this.Modelo_dependencia = Modelo_dependencia;
        this.Modelo_empresa_seguridad_social = Modelo_empresa_seguridad_social;
        this.Modelo_grupo_horario = Modelo_grupo_horario;
        this.Modelo_turno = Modelo_turno;
        this.Modelo_departamento = Modelo_departamento;
        this.Modelo_area = Modelo_area;
        this.Modelo_ciudad = Modelo_ciudad;
        this.Modelo_centro_costo = Modelo_centro_costo;
        this.Modelo_cargo = Modelo_cargo;
        this.Modelo_empresa_trabaja = Modelo_empresa_trabaja;
        this.Modelo_grupo_consumo = Modelo_grupo_consumo;
        this.Lista_Modelo_Imagenes = Lista_Modelo_Imagenes;
        this.Lista_Modelo_Template = Lista_Modelo_Template;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getRecibe_visitas() {
        return recibe_visitas;
    }

    public void setRecibe_visitas(String recibe_visitas) {
        this.recibe_visitas = recibe_visitas;
    }

    public String getNombre_eps() {
        return nombre_eps;
    }

    public void setNombre_eps(String nombre_eps) {
        this.nombre_eps = nombre_eps;
    }

    public String getNombre_arl() {
        return nombre_arl;
    }

    public void setNombre_arl(String nombre_arl) {
        this.nombre_arl = nombre_arl;
    }

    public String getAcceso_restringido() {
        return acceso_restringido;
    }

    public void setAcceso_restringido(String acceso_restringido) {
        this.acceso_restringido = acceso_restringido;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getConsumo_casino() {
        return consumo_casino;
    }

    public void setConsumo_casino(String consumo_casino) {
        this.consumo_casino = consumo_casino;
    }

    public String getTarjeta_acceso() {
        return tarjeta_acceso;
    }

    public void setTarjeta_acceso(String tarjeta_acceso) {
        this.tarjeta_acceso = tarjeta_acceso;
    }

    public String getCodigo_nomina() {
        return codigo_nomina;
    }

    public void setCodigo_nomina(String codigo_nomina) {
        this.codigo_nomina = codigo_nomina;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ModeloDependencia getModelo_dependencia() {
        return Modelo_dependencia;
    }

    public void setModelo_dependencia(ModeloDependencia Modelo_dependencia) {
        this.Modelo_dependencia = Modelo_dependencia;
    }

    public ModeloEmpresa getModelo_empresa_seguridad_social() {
        return Modelo_empresa_seguridad_social;
    }

    public void setModelo_empresa_seguridad_social(ModeloEmpresa Modelo_empresa_seguridad_social) {
        this.Modelo_empresa_seguridad_social = Modelo_empresa_seguridad_social;
    }

    public ModeloGrupoTurnos getModelo_grupo_horario() {
        return Modelo_grupo_horario;
    }

    public void setModelo_grupo_horario(ModeloGrupoTurnos Modelo_grupo_horario) {
        this.Modelo_grupo_horario = Modelo_grupo_horario;
    }

    public ModeloTurnos getModelo_turno() {
        return Modelo_turno;
    }

    public void setModelo_turno(ModeloTurnos Modelo_turno) {
        this.Modelo_turno = Modelo_turno;
    }

    public ModeloDepartamento getModelo_departamento() {
        return Modelo_departamento;
    }

    public void setModelo_departamento(ModeloDepartamento Modelo_departamento) {
        this.Modelo_departamento = Modelo_departamento;
    }

    public ModeloArea getModelo_area() {
        return Modelo_area;
    }

    public void setModelo_area(ModeloArea Modelo_area) {
        this.Modelo_area = Modelo_area;
    }

    public ModeloCiudad getModelo_ciudad() {
        return Modelo_ciudad;
    }

    public void setModelo_ciudad(ModeloCiudad Modelo_ciudad) {
        this.Modelo_ciudad = Modelo_ciudad;
    }

    public ModeloCentro_costo getModelo_centro_costo() {
        return Modelo_centro_costo;
    }

    public void setModelo_centro_costo(ModeloCentro_costo Modelo_centro_costo) {
        this.Modelo_centro_costo = Modelo_centro_costo;
    }

    public ModeloCargo getModelo_cargo() {
        return Modelo_cargo;
    }

    public void setModelo_cargo(ModeloCargo Modelo_cargo) {
        this.Modelo_cargo = Modelo_cargo;
    }

    public ModeloEmpresa getModelo_empresa_trabaja() {
        return Modelo_empresa_trabaja;
    }

    public void setModelo_empresa_trabaja(ModeloEmpresa Modelo_empresa_trabaja) {
        this.Modelo_empresa_trabaja = Modelo_empresa_trabaja;
    }

    public ModeloGrupo_consumo getModelo_grupo_consumo() {
        return Modelo_grupo_consumo;
    }

    public void setModelo_grupo_consumo(ModeloGrupo_consumo Modelo_grupo_consumo) {
        this.Modelo_grupo_consumo = Modelo_grupo_consumo;
    }

    public LinkedList<ModeloImagen> getLista_Modelo_Imagenes() {
        return Lista_Modelo_Imagenes;
    }

    public void setLista_Modelo_Imagenes(LinkedList<ModeloImagen> Lista_Modelo_Imagenes) {
        this.Lista_Modelo_Imagenes = Lista_Modelo_Imagenes;
    }

    public LinkedList<ModeloTemplate> getLista_Modelo_Template() {
        return Lista_Modelo_Template;
    }

    public void setLista_Modelo_Template(LinkedList<ModeloTemplate> Lista_Modelo_Template) {
        this.Lista_Modelo_Template = Lista_Modelo_Template;
    }
    
    
   }

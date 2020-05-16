
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Blob;
import java.sql.Date;
import java.util.LinkedList;

/**
 *
 * @author Carlos A Dominguez D
 */
public class ModeloPersonas {

    int id;
    String tipoIdentificacion;
    String identificacion;
    String nombres;
    String apellidos;
    String email;
    String direccion;
    String ciudad;
    String tipoPersona;
    String tipoVisitante;
    int usuarioId;
    String codigoInterno;
    String extensionTelefonica;
    String foto;
    String nombreEPS;
    String vencimientoEPS;
    String nombreARP;
    Date pasadoJudicialVencto;
    String huella;
    String recibeVisitas;
    Blob plantillaHuella;
    int longitudPlantilla;
    Date vencimientoARP;
    int enrollNumber;
    int definicionId;
    int itinerarioId;
    String accesoRestringido;
    Date vencimientoPension;
    Date vencimientoSeguridadIndustrial;
    Date vencimientoAudiovisualSeguridadIndustrial;
    Date vencimientoTrabajoAlturas;
    Date vencimientoTrabajoConfinados;
    Date vencimientoTrabajoCaliente;
    Date vencimientoTrabajoExcavaciones;
    Date vencimientoTrabajoEnergiaElectrica;
    Date vencimientoOtros;
    String rH;
    String seguridadIndustrialSiNo;
    String audiovisualSiNo;
    String alturasSiNo;
    String confinadosSiNo;
    String calienteSiNo;
    String excavacionesSiNo;
    String strienergiaElectricaSiNo;
    String otrosSiNo;
    String telefono;
    int zonaId;
    String tipoAcceso;
    Date fechaInicioAcceso;
    Date fechaFinAcceso;
    String observaciones;
    String consumocasino;
    int tipoTrabajoId;
    int empresaenqueTrabajaId;
    String tarjetaAcceso;
    String cod_nomina;
    int id_Dependencias;
    int id_Empresa;
    String estado;
    int id_Grupo_Horario;
    int id_Turnos;
    int id_Departamento;
    int id_Areas;
    int id_Ciudad;
    int id_Centro_Costos;
    ModeloEmpresa modeloEmpresa;
    ModeloCentro_costo modeloCentroCosto;
    ModeloGrupoConsumo modeloGrupoConsumo;
    LinkedList<ModeloCargo> listModeloCargoses;

    public ModeloPersonas() {
    }

    public ModeloPersonas(int id, String tipoIdentificacion, String identificacion, String nombres, String apellidos, String email, String direccion, String ciudad, String tipoPersona, String tipoVisitante, int usuarioId, String codigoInterno, String extensionTelefonica, String foto, String nombreEPS, String vencimientoEPS, String nombreARP, Date pasadoJudicialVencto, String huella, String recibeVisitas, Blob plantillaHuella, int longitudPlantilla, Date vencimientoARP, int enrollNumber, int definicionId, int itinerarioId, String accesoRestringido, Date vencimientoPension, Date vencimientoSeguridadIndustrial, Date vencimientoAudiovisualSeguridadIndustrial, Date vencimientoTrabajoAlturas, Date vencimientoTrabajoConfinados, Date vencimientoTrabajoCaliente, Date vencimientoTrabajoExcavaciones, Date vencimientoTrabajoEnergiaElectrica, Date vencimientoOtros, String rH, String seguridadIndustrialSiNo, String audiovisualSiNo, String alturasSiNo, String confinadosSiNo, String calienteSiNo, String excavacionesSiNo, String strienergiaElectricaSiNo, String otrosSiNo, String telefono, int zonaId, String tipoAcceso, Date fechaInicioAcceso, Date fechaFinAcceso, String observaciones, String consumocasino, int tipoTrabajoId, int empresaenqueTrabajaId, String tarjetaAcceso, String cod_nomina, int id_Dependencias, int id_Empresa, String estado, int id_Grupo_Horario, int id_Turnos, int id_Departamento, int id_Areas, int id_Ciudad, int id_Centro_Costos, ModeloEmpresa modeloEmpresa, ModeloCentro_costo modeloCentroCosto, ModeloGrupoConsumo modeloGrupoConsumo, LinkedList<ModeloCargo> listModeloCargoses) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tipoPersona = tipoPersona;
        this.tipoVisitante = tipoVisitante;
        this.usuarioId = usuarioId;
        this.codigoInterno = codigoInterno;
        this.extensionTelefonica = extensionTelefonica;
        this.foto = foto;
        this.nombreEPS = nombreEPS;
        this.vencimientoEPS = vencimientoEPS;
        this.nombreARP = nombreARP;
        this.pasadoJudicialVencto = pasadoJudicialVencto;
        this.huella = huella;
        this.recibeVisitas = recibeVisitas;
        this.plantillaHuella = plantillaHuella;
        this.longitudPlantilla = longitudPlantilla;
        this.vencimientoARP = vencimientoARP;
        this.enrollNumber = enrollNumber;
        this.definicionId = definicionId;
        this.itinerarioId = itinerarioId;
        this.accesoRestringido = accesoRestringido;
        this.vencimientoPension = vencimientoPension;
        this.vencimientoSeguridadIndustrial = vencimientoSeguridadIndustrial;
        this.vencimientoAudiovisualSeguridadIndustrial = vencimientoAudiovisualSeguridadIndustrial;
        this.vencimientoTrabajoAlturas = vencimientoTrabajoAlturas;
        this.vencimientoTrabajoConfinados = vencimientoTrabajoConfinados;
        this.vencimientoTrabajoCaliente = vencimientoTrabajoCaliente;
        this.vencimientoTrabajoExcavaciones = vencimientoTrabajoExcavaciones;
        this.vencimientoTrabajoEnergiaElectrica = vencimientoTrabajoEnergiaElectrica;
        this.vencimientoOtros = vencimientoOtros;
        this.rH = rH;
        this.seguridadIndustrialSiNo = seguridadIndustrialSiNo;
        this.audiovisualSiNo = audiovisualSiNo;
        this.alturasSiNo = alturasSiNo;
        this.confinadosSiNo = confinadosSiNo;
        this.calienteSiNo = calienteSiNo;
        this.excavacionesSiNo = excavacionesSiNo;
        this.strienergiaElectricaSiNo = strienergiaElectricaSiNo;
        this.otrosSiNo = otrosSiNo;
        this.telefono = telefono;
        this.zonaId = zonaId;
        this.tipoAcceso = tipoAcceso;
        this.fechaInicioAcceso = fechaInicioAcceso;
        this.fechaFinAcceso = fechaFinAcceso;
        this.observaciones = observaciones;
        this.consumocasino = consumocasino;
        this.tipoTrabajoId = tipoTrabajoId;
        this.empresaenqueTrabajaId = empresaenqueTrabajaId;
        this.tarjetaAcceso = tarjetaAcceso;
        this.cod_nomina = cod_nomina;
        this.id_Dependencias = id_Dependencias;
        this.id_Empresa = id_Empresa;
        this.estado = estado;
        this.id_Grupo_Horario = id_Grupo_Horario;
        this.id_Turnos = id_Turnos;
        this.id_Departamento = id_Departamento;
        this.id_Areas = id_Areas;
        this.id_Ciudad = id_Ciudad;
        this.id_Centro_Costos = id_Centro_Costos;
        this.modeloEmpresa = modeloEmpresa;
        this.modeloCentroCosto = modeloCentroCosto;
        this.modeloGrupoConsumo = modeloGrupoConsumo;
        this.listModeloCargoses = listModeloCargoses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoVisitante() {
        return tipoVisitante;
    }

    public void setTipoVisitante(String tipoVisitante) {
        this.tipoVisitante = tipoVisitante;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getExtensionTelefonica() {
        return extensionTelefonica;
    }

    public void setExtensionTelefonica(String extensionTelefonica) {
        this.extensionTelefonica = extensionTelefonica;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombreEPS() {
        return nombreEPS;
    }

    public void setNombreEPS(String nombreEPS) {
        this.nombreEPS = nombreEPS;
    }

    public String getVencimientoEPS() {
        return vencimientoEPS;
    }

    public void setVencimientoEPS(String vencimientoEPS) {
        this.vencimientoEPS = vencimientoEPS;
    }

    public String getNombreARP() {
        return nombreARP;
    }

    public void setNombreARP(String nombreARP) {
        this.nombreARP = nombreARP;
    }

    public Date getPasadoJudicialVencto() {
        return pasadoJudicialVencto;
    }

    public void setPasadoJudicialVencto(Date pasadoJudicialVencto) {
        this.pasadoJudicialVencto = pasadoJudicialVencto;
    }

    public String getHuella() {
        return huella;
    }

    public void setHuella(String huella) {
        this.huella = huella;
    }

    public String getRecibeVisitas() {
        return recibeVisitas;
    }

    public void setRecibeVisitas(String recibeVisitas) {
        this.recibeVisitas = recibeVisitas;
    }

    public Blob getPlantillaHuella() {
        return plantillaHuella;
    }

    public void setPlantillaHuella(Blob plantillaHuella) {
        this.plantillaHuella = plantillaHuella;
    }

    public int getLongitudPlantilla() {
        return longitudPlantilla;
    }

    public void setLongitudPlantilla(int longitudPlantilla) {
        this.longitudPlantilla = longitudPlantilla;
    }

    public Date getVencimientoARP() {
        return vencimientoARP;
    }

    public void setVencimientoARP(Date vencimientoARP) {
        this.vencimientoARP = vencimientoARP;
    }

    public int getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(int enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public int getDefinicionId() {
        return definicionId;
    }

    public void setDefinicionId(int definicionId) {
        this.definicionId = definicionId;
    }

    public int getItinerarioId() {
        return itinerarioId;
    }

    public void setItinerarioId(int itinerarioId) {
        this.itinerarioId = itinerarioId;
    }

    public String getAccesoRestringido() {
        return accesoRestringido;
    }

    public void setAccesoRestringido(String accesoRestringido) {
        this.accesoRestringido = accesoRestringido;
    }

    public Date getVencimientoPension() {
        return vencimientoPension;
    }

    public void setVencimientoPension(Date vencimientoPension) {
        this.vencimientoPension = vencimientoPension;
    }

    public Date getVencimientoSeguridadIndustrial() {
        return vencimientoSeguridadIndustrial;
    }

    public void setVencimientoSeguridadIndustrial(Date vencimientoSeguridadIndustrial) {
        this.vencimientoSeguridadIndustrial = vencimientoSeguridadIndustrial;
    }

    public Date getVencimientoAudiovisualSeguridadIndustrial() {
        return vencimientoAudiovisualSeguridadIndustrial;
    }

    public void setVencimientoAudiovisualSeguridadIndustrial(Date vencimientoAudiovisualSeguridadIndustrial) {
        this.vencimientoAudiovisualSeguridadIndustrial = vencimientoAudiovisualSeguridadIndustrial;
    }

    public Date getVencimientoTrabajoAlturas() {
        return vencimientoTrabajoAlturas;
    }

    public void setVencimientoTrabajoAlturas(Date vencimientoTrabajoAlturas) {
        this.vencimientoTrabajoAlturas = vencimientoTrabajoAlturas;
    }

    public Date getVencimientoTrabajoConfinados() {
        return vencimientoTrabajoConfinados;
    }

    public void setVencimientoTrabajoConfinados(Date vencimientoTrabajoConfinados) {
        this.vencimientoTrabajoConfinados = vencimientoTrabajoConfinados;
    }

    public Date getVencimientoTrabajoCaliente() {
        return vencimientoTrabajoCaliente;
    }

    public void setVencimientoTrabajoCaliente(Date vencimientoTrabajoCaliente) {
        this.vencimientoTrabajoCaliente = vencimientoTrabajoCaliente;
    }

    public Date getVencimientoTrabajoExcavaciones() {
        return vencimientoTrabajoExcavaciones;
    }

    public void setVencimientoTrabajoExcavaciones(Date vencimientoTrabajoExcavaciones) {
        this.vencimientoTrabajoExcavaciones = vencimientoTrabajoExcavaciones;
    }

    public Date getVencimientoTrabajoEnergiaElectrica() {
        return vencimientoTrabajoEnergiaElectrica;
    }

    public void setVencimientoTrabajoEnergiaElectrica(Date vencimientoTrabajoEnergiaElectrica) {
        this.vencimientoTrabajoEnergiaElectrica = vencimientoTrabajoEnergiaElectrica;
    }

    public Date getVencimientoOtros() {
        return vencimientoOtros;
    }

    public void setVencimientoOtros(Date vencimientoOtros) {
        this.vencimientoOtros = vencimientoOtros;
    }

    public String getrH() {
        return rH;
    }

    public void setrH(String rH) {
        this.rH = rH;
    }

    public String getSeguridadIndustrialSiNo() {
        return seguridadIndustrialSiNo;
    }

    public void setSeguridadIndustrialSiNo(String seguridadIndustrialSiNo) {
        this.seguridadIndustrialSiNo = seguridadIndustrialSiNo;
    }

    public String getAudiovisualSiNo() {
        return audiovisualSiNo;
    }

    public void setAudiovisualSiNo(String audiovisualSiNo) {
        this.audiovisualSiNo = audiovisualSiNo;
    }

    public String getAlturasSiNo() {
        return alturasSiNo;
    }

    public void setAlturasSiNo(String alturasSiNo) {
        this.alturasSiNo = alturasSiNo;
    }

    public String getConfinadosSiNo() {
        return confinadosSiNo;
    }

    public void setConfinadosSiNo(String confinadosSiNo) {
        this.confinadosSiNo = confinadosSiNo;
    }

    public String getCalienteSiNo() {
        return calienteSiNo;
    }

    public void setCalienteSiNo(String calienteSiNo) {
        this.calienteSiNo = calienteSiNo;
    }

    public String getExcavacionesSiNo() {
        return excavacionesSiNo;
    }

    public void setExcavacionesSiNo(String excavacionesSiNo) {
        this.excavacionesSiNo = excavacionesSiNo;
    }

    public String getStrienergiaElectricaSiNo() {
        return strienergiaElectricaSiNo;
    }

    public void setStrienergiaElectricaSiNo(String strienergiaElectricaSiNo) {
        this.strienergiaElectricaSiNo = strienergiaElectricaSiNo;
    }

    public String getOtrosSiNo() {
        return otrosSiNo;
    }

    public void setOtrosSiNo(String otrosSiNo) {
        this.otrosSiNo = otrosSiNo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getZonaId() {
        return zonaId;
    }

    public void setZonaId(int zonaId) {
        this.zonaId = zonaId;
    }

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }

    public Date getFechaInicioAcceso() {
        return fechaInicioAcceso;
    }

    public void setFechaInicioAcceso(Date fechaInicioAcceso) {
        this.fechaInicioAcceso = fechaInicioAcceso;
    }

    public Date getFechaFinAcceso() {
        return fechaFinAcceso;
    }

    public void setFechaFinAcceso(Date fechaFinAcceso) {
        this.fechaFinAcceso = fechaFinAcceso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getConsumocasino() {
        return consumocasino;
    }

    public void setConsumocasino(String consumocasino) {
        this.consumocasino = consumocasino;
    }

    public int getTipoTrabajoId() {
        return tipoTrabajoId;
    }

    public void setTipoTrabajoId(int tipoTrabajoId) {
        this.tipoTrabajoId = tipoTrabajoId;
    }

    public int getEmpresaenqueTrabajaId() {
        return empresaenqueTrabajaId;
    }

    public void setEmpresaenqueTrabajaId(int empresaenqueTrabajaId) {
        this.empresaenqueTrabajaId = empresaenqueTrabajaId;
    }

    public String getTarjetaAcceso() {
        return tarjetaAcceso;
    }

    public void setTarjetaAcceso(String tarjetaAcceso) {
        this.tarjetaAcceso = tarjetaAcceso;
    }

    public String getCod_nomina() {
        return cod_nomina;
    }

    public void setCod_nomina(String cod_nomina) {
        this.cod_nomina = cod_nomina;
    }

    public int getId_Dependencias() {
        return id_Dependencias;
    }

    public void setId_Dependencias(int id_Dependencias) {
        this.id_Dependencias = id_Dependencias;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_Grupo_Horario() {
        return id_Grupo_Horario;
    }

    public void setId_Grupo_Horario(int id_Grupo_Horario) {
        this.id_Grupo_Horario = id_Grupo_Horario;
    }

    public int getId_Turnos() {
        return id_Turnos;
    }

    public void setId_Turnos(int id_Turnos) {
        this.id_Turnos = id_Turnos;
    }

    public int getId_Departamento() {
        return id_Departamento;
    }

    public void setId_Departamento(int id_Departamento) {
        this.id_Departamento = id_Departamento;
    }

    public int getId_Areas() {
        return id_Areas;
    }

    public void setId_Areas(int id_Areas) {
        this.id_Areas = id_Areas;
    }

    public int getId_Ciudad() {
        return id_Ciudad;
    }

    public void setId_Ciudad(int id_Ciudad) {
        this.id_Ciudad = id_Ciudad;
    }

    public int getId_Centro_Costos() {
        return id_Centro_Costos;
    }

    public void setId_Centro_Costos(int id_Centro_Costos) {
        this.id_Centro_Costos = id_Centro_Costos;
    }

    public ModeloEmpresa getModeloEmpresa() {
        return modeloEmpresa;
    }

    public void setModeloEmpresa(ModeloEmpresa modeloEmpresa) {
        this.modeloEmpresa = modeloEmpresa;
    }

    public ModeloCentro_costo getModeloCentroCosto() {
        return modeloCentroCosto;
    }

    public void setModeloCentroCosto(ModeloCentro_costo modeloCentroCosto) {
        this.modeloCentroCosto = modeloCentroCosto;
    }

    public ModeloGrupoConsumo getModeloGrupoConsumo() {
        return modeloGrupoConsumo;
    }

    public void setModeloGrupoConsumo(ModeloGrupoConsumo modeloGrupoConsumo) {
        this.modeloGrupoConsumo = modeloGrupoConsumo;
    }

    public LinkedList<ModeloCargo> getListModeloCargoses() {
        return listModeloCargoses;
    }

    public void setListModeloCargoses(LinkedList<ModeloCargo> listModeloCargoses) {
        this.listModeloCargoses = listModeloCargoses;
    }

}

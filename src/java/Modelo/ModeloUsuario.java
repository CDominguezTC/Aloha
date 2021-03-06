/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Julian A Aristizabal
 */
public class ModeloUsuario {

    /*private int id;
    private String nombre;
    private String login;
    private String password;*/
    private Integer id;
    private String nombre;
    private String login;
    private String password;
    private ModeloRol rol;
    private String estado;

    public ModeloUsuario() {
    }

    public ModeloUsuario(Integer id, String nombre, String login, String password, ModeloRol rol, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.login = login;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rol
     */
    public ModeloRol getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(ModeloRol rol) {
        this.rol = rol;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public String getString() {
        return nombre;
    }
    
    


}

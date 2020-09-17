/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Rest;

import Herramienta.Herramienta;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Carlos A Dominguez D
 */
@Path("Tokent")
public class TokenResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TokenResource
     */
    public TokenResource() {
    }

    /**
     * Retrieves representation of an instance of Rest.TokenResource
     *
     * @param Key
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("Key") String Key) {
        Herramienta herramientas = new Herramienta();
        if (Key != null) {
            if ("800211327".equals(Key)) {
                return herramientas.getSHA256(Key);
            }            
        }
        return null;
    }

    /**
     * PUT method for updating or creating an instance of TokenResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}

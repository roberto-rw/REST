/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ws;

import entidades.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author user
 */
@Path("producto")
public class ProductoResource {

    @Context
    private UriInfo context;

    List<Producto>lista = new ArrayList<Producto>();
    public ProductoResource() {
        this.lista.add(new Producto(1, "Xiaomi"));
        this.lista.add(new Producto(2, "iPhone"));
        this.lista.add(new Producto(3, "Motorola"));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearProducto(@QueryParam("id") int id, @QueryParam("nombre") String nombre){
        Producto productoNuevo = new Producto(id, nombre);
        lista.add(productoNuevo);
        
        Producto[] arrayProductos = new Producto[lista.size()];
        arrayProductos = lista.toArray(new Producto[0]);
        
        return Response.status(201).entity(arrayProductos).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(@QueryParam("id") int id, @QueryParam("nombre") String nombre){
        
        Producto productoActualizado = new Producto(id, nombre);

        for(Producto p: lista){
            if(p.getId() == id){
                p.setNombre(nombre);
                System.out.println("YEAH");
                break;
            }
        }
        
        Producto[] arrayProductos = new Producto[lista.size()];
        arrayProductos = lista.toArray(new Producto[0]);
        
        return Response.status(200).entity(arrayProductos).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerProducto(@PathParam("id") int id){
        Producto producto = null;
        for(Producto p: lista){
            if(p.getId() == id){
                producto = p;
                break;
            }
        }
        
        Producto[] arrayProductos = new Producto[lista.size()];
        arrayProductos = lista.toArray(new Producto[0]);
        
        return Response.accepted(200).entity(producto).build();
    }
    
    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response obtenerProductos(){
        
        Producto[] arrayProductos = new Producto[lista.size()];
        arrayProductos = lista.toArray(new Producto[0]);
        
        return Response.accepted(200).entity(arrayProductos).build();
    }
    
    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrarProducto(@PathParam("id") int id){
        
        for(Producto p: lista){
            if(p.getId() == id){
                lista.remove(p);
                break;
            }
        }
        
        Producto[] arrayProductos = new Producto[lista.size()];
        arrayProductos = lista.toArray(new Producto[0]);
        
        return Response.accepted(204).entity(arrayProductos).build();
    }
    
    
}

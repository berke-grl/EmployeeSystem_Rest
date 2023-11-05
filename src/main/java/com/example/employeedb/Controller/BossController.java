package com.example.employeedb.Controller;

import com.example.employeedb.Entity.Boss;
import com.example.employeedb.Entity.Department;
import com.example.employeedb.Repository.BossRepository;
import com.example.employeedb.Repository.DepartmentRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

public class BossController {

    private BossRepository repository = new BossRepository();

    @Path("/getAll")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Boss> list;
        try {
            list = repository.findAll();
            if (!list.isEmpty())
                return Response.ok().entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Boss list is empty").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("an exception has occurred").build();
        }
    }

    @Path("/getById/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Boss boss;
        try {
            boss = repository.findById(id);
            if (boss != null)
                return Response.ok().entity(boss).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + "id Boss not exist").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("an exception has occurred").build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(Boss boss) {
        try {
            boolean result = repository.save(boss);
            if (result)
                return Response.ok().entity("Boss added successfully").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Boss added FAIL !").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("An exception has occured").build();
        }
    }

    @Path("/deleteById/{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(@PathParam("id") int id) {
        try {
            boolean result = repository.deleteById(id);
            if (result)
                return Response.ok().entity(id + " id Boss deleted successfully").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Boss DELETE FAIL !").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("An exception has occured").build();
        }
    }
}

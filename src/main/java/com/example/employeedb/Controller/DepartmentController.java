package com.example.employeedb.Controller;

import com.example.employeedb.Entity.Department;
import com.example.employeedb.Entity.Employee;
import com.example.employeedb.Repository.DepartmentRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/department")
public class DepartmentController {

    private DepartmentRepository repository = new DepartmentRepository();

    @Path("/getAll")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Department> list;
        try {
            list = repository.findAll();
            if (!list.isEmpty())
                return Response.ok().entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Department list is empty").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("an exception has occurred").build();
        }
    }

    @Path("/getById/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Department department;
        try {
            department = repository.findById(id);
            if (department != null)
                return Response.ok().entity(department).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity(id + "id Department not exist").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("an exception has occurred").build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(Department department) {
        try {
            boolean result = repository.save(department);
            if (result)
                return Response.ok().entity("Department added successfully").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Department added FAIL !").build();
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
                return Response.ok().entity(id + " id Department deleted successfully").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Department DELETE FAIL !").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("An exception has occured").build();
        }
    }
}

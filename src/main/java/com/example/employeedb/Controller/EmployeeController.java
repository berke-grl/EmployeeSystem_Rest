package com.example.employeedb.Controller;

import com.example.employeedb.Entity.Employee;
import com.example.employeedb.Repository.EmployeeRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;

@Path("/employee")
public class EmployeeController {

    private EmployeeRepository repository = new EmployeeRepository();

    @Path("/getAll")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response findAll() {
        List<Employee> list;
        try {
            list = repository.findAll();
            if (!list.isEmpty())
                return Response.ok().entity(list).build();
            else
                return Response.status(Response.Status.NOT_FOUND).entity("Employee list is empty").build();

        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("An exception has occured").build();
        }
    }

    @Path("/getById/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Employee employee;
        try {
            employee = repository.findById(id);
            if (employee != null)
                return Response.ok().entity(employee).build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Employee Not Found !").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("An exception has occured").build();
        }
    }

    @Path("/save")
    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        try {
            boolean result = repository.save(employee);
            if (result)
                return Response.ok().entity("Employee added successfully").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Employee added FAIL !").build();
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
                return Response.ok().entity(id + " id Employee deleted successfully").build();
            else
                return Response.status(Response.Status.BAD_REQUEST).entity("Employee DELETE FAIL !").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.serverError().entity("An exception has occured").build();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.exception.OutputEntity;
import com.example.demo.service.ClienteService;
import com.example.demo.util.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author marco-romero
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/custom")
public class ClienteResponseCustom {

    @Autowired
    private ClienteService clienteService;

    //Listar Cliente
    @GetMapping(value = "/listarClientes")
    public ResponseEntity<OutputEntity> listarCliente() {
        OutputEntity<List<Cliente>> out = new OutputEntity<>();
        try {
            List<Cliente> result = clienteService.findAllClientes();
            if (result == null) {
                out.failed(Response.NOTFOUND.getCode(), Response.NOTFOUND.getKey(), result);
            } else {
                out.succes(Response.OK.getCode(), Response.OK.getKey(), result);
            }

        } catch (Exception e) {
            out.error();

        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Guardar Cliente
    @PostMapping(value = "/guardarCliente")
    public ResponseEntity<OutputEntity> guardarCliente(@RequestBody ClienteDTO cliente) {
        OutputEntity<Cliente> out = new OutputEntity<>();
        try {
            Cliente result = clienteService.saveCliente(cliente);
            out.succes(Response.CREATED.getCode(), Response.CREATED.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Buscar Cliente
    @GetMapping(value = "/buscarCliente/{id}")
    public ResponseEntity<OutputEntity> buscarCliente(@PathVariable Integer id) {
        OutputEntity<Cliente> out = new OutputEntity<>();
        try {
            Cliente result = clienteService.findClienteById(id);
            out.succes(Response.OK.getCode(), Response.OK.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Actualizar cliente
    @PutMapping(value = "/actualizarCliente/{id}")
    public ResponseEntity<OutputEntity> updateCliente(@PathVariable Integer id, @RequestBody ClienteDTO cliente) {
        OutputEntity<Integer> out = new OutputEntity<>();
        try {
            Integer result = clienteService.updateCliente(id, cliente);
            out.succes(Response.UPDATE.getCode(), Response.UPDATE.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }

    //Eliminar cliente
    @DeleteMapping(value = "/eliminarCliente/{id}")
    public ResponseEntity<OutputEntity> deleteCliente(@PathVariable Integer id) {
        OutputEntity<Integer> out = new OutputEntity<>();
        try {
            Integer result = clienteService.deleteCliente(id);
            out.succes(Response.DELETED.getCode(), Response.DELETED.getKey(), result);
        } catch (Exception e) {
            out.error();
        }
        return new ResponseEntity<>(out, out.getCode());
    }
}

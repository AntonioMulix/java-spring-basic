/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.service.ClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@RequestMapping("/api/v1/test")
public class ClienteTestController {

    @Autowired
    private ClienteService clienteService;

    //Listar clientes
    @GetMapping(value = "/listarClientes")
    public ResponseEntity<List> listarCliente() {
        try {
            List<Cliente> result = clienteService.findAllClientes();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Guardar cliente
    @PostMapping(value = "/guardarCliente")
    public ResponseEntity<Integer> guardarCliente(@RequestBody ClienteDTO cliente) {
        try {
            Integer result = clienteService.saveCliente(cliente);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Buscar cliente
    @GetMapping(value = "/buscarCliente/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id) {
        try {
            Cliente result = clienteService.findClienteById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Actualizar Cliente
    @PutMapping(value = "/actualizarCliente/{id}")
    public ResponseEntity<Integer> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO cliente) {
        try {
            Integer result = clienteService.updateCliente(id, cliente);
            if (result == 1) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Eliminar Cliente
    @DeleteMapping(value = "/eliminarCliente/{id}")
    public ResponseEntity<Integer> eliminarCliente(@PathVariable Integer id) {
        try {
            Integer result = clienteService.deleteCliente(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

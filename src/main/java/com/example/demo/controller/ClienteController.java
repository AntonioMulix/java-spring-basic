/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.controller;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ClienteRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/listarClientes")
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @PostMapping("/guardarCliente")
    public Cliente guardarCliente(@RequestBody ClienteDTO cliente) {
        Cliente guardarCliente = new Cliente();
        guardarCliente.setNombre(cliente.getNombre());
        guardarCliente.setApellido(cliente.getApellido());
        guardarCliente.setEmail(cliente.getEmail());
        return clienteRepository.save(guardarCliente);
    }

    @GetMapping("/buscarCliente/{id}")
    public ResponseEntity<Cliente> listarClienteById(@PathVariable Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe : " + id));
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/actualizarCliente/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteRequest) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe: " + id));

        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setEmail(clienteRequest.getEmail());

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActualizado);

    }

    @DeleteMapping("/eliminarCliente/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("El cliente con ese ID no existe: " + id));
        clienteRepository.delete(cliente);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}

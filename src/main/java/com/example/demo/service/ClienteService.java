/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.entity.Cliente;
import java.util.List;

/**
 *
 * @author marco-romero
 */
public interface ClienteService {

    // ********************* CRUD Cliente ********************
    //Listar todos
    public List<Cliente> findAllClientes();

    //Guardar nuevo cliente
    public Cliente saveCliente(ClienteDTO cliente);

    //Buscar cliente
    public Cliente findClienteById(Integer id);

    //Actualizar cliente
    public Integer updateCliente(Integer id, ClienteDTO cliente);

    //Eliminar cliente
    public Integer deleteCliente(Integer id);

}

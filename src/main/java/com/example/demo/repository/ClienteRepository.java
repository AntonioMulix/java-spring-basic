/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.repository;

import com.example.demo.entity.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author marco-romero
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT * FROM cliente", nativeQuery = true)
    public List<Cliente> listarClientes();

    @Query(value = "SELECT * FROM cliente WHERE id =:id", nativeQuery = true)
    public Cliente findClienteById(@Param("id") Integer id);
}

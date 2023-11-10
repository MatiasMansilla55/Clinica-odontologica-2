package com.backend.clinicaodontologica.repository;

import com.backend.clinicaodontologica.model.Paciente;

import java.util.List;

public interface IDao<T> {
    T registrar(T t);
    List<T> listarTdos();
    T buscarPorId(int id);
    void eliminar(int id);
    T actualizar(T t);
}

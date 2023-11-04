package com.backend.clinicaodontologica.repository.impl;


import com.backend.clinicaodontologica.repository.H2Connection;
import com.backend.clinicaodontologica.repository.IDao;
import com.backend.clinicaodontologica.model.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao<Odontologo> {

    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection= null;
        Odontologo odontologoRegistrado= null;

        try {

            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA,NOMBRE,APELLIDO) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.execute();

            odontologoRegistrado = new Odontologo(odontologo.getMatricula(),odontologo.getNombre(),odontologo.getApellido() );
            ResultSet resultSet= preparedStatement.getGeneratedKeys();

            while (resultSet.next()){
                odontologoRegistrado.setId(resultSet.getInt("id"));
            }

            connection.commit();

            LOGGER.info("Se ha registrado al odontologo: " + odontologoRegistrado);


        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    LOGGER.info("Tuvimos un problema");
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        return odontologoRegistrado;

    }

    @Override
    public List<Odontologo> listarTdos() {
        List<Odontologo> odontologos=new ArrayList<>();
        Connection connection= null;

        try{
            connection=H2Connection.getConnection();

            PreparedStatement preparedStatement= connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Odontologo odontologo = new Odontologo(resultSet.getInt("id"),resultSet.getInt("matricula"),resultSet.getString("nombre"),resultSet.getString("apellido"));
                odontologos.add(odontologo);
            }

            LOGGER.info("El listado de odontologos es: " + odontologos);


        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }

    @Override
    public void eliminar(int id) {

    }

}

package com.backend.clinicaodontologica;

import com.backend.clinicaodontologica.repository.impl.OdontologoDaoH2;
import com.backend.clinicaodontologica.repository.impl.OdontologoMemoria;
import com.backend.clinicaodontologica.repository.impl.PacienteDaoH2;
import com.backend.clinicaodontologica.model.Domicilio;
import com.backend.clinicaodontologica.model.Odontologo;
import com.backend.clinicaodontologica.model.Paciente;
import com.backend.clinicaodontologica.service.impl.OdontologService;
import com.backend.clinicaodontologica.service.impl.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class ClinicaOdontologicaApplicationTests {
	private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplicationTests.class);
	private OdontologService odontologService = new OdontologService(new OdontologoDaoH2());
	private static OdontologService odontologServiceMemoria=new OdontologService(new OdontologoMemoria(new ArrayList<>()));

	//private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

	@BeforeAll
	static void doBefore() {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
			connection = DriverManager.getConnection("jdbc:h2:~/clinica-odontologica;INIT=RUNSCRIPT FROM 'create.sql'", "sa", "sa");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


	@Test
	void deberiaRegistrarYRetonarUnOdontologo(){
		Odontologo odontologo = new Odontologo(123,"matias","perez");


		Odontologo odontologoRegistrado= odontologService.registrarOdontologo(odontologo);

		Assertions.assertTrue(odontologoRegistrado.getId() != 0);
	}

	@Test
	void deberiaRetornarUnaListaDeOdontologos(){

		Assertions.assertFalse(odontologService.listarOdontologo().isEmpty());
	}

	@Test
	void deberiaRegistrarYDevolverEnMemoriaUnOdontologo(){
		odontologServiceMemoria=new OdontologService(new OdontologoMemoria(new ArrayList<>()));
		Odontologo odontologo1= new Odontologo(1,2,"jose","cevallos");
		Odontologo odontologAPersistir= odontologServiceMemoria.registrarOdontologo(odontologo1);

		Assertions.assertNotNull(odontologAPersistir.getId());
	}


	//DEBERIA DE RETORNARNOS UNA LA LISTA CON UN ODONTOLOGO PERO NO NOS RETORNA VACIA, NO SABEMOS EN DONDE NOS ESTAMOS EQUIVOCANDO.
	@Test
	void deberiaRetornarLaListaDeOdonotologos(){

		//Odontologo odontologoGuardado= new Odontologo(2,3,"sebastian","sanchez");
		//Odontologo odontologoGuardado2= new Odontologo(3,44,"Lucia","Marquez");

		//odontologServiceMemoria.registrarOdontolog(odontologoGuardado);
		//odontologServiceMemoria.registrarOdontolog(odontologoGuardado2);
		//LOGGER.info(odontologServiceMemoria.listaDeOdontologos());
		Assertions.assertFalse(odontologServiceMemoria.listarOdontologo().isEmpty());
	}

	//Test para pacientes:

	//@Test
	//void deberiaAgregarUnPaciente(){

		//Paciente paciente = new Paciente("Nombre", "Apellido", 123456, LocalDate.of(2023, 05, 02), new Domicilio("Calle", 13, "Localidad", "Provincia"));

		//Paciente pacienteRegistrado = pacienteService.registrarPaciente(paciente);

		//Assertions.assertTrue(pacienteRegistrado.getId() != 0);

	//}

	//@Test
	//void deberiaRetornarUnaListaNoVacia(){

		//assertFalse(pacienteService.listarPacientes().isEmpty());

	//}

}

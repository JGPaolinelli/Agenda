package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DomicilioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DomicilioDAO;

public class DomicilioDAOSQL implements DomicilioDAO {

	private static final String insert = "INSERT INTO domicilios (idDomicilio, calle, altura, piso, departamento, idLocalidad) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM domicilios WHERE idDomicilio = ?";
	private static final String update = "UPDATE domicilios SET calle = ?, altura = ?, piso = ?, departamento = ?, idLocalidad = ? WHERE idDomicilio = ?";
	private static final String maxid = "SELECT MAX(idDomicilio) AS lastIdAdd FROM domicilios";
	private static final String readall = "SELECT * FROM domicilios";
	private static final String getDomicilio = "SELECT * FROM domicilios WHERE idDomicilio = ?";

	public boolean insert(DomicilioDTO domicilio) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setLong(1, domicilio.getIdDomicilio());
			statement.setString(2, domicilio.getCalle());
			statement.setInt(3, domicilio.getAltura());
			statement.setInt(4, domicilio.getPiso());
			statement.setInt(5, domicilio.getDepartamento());
			statement.setLong(6, domicilio.getIdLocalidad());
			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(DomicilioDTO domicilio_a_eliminar) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Long.toString(domicilio_a_eliminar.getIdDomicilio()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutÃ³ devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int max() {
		PreparedStatement statement;
		int idMax = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(maxid);
			ResultSet max = statement.executeQuery();
			if (max.next()) {
			   idMax = max.getInt("lastIdAdd");  
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idMax;
	}

	public boolean update(DomicilioDTO domicilio_para_actualizar) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);			
			statement.setString(1, domicilio_para_actualizar.getCalle());
			statement.setInt(2, domicilio_para_actualizar.getAltura());
			statement.setInt(3, domicilio_para_actualizar.getPiso());
			statement.setInt(4, domicilio_para_actualizar.getDepartamento());
			statement.setLong(5, domicilio_para_actualizar.getIdLocalidad());
			statement.setLong(6, domicilio_para_actualizar.getIdDomicilio());
			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<DomicilioDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<DomicilioDTO> domicilios = new ArrayList<DomicilioDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				domicilios.add(new DomicilioDTO(
						resultSet.getLong("idDomicilio"),
						resultSet.getString("calle"),
						resultSet.getInt("altura"),
						resultSet.getInt("piso"),
						resultSet.getInt("departamento"),
						resultSet.getLong("idLocalidad")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domicilios;
	}

	public DomicilioDTO getDomicilioById(long idDomicilio) {
		PreparedStatement statement;
		ResultSet rs;
		DomicilioDTO domicilio = null;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(getDomicilio);
			statement.setLong(1, idDomicilio);
			rs = statement.executeQuery();
			while (rs.next()) {
				domicilio = new DomicilioDTO(
							rs.getLong("idDomicilio"), 
							rs.getString("calle"), 
							rs.getInt("altura"), 
							rs.getInt("piso"), 
							rs.getInt("departamento"), 
							rs.getLong("idLocalidad")
							);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return domicilio;
	}

}

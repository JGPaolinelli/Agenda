package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoDeContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoDeContactoDAO;

public class TipoDeContactoDAOSQL implements TipoDeContactoDAO {
	
	private static final String insert = "INSERT INTO tiposdecontactos (idTipoDeContacto, tipoDeContacto) VALUES (?, ?)";
	private static final String delete = "DELETE FROM tiposdecontactos WHERE idTipoDeContacto=?";
    private static final String update = "UPDATE tiposdecontactos SET tipoDeContacto=? WHERE idTipoDeContacto=?";
	private static final String readall = "SELECT * FROM tiposdecontactos";

	public boolean insert(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setLong(1, tipoDeContacto.getIdTipoDeContacto());
			statement.setString(2, tipoDeContacto.getTipoDeContacto());
			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Long.toString(tipoDeContacto.getIdTipoDeContacto()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutÃ³ devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean update(TipoDeContactoDTO tipoDeContacto) {
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);			
			statement.setString(1, tipoDeContacto.getTipoDeContacto());
			statement.setLong(2, tipoDeContacto.getIdTipoDeContacto());
			if (statement.executeUpdate() > 0) // Si se ejecutó devuelvo true
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<TipoDeContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoDeContactoDTO> tipoDeContactos = new ArrayList<TipoDeContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				tipoDeContactos.add(new TipoDeContactoDTO(
						resultSet.getLong("idTipoDeContacto"),
						resultSet.getString("tipoDeContacto")
						));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoDeContactos;
	}

}

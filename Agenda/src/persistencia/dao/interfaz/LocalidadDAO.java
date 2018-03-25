package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidadParaAgregar);

	public boolean delete(LocalidadDTO localidadParaELiminar);

	public boolean update(LocalidadDTO localidadParaActualizar);

	public List<LocalidadDTO> readAll();

}

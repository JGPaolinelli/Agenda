package persistencia.dao.interfaz;

import java.util.List;

import dto.DomicilioDTO;

public interface DomicilioDAO {

	public boolean insert(DomicilioDTO domicilioParaAgregar);

	public boolean delete(DomicilioDTO domicilioParaEliminar);

	public boolean update(DomicilioDTO domicilioParaActualizar);
	
	public int max();

	public List<DomicilioDTO> readAll();

	public DomicilioDTO getDomicilioById(long idDomicilio);

}

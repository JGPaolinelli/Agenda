package persistencia.dao.interfaz;

import java.util.List;
import dto.TipoDeContactoDTO;

public interface TipoDeContactoDAO {

	public boolean insert(TipoDeContactoDTO tipoDeContactoParaAgregar);

	public boolean delete(TipoDeContactoDTO tipoDeContactoParaEliminar);
	
	public boolean update(TipoDeContactoDTO tipoDeContactoParaActualizar);

	public List<TipoDeContactoDTO> readAll();
	
}

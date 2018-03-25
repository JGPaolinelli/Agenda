package dto;

public class TipoDeContactoDTO {

	private long idTipoDeContacto;
	private String tipoDeContacto;
	
	public TipoDeContactoDTO(long idTipoDeContacto, String tipoDeContacto) {
		this.idTipoDeContacto = idTipoDeContacto;
		this.tipoDeContacto = tipoDeContacto;
	}
	
	public long getIdTipoDeContacto() {
		return idTipoDeContacto;
	}
	public void setIdTipoDeContacto(long idTipoDeContacto) {
		this.idTipoDeContacto = idTipoDeContacto;
	}
	public String getTipoDeContacto() {
		return tipoDeContacto;
	}
	public void setTipoDeContacto(String tipoDeContacto) {
		this.tipoDeContacto = tipoDeContacto;
	}
	
    public String toString()
    {
     return tipoDeContacto;
    }
	
}

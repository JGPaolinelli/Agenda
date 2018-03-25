package dto;

public class LocalidadDTO {

	private long idLocalidad;
	private String localidad;
	
	public LocalidadDTO(long idLocalidad, String localidad) {
		this.idLocalidad = idLocalidad;
		this.localidad = localidad;
	}

	public long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	
    public String toString()
    {
     return localidad;
    }
	
}

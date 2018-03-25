package dto;

public class ZodiacoDTO {
	
	private String serie;
	private String categoria;
	private int valor;
	
	public ZodiacoDTO(String serie, String categoria, int valor) {
		super();
		this.serie = serie;
		this.categoria = categoria;
		this.valor = valor;
	}
	
	public String getSerie() {
		return serie;
	}
	
	public void setSerie(String serie) {
		this.serie = serie;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
}

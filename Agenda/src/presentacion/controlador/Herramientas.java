package presentacion.controlador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.PersonaDTO;
import dto.PersonaZodiacoDTO;

public class Herramientas {

	public static List<PersonaZodiacoDTO> agregarSignoZodiaco(List<PersonaDTO> personaList) {
		List<PersonaZodiacoDTO> personaZodiacoList = new ArrayList<>();
		for (PersonaDTO personaDTO : personaList) {
			personaZodiacoList.add(zodiaco(personaDTO));
		}		
		return ordenarZodiaco(personaZodiacoList);
	}

	private static List<PersonaZodiacoDTO> ordenarZodiaco(List<PersonaZodiacoDTO> personaZodiacoList) {
		List<PersonaZodiacoDTO> listaOrdenada= new ArrayList<PersonaZodiacoDTO>();
		for (int j = 0; j < 11; j++) {
			for (int i = 0; i < personaZodiacoList.size(); i++) {
				String signo = personaZodiacoList.get(i).getSignoZodiaco();
				if (j==0 && signo.equals("Aries")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==1 && signo.equals("Tauro")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==2 && signo.equals("Geminis")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==3 && signo.equals("Cancer")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==4 && signo.equals("Leo")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==5 && signo.equals("Virgo")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==6 && signo.equals("Libra")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==7 && signo.equals("Esccorpio")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==8 && signo.equals("Sagitario")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==9 && signo.equals("Capricornio")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==10 && signo.equals("Acuario")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}else if (j==11 && signo.equals("Pisis")) {
					listaOrdenada.add(personaZodiacoList.get(i));
				}
			}
		}
		return listaOrdenada;
	}

	@SuppressWarnings("deprecation")
	private static PersonaZodiacoDTO zodiaco(PersonaDTO persona) {
		
		Date fechaDeNacimiento = new Date(persona.getFechaDeNacimiento().getTime());
		PersonaZodiacoDTO personaZodiaco = new PersonaZodiacoDTO(persona.getIdPersona(), 
																 persona.getNombre(), 
				                                                 persona.getTelefono(), 
				                                                 persona.getEmail(), 
				                                                 fechaDeNacimiento, 
				                                                 persona.getIdDomicilio(), 
				                                                 persona.getIdTipoDeContacto(), 
				                                                 null);
		
		int mes = fechaDeNacimiento.getMonth();		
		int dia = fechaDeNacimiento.getDay();

		switch (mes) {
		case 0:
			if (dia < 20) {
				personaZodiaco.setSignoZodiaco("Capricornio");
			} else {
				personaZodiaco.setSignoZodiaco("Acuario");
			}
			break;
		case 1:
			if (dia < 18) {
				personaZodiaco.setSignoZodiaco("Acuario");
			} else {
				personaZodiaco.setSignoZodiaco("Piscis");
			}
			break;
		case 2:
			if (dia < 21) {
				personaZodiaco.setSignoZodiaco("Piscis");
			} else {
				personaZodiaco.setSignoZodiaco("Aries");
			}
			break;
		case 3:
			if (dia < 20) {
				personaZodiaco.setSignoZodiaco("Aries");
			} else {
				personaZodiaco.setSignoZodiaco("Tauro");
			}
			break;
		case 4:
			if (dia < 21) {
				personaZodiaco.setSignoZodiaco("Tauro");
			} else {
				personaZodiaco.setSignoZodiaco("Geminis");
			}
			break;
		case 5:
			if (dia < 21) {
				personaZodiaco.setSignoZodiaco("Geminis");
			} else {
				personaZodiaco.setSignoZodiaco("Cancer");
			}
			break;
		case 6:
			if (dia < 23) {
				personaZodiaco.setSignoZodiaco("Cancer");
			} else {
				personaZodiaco.setSignoZodiaco("Leo");
			}
			break;
		case 7:
			if (dia < 23) {
				personaZodiaco.setSignoZodiaco("Leo");
			} else {
				personaZodiaco.setSignoZodiaco("Virgo");
			}
			break;
		case 8:
			if (dia < 23) {
				personaZodiaco.setSignoZodiaco("Virgo");
			} else {
				personaZodiaco.setSignoZodiaco("Libra");
			}
			break;
		case 9:
			if (dia < 23) {
				personaZodiaco.setSignoZodiaco("Libra");
			} else {
				personaZodiaco.setSignoZodiaco("Escorpio");
			}
			break;
		case 10:
			if (dia < 22) {
				personaZodiaco.setSignoZodiaco("Escorpio");
			} else {
				personaZodiaco.setSignoZodiaco("Sagitario");
			}
			break;
		case 11:
			if (dia < 22) {
				personaZodiaco.setSignoZodiaco("Sagitario");
			} else {
				personaZodiaco.setSignoZodiaco("Capricornio");
			}
			break;
		default: 
        	break;
		}
		return personaZodiaco;
	}
}

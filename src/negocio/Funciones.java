package negocio;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.List;

public class Funciones {

	public static int traerAnio(GregorianCalendar f){
		return f.get(Calendar.YEAR);
	}

	public static int traerMes(GregorianCalendar f){
		return f.get(Calendar.MONTH)+1;
	}

	public static int traerDia(GregorianCalendar f){
		return f.get(Calendar.DAY_OF_MONTH);
	}

	public static boolean esBisiesto(int anio){
		boolean resultado = false;

		if (anio % 4 == 0) {
			resultado = true;
		}
		if (anio % 100 == 0 && anio % 400 != 0) {
			resultado = false;
		}

		return resultado;
	}

	public static boolean esFechaValida(int anio, int mes, int dia) {
		boolean salida = true;

		if (mes < 0 || mes > 11 || anio < 1900 || anio > 2200 || dia < 1) {
			salida = false;
		}
		else {
			if (dia > traerCantDiasDeUnMes(anio,mes)) {
				salida = false;
			}
		}

		return salida;
	}


	public static GregorianCalendar traerFecha(int anio, int mes, int dia) {
		GregorianCalendar objCalendario = null; 
		if (esFechaValida(anio, mes, dia)) {
			objCalendario = new GregorianCalendar(anio,mes,dia); 
		}
		return objCalendario;
	}

	public static GregorianCalendar traerFecha(String fecha) {
		GregorianCalendar objCalendario = null;
		int dia = 0;
		int mes = 0;
		int anio = 0;

		try{
			dia = Integer.parseInt(fecha.substring(0, 2));
			mes = Integer.parseInt(fecha.substring(3, 5));
			anio = Integer.parseInt(fecha.substring(6, 10));

			if (esFechaValida(anio, mes, dia)) {
				objCalendario = new GregorianCalendar(anio,mes,dia);
			}
		}
		catch ( Exception e ){
			System.out.println("Excepcion: " + e.getMessage() );
		}

		return objCalendario;
	}

	public static String traerFechaCorta(GregorianCalendar fecha) {
		return fechaConFormato(fecha.get(Calendar.DAY_OF_MONTH), fecha.get(Calendar.MONTH), fecha.get(Calendar.YEAR));
	}

	public static String traerFechaCorta() {
		GregorianCalendar objGregorianCalendar = new GregorianCalendar();
		return fechaConFormato(objGregorianCalendar.get(Calendar.DAY_OF_MONTH), objGregorianCalendar.get(Calendar.MONTH), objGregorianCalendar.get(Calendar.YEAR));
	}

	public static String fechaConFormato(int dia, int mes, int anio) {
		return String.valueOf(dia) + "/" + String.valueOf(mes) + "/" + String.valueOf(anio);  
	}

	public static boolean esDiaHabil(GregorianCalendar fecha) {
		boolean esDiaHabil = true;
		if (fecha.get(Calendar.DAY_OF_WEEK) == 1 || fecha.get(Calendar.DAY_OF_WEEK) == 7) {
			esDiaHabil = false;
		}
		return esDiaHabil;
	}
	
	public static void siguienteFechaHabil(GregorianCalendar fecha) {		
		while (!esDiaHabilConFeriado(fecha)){
			fecha.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	
	public static boolean esDiaHabilConFeriado(GregorianCalendar fecha) {
		boolean esDiaHabil = true;
		int lenD = traerLstFeriados(Funciones.traerAnio(fecha)).size();
		int i = 0;
		while (i < lenD) {
			if (Funciones.sonFechasIguales(fecha, traerLstFeriados(Funciones.traerAnio(fecha)).get(i))) {
				esDiaHabil = false;
				i = lenD;
			}
			i++;
		}
		if (fecha.get(Calendar.DAY_OF_WEEK) == 1 || fecha.get(Calendar.DAY_OF_WEEK) == 7) {
			esDiaHabil = false;
		}
		return esDiaHabil;
	}

	public static GregorianCalendar traerFechaProximo(GregorianCalendar fecha, int cantDias) {
		int anio = Funciones.traerAnio(fecha);
		int mes = Funciones.traerMes(fecha) - 1;
		int dia = Funciones.traerDia(fecha);
		GregorianCalendar fechaProxima = new GregorianCalendar(anio, mes, dia);
		fechaProxima.add(Calendar.DAY_OF_MONTH, cantDias);
		return fechaProxima;
	}

	public static GregorianCalendar aniadirAFechaSegundos(GregorianCalendar fecha, int cantSegs) {
		int anio = traerAnio(fecha);
		int mes = traerMes(fecha) - 1;
		int dia = traerDia(fecha);
		int hora = traerHora(fecha);
		int minuto = traerMin(fecha);
		int segundo = traerSeg(fecha);
		GregorianCalendar fechaProxima = new GregorianCalendar(anio, mes, dia, hora, minuto, segundo);
		fechaProxima.add(Calendar.SECOND, cantSegs);
		return fechaProxima;
	}

	
	public static String traerDiaDeLaSemana(GregorianCalendar fecha) {
		String [] diasDeLaSemana={"domingo","lunes","martes","miércoles","jueves","viernes","sábado"};
		return diasDeLaSemana[fecha.get(Calendar.DAY_OF_WEEK)-1];
	}


	public static String traerMesEnLetras(GregorianCalendar fecha) {
		String [] mesEnLetras={"enero","febrero","marzo","abril","mayo","junio","julio","agosto","septiembre","octubre","noviembre","diciembre"};
		return mesEnLetras[fecha.get(Calendar.MONTH)];
	}

	public static String traerFechaLarga(GregorianCalendar fecha) {
		return traerDiaDeLaSemana(fecha)+", "+String.valueOf(fecha.get(Calendar.DAY_OF_MONTH))+" de "+traerMesEnLetras(fecha)+" de "+String.valueOf(fecha.get(Calendar.YEAR));
	}

	public static boolean sonFechasIguales(GregorianCalendar fechaUno, GregorianCalendar fechaDos) {
		boolean sonIguales = false;
		
		int diaUno = fechaUno.get(Calendar.DAY_OF_MONTH);
		int mesUno = fechaUno.get(Calendar.MONTH);
		int anioUno = fechaUno.get(Calendar.YEAR);

		int diaDos = fechaDos.get(Calendar.DAY_OF_MONTH);
		int mesDos = fechaDos.get(Calendar.MONTH);
		int anioDos = fechaDos.get(Calendar.YEAR);

		if (diaUno == diaDos && mesUno == mesDos && anioUno == anioDos) {
			sonIguales = true;
		}

		return sonIguales;
	}

	public static int traerCantDiasDeUnMes(int anio, int mes){
		int [] topeMes={31,28,31,30,31,30,31,31,30,31,30,31};
		int valor = -1;

		try {
			if (mes == 1) {
				valor = esBisiesto(anio) ? topeMes[mes]+1 : topeMes[mes];
			}
			else {
				valor = topeMes[mes];
			}
		}
		catch(Exception e) {
			System.out.println("Excepción: " + e.getMessage()); 
		}
		
		return valor;
	}
	
	public static double aproximarDecimal(Double param) {
		return param + 0.005;
	}

	// Lsta de feriados
	public static List<GregorianCalendar> traerLstFeriados(int anio) {
		ArrayList<GregorianCalendar> lstFeriados = new ArrayList<GregorianCalendar>();
		int[][] matrizFeriados = { { 1, 0, 0 }, { 27, 28, 0 }, { 24, 0, 0 }, { 2, 99, 99 }, { 1, 25, 0 }, { 17, 20, 0 },
				{ 9, 0, 0 }, { 21, 0, 0 }, { 0, 0, 0 }, { 12, 0, 0 }, { 20, 0, 0 }, { 8, 25, 0 } };// feriados
																									// 2017
		for (int x = 0; x < 12; x++) {
			for (int y = 0; y < 3; y++) {
				GregorianCalendar fecha = new GregorianCalendar();
				if (matrizFeriados[x][y] != 0) {
					if(matrizFeriados[x][y]!=99){
						fecha.set(anio, x, matrizFeriados[x][y]);// creo nuevo feriado
					}else{
						fecha=(traerFechaPascual(anio));
					}
					lstFeriados.add(fecha);// listo feriado
				} else {
					y++;
				}
			}
		}

		return lstFeriados;
	}

	public static GregorianCalendar traerFechaPascual(int anio) {
		GregorianCalendar fechaPascual = new GregorianCalendar();
		int fecha = 0, A = 0, B = 0, C = 0, D = 0, E = 0, N = 0;
		A = anio % 19;
		B = anio % 4;
		C = anio % 7;
		D = (19 * A + 24) % 30;
		E = (2 * B + 4 * C + 6 * D + 5) % 7;
		N = (22 + D + E);
		fecha = N;
		if (fecha <= 31) {
			fechaPascual.set(anio, 2, fecha-2);//el viernes obtengo con -2
		} else {
			fechaPascual.set(anio, 3, fecha - 31-2);
		}
		return fechaPascual;
	}
	
	/*--------------------------------------------------------------*/
	// Nuevas
	/*--------------------------------------------------------------*/

	public static double convertirADouble(int n) {
		return ((double) n);
	}

	// despues
	public static boolean after(GregorianCalendar fDesde, GregorianCalendar fHasta) {
		boolean despues = false;
		if (fDesde.getTimeInMillis() >= fHasta.getTimeInMillis())
			despues = true;
		return despues;
	}

	// antes
	public static boolean before(GregorianCalendar fDesde, GregorianCalendar fHasta) {
		boolean despues = false;
		if (fDesde.getTimeInMillis() <= fHasta.getTimeInMillis())
			despues = true;
		return despues;
	}

	// HH,MM,SS
	public static int traerHora(GregorianCalendar fecha) {
		return fecha.get(Calendar.HOUR);
	}

	public static int traerMin(GregorianCalendar fecha) {
		return fecha.get(Calendar.MINUTE);
	}

	public static int traerSeg(GregorianCalendar fecha) {
		return fecha.get(Calendar.SECOND);
	}
}

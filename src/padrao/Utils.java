package padrao;

import java.text.DecimalFormat;

public class Utils {
	
	private static final DecimalFormat MASCARA_FORMATACAO = new DecimalFormat("#.##");
	
	public static String formatar(Double valor) {
		return MASCARA_FORMATACAO.format(valor);
	}
}

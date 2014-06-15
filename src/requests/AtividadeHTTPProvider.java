package requests;

import java.util.HashMap;
import java.util.Map;
import model.Atividade;

public class AtividadeHTTPProvider {

	public static void postAtividade(Atividade atv) {
		Map[] atividadesMaps = new Map[1];
		
		Map<String, String> atividadeMap = new HashMap<String, String>();
		
		atividadeMap.put("titulo", atv.getTitulo());
		atividadeMap.put("tempoInvestido", String.valueOf(atv.getTempo()));
		atividadeMap.put("data", atv.getData().toString());
		
		atividadesMaps[0] = atividadeMap;
		
		new Request().execute(atividadesMaps);
	}
	
	public static void getAtividade(Atividade atv) {
		//TODO Fazer a recuperação de todas/algumas das atividades do usuário.
	}
}

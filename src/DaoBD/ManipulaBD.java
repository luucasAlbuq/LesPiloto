package DaoBD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Atividade;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

@SuppressLint("SimpleDateFormat")
public class ManipulaBD {

	private SQLiteDatabase database;
	private CriaBD criaBD;

	public ManipulaBD(Context context) {
		criaBD = new CriaBD(context);
	}

	public long criaAtividade (String nome, int tempo, String data){
		//Convertendo a data para o formato que o SQLite utiliza
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			data = new SimpleDateFormat("yyyy-MM-dd").format(format.parse(data));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ContentValues valores = new ContentValues();
		valores.put(CriaBD.NOME_ATIVIDADE, nome);
		valores.put(CriaBD.TEMPO_ATIVIDADE, tempo);
		valores.put(CriaBD.DATA_ATIVIDADE, data);
		database = criaBD.getReadableDatabase();
		long index = database.insert(CriaBD.TABLE_NAME, null, valores);
		return index;
	}

	public ArrayList<String> getNomeAtividades(){
		database = criaBD.getReadableDatabase(); 
		Cursor cursor = database.rawQuery("select * from atividade", null);
		ArrayList<String> columnArray1 = new ArrayList<String>();
		//ArrayList<String> columnArray2 = new ArrayList<String>();
		//ArrayList<String> columnArray3 = new ArrayList<String>();
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			if (!(columnArray1.contains(cursor.getString(1)))){
				columnArray1.add(cursor.getString(cursor.getColumnIndex(CriaBD.NOME_ATIVIDADE)));
			}
			//columnArray2.add(cursor.getString(2));
			//columnArray3.add(cursor.getString(3));
		}
		return columnArray1;
	}

	public Atividade getAtividadePorNome(String nomeAtividade){
		database = criaBD.getReadableDatabase(); 
		//		 String query = "select * from " + CriaBD.TABLE_NAME + " where " 
		//	     + CriaBD.NOME_ATIVIDADE + " = ?";
		String[] arrayColunas = {CriaBD.NOME_ATIVIDADE,
				"sum(" +CriaBD.TEMPO_ATIVIDADE + ") as " + CriaBD.TEMPO_ATIVIDADE,CriaBD.DATA_ATIVIDADE};
		String[] arrayArgs = {/*CriaBD.NOME_ATIVIDADE,*/ nomeAtividade};
		//Novamente, se colocando o nome da coluna do where por parametro ele n funciona
		Cursor cursor = database.query(CriaBD.TABLE_NAME, arrayColunas, "nome = ?", arrayArgs, null, null, null);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Atividade atv = null;
		//Se não ta vazio
		if(cursor.moveToFirst()){
			String nome = cursor.getString(cursor.getColumnIndex(CriaBD.NOME_ATIVIDADE));
			int tempo = cursor.getInt(cursor.getColumnIndex(CriaBD.TEMPO_ATIVIDADE));
			Date data = null;
			try {
				data = format.parse(cursor.getString(cursor.getColumnIndex(CriaBD.DATA_ATIVIDADE)));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			atv = new Atividade(nome, tempo, data);
		}
		return atv;
	}

	public List<Atividade> getAtividadesDaSemana(String dataInicio, String dataFim){
		database = criaBD.getReadableDatabase(); 
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dataInicio = dbFormat.format(format.parse(dataInicio));
			dataFim =dbFormat.format(format.parse(dataFim));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] arrayColunas = {CriaBD.NOME_ATIVIDADE, CriaBD.TEMPO_ATIVIDADE, CriaBD.DATA_ATIVIDADE};
		String[] arrayArgs = {dataInicio , dataFim};
		List<Atividade> lista = new ArrayList<Atividade>();
		//Por algum motivo se passar "data" por argumento ele nao funciona
		Cursor cursor = database.query(CriaBD.TABLE_NAME, arrayColunas, "data between ? and ?", arrayArgs, null, null, null);
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			String nome = cursor.getString(cursor.getColumnIndex(CriaBD.NOME_ATIVIDADE));
			int tempo = cursor.getInt(cursor.getColumnIndex(CriaBD.TEMPO_ATIVIDADE));
			Date data = null;
			try {
				data = dbFormat.parse(cursor.getString(cursor.getColumnIndex(CriaBD.DATA_ATIVIDADE)));
			} catch (ParseException e) {}
			Atividade atv = new Atividade(nome, tempo, data);
			if (lista.contains(atv)){
				Atividade temp = lista.get(atv.getIndexAtividade(lista, atv));
				int tempoLocal = temp.getTempo();
				temp.setTempo(atv.getTempo()+tempoLocal);
			}else{
				lista.add(atv);
			}
			
		}

		return lista;
	}

	public void deletaAtividade (int idContacto){ 
		database.delete(CriaBD.TABLE_NAME, CriaBD.ID + " = " + idContacto, null); 
	}


}

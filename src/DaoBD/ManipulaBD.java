package DaoBD;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ManipulaBD {
	
	private SQLiteDatabase database;
	private CriaBD criaBD;
	
	public ManipulaBD(Context context) {
		criaBD = new CriaBD(context);
	}
	
	public long criaAtividade (String nome, int tempo, String data){
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
		 ArrayList<String> columnArray2 = new ArrayList<String>();
		 ArrayList<String> columnArray3 = new ArrayList<String>();
		 for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
		     if (!(columnArray1.contains(cursor.getString(1)))){
		    	 columnArray1.add(cursor.getString(1));
		     }
		     columnArray2.add(cursor.getString(2));
		     columnArray3.add(cursor.getString(3));
		 }
		 return columnArray1;
	}
	
	public String getAtividadePorNome(String nomeAtividade){
		 String query = "select * from " + CriaBD.TABLE_NAME + " where " 
	     + CriaBD.NOME_ATIVIDADE + " = ?";
		 Cursor cursor = database.rawQuery(query, null);
		 return cursor.toString();
	}
	
	public String getAtividadesDaSemana(String data1, String data2){
		 String query = "select * from " + CriaBD.TABLE_NAME + " where " 
		 + CriaBD.NOME_ATIVIDADE + " between ? and ?";
		 Cursor cursor = database.rawQuery(query, null);
		return cursor.toString();
	}
	
	public void deletaAtividade (int idContacto){ 
        database.delete(CriaBD.TABLE_NAME, CriaBD.ID + " = " + idContacto, null); 
}
	
	
}

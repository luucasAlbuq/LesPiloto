package DaoBD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBD extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	public static final String ID = "_id";
	public static final String NOME_ATIVIDADE = "nome";
	public static final String DATA_ATIVIDADE = "data";
	public static final String TEMPO_ATIVIDADE = "tempo";
	public static final String PRIORIDADE_ATIVIDADE = "prioridade";
	public static final String TABLE_NAME = "atividade";
	public static final String DATABASE_NAME = "projetoPiloto.db";
	private static final String DATABASE_CREATE = "create table " + 
	TABLE_NAME + "( " + ID + " integer primary key autoincrement, " +
	NOME_ATIVIDADE + " text not null, " + TEMPO_ATIVIDADE + " integer not null, " +
	DATA_ATIVIDADE + " text not null, " + PRIORIDADE_ATIVIDADE + " text not null);";
	
	public CriaBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
		
	}
	
}

package es.dabdm.decide.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelperBBDD extends SQLiteOpenHelper {

	public static final String FORMATO_FECHA = "yyyy-MM-dd HH:mm:ss";
	
	
	public MyHelperBBDD(Context context) {
		super(context, "myfile.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE preguntas  (idPregunta integer PRIMARY KEY, texto Text NOT NULL, idComunidad integer NOT NULL,fechaLimite Text, idRespuestaDada integer);");
		db.execSQL("CREATE TABLE respuestas (idPregunta integer NOT NULL,idRespuestaPosible integer NOT NULL, valor Text NOT NULL, PRIMARY KEY(idPregunta,idRespuestaPosible));");
  
		//db.execSQL("INSERT INTO preguntas  (idPregunta,texto,idComunidad,fechaLimite) VALUES (1,'texto de una pregunta de lo que sea que puede tener dos o cuatro respuestas posibles',1,'');");
		//db.execSQL("INSERT INTO respuestas (idPregunta,idRespuestaPosible,valor) VALUES (1,1,'Sí');");
		//db.execSQL("INSERT INTO respuestas (idPregunta,idRespuestaPosible,valor) VALUES (1,2,'No');");
		//db.execSQL("INSERT INTO respuestas (idPregunta,idRespuestaPosible,valor) VALUES (1,3,'NS/NC');");
		//db.execSQL("commit;");		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// No se hace upgrade....
		
	}

}

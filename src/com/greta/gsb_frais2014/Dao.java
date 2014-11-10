/**
 * 
 */
package com.greta.gsb_frais2014;

import java.io.File;
import java.io.FileWriter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * @author greta
 *
 */
public class Dao extends SQLiteOpenHelper {
	
	
	//nom de la base de donn�es
	static final String DATABASE= "gsb.db";
		
	static final int VERSION= 1;
	
	//table Visiteur
	static final String TABLE_VISITEUR ="visiteur";
	static final String C_MAT= "mat";
	static final String C_NOM= "nom";
	static final String C_MDP= "mpd";

	static final String TABLE_FF = "fraisforfait";
	static final String C_ID = "_id";
	static final String C_IDFRAISFORFAIT = "idFraisForfait";
	static final String C_QUANTITE = "quantite";
	static final String C_DATE = "date";

	static final String TABLE_FHF = "fraishorsforfait";
	static final String C_IDFHF = "_iden";
	static final String C_MATFHF= "matri";
	static final String C_DATEFHF = "dated";
	static final String C_LIBELLE = "libelle";		
	static final String C_MONTANT = "montant";

	private Context cont;
	Spinner spinnerPV;
		
	//objet qui a la forme d'une base de donn�es
	SQLiteDatabase db;
	String sql;
	Cursor c;

	
	 public Dao(Context context) {
			
			super(context, DATABASE, null, VERSION);
			cont = context;
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		//Cr�ation de la table Visiteur
		db.execSQL("CREATE TABLE  "+TABLE_VISITEUR+ 
				"(" +C_MAT + " TEXT PRIMARY KEY, " 
					+C_NOM +" TEXT, "
					+C_MDP + " TEXT)");
		
		
		//creation de la table fraisforfait
		db.execSQL("CREATE TABLE " +TABLE_FF+ 
				" ( " +C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ C_MAT+ " TEXT, "  
					+C_DATE+ " TEXT, "
					+C_IDFRAISFORFAIT+ " TEXT," 
					+C_QUANTITE+ " INT)");	
		
		//creation de la table fraishorsforfait
		db.execSQL("CREATE TABLE " +TABLE_FHF+ 
				" ( " +C_IDFHF + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ C_MATFHF+ " TEXT, "  
					+C_DATEFHF+ " TEXT, "
					+C_LIBELLE+ " TEXT, " 
					+C_MONTANT+ " DOUBLE)");		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
		//Drop old version table
		db.execSQL("Drop table" + TABLE_VISITEUR);		
		db.execSQL("Drop table" + TABLE_FF);
		db.execSQL("Drop table" + TABLE_FHF);
		onCreate(db);
		
	}
	
	public void sqlInsererVisiteur(Visiteur visiteur){
		
		//dans ce conteneur on va mettre les donn�es de la table Visiteur
		ContentValues values = new ContentValues();
		
		values.put(C_MAT, visiteur.getMat());
		values.put(C_NOM, visiteur.getNom());
		values.put(C_MDP, visiteur.getMdp());
		db = getWritableDatabase();
		db.insert(TABLE_VISITEUR, null, values);
		db.close();
		
	}
	
	
	public void sqlInsererFraisForfait(FraisForfait ff,String dateFrais, String presta){
		
		db= getReadableDatabase();
		
		
			c= db.rawQuery("select * from " +TABLE_FF+ " where " +C_IDFRAISFORFAIT+ " = '"+presta+"'  and " +C_DATE+ " = '"+dateFrais+"'" , null);
								
			if( c.getCount()!= 0)
			{
				Toast.makeText(cont, "la prestation est deja saisie pour ce jour", Toast.LENGTH_LONG).show();			
			
			}else{
			
		
			//dans ce conteneur on va mettre les donn�es de la table Frais Forfait
			ContentValues values = new ContentValues();
			
			values.put(C_MAT, ff.getNumVis());
			values.put(C_DATE, ff.getDate());
			values.put(Dao.C_IDFRAISFORFAIT, ff.getIdFraisForfait());
			values.put(Dao.C_QUANTITE, ff.getQuantite());
	
			db = getWritableDatabase();
			db.insert(TABLE_FF,null,  values);		
			c.close();
			db.close();	
			
			Toast.makeText(cont, presta + " " + ff.getQuantite()
					+ "  " +
					ff.getDate() + " "+ff.getNumVis(),
					Toast.LENGTH_LONG).show();						
			}	
		
	}
	
	public void sqlInsererFraisHorsForfait(FraisHorsForfait fhf){
		
		//dans ce conteneur on va mettre les donn�es de la table Frais Forfait
		ContentValues values = new ContentValues();
			
		values.put(C_MATFHF,  fhf.getMat());
		values.put(C_DATEFHF, fhf.getDate());
		values.put(C_LIBELLE, fhf.getLibelle());
		values.put(C_MONTANT, fhf.getMontant());

		db = getWritableDatabase();
		db.insert(TABLE_FHF,null,  values);
		db.close();
		
	}

	public int countVisiteur(){
		db= getWritableDatabase();
				
		sql = "select count(*) from  " + TABLE_VISITEUR;
		
		//un curseur c'est un espace m�moire o� on stocke les donn�es, pour gerer une requete on a besoin d'un cursor
		//on entre dans la requ�te
		c= db.rawQuery(sql, null);
		
		//on execute la requete
		c.moveToFirst();
		
		//on r�cup�re la requete
		int total = c.getInt(0);
		
		c.close();
		db.close();		
		
		return total;		
	}
	
	public String identificationVisiteur(String pass){
		
		String matVisiteur = "0";
		db= getWritableDatabase();
		sql = "select mat from visiteur where mpd =  '"+pass+"'";

		c=db.rawQuery(sql, null);
		c.moveToFirst();

		if(c.getCount()!=0){
			matVisiteur = c.getString(0);
		}
		
		c.close();
		db.close();
		
		return matVisiteur;				
	}
	
	public Cursor tousLesFraisForfait(){
		
		db= getReadableDatabase();
		
		c= db.query(TABLE_FF, null, null, null, null, null, null);
		
		return c;
	}
	
	public String recupIdVisiteur(){
		
		String matVisiteur;
		
		db= getWritableDatabase();
		
		sql = "select mat from visiteur";
		
		c= db.rawQuery(sql, null);
		
		c.moveToFirst();
		
		matVisiteur= c.getString(0);
		
		c.close();
		db.close();
		return matVisiteur;				
	}
	
	public void sqlModifFrais(FraisForfait frais, String selected_ID){
		
		ContentValues values = new ContentValues();
		values.put(C_DATE, frais.getDate());
		values.put(C_IDFRAISFORFAIT, frais.getIdFraisForfait());
		values.put(C_QUANTITE, frais.getQuantite());
		
		//call update method of SQLiteDatabse Class and close after performing task
		
		db = getWritableDatabase();
		db.update(TABLE_FF, values, C_ID+ "=?", new String[]{selected_ID});
		db.close();
		
	}

	public void sqlSupprimerFraisForfait(String selected_ID) {
		
		db=getWritableDatabase();
		db.delete(TABLE_FF, C_ID + "=?", new String[] {selected_ID});
		db.close();
		
	}
	

}

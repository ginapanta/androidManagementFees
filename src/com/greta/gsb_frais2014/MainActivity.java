package com.greta.gsb_frais2014;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//declarer la classe pour exploiter les méthodes
	Dao dao;
	
	//initialiser la base de données
	SQLiteDatabase db;
	
	EditText mat, nom, mdp;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		dao = new Dao(this);
		
		//appel de la méthode  countVisteur
		int nbre = dao.countVisiteur();
		
		
		super.onCreate(savedInstanceState);
		
		if(nbre==0)
		{
			setContentView(R.layout.activity_param);
		}
		else{
			setContentView(R.layout.activity_identification);
		}
		
		//permet de lancer le layout activityMain.xml
		//point de départe de l'appli
//		setContentView(R.layout.activity_param);
		
	}

	public void configVisiteur(View v){
		
		//editText sont des variables graphiques, il faut les parser
		mat= (EditText) findViewById(R.id.etMat);
		nom= (EditText)findViewById(R.id.etNom);
		mdp= (EditText)findViewById(R.id.etMdp);
		
		Visiteur visiteur = new Visiteur(mat.getText().toString(),
										nom.getText().toString(),
										mdp.getText().toString());
		
		//on appele la méthode pour insérer 
		dao.sqlInsererVisiteur(visiteur);
		
		Toast.makeText(this, "Config terminé", Toast.LENGTH_LONG).show();
	
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
		startActivity(intent);
	}
	
	public void valider(View v){
	mdp = (EditText) findViewById(R.id.etPwd);
	String mat = dao.identificationVisiteur(mdp.getText().toString());
		if(mat.equals("0"))
	
			Toast.makeText(this, "Pas bon", Toast.LENGTH_LONG).show();
		else{
			
			Intent intent = new Intent(MainActivity.this, MenuActivity.class);
			startActivity(intent);
		}
		
	}
		
}

package com.greta.gsb_frais2014;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	//declarer la classe pour exploiter les m�thodes
	Dao dao;
	
	//initialiser la base de donn�es
	SQLiteDatabase db;
	
	EditText mat, nom, mdp;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		dao = new Dao(this);
		
		//appel de la m�thode  countVisteur
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
		//point de d�parte de l'appli
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
		
		//on appele la m�thode pour ins�rer 
		dao.sqlInsererVisiteur(visiteur);
		
		Toast.makeText(this, "Config termin�", Toast.LENGTH_LONG).show();
	
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

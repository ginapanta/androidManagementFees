package com.greta.gsb_frais2014;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StatistiquesActivity extends Activity {
	
	//declarer la classe pour exploiter les m�thodes
	Dao dao;
	
	//initialiser la base de donn�es
	SQLiteDatabase db;
	
	EditText date1, date2, essence, nuitees, repas, etapes, total;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		dao = new Dao(this);
				
		super.onCreate(savedInstanceState);
				
		setContentView(R.layout.activity_statistiques);
		
		date1= (EditText)findViewById(R.id.etDate1);		
		date2= (EditText)findViewById(R.id.etDate2);
		essence= (EditText)findViewById(R.id.etEssence);
		nuitees= (EditText)findViewById(R.id.etNuitees);
		repas= (EditText)findViewById(R.id.etRepas);
		etapes= (EditText)findViewById(R.id.etEtapes);
		total= (EditText)findViewById(R.id.etTotal);		
		
	}

	
	
	public void valider(View v){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		
		if(Integer.parseInt(date1.getText().toString()) > Integer.parseInt(date2.getText().toString()) )
		{
			Toast.makeText(StatistiquesActivity.this, "la date de début doit être inférieur à la date de fin", Toast.LENGTH_LONG).show();
		}
		if(Integer.parseInt(date2.getText().toString()) > Integer.parseInt(nowDate))
		{
			Toast.makeText(StatistiquesActivity.this, "la date de fin doit être inférieur à la date courant", Toast.LENGTH_LONG).show();
		}
		
	
	}
		
}

package com.greta.gsb_frais2014;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class StatistiquesActivity extends Activity {
	
	//declarer la classe pour exploiter les méthodes
	Dao dao;
	
	//initialiser la base de données
	SQLiteDatabase db;
	
	EditText date1, date2, essence, nuitees, repas, etapes, total;
	Spinner spinnerPV;
	RadioButton rbFF;
	RadioGroup rgFF;
	int cout;
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
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
	
	public void valider(View v)
	{		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		date1 = (EditText)findViewById(R.id.etDate1);
		date2 = (EditText)findViewById(R.id.etDate2);
		
		String presta = dao.sqlLireFraisForfait(date1.getText().toString(), date2.getText().toString());
				
		if(Integer.parseInt(date1.getText().toString()) > Integer.parseInt(date2.getText().toString()) )
		{
			Toast.makeText(StatistiquesActivity.this, "la date de debut doit etre inferieur à la date de fin", Toast.LENGTH_LONG).show();
		}
		
		if(Integer.parseInt(date2.getText().toString()) > Integer.parseInt(nowDate))
		{
			Toast.makeText(StatistiquesActivity.this, "la date de fin doit etre inferieur à la date courant", Toast.LENGTH_LONG).show();
		}
			
		switch(presta)
		{
		case "nuitees":		
			cout = 50;
			break;
		case "repas":
			cout = 10;
			break;
		case "etapes":
			cout = 20;
			break;
		case "4CV Diesel":
			cout = 1;
			break;
		case "5/6CV Diesel":
			cout = 2;
			break;
		case "4CV Essence":
			cout = 3;
			break;
		case "5/6 Essence":
			cout = 4;
			break;						
		}
						
		
	}
		
}

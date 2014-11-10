package com.greta.gsb_frais2014;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.view.View;

public class FraisHorsForfaitActivity extends Activity{

	EditText tmat;
	EditText tdate;	
	EditText tlibelle;
	EditText tMontant;
	Button btnValider;
	SimpleCursorAdapter adapter;
	Dao dao;
	SQLiteDatabase db;
	
	
	protected void onCreate(Bundle savedInstanceState){
		dao = new Dao(this);
			
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_frais_hors_forfait);								
		
//		dateSysteme();
		
		
		//mettre la date systeme dans etDate du layout activity_frais_forfait
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		tdate= (EditText) findViewById(R.id.etDateh);
		tdate.setText(nowDate);
													
		String matVis = dao.recupIdVisiteur();	
		tmat = (EditText)findViewById(R.id.etMath);
		tmat.setText(matVis);
		
	}

	public void insertionFraisHorsForfait(View v){
			
		tlibelle=(EditText) findViewById(R.id.etLibelle);
		
		tMontant=(EditText)findViewById(R.id.etMontant);
											
		String matVis = dao.recupIdVisiteur();
		tmat = (EditText)findViewById(R.id.etMath);
		tmat.setText(matVis);
		
						
		FraisHorsForfait fhf= new FraisHorsForfait(matVis,
		tlibelle.getText().toString(),	
		tdate.getText().toString(),
		Double.parseDouble(tMontant.getText().toString()));
		
		Toast.makeText(FraisHorsForfaitActivity.this,
				tlibelle.getText().toString() + " " + Double.parseDouble(tMontant.getText().toString())
				+ "  " +
				tdate.getText().toString()
				+ " "+ 
				matVis,
				Toast.LENGTH_LONG).show();
		
		dao.sqlInsererFraisHorsForfait(fhf);
	
	}

	
	
	
	public void dateSysteme(){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		
		tdate= (EditText)findViewById(R.id.etDateh);
		tdate.setText(nowDate);
		
	}
	
}

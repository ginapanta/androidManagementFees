package com.greta.gsb_frais2014;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {
	
	Dao dao;
	SQLiteDatabase db;
	Button btff;
	Button btfhf;
	Button btstat;
	Button btexp;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		dao = new Dao(this);	
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_menu);
		
		
		
		btff = (Button)findViewById(R.id.btFF);
		
		btff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MenuActivity.this, FraisForfaitActivity.class);
				startActivity(intent);
				
			}
		});
		
		btfhf = (Button)findViewById(R.id.btFHF);
		
		btfhf.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MenuActivity.this, FraisHorsForfaitActivity.class);
				startActivity(intent);
				
			}
		});
		
		
		btstat = (Button)findViewById(R.id.btStatistiques);
		
		btstat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MenuActivity.this, StatistiquesActivity.class);
				startActivity(intent);
				
			}
		});
		
		btexp = (Button)findViewById(R.id.btExport);
		
		btexp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(MenuActivity.this, CreateCsvActivity.class);
				startActivity(intent);
				
			}
		});
		
	}
	
	
	
	

}

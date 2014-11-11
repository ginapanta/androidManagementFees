package com.greta.gsb_frais2014;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

public class FraisForfaitActivity extends Activity
{

	RadioButton rbFF;
	RadioGroup rgFF;
	EditText txtQuantite, txtDate;
	Spinner spinnerPV;
	Button btnAjouter;
	ListView lvFrais;
	SimpleCursorAdapter adapter;
	Dao dao;
	SQLiteDatabase db;
	String ff_ID="";
	
	protected void onCreate(Bundle savedInstanceState)
	{
		dao = new Dao(this);
			
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_frais_forfait);			
		
		lvFrais = (ListView) findViewById(R.id.listView1);
		
		//mettre la date systeme dans etDate du layout activity_frais_forfait
		
		dateSystem();
		
		txtQuantite = (EditText)findViewById(R.id.etQuantite);
		
		rgFF = (RadioGroup)findViewById(R.id.rg1);			
		int idChoix = rgFF.getCheckedRadioButtonId();		
		rbFF = (RadioButton)findViewById(idChoix);
					
		String numVis = dao.recupIdVisiteur();
						
		lvFrais.setOnItemClickListener(new OnItemClickListener()
		{
			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> adapter, View v,int position , long id) 
			{
				String lib, date, qt;
				
				Cursor row =(Cursor)adapter.getItemAtPosition(position);
				
				ff_ID = row.getString(0);
				Toast.makeText(FraisForfaitActivity.this, row.getString(0), Toast.LENGTH_LONG).show();
																
				lib=row.getString(3);
				date= row.getString(2);
				qt= row.getString(4);
				txtQuantite.setText(qt);
				txtDate.setText(date);
									
				if("Repas".equals(lib.trim()))
					rgFF.check(R.id.rbRepas);
				if("Nuitée".equals(lib.trim()))
					rgFF.check(R.id.rbNuite);
				if("Km".equals(lib.trim()))
					rgFF.check(R.id.rbKm);
				if("Etape".equals(lib.trim()))
					rgFF.check(R.id.rbEtape);
			}			
		});							
		garnirListView();
	}

	public void insertionFraisForfait(View v)
	{																
		txtQuantite = (EditText) findViewById(R.id.etQuantite);		
															//radio button prestations
		rgFF= (RadioGroup)findViewById(R.id.rg1);
		
		int idChoix = rgFF.getCheckedRadioButtonId();
		
		rbFF = (RadioButton)findViewById(idChoix);
		
		String presta = rbFF.getText().toString();
		
		spinnerPV = (Spinner) findViewById(R.id.spinner1);
															
		if(presta.equals("Km"))
		{				
			presta= String.valueOf(spinnerPV.getSelectedItem());	
		}		
		
		txtDate=(EditText)findViewById(R.id.etDate);
		
		String numVis= dao.recupIdVisiteur();				
															//control saisie de km
		if(txtQuantite.getText().toString().length() == 0)
		{			
			Toast.makeText(FraisForfaitActivity.this, "veuillez saisir le nombre de km", Toast.LENGTH_LONG).show();
		}
		else
		{			
			FraisForfait ff= new FraisForfait
			(numVis,
			txtDate.getText().toString(),
			presta,
			Integer.parseInt(txtQuantite.getText().toString()));
			
		dao.sqlInsererFraisForfait(ff, txtDate.getText().toString(),rbFF.getText().toString());
		
		garnirListView();
		}			
	}

	
	public void initialiserQuantite(View v){		
		txtQuantite = (EditText)findViewById(R.id.etQuantite);
		txtQuantite.setText("1");	
	}
	
	public void initialiserKM(View v)
	{		
		txtQuantite=(EditText)findViewById(R.id.etQuantite);
		txtQuantite.setText("");			
	}
	
	@SuppressWarnings("deprecation")
	public void garnirListView()
	{	
		Cursor c = dao.tousLesFraisForfait();
		adapter = new SimpleCursorAdapter(
				this,
				R.layout.activity_item_frais_forfait,
				c,
				new String[] {Dao.C_IDFRAISFORFAIT, Dao.C_DATE, Dao.C_QUANTITE },
				new int[]{R.id.tvLib, R.id.tvDate2, R.id.tvMnt2});
		
		lvFrais.setAdapter(adapter);		
	}
	
	public void supprimerFF(View v)
	{		
		dao.sqlSupprimerFraisForfait(ff_ID);
		garnirListView();		
	}

	public void dateSystem()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");			
		Date date = new Date();
		String nowDate = dateFormat.format(date);
		
		txtDate= (EditText) findViewById(R.id.etDate);
		txtDate.setText(nowDate);
	}
	
}

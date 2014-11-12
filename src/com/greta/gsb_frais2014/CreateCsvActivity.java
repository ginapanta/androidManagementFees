package com.greta.gsb_frais2014;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;



public class CreateCsvActivity extends Activity 
{
	
	Dao dao;
	SQLiteDatabase db =null;
    String DataBase_Name="gsb.db";
    String Table_Name="fraisforfait";
    Cursor c1,c2;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		
		try
		{
			db = this.openOrCreateDatabase(DataBase_Name, MODE_PRIVATE, null);
			c1 = db.rawQuery("select * from " + Table_Name, null);
	        c1.moveToFirst();
	        int count1 = c1.getCount();
	        
	        if (count1 == 0) {	            
	            Toast.makeText(CreateCsvActivity.this, "Il n'y a pas de donn�es � exporter", Toast.LENGTH_LONG).show();
	        }
	        
	        try
	        {
	        	new ExportDatabaseCSVTask().execute("");
	        } 
	        catch(Exception ex) 
	        {
	        	Log.e("Error in MainActivity",ex.toString());
	        }
		}
		 catch(SQLException ex) 
		 { 
			 ex.printStackTrace(); 
		 }
		  /*finally {
		        if (myDB != null) { myDB.close(); }
		    }*/	
	
	}

	public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> 
	{
		
	    private final ProgressDialog dialog = new ProgressDialog(CreateCsvActivity.this);
	    
	
	    
	    @Override
		protected void onPreExecute() 
	    {
			
	    	this.dialog.setMessage("Exporting database...");
	        this.dialog.show();
	
		}
	
	
	    @Override
	    protected Boolean doInBackground(String... params) 
	    {
	    	
	    	File dbFile = getDatabasePath("gsb.db");
	        System.out.println(dbFile);  // displays the data base path in your logcat 
	         File exportDir = new File(Environment.getExternalStorageDirectory(), "");
	
	        if (!exportDir.exists()) 
	        { 
	        	exportDir.mkdirs(); 
	        }
	
	        File file = new File(exportDir, "myfile.csv");
	        try 
	        {
	            file.createNewFile();
	            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
	            Cursor curCSV = db.rawQuery("select * from " + Table_Name,null);
	            csvWrite.writeNext(curCSV.getColumnNames());
	            while(curCSV.moveToNext()) 
	            {
	                String arrStr[] ={curCSV.getString(0),curCSV.getString(1),curCSV.getString(2)};
	                // curCSV.getString(3),curCSV.getString(4)};
	                csvWrite.writeNext(arrStr);
	            }
	            csvWrite.close();
	            curCSV.close();
	            return true;
	        } 
	        catch(SQLException sqlEx)
	        {
	            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
	            return false;
	        } 
	        catch (IOException e)
	        {
	            Log.e("MainActivity", e.getMessage(), e);
	            return false;
	        }
	    }
	
	
		@Override
		protected void onPostExecute(Boolean success) 
		{		
			if (this.dialog.isShowing()) 
			{ 
				this.dialog.dismiss(); 
			}
	        if (success) 
	        {
	            Toast.makeText(CreateCsvActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
	        } 
	        else 
	        {
	            Toast.makeText(CreateCsvActivity.this, "Export failed", Toast.LENGTH_SHORT).show();
	        }
	    }
	
	}

}


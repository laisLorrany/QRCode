package br.edu.ifpb.appqrcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends Activity {
	
	private Button scanBtn;
	private TextView formatTxt, contentTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new IntentIntegrator(this).initiateScan();
		
		scanBtn = (Button)findViewById(R.id.scanButton);
		formatTxt = (TextView)findViewById(R.id.formatTxt);
		contentTxt = (TextView)findViewById(R.id.formatTxt);
		

		scanBtn.setOnClickListener((android.view.View.OnClickListener) this);
	}
	
	public void onClick(View v){
		if(v.getId()==R.id.scanButton){
			 new IntentIntegrator(this).initiateScan();
		}
	}
		
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent intent) {
		IntentResult scanningResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanningResult != null) {
			String scanContent = scanningResult.getContents();
			String scanFormat = scanningResult.getFormatName();
			
			formatTxt.setText("FORMAT: " + scanFormat);
			contentTxt.setText("CONTENT: " + scanContent);
		}else{
			Toast toast = Toast.makeText(getApplicationContext(), 
			        "Falha na leitura!", Toast.LENGTH_SHORT);
			    toast.show();
		}
	}
	
}

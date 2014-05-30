package com.example.plotadordehoras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button cadastro = (Button) findViewById(R.id.cadastroAtividade);
        cadastro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent telaAtividade = new Intent(MainActivity.this, CadastroDeAtividade.class);
				MainActivity.this.startActivity(telaAtividade);
				
			}
		});
    }
    
}

package com.projetopiloto.plotadordehoras.testes;

import android.test.ActivityInstrumentationTestCase2;

import com.example.plotadordehoras.MainActivity;
import com.robotium.solo.Solo;

public class TesteMainActivity extends ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo mSolo;

	public TesteMainActivity() {
		super(MainActivity.class);
	} 
	
	protected void setUp() throws Exception {
		mSolo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testInterface() {

		mSolo.assertCurrentActivity("Activity Errada", MainActivity.class);
		
	}
}
package com.tonychen.creen_brightness;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private SeekBar  seekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		// ��������������ȣ�255��������� 
        seekBar.setMax(255); 
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// ȡ�õ�ǰ���� 
        int normal = Settings.System.getInt(getContentResolver(), 
                Settings.System.SCREEN_BRIGHTNESS, 255); 
        // �������󶨵�ǰ���� 
        seekBar.setProgress(normal); 
 
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() { 
 
            @Override 
            public void onStopTrackingTouch(SeekBar seekBar) { 
                // ȡ�õ�ǰ���� 
                int tmpInt = seekBar.getProgress(); 
 
 
                // ���ݵ�ǰ���ȸı����� 
                Settings.System.putInt(getContentResolver(), 
                        Settings.System.SCREEN_BRIGHTNESS, tmpInt); 
                tmpInt = Settings.System.getInt(getContentResolver(), 
                        Settings.System.SCREEN_BRIGHTNESS, -1); 
                WindowManager.LayoutParams wl = getWindow().getAttributes(); 
 
                float tmpFloat = (float) tmpInt / 255; 
                if (tmpFloat > 0 && tmpFloat <= 1) { 
                    wl.screenBrightness = tmpFloat; 
                } 
                getWindow().setAttributes(wl); 
 
            } 
 
            @Override 
            public void onStartTrackingTouch(SeekBar seekBar) { 
                // TODO Auto-generated method stub 
            } 
 
            @Override 
            public void onProgressChanged(SeekBar seekBar, int progress, 
                    boolean fromUser) { 
                // TODO Auto-generated method stub 
            } 
        }); 
	}

}

package apps.joey.animationsdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView human;
    ImageView orc;
    boolean isHuman = true;
    boolean fade = false;
    boolean slide = false;
    boolean spin = false;
    long animationDuration;
    SeekBar seekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        animationDuration = 5000l;

        human = (ImageView) findViewById(R.id.human);
        orc = (ImageView) findViewById(R.id.orc);

        fade = findViewById(R.id.fadeButton).isSelected();
        slide = findViewById(R.id.slideButton).isSelected();
        spin = findViewById(R.id.slideButton).isSelected();

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                animationDuration = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setProgress((int) animationDuration);
    }

    public void toggleImage(View view){
        if(fade) {
            if (isHuman) {
                human.animate().alpha(0f).setDuration(animationDuration);
                orc.animate().alpha(1f).setDuration(animationDuration);
                isHuman = false;
            } else {
                human.animate().alpha(1f).setDuration(animationDuration);
                orc.animate().alpha(0f).setDuration(animationDuration);
                isHuman = true;
            }
        }
        if(slide) {
            if (isHuman) {
                human.animate().translationX(1000l).setDuration(animationDuration);
                orc.animate().translationX(0f).setDuration(animationDuration);
                isHuman = false;
            } else {
                human.animate().translationX(0l).setDuration(animationDuration);
                orc.animate().translationX(1000f).setDuration(animationDuration);
                isHuman = true;
            }
        }
        if(spin){
            if(isHuman){
                human.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(animationDuration);
                orc.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(animationDuration);
                isHuman = false;
            } else {
                orc.animate().rotation(720f).scaleX(0f).scaleY(0f).setDuration(animationDuration);
                human.animate().rotation(-720f).scaleX(1f).scaleY(1f).setDuration(animationDuration);
                isHuman = true;
            }
        }
    }

    public void setFade(View view){
        if(isHuman){
            orc.animate().alpha(0).setDuration(0); //have to use animate() instead of setImageAlpha because I used api 15 instead of 16
            orc.setScaleX(1);
            orc.setScaleY(1);
            orc.setTranslationX(0);
        } else {
            human.animate().alpha(0).setDuration(0);
            human.setScaleX(1);
            human.setScaleY(1);
            human.setTranslationX(0);
        }
        fade = true;
        slide = false;
        spin = false;
    }

    public void setSlide(View view){
        if(isHuman){
            orc.animate().alpha(1).setDuration(0);
            orc.setScaleX(1);
            orc.setScaleY(1);
            orc.setTranslationX(1000f);
        } else {
            human.animate().alpha(1).setDuration(0);
            human.setScaleX(1);
            human.setScaleY(1);
            human.setTranslationX(1000f);
        }
        fade = false;
        slide = true;
        spin = false;
    }

    public void setSpin(View view){
        if(isHuman){
            orc.animate().alpha(1).setDuration(0);
            orc.setScaleX(0);
            orc.setScaleY(0);
            orc.setTranslationX(0);
        } else {
            human.animate().alpha(1).setDuration(0);
            human.setScaleX(0);
            human.setScaleY(0);
            human.setTranslationX(0);
        }

        fade = false;
        slide = false;
        spin = true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

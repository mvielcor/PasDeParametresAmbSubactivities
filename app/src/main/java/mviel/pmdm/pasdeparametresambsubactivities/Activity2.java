package mviel.pmdm.pasdeparametresambsubactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;



public class Activity2 extends AppCompatActivity {

    /*Atributs*/
    TextView paramString, paramInt;
    CheckBox dadesCorrectes, enMajuscules;
    Button enviarDades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        paramString = (TextView) findViewById(R.id.parametreString);
        paramInt = (TextView) findViewById(R.id.parametreInteger);

        dadesCorrectes = (CheckBox) findViewById(R.id.ch_dades_correctes);
        enMajuscules = (CheckBox) findViewById(R.id.ch_majuscules);
        enviarDades = (Button) findViewById(R.id.button2);

        //Agafem el bundle que ve amb l'Intent que carrega este activity

        final Bundle parametresRebuts = getIntent().getExtras();
        //Agafem els paràmetres del Bundle i els mostrem als TextView corresponents
        paramString.setText(parametresRebuts.getString("parametreString"));
        paramInt.setText("" + parametresRebuts.getInt("parametreInt"));

        // Afegim un Listener al Botó Enviar Dades
        enviarDades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDeTornada = new Intent();
                Bundle parametresDeTornada = new Bundle();
                if (enMajuscules.isChecked()) {
                    parametresDeTornada.putString("parametreStringEnMajuscules", parametresRebuts.getString("parametreString").toUpperCase());
                    parametresDeTornada.putBoolean("enMajuscules", true);
                } else {
                    parametresDeTornada.putBoolean("enMajuscules", false);

                }
                intentDeTornada.putExtras(parametresDeTornada);
                if (dadesCorrectes.isChecked()) {
                    setResult(RESULT_OK, intentDeTornada);  //IMPORTANT Establir el resultat del subactivity
                } else {
                    setResult(RESULT_CANCELED, intentDeTornada);   //IMPORTANT Establir el resultat del subactivity
                }
                finish();
            }
        });

    }
}

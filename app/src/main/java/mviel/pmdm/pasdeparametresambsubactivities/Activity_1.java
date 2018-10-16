package mviel.pmdm.pasdeparametresambsubactivities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Activity_1 extends AppCompatActivity {


    /*Declarem els atributs de la classe que necessitarem
    * 2 Editext i 1 button*/
    EditText parametre_a_enviar_1, parametre_a_enviar_2;
    Button b_enviar_parametres;
    final int SUBACTIVITY2=2;   // IDENTIFICADOR  que li posem al subactivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        /*Enllacem els atributs de la classe amb l'element de l'xml que representaran */
        parametre_a_enviar_1 = (EditText) findViewById(R.id.editText);
        parametre_a_enviar_2 = (EditText) findViewById(R.id.editText2);
        b_enviar_parametres = (Button) findViewById(R.id.button);

        /* Afegim un listener al botó */
        b_enviar_parametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creem l'Intent
                Intent i = new Intent(getApplicationContext(),Activity2.class);
                // Creem l'objecte Bundle
                Bundle b = new Bundle();
                // Afegim al Bundle els paràmetres que volem passar

                String nom = parametre_a_enviar_1.getText().toString();
                int edat;
                try {
                    edat =Integer.parseInt(parametre_a_enviar_2.getText().toString());
                }catch(NumberFormatException nfe){
                    edat=0;
                }

                b.putString("parametreString",nom);
                b.putInt("parametreInt",edat);
                //Afegim el Bundle a l'Intent
                i.putExtras(b);
                //Carreguem l'Activity2
               // startActivity(i);
                //Carreguem l'Activity2 com si fora un subactivity
                startActivityForResult(i,SUBACTIVITY2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //comprovem des de quin subactivity venim
        if(requestCode==SUBACTIVITY2){  // Venim del subactivity2
            Bundle bundleRebut = data.getExtras();
            if(bundleRebut.getBoolean("enMajuscules")==true){ //Posem en Majuscules el nom
                parametre_a_enviar_1.setText(bundleRebut.get("parametreStringEnMajuscules").toString());
            }
            //Comprovem el resultat del subactivity
            if(resultCode==RESULT_OK){  //Les dades s'han passat correctament
                Snackbar.make(findViewById(R.id.myConstraintLayout), "Dades Enviades correctament!!",
                        Snackbar.LENGTH_SHORT).show();
            }else{
                Snackbar.make(findViewById(R.id.myConstraintLayout), "L'usuari diu que les dades no s'han passat correctament !!",
                        Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}

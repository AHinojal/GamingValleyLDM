package hinojalrobledo.myapplication.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hinojalrobledo.myapplication.R;

public class ShowProduct extends AppCompatActivity {

    Button buttonContact;
    TextView nameProductBuy,descriptionProductBuy,priceProductBuy,emailSellerProductBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        getSupportActionBar().hide();

        buttonContact = (Button) findViewById (R.id.buttonContact);
        nameProductBuy =  (TextView) findViewById (R.id.tv_nameProduct);
        descriptionProductBuy = (TextView) findViewById(R.id.tv_descriptionProduct);
        priceProductBuy = (TextView) findViewById(R.id.tv_priceProduct);
        emailSellerProductBuy = (TextView) findViewById(R.id.tv_emailSellerProduct);


        Bundle extra = getIntent().getExtras();
        String nameProduct = null;
        String descriptionProduct = null;
        String priceProduct = null;
        String emailSellerProduct = null;

        if (extra != null){
            nameProduct = extra.getString("nameProduct");
            nameProductBuy.setText(nameProduct);
            descriptionProduct = extra.getString("descriptionProduct");
            descriptionProductBuy.setText(descriptionProduct);
            priceProduct = extra.getString("priceProduct");
            priceProductBuy.setText(priceProduct + " €");
            emailSellerProduct = extra.getString("emailSellerProduct");
            emailSellerProductBuy.setText(emailSellerProduct);
        }

        buttonContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    };

    public void sendEmail() {
        String[] TO = {emailSellerProductBuy.getText().toString()}; //Aqui se pone el correo del vendedor
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        //Aqui modificarias el asunto del correo
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Posible comprador B&S: " + nameProductBuy.getText().toString() + " - " + priceProductBuy.getText().toString() + " €");
        //Aqui modificarias el cuerpo del correo
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hola, vendedor de BuyAndSell! \n\n" +
                "Estaría interesado en la oferta que has puesto de: " +  nameProductBuy.getText().toString() + ".\n " +
                "Me gustaría que contactaras conmigo para indicar las condiciones de la compraventa. \n\n" +
                "Un saludo!");

        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(ShowProduct.this,"No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }

}

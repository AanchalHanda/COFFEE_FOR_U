package com.example.android.budday;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import com.example.android.budday.R;
import java.util.Locale;



public class MainActivity extends AppCompatActivity {

    int coffee=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whipcream=(CheckBox) findViewById(R.id.whipp);
        boolean hascream=whipcream.isChecked();
        CheckBox choco=(CheckBox) findViewById(R.id.chocolate);
        boolean chocolate=choco.isChecked();
        EditText name=(EditText) findViewById(R.id.name);
        String value=name.getText().toString();
        int price= calculateprice(hascream,chocolate);
        String s=summaryorder(price,hascream,chocolate,value);

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this

            intent.putExtra(Intent.EXTRA_SUBJECT, "Just_Java coffee");
            intent.putExtra(Intent.EXTRA_TEXT,s);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

    }
    //this is increment method
    public void increaseval(View view){
        coffee+=1;
        display(coffee);
    }
    //this is decrement method
    public void decreaseval(View view)
    {if (coffee>0)
        coffee-=1;
        display(coffee);
    }
    //this method calculates the price
     private int calculateprice(boolean hascream,boolean haschocolate)
    { int initial=5;
        if(hascream)
            initial+=1;
        if(haschocolate)
            initial+=2;
        int prices=coffee*initial;
        return prices;

    }
    //summary method



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity);
        quantityTextView.setText(""+number);
    }
    /**
     * This method displays the given price on the screen.
     */
    //returns summary
    private String summaryorder(int price,boolean hascream,boolean chocolate,String name)
    {  int pri=price;
        String s="Name:" +
                name+"\nQuantity:";
        s+=coffee;
        s+="\nHasWhippedCream ?"+hascream;
        s+="\nHasChocolate ?"+chocolate;
        s+="\nPrice:";
        s+=+pri+"\nThanks For Ordering";
        return s;
    }

}
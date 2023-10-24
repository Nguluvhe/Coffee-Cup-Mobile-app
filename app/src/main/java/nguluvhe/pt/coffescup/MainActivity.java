package nguluvhe.pt.coffescup;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    private Spinner ddlMilk;
    String[] sAnswerMilk = {
            "Yes",
            "No"
    };
    private TextInputEditText txtNoOfCups,txtSugar, txtName;
    private Button btnPlaceOrder;
    private TextView lblCoffeeType;
    private ImageSwitcher mImageSwitcher;
    private ImageView imgPrev, imgNext, imgRemoveCup, imgAddCup, imgRemoveSugar, imgAddSugar;
    private RadioGroup radgrpSize;

    int[] iAssets = {
            R.drawable.black_coffee,
            R.drawable.ice_coffee,
            R.drawable.espresso,
            R.drawable.latte
    };
    private int iCups, iSugar;

    int id = 0;
    String sSize = "";
    int iNoOfCups = 1;
    int iNoOfSugar = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mImageSwitcher = findViewById(R.id.imageSwitcher);
        imgPrev = findViewById(R.id.imgPrev);
        imgNext = findViewById(R.id.imgNext);
        imgRemoveCup = findViewById(R.id.imgRemoveCup);
        imgAddCup = findViewById(R.id.imgAddCup);
        imgRemoveSugar = findViewById(R.id.imgRemoveSugar);
        imgAddSugar = findViewById(R.id.imgAddSugar);
        radgrpSize = findViewById(R.id.radgrpSize);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        lblCoffeeType = findViewById(R.id.lblCoffeeType);
        ddlMilk = findViewById(R.id.ddlMilk);
        txtNoOfCups = findViewById(R.id.txtNoOfCups);
        txtSugar = findViewById(R.id.txtSugar);
        txtName = findViewById(R.id.txtName);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item, sAnswerMilk);

        ddlMilk.setAdapter(adapter);
        ddlMilk.setSelection(0);


        txtNoOfCups.setText(Integer.toString(iNoOfCups));
        txtSugar.setText(Integer.toString(iNoOfSugar));


        iCups = Integer.parseInt(txtNoOfCups.getText().toString());
        iSugar = Integer.parseInt(txtSugar.getText().toString());

        Animation next_in = AnimationUtils.loadAnimation(this, R.anim.next_in);
        Animation next_out = AnimationUtils.loadAnimation(this, R.anim.next_out);
        Animation prev_in = AnimationUtils.loadAnimation(this, R.anim.prev_in);
        Animation prev_out = AnimationUtils.loadAnimation(this, R.anim.prev_out);


        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            @Override
            public View makeView()
            {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setImageResource(R.drawable.black_coffee);
                lblCoffeeType.setText("Black Coffee");
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });

        radgrpSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkId)
            {
                RadioButton radSmall = findViewById(R.id.radSmall);
                RadioButton radMedium = findViewById(R.id.radMedium);
                RadioButton radLarge = findViewById(R.id.radLarge);
                if (radSmall.isChecked())
                {
                    sSize = "small";
                } else if (radMedium.isChecked())
                {
                    sSize = "medium";
                } else if (radLarge.isChecked())
                {
                    sSize = "large";
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Please select size",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnPlaceOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent orderDetailsIntent = new Intent(MainActivity.this, OrderDetails.class);

                Bundle orderDetailsInfo = new Bundle();

                orderDetailsInfo.putString("size", sSize);
                orderDetailsInfo.putInt("id", id);
                orderDetailsInfo.putString("name", txtName.getText().toString());
                orderDetailsInfo.putString("size", sSize);
                orderDetailsInfo.putString("order", lblCoffeeType.getText().toString());
                orderDetailsInfo.putString("milk", ddlMilk.getSelectedItem().toString().toLowerCase());
                orderDetailsInfo.putInt("NoOfCups", Integer.parseInt(txtNoOfCups.getText().toString()));
                orderDetailsInfo.putInt("NoOfSugarSpoons", Integer.parseInt(txtSugar.getText().toString()));

                orderDetailsIntent.putExtras(orderDetailsInfo);
                startActivity(orderDetailsIntent);
            }
        });

        imgNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mImageSwitcher.setAnimation(next_in);
                mImageSwitcher.setAnimation(next_out);
                id++;
                if (id == iAssets.length)
                {
                    id = 0;
                }
                mImageSwitcher.setImageResource(iAssets[id]);
                changeName(id);
            }
        });

        imgPrev.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mImageSwitcher.setInAnimation(prev_in);
                mImageSwitcher.setOutAnimation(prev_out);
                id--;
                if (id < 1)
                {
                    id = iAssets.length - 1;
                }
                mImageSwitcher.setImageResource(iAssets[id]);
                changeName(id);
            }
        });

        imgRemoveCup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (iCups == 1)
                {
                    iCups = 1;
                }
                else
                {
                    iCups--;
                }
                txtNoOfCups.setText(String.valueOf(iCups));

            }
        });

        imgAddCup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (iCups == 10)
                {
                    iCups = 10;
                }
                else
                {
                    iCups++;
                }
                txtNoOfCups.setText(String.valueOf(iCups));
            }
        });

        imgRemoveSugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (iSugar == 1)
                {
                    iSugar = 1;
                }
                else
                {
                    iSugar--;
                }
                txtSugar.setText(String.valueOf(iSugar));
            }
        });

        imgAddSugar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (iSugar == 3)
                {
                    iSugar = 3;
                }
                else
                {
                    iSugar++;
                }
                txtSugar.setText(String.valueOf(iSugar));
            }
        });
    }
    private void changeName(int _id)
    {
        String sName = "";
        switch (_id)
        {
            case 0:
                sName = "Black Coffee";
                break;
            case 1:
                sName = "Ice Coffee";
                break;
            case 2:
                sName = "Espresso";
                break;
            case 3:
                sName = "Latte";
                break;
            default:
                break;
        }
        lblCoffeeType.setText(sName);

    }
    public void doPositiveClick() {
        //---perform steps when user clicks on OK---
        Log.d("DialogFragmentExample", "Order file download Successfully");

    }
    public void doNegativeClick() {
        //---perform steps when user clicks on Cancel---
        Log.d("DialogFragmentExample", "Order file download Cancelled");
    }
}
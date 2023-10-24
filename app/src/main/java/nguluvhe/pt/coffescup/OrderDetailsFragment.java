package nguluvhe.pt.coffescup;

import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;


public class OrderDetailsFragment extends Fragment
{
    private TextView lblOrderDetails, lblGreeting, lblCostOfOrder, lblPickupTime, lblDisplayTime, lblOrderConfirmation;
    private Button btnCancelOrder, btnConfirmOrder;
    private TextInputEditText txtName;
    private Spinner ddlMilk;
    private ImageView imgTime, imgDownload;
    private static final Double mCoffeePrice = 19.95;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_order_details, container, false);
    }


    @Override
    public void onStart()
    {
        super.onStart();
        lblOrderDetails = getActivity().findViewById(R.id.lblOrderDetails);
        lblGreeting = getActivity().findViewById(R.id.lblGreeting);
        lblCostOfOrder = getActivity().findViewById(R.id.lblCostOfOrder);
        lblPickupTime = getActivity().findViewById(R.id.lblPickupTime);
        lblDisplayTime = getActivity().findViewById(R.id.lblDisplayTime);
        lblOrderConfirmation = getActivity().findViewById(R.id.lblOrderConfirmation);
        btnCancelOrder = getActivity().findViewById(R.id.btnCancelOrder);
        btnConfirmOrder = getActivity().findViewById(R.id.btnConfirmOrder);
        imgTime = getActivity().findViewById(R.id.imgTime);
        imgDownload = getActivity().findViewById(R.id.imgDownload);
        txtName = getActivity().findViewById(R.id.txtName);
        ddlMilk = getActivity().findViewById(R.id.ddlMilk);

        lblGreeting.append( " " + getActivity().getIntent().getStringExtra("name"));

        String sMilk = "";
        if(getActivity().getIntent().getStringExtra("milk").toString().toLowerCase() == "yes")
        {
            sMilk += "with milk";
        }
        else if(getActivity().getIntent().getStringExtra("milk").toString().toLowerCase() == "no")
        {
            sMilk += "without milk";
        }
        lblOrderDetails.setText("You have places an order for " +
                getActivity().getIntent().getIntExtra("NoOfCups", 1) +
                " " + getActivity().getIntent().getStringExtra("size") + " cups of "
                + getActivity().getIntent().getStringExtra("order") +
                " " +  sMilk +  " and each with " +
                getActivity().getIntent().getIntExtra("NoOfSugarSpoons", 0) +
                " spoons of sugar. \n\n");

        lblOrderDetails.append("This order will cost you R" + String.format(
                "%.2f",getActivity().getIntent().getIntExtra("NoOfCups", 1) * mCoffeePrice) +
                "\n");

        lblPickupTime.setText("Select pick-up time: ");
        btnConfirmOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                lblOrderConfirmation.setText("Your order was placed successfully, kindly pick-up at "
                        + lblDisplayTime.getText());
            }
        });

        imgDownload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CoffeeOrderDialogFragment dialogFragment = CoffeeOrderDialogFragment.newInstance(
                        "Confirm order file download!");
                dialogFragment.show(getActivity().getFragmentManager(), "dialog");
            }
        });

        imgTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(07)
                        .setMinute(30)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                        .build();
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        imgTime.setVisibility(View.GONE);
                        lblDisplayTime.setText(timePicker.getHour() + " : " + timePicker.getMinute());
                    }
                });
                timePicker.show(getFragmentManager(), "tag");
            }
        });

        btnCancelOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent("nguluvhe.pt.coffeecup.MainActivity"));
            }
        });
    }
}

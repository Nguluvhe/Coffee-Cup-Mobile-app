package nguluvhe.pt.coffescup;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;



public class OrderConfirmationFragment extends Fragment
{
    private ListView lstCoffee;
    private ArrayList<Coffee> mCoffeeList;
    private CoffeeAdapter mCoffeeAdapter;
    private Button btnArchivedOrder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_confirmation, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        btnArchivedOrder = getActivity().findViewById(R.id.btnArchivedOrder);
        lstCoffee = getActivity().findViewById(R.id.lstOrder);
        mCoffeeList = new ArrayList();
        mCoffeeList.add(new
                Coffee(getActivity().getIntent().getStringExtra("order"),
                getActivity().getIntent().getStringExtra("milk"),
                getActivity().getIntent().getIntExtra("NoOfCups", 1),
                getActivity().getIntent().getIntExtra("NoOfSugarSpoons",
                        0)));
        int id = getActivity().getIntent().getIntExtra("id", 0);
        mCoffeeAdapter = new CoffeeAdapter(getActivity(), mCoffeeList, id);
        lstCoffee.setAdapter(mCoffeeAdapter);

        btnArchivedOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }
}

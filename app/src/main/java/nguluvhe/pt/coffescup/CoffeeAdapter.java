package nguluvhe.pt.coffescup;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class CoffeeAdapter extends BaseAdapter
{
    private Context mContext;
    private List<Coffee> mCoffeeList;
    private int id;
    int[] iAssets = {
            R.drawable.black_coffee,
            R.drawable.ice_coffee,
            R.drawable.espresso,
            R.drawable.latte
    };
    public CoffeeAdapter(Context context, List<Coffee> coffeeList, int id)
    {
        mContext = context;
        mCoffeeList = coffeeList;
        this.id = id;
    }
    @Override
    public int getCount()
    {
        return mCoffeeList.size();
    }
    @Override
    public Object getItem(int position)
    {
        return mCoffeeList.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View v = View.inflate(mContext, R.layout.list_order_item, null);
        TextView lblCoffee = (TextView) v.findViewById(R.id.lblCoffee);
        TextView lblCups = (TextView) v.findViewById(R.id.lblCups);
        TextView lblMilk = (TextView) v.findViewById(R.id.lblMilk);
        TextView lblSugar = (TextView) v.findViewById(R.id.lblSugar);
        ImageView imgCoffee = (ImageView) v.findViewById(R.id.imgCoffee);
        lblCoffee.append(mCoffeeList.get(position).getsCoffeeName());
        lblCups.append(String.valueOf(mCoffeeList.get(position).getiNoOfCups()));
        lblMilk.append(mCoffeeList.get(position).getsMilk());
        lblSugar.append(String.valueOf(mCoffeeList.get(position).getiSugar()));
        imgCoffee.setImageResource(iAssets[id]);
        return v;
    }
}

package nguluvhe.pt.coffescup;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ArchivedOrders extends AppCompatActivity
{
    private ListView lstCoffee;
    DatabaseHelper mDatabaseHelper;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived_orders);
        lstCoffee = findViewById(R.id.lstCoffee);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }
    private void populateListView()
    {
        Cursor data = mDatabaseHelper.getData();

        ArrayList<String> lstData = new ArrayList<>();

        while(data.moveToNext())
        {
            lstData.add(data.getString(1));
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lstData);

        lstCoffee.setAdapter(adapter);

        lstCoffee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //getItemAtPosition returns an object you can create an object or create a string.
                String sName = adapterView.getItemAtPosition(position).toString();
                Cursor data = mDatabaseHelper.getItemId(sName);

                int itemId = -1;
                //Move to each item in the list
                while(data.moveToNext())
                {
                    itemId = data.getInt(0);

                }
                if(itemId > -1)
                {
                    Intent editDataIntent = new Intent(ArchivedOrders.this, EditAchivedOrder.class);
                    editDataIntent.putExtra("id", itemId);
                    editDataIntent.putExtra("name", sName);
                    startActivity(editDataIntent);


                }else {
                    toastMessage("No Id matches the name selected");
                }
            }
        });
    }


    private void toastMessage(String _sMsg)
    {
        Toast.makeText(this, _sMsg, Toast.LENGTH_SHORT).show();
    }
}

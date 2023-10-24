package nguluvhe.pt.coffescup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAchivedOrder extends AppCompatActivity
{
    private Button btnArchivedOrder;

    DatabaseHelper mDatabaseHelper;

    private int iSelectedId;
    private String sSelectedName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order_confirmation);

        mDatabaseHelper = new DatabaseHelper(this);
        btnArchivedOrder = findViewById(R.id.btnArchivedOrder);

        iSelectedId = getIntent().getIntExtra("id", -1);
        sSelectedName = getIntent().getStringExtra("name");

        btnArchivedOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sNewName = sSelectedName.toString();

                if(!sNewName.equals(""))
                {
                    mDatabaseHelper.updateName(sNewName, iSelectedId, sSelectedName);
                    Intent listDataFromDeleteIntent = new Intent(EditAchivedOrder.this, ArchivedOrders.class);
                    startActivity(listDataFromDeleteIntent);
                    finish();

                }else toastMessage("You must enter a name on the text field");
            }
        });

    }
    private void toastMessage(String _sMsg)
    {
        Toast.makeText(this, _sMsg, Toast.LENGTH_SHORT).show();
    }
}

package nguluvhe.pt.coffescup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class CoffeeOrderDialogFragment extends DialogFragment
{

    static CoffeeOrderDialogFragment newInstance(String title) {
        CoffeeOrderDialogFragment fragment = new CoffeeOrderDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String smg = "Coffee: " + getActivity().getIntent().getStringExtra("order") + "\n" +
                "Milk: " + getActivity().getIntent().getStringExtra("milk") + "\n" +
                "Number of Cups: " + getActivity().getIntent().getIntExtra("NoOfCups", 1) + "\n" +
                "Sugar: " + getActivity().getIntent().getIntExtra("NoOfSugarSpoons", 0);
        String title = getArguments().getString("title");
        return new AlertDialog.Builder(getActivity())
                .setIcon(R.mipmap.ic_launcher)
                .setTitle(title)
                .setMessage(smg)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                try
                                {
                                    //--SD card storage
                                    File sdCard = Environment.getExternalStorageDirectory();
                                    File directory = new File(sdCard.getAbsolutePath() + "/MyCoffeeCupFiles");
                                    directory.mkdirs();

                                    File file = new File(directory, "CoffeeOrdertext.txt");

                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

                                    outputStreamWriter.write(smg);

                                    outputStreamWriter.close();
                                } catch (IOException ioe)
                                {
                                    ioe.printStackTrace();
                                }

                                ((MainActivity)
                                        getActivity()).doPositiveClick();

                            }

                            })
                                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                int whichButton){
                                    ((MainActivity)
                                            getActivity()).doNegativeClick();
                                }

                            }).create();
    }
}



package nguluvhe.pt.coffescup;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Coffee
{
    private String sCoffeeName;
    private String sMilk;
    private int iNoOfCups;
    private int iSugar;

    public Coffee(String sCoffeeName, String sMilk, int iNoOfCups, int
            iSugar)
    {
        this.sCoffeeName = sCoffeeName;
        this.sMilk = sMilk;
        this.iNoOfCups = iNoOfCups;
        this.iSugar = iSugar;
    }
    public String getsCoffeeName() {
        return sCoffeeName;
    }
    public void setsCoffeeName(String sCoffeeName) {
        this.sCoffeeName = sCoffeeName;
    }
    public String getsMilk() {
        return sMilk;
    }
    public void setsMilk(String sMilk) {
        this.sMilk = sMilk;
    }
    public int getiNoOfCups() {
        return iNoOfCups;
    }
    public void setiNoOfCups(int iNoOfCups) {
        this.iNoOfCups = iNoOfCups;
    }
    public int getiSugar() {
        return iSugar;
    }
    public void setiSugar(int iSugar) {
        this.iSugar = iSugar;
    }
}

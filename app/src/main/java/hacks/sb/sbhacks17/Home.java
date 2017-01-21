package hacks.sb.sbhacks17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalInfo;

public class Home extends AppCompatActivity {
    public final static String POP_DENSITY = "POP_DENSITY";
    public final static String MED_INCOME = "MED_INCOME";

    private static Spinner spinnerPop;
    private static Spinner spinnerMedIncome;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("DreamHouse");

        //Initialize Population Spinner
        spinnerPop = (Spinner) findViewById(R.id.spinnerPop);
        spinnerMedIncome = (Spinner) findViewById(R.id.spinnerIncome);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pop_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPop.setAdapter(adapter);

        //Initialize Median Income Spinner
        ArrayAdapter<CharSequence> medIncome = ArrayAdapter.createFromResource(this,
                R.array.house_income, android.R.layout.simple_spinner_item);
        medIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedIncome.setAdapter(medIncome);






    }

    public void showMap(View view) {

        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra(POP_DENSITY, spinnerPop.getSelectedItem().toString());
        intent.putExtra(MED_INCOME, spinnerMedIncome.getSelectedItem().toString());

        startActivity(intent);
    }

    public void toToPoMap(View view) {

        Intent intent = new Intent(this, TopographicMap.class);
        startActivity(intent);
    }


}



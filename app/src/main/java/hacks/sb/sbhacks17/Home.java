package hacks.sb.sbhacks17;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

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


        range population_density = formatRange(spinnerPop.getSelectedItem().toString());
        range median_household_income = formatRange(spinnerMedIncome.getSelectedItem().toString());

        /*//searches data for population density range
        countyFinder finder = new countyFinder();
        finder.addSearch("density", 20, 50);
        finder.addSearch("household.income", median_household_income.getFloor(), median_household_income.getCeiling());
        countyList listPop = finder.search();
        int numCounties = listPop.size();

        Context context = getApplicationContext();
        String text = "Counties: " + Integer.toString(numCounties);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        */


    }

    public void showMap(View view) {

        Intent intent = new Intent(this, MainActivity.class);


        startActivity(intent);
    }

    public void toToPoMap(View view) {

        Intent intent = new Intent(this, TopographicMap.class);
        startActivity(intent);
    }

    public static range formatRange(String raw_data) {
        try{
            String[] parts = raw_data.split(" - ");
            String floor_str = parts[0].replaceAll("[^\\w\\s]","");
            String ceiling_str = parts[1].replaceAll("[^\\w\\s]","");
            int floor = Integer.parseInt(floor_str);
            int ceiling = Integer.parseInt(ceiling_str);

            return new range(floor, ceiling);
        }catch (ArrayIndexOutOfBoundsException e){
            return new range(0, 0);
        }

    }


}
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









    }

    public void showMap(View view) {

        Intent intent = new Intent(this, MainActivity.class);


        /*range population_density = formatPopRange(spinnerPop.getSelectedItem().toString());
        range median_household_income = formatIncomeRange(spinnerMedIncome.getSelectedItem().toString());

        //searches data for population density range
        countyFinder finder = new countyFinder();
        finder.addSearch("density", 20, 50);
        finder.addSearch("household.income", median_household_income.getFloor(), median_household_income.getCeiling());
        countyList listPop = finder.search();
        int numCounties = listPop.size();*/

        startActivity(intent);
    }

    public void toToPoMap(View view) {

        Intent intent = new Intent(this, TopographicMap.class);
        startActivity(intent);
    }

    public static range formatPopRange(String raw_data) {
        int floor;
        int ceiling;

        if(raw_data == "Countryside"){
            floor = 0;
            ceiling = 500;
        }
        else if(raw_data == "Small Town"){
            floor = 500;
            ceiling = 3000;
        }
        else if(raw_data == "Large Town"){
            floor = 3000;
            ceiling = 5000;
        }
        else if(raw_data == "City"){
            floor = 5000;
            ceiling = 10000;
        }
        else if(raw_data == "Mega City"){
            floor = 5000;
            ceiling = 50000;
        }
        else{
            floor = 0;
            ceiling = 50000;
        }


        return new range(floor, ceiling);


    }


    public static range formatIncomeRange(String raw_data) {
        int floor;
        int ceiling;

        if(raw_data == "Lower Class"){
            floor = 0;
            ceiling = 25000;
        }
        else if(raw_data == "Lower Middle Class"){
            floor = 25000;
            ceiling = 60000;
        }
        else if(raw_data == "Middle Class"){
            floor = 60000;
            ceiling = 100000;
        }
        else if(raw_data == "Upper Middle Class"){
            floor = 100000;
            ceiling = 150000;
        }
        else if(raw_data == "Upper Class"){
            floor = 150000;
            ceiling = 1000000;
        }
        else{
            floor = 0;
            ceiling = 50000;
        }


        return new range(floor, ceiling);


    }


}
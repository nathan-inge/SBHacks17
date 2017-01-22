package hacks.sb.sbhacks17;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalInfo;

public class Home extends AppCompatActivity {
    public final static String POP_DENSITY = "POP_DENSITY";
    public final static String MED_INCOME = "MED_INCOME";

    private static Spinner spinnerPop;
    private static Spinner spinnerMedIncome;
    private static Spinner spinnerLandscape;
    private static Spinner spinnerRegion;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("DreamHouse");

        //Initialize Population Spinner
        spinnerPop = (Spinner) findViewById(R.id.spinnerPop);
        spinnerMedIncome = (Spinner) findViewById(R.id.spinnerIncome);
        spinnerLandscape = (Spinner) findViewById(R.id.spinnerLandscape);
        spinnerRegion = (Spinner) findViewById(R.id.spinnerRegion);




        //Population Range
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pop_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPop.setAdapter(adapter);

        spinnerPop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int item = parent.getSelectedItemPosition();
                range population_density = formatPopRange(item);

                Context context = getApplicationContext();
                CharSequence text = "Population Range: " + Integer.toString(population_density.getFloor()) + " to " + Integer.toString(population_density.getCeiling());

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        //Initialize Median Income Spinner
        ArrayAdapter<CharSequence> medIncome = ArrayAdapter.createFromResource(this,
                R.array.house_income, android.R.layout.simple_spinner_item);
        medIncome.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMedIncome.setAdapter(medIncome);

        spinnerMedIncome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int item = parent.getSelectedItemPosition();
                range median_household_income = formatIncomeRange(item);

                Context context = getApplicationContext();
                CharSequence text = "Economic Range: " + Integer.toString(median_household_income.getFloor()) + " to " + Integer.toString(median_household_income.getCeiling());

                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //Region Spinner
        ArrayAdapter<CharSequence> adapterRegion = ArrayAdapter.createFromResource(this,
                R.array.region_array, android.R.layout.simple_spinner_item);
        adapterRegion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegion.setAdapter(adapterRegion);

        //Landscape Spinner
        ArrayAdapter<CharSequence> adapterLandscape = ArrayAdapter.createFromResource(this,
                R.array.landscape_array, android.R.layout.simple_spinner_item);
        adapterLandscape.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLandscape.setAdapter(adapterLandscape);









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



        range population_density = formatPopRange(spinnerPop.getSelectedItemPosition());
        range median_household_income = formatIncomeRange(spinnerMedIncome.getSelectedItemPosition());

        Boolean buyRent = ((Switch) findViewById(R.id.buy_sell)).isChecked(); //True = rent, false = buy
        Boolean kids = ((Switch) findViewById(R.id.kids)).isChecked(); //True = yes, false = no




        startActivity(intent);
    }

    public void toToPoMap(View view) {

        Intent intent = new Intent(this, TopographicMap.class);
        startActivity(intent);
    }

    public static range formatPopRange(int raw_data) {
        int floor;
        int ceiling;

        if(raw_data == 1){
            floor = 0;
            ceiling = 500;
        }
        else if(raw_data == 2){
            floor = 500;
            ceiling = 3000;
        }
        else if(raw_data == 3){
            floor = 3000;
            ceiling = 5000;
        }
        else if(raw_data == 4){
            floor = 5000;
            ceiling = 10000;
        }
        else if(raw_data == 5){
            floor = 5000;
            ceiling = 50000;
        }
        else{
            floor = 0;
            ceiling = 50000;
        }


        return new range(floor, ceiling);


    }


    public static range formatIncomeRange(int raw_data) {
        int floor;
        int ceiling;

        if(raw_data == 1){
            floor = 0;
            ceiling = 25000;
        }
        else if(raw_data == 2){
            floor = 25000;
            ceiling = 60000;
        }
        else if(raw_data == 3){
            floor = 60000;
            ceiling = 100000;
        }
        else if(raw_data == 4){
            floor = 100000;
            ceiling = 150000;
        }
        else if(raw_data == 5){
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
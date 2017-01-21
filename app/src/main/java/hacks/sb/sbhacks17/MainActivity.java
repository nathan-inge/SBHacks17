package hacks.sb.sbhacks17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private MapView mMapView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mMapView = (MapView) findViewById(R.id.mapView);
        //ArcGISMap map = new ArcGISMap(Basemap.Type.STREETS_NIGHT_VECTOR, 34.056295, -117.195800, 16);
        //mMapView.setMap(map);

        Intent fromHome = getIntent();

        //Population Density
        String pop_density_str = fromHome.getStringExtra("POP_DENSITY");
        range population_density = formatRange(pop_density_str);   //returns range function of
        TextView popDensity = (TextView) findViewById(R.id.pop_density);
        popDensity.setText("Population density range: " + population_density.getFloor() + " to " + population_density.getCeiling());

        //Median Household Income
        String med_income_str = fromHome.getStringExtra("MED_INCOME");
        range median_household_income = formatRange(med_income_str);
        TextView medIncome = (TextView) findViewById(R.id.med_income);
        medIncome.setText("Median household income range: " + median_household_income.getFloor() +" to " + median_household_income.getCeiling());






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
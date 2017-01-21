package hacks.sb.sbhacks17;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private MapView mMapView;
    private RegionHighlighter rh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.mapView);
        ArcGISMap mMap = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 35, -100, 3);


        rh = new RegionHighlighter(mMapView);
        //rh.sendTo(mMapView.getContext(), "louisville", "co", "80027");
        //mMapView.addMapScaleChangedListener(rh);
        mMapView.setMap(mMap);

        Intent fromHome = getIntent();

        //Population Density
        String pop_density_str = fromHome.getStringExtra("POP_DENSITY");
        range population_density = formatRange(pop_density_str);




        //Median Household Income
        String med_income_str = fromHome.getStringExtra("MED_INCOME");
        range median_household_income = formatRange(med_income_str);



        /*//searches data for population density range
        countyFinder finder = new countyFinder();
        finder.addSearch("density", 20, 50);
        finder.addSearch("household.income", median_household_income.getFloor(), median_household_income.getCeiling());
        countyList listPop = finder.search();
        int numCounties = listPop.size(); */

        Context context = getApplicationContext();
        String text = "Counties: " ;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();





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


    @Override
    protected void onPause(){
        mMapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mMapView.resume();
    }




}
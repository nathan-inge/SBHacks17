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
        String pop_floor = fromHome.getStringExtra("POP_FLOOR");
        String pop_ceiling = fromHome.getStringExtra("POP_CEILING");

        TextView popFloor = (TextView) findViewById(R.id.pop_floor);
        TextView popCeiling = (TextView) findViewById(R.id.pop_ceiling);
        popFloor.setText(pop_floor);
        popCeiling.setText(pop_ceiling);


    }




}
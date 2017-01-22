package hacks.sb.sbhacks17;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.MapView;

public class TopographicMap extends AppCompatActivity {

    private MapView mMapView;
    private RegionHighlighter rh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMapView = (MapView) findViewById(R.id.mapView);
        //ArcGISMap map = new ArcGISMap(Basemap.Type.TOPOGRAPHIC, 34.056295, -117.195800, 16);
        ArcGISMap map = new ArcGISMap("https://dreamhouse.maps.arcgis.com/home/webmap/viewer.html?webmap=f2435c0e48324a0d85df41d4ec8edc9c");

        //rh = new RegionHighlighter(mMapView);
        //mMapView.addMapScaleChangedListener(rh);

        mMapView.setMap(map);
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

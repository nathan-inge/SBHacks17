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
        //rh.sendTo(mMapView.getContext(), "louisville", "co", "80027"); this is sketch
        //mMapView.addMapScaleChangedListener(rh);
        mMapView.setMap(mMap);













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
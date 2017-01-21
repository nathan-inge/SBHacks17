package hacks.sb.sbhacks17;

import android.graphics.Color;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;
import com.esri.arcgisruntime.symbology.SimpleRenderer;
import com.esri.arcgisruntime.util.ListenableList;

/**
 * Created by Rick on 1/21/2017.
 */
public class RegionHighlighter implements MapScaleChangedListener{
    MapView mMapView;

    public RegionHighlighter(MapView mMapView) {
        this.mMapView = mMapView;
    }


    private void reset(){
        ListenableList list = mMapView.getGraphicsOverlays();
        while(!list.isEmpty())
            list.remove(0);
        // add graphics overlay
        highlightRegion(-12000000, 4000000, true);
    }

    private void highlightRegion(int x, int y, boolean satisfactory){
        Point pointGeometry = new Point(x, y, SpatialReferences.getWebMercator());
        SimpleMarkerSymbol pointSymbol = null;
        if(satisfactory){
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.argb(125, 50, 200, 50), (int)(10*mMapView.getMapScale()));
        }else{
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.argb(125,200,50,50), (int)(10*mMapView.getMapScale()));
        }
        Graphic pointGraphic = new Graphic(pointGeometry);
        GraphicsOverlay pointGraphicOverlay = new GraphicsOverlay();
        SimpleRenderer pointRenderer = new SimpleRenderer(pointSymbol);
        pointGraphicOverlay.setRenderer(pointRenderer);
        pointGraphicOverlay.getGraphics().add(pointGraphic);
        mMapView.getGraphicsOverlays().add(pointGraphicOverlay);
    }

    public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
        reset();
    }
}

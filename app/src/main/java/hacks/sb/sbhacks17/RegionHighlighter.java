package hacks.sb.sbhacks17;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
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


        Context context = mMapView.getContext();
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.zillow_icon);
        BitmapDrawable img = new BitmapDrawable(context.getResources(), bmp);
        PictureMarkerSymbol zilloPic = new PictureMarkerSymbol(img);

        Point pointGeometry2 = new Point(x, y, SpatialReferences.getWebMercator());
        Graphic pointGraphic2 = new Graphic(pointGeometry2);
        GraphicsOverlay pointGraphicOverlay2 = new GraphicsOverlay();
        SimpleRenderer pointRenderer2 = new SimpleRenderer(zilloPic);
        pointGraphicOverlay2.setRenderer(pointRenderer2);
        pointGraphicOverlay2.getGraphics().add(pointGraphic2);
        mMapView.getGraphicsOverlays().add(pointGraphicOverlay2);
    }

    public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
        reset();
    }

    public void sendTo(Context context, String city, String state, String zip){
        String urlString="http://www.zillow.com/" + city + "-"+state+"-"+zip+"/";
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            // Chrome browser presumably not installed so allow user to choose instead
            intent.setPackage(null);
            context.startActivity(intent);
        }
    }}

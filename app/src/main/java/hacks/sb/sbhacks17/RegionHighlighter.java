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
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.esri.arcgisruntime.geometry.ImmutablePart;
import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.Polygon;
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
public class RegionHighlighter implements MapView.OnTouchListener {
    MapView mMapView;
    MapView.OnTouchListener mapNavigation;
    county[] counties;


    public RegionHighlighter(MapView mMapView, county[] counties) {
        this.mMapView = mMapView;
        mapNavigation = mMapView.getOnTouchListener();
        mMapView.setOnTouchListener(this);
        this.counties = counties;

        reset();
    }


    private void reset(){
        ListenableList list = mMapView.getGraphicsOverlays();
        while(!list.isEmpty())
            list.remove(0);
        // add graphics overlay
        for(int i = 0; i < counties.length; i++) {
            highlightRegion(counties[i].lat, counties[i].lon, true);
        }
    }

    private void highlightRegion(double x, double y, boolean satisfactory){
        Point pointGeometry = new Point(x, y, SpatialReferences.getWebMercator());
        SimpleMarkerSymbol pointSymbol = null;
        if(satisfactory){
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.argb(125, 50, 200, 50), (int)(100));
        }else{
            pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, Color.argb(125,200,50,50), (int)(100));
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

    public void sendTo(Context context, String name){
        String urlString="http://www.zillow.com/" + name;
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
    }

    @Override
    public boolean onMultiPointerTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTouchDrag(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onRotate(MotionEvent motionEvent, double v) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    boolean noMove;
    public boolean onTouch(View v, MotionEvent event) {
        Polygon p = mMapView.getVisibleArea();
        ImmutablePart points = p.getParts().get(0);
        Point upLeft = points.getPoint(2);
        double width = points.getPoint(0).getX()- points.getPoint(2).getX();
        double height = points.getPoint(0).getY()- points.getPoint(2).getY();
        System.out.println(width + "\t" + height);
        if(event.getAction() == MotionEvent.ACTION_DOWN)
            noMove = true;
        if(event.getAction() == MotionEvent.ACTION_MOVE)
            noMove = false;
        if(event.getAction() == MotionEvent.ACTION_UP && noMove){
            double lat = upLeft.getX() + event.getX()/mMapView.getWidth()*width;
            double lon = upLeft.getY() + event.getY()/mMapView.getHeight()*height;
            double size = 50/mMapView.getWidth()*width;
            checkTap(lat, lon, size);
        }
        mapNavigation.onTouch(v, event);
        return true;
    }

    public void checkTap(double lat, double lon, double size){
        for(int i = 0; i < counties.length;i++){
            if(counties[i].lat - lat < size && counties[i].lon - lon < size){
                sendTo(mMapView.getContext(),counties[i].name);
            }
        }
    }
}

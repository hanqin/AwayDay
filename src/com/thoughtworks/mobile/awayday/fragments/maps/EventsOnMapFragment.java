package com.thoughtworks.mobile.awayday.fragments.maps;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.thoughtworks.mobile.awayday.AwayDayActivity;
import com.thoughtworks.mobile.awayday.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class EventsOnMapFragment extends Fragment   {

    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final AwayDayActivity activity = (AwayDayActivity) getActivity();
        mapView = activity.getMapView();
        mapView.setBuiltInZoomControls(true);
        mapView.setAlwaysDrawnWithCacheEnabled(true);
        mapView.getController().setZoom(10);
        mapView.setVisibility(View.VISIBLE);
        mapView.setClickable(true);

        final DefaultItemizedOverlay overlay = new DefaultItemizedOverlay(getResources().getDrawable(R.drawable.map_marker));
        mapView.getOverlays().add(overlay);

        final List<OverlayItem> locations = getTravelLocations();
        locations.addAll(getChengduLocations());
        overlay.setOverlayItems(locations);

        mapView.getController().setCenter(getCenter(getChengduLocations()));

        return mapView;
    }

    private List<OverlayItem> getTravelLocations() {
        final ArrayList<OverlayItem> result = new ArrayList<OverlayItem>();
        result.add(new OverlayItem(new GeoPoint(40088211, 116603407), "首都国际机场T3航站楼", "首都国际机场T3航站楼"));
        result.add(new OverlayItem(new GeoPoint(34449163, 108773703), "咸阳国际机场2号航站楼", "咸阳国际机场2号航站楼"));
        result.add(new OverlayItem(new GeoPoint(31162951, 121809631), "浦东国际机场", "浦东国际机场"));
        return result;
    }

    private List<OverlayItem> getChengduLocations() {
        final ArrayList<OverlayItem> result = new ArrayList<OverlayItem>();
        result.add(new OverlayItem(new GeoPoint(30581395, 103967956), "双流国际机场", "双流国际机场"));
        result.add(new OverlayItem(new GeoPoint(30904029, 103600712), "青城山", "青城山"));
        result.add(new OverlayItem(new GeoPoint(30900527, 103606952), "青城豪生国际酒店", "青城豪生国际酒店"));
        result.add(new OverlayItem(new GeoPoint(30651026, 104056386), "锦里古街", "锦里古街"));
        result.add(new OverlayItem(new GeoPoint(30669257, 104062419), "宽窄巷子", "宽窄巷子"));
        result.add(new OverlayItem(new GeoPoint(30668105, 104033851), "杜甫草堂", "杜甫草堂"));
        result.add(new OverlayItem(new GeoPoint(31039124, 103637342), "都江堰", "都江堰"));
        result.add(new OverlayItem(new GeoPoint(30739446, 104153553), "熊猫基地", "熊猫基地"));
        return result;
    }

    private GeoPoint getCenter(List<OverlayItem> travelLocations) {
        int smallestLatitude = Integer.MAX_VALUE;
        int biggestLatitude = -Integer.MAX_VALUE;
        int smallestLongitude = Integer.MAX_VALUE;
        int biggestLongitude = -Integer.MAX_VALUE;

        for (OverlayItem item : travelLocations) {
            GeoPoint point = item.getPoint();

            if (point.getLatitudeE6() < smallestLatitude) {
                smallestLatitude = point.getLatitudeE6();
            }
            if (point.getLatitudeE6() > biggestLatitude) {
                biggestLatitude = point.getLatitudeE6();
            }
            if (point.getLongitudeE6() < smallestLongitude) {
                smallestLongitude = point.getLongitudeE6();
            }
            if (point.getLongitudeE6() > biggestLongitude) {
                biggestLongitude = point.getLongitudeE6();
            }
        }
        return new GeoPoint(((smallestLatitude + biggestLatitude) / 2),
                (smallestLongitude + biggestLongitude) / 2);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        final ViewGroup parent = (ViewGroup) mapView.getParent();
        parent.removeView(mapView);
    }

    private static class DefaultItemizedOverlay extends ItemizedOverlay<OverlayItem> {

        private List<OverlayItem> overlayItems = new ArrayList<OverlayItem>();

        public DefaultItemizedOverlay(Drawable defaultMarker) {
            super(boundCenterBottom(defaultMarker));
        }

        public void setOverlayItems(List<OverlayItem> overlayItems) {
            this.overlayItems.addAll(overlayItems);
            setLastFocusedIndex(-1);
            populate();
        }

        @Override
        protected OverlayItem createItem(int position) {
            return overlayItems.get(position);
        }

        @Override
        public int size() {
            return overlayItems.size();
        }

        public void addOverlayItem(OverlayItem overlayItem) {
            overlayItems.add(overlayItem);
            populate();
        }
    }

}

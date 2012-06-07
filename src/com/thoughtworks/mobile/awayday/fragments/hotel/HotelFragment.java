package com.thoughtworks.mobile.awayday.fragments.hotel;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.io.InputStream;

import static android.graphics.drawable.Drawable.createFromStream;

public class HotelFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final LinearLayout linearLayout = new LinearLayout(getActivity());

        final Gallery gallery = new Gallery(getActivity());
        gallery.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        gallery.setAdapter(new ImageAdapter());

        linearLayout.addView(gallery);
        return linearLayout;
    }

    private static class ImageAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView resultView;
            if (convertView == null) {
                resultView = new ImageView(parent.getContext());
            } else {
                resultView = (ImageView) convertView;
            }
            String fileName = "hotel/thumbs/hotel_" + position + ".jpg";
            Log.d("FastLog", "fileName = " + fileName);
            resultView.setImageDrawable(getDrawableOf(parent.getContext(), fileName));
            resultView.setLayoutParams(new Gallery.LayoutParams(Gallery.LayoutParams.WRAP_CONTENT, Gallery.LayoutParams.WRAP_CONTENT));
            resultView.setScaleType(ImageView.ScaleType.FIT_XY);

            return resultView;
        }

        private Drawable getDrawableOf(Context context, String fileName) {
            InputStream stream = null;
            try {
                stream = context.getAssets().open(fileName);
                return createFromStream(stream, null);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }
    }
}

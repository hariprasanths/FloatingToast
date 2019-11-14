package hari.floatingtoastsample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import hari.floatingtoast.FloatingToast;

public class FirstFragment extends Fragment {

    TextView titleView;

    public FirstFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialogs_fragment, container, false);
        String title = "Fragment";
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        titleView = view.findViewById(R.id.fragmentTitle);
        titleView.setText(title);

        final Button whiteTop = view.findViewById(R.id.whiteTop);
        final Button whiteCenter = view.findViewById(R.id.whiteCenter);
        final Button whiteBottom = view.findViewById(R.id.whiteBottom);
        final Button redCenter = view.findViewById(R.id.redCenter);

        final Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/custom_font.ttf");

        whiteTop.setOnClickListener(v -> {
            //Floating toast with white text
            //Duration of 1250 millis
            //Gravity - Mid Top                 (Default is Center)
            //Fade out duration of 750 millis   (Default is 750 millis)
            //Text size - 12dp                  (Default is 16dp)
            //Float distance - 30px             (Default is 40px)
            //Background blur - On              (Default is On)
            //Text shadow - On                  (Default is Off)
            //Custom font                       (No custom font is provided by default)
            FloatingToast.makeToast(whiteTop, "Lorem ipsum dolor sit amet", FloatingToast.LENGTH_LONG)
                    .setGravity(FloatingToast.GRAVITY_MID_TOP)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_LONG)
                    .setTextSizeInDp(12)
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_SHORT)
                    .setTextColor(Color.parseColor("#ffffff"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .setTextTypeface(customFont)
                    .show();    //Show toast at the specified fixed position
        });

        whiteCenter.setOnClickListener(v -> {
            //Floating toast with white text
            //Gravity - Center
            //Duration of 1000 millis
            //Fade out duration of 750 millis
            //Text size - 12dp
            //Background blur - On
            //Text shadow - On
            //Custom font
            FloatingToast.makeToast(whiteCenter, "Lorem ipsum dolor sit amet", FloatingToast.LENGTH_MEDIUM)
                    .setGravity(FloatingToast.GRAVITY_CENTER)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_MEDIUM)
                    .setTextSizeInDp(12)
                    .setBackgroundBlur(true)
                    .setTextColor(Color.parseColor("#ffffff"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .setTextTypeface(customFont)
                    .show();    //Show toast at the specified fixed position
        });

        whiteBottom.setOnClickListener(v -> {
            //Floating toast with white text
            //Gravity - Mid Bottom
            //Duration of 1000 millis
            //Fade out duration of 750 millis
            //Text size - 12dp
            //Background blur - On
            //Text shadow - On
            //Custom font
            FloatingToast.makeToast(whiteBottom, "Lorem ipsum dolor sit amet", FloatingToast.LENGTH_MEDIUM)
                    .setGravity(FloatingToast.GRAVITY_MID_BOTTOM)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_MEDIUM)
                    .setTextSizeInDp(12)
                    .setBackgroundBlur(true)
                    .setTextColor(Color.parseColor("#ffffff"))
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .setTextTypeface(customFont)
                    .show();    //Show toast at the specified fixed position
        });

        redCenter.setOnClickListener(v -> {
            //Floating toast with red text
            //Gravity - Center
            //Duration of 750 millis
            //Fade out duration of 1000 millis
            //Text size - 12dp
            //Background blur - On
            //Text shadow - Off (Default is Off)
            //Custom font
            FloatingToast.makeToast(redCenter, "Lorem ipsum dolor sit amet", 750)
                    .setGravity(FloatingToast.GRAVITY_CENTER)
                    .setFadeOutDuration(1000)
                    .setTextSizeInDp(12)
                    .setBackgroundBlur(true)
                    .setTextColor(Color.parseColor("#ff0000"))
                    .setTextTypeface(customFont)
                    .show();    //Show toast at the specified fixed position
        });


        return view;
    }

    public static FirstFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

}

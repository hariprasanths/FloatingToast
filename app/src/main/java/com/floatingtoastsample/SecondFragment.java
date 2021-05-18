package com.floatingtoastsample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import com.github.hariprasanths.floatingtoast.FloatingToast;

public class SecondFragment extends Fragment {

    TextView titleView;

    public SecondFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_second, container, false);
        String title = "Fragment";
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
        titleView = view.findViewById(R.id.fragmentTitle);
        titleView.setText(title);

        final Button redOnTop = view.findViewById(R.id.redOnTop);
        final Button whiteOnTop = view.findViewById(R.id.whiteOnTop);

        final Typeface customFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/custom_font.ttf");

        redOnTop.setOnClickListener(v -> {
            //Floating toast with red text and positioned at the touch location inside the view
            //Duration of 1000 millis
            //Fade out duration of 750 millis
            //Text size - 12dp
            //Background blur - On
            //Text shadow - Off
            //Custom font
            FloatingToast.makeToast(redOnTop, "Lorem ipsum dolor sit amet", FloatingToast.LENGTH_MEDIUM)
                    .setTextColor(Color.parseColor("#ff0000"))
                    .setTextTypeface(customFont)
                    .setTextSizeInDp(12)
                    //Show toast at the touch location inside the view redOnTop
                    .showAtTouchPosition(redOnTop);
        });

        whiteOnTop.setOnClickListener(v -> {
            //Floating toast with White text and positioned at the touch location inside the view
            //Duration of 1000 millis
            //Fade out duration of 750 millis
            //Text size - 12dp
            //Background blur - On
            //Text shadow - On
            //Custom font
            FloatingToast.makeToast(whiteOnTop, "Lorem ipsum dolor sit amet", FloatingToast.LENGTH_MEDIUM)
                    .setTextColor(Color.parseColor("#ffffff"))
                    .setTextTypeface(customFont)
                    .setTextSizeInDp(12)
                    .setShadowLayer(5, 1, 1, Color.parseColor("#000000"))
                    .showAtTouchPosition(whiteOnTop);
        });

        final LinearLayout layout = view.findViewById(R.id.layout);

        layout.setOnClickListener(v -> {
            //Floating toast with blue text and positioned at the touch location inside the view
            //Duration of 1000 millis
            //Fade out duration of 750 millis
            //Text size - 12dp
            //Background blur - On
            //Text shadow - Off
            //Custom font
            FloatingToast.makeToast(layout, "Hey I come wherever u touch", FloatingToast.LENGTH_MEDIUM)
                    .setTextColor(Color.parseColor("#0000ff"))
                    .setTextTypeface(customFont)
                    .setTextSizeInDp(12)
                    .showAtTouchPosition(layout);
        });


        return view;
    }

    public static SecondFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title", title);
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(args);
        return fragment;
    }
}

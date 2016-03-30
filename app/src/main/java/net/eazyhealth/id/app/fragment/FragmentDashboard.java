package net.eazyhealth.id.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import net.eazyhealth.id.app.R;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class FragmentDashboard extends Fragment {

    private SliderLayout slider;

    @Override
    public void onResume() {
        super.onResume();
        slider.startAutoCycle();
    }

    @Override
    public void onPause() {
        slider.stopAutoCycle();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        slider.removeAllSliders();
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        slider = (SliderLayout) view.findViewById(R.id.slider_layout);
        slider.setDuration(5000);
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));

        SparseArray<String> data = new SparseArray<>();
        data.put(0, "http://f.tqn.com/y/careerplanning/1/W/z/E/doctor.jpg");
        data.put(1, "http://www.learnhowtobecome.org/wp-content/themes/howtobecome/images/career/doctor-3.jpg");

        for (int i=0; i<data.size(); i++) {
            DefaultSliderView tsv = new DefaultSliderView(getActivity());
            tsv.image(data.get(i));
            tsv.setScaleType(BaseSliderView.ScaleType.CenterCrop);
            tsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(BaseSliderView baseSliderView) {
                }
            });
            slider.addSlider(tsv);
        }
    }
}

package net.eazyhealth.id.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.MCUActivity;
import net.eazyhealth.id.app.custom.CustomImageView;
import net.eazyhealth.id.app.custom.CustomTextView;

/**
 * Created by GALIH ADITYO on 3/29/2016.
 */
public class FragmentDashboard extends Fragment {

    private SliderLayout slider;
    private LinearLayout listMenu;

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
        initVariable();
        return view;
    }

    private void initVariable() {
        getAdsSlider();

        LayoutInflater inflater = LayoutInflater.from(getActivity());
        SparseArray<String> menus = getListMenu();
        for (int i=0; i<menus.size(); i++) {
            View view  = inflater.inflate(R.layout.item_adapter_dashboard_menu, listMenu, false);
            RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.menu_layout);
            CustomImageView ivImage = (CustomImageView) view.findViewById(R.id.menu_iv);
            CustomTextView tvMenu = (CustomTextView) view.findViewById(R.id.menu_tv);

            ivImage.setBackgroundResource(R.drawable.medical_check_up);
            tvMenu.setText(menus.get(i));
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), MCUActivity.class);
                    i.putExtra(MCUActivity.BUNDLE_TITLE, getString(R.string.mcu));
                    startActivityForResult(i, 0);
                }
            });

            listMenu.addView(view);
        }
    }

    private void getAdsSlider() {
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

    private SparseArray<String> getListMenu() {
        SparseArray<String> data = new SparseArray<>();
        data.put(0, "Medical Check Up");
        data.put(1, "Apotek");
        data.put(2, "Ambulance");
        data.put(3, "Doctor");
        return data;
    }

    private void initView(View view) {
        listMenu = (LinearLayout) view.findViewById(R.id.list_menu);

        slider = (SliderLayout) view.findViewById(R.id.slider_layout);
        slider.setDuration(5000);
        slider.setPresetTransformer(SliderLayout.Transformer.Default);
        slider.setCustomIndicator((PagerIndicator) view.findViewById(R.id.custom_indicator));
    }
}

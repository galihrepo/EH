package net.eazyhealth.id.app.fragment.patients;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.eazyhealth.id.app.R;
import net.eazyhealth.id.app.activity.DetailTemplateActivity;
import net.eazyhealth.id.app.adapter.patients.AdapterSchedule;
import net.eazyhealth.id.app.model.response.PatientsScheduleResponse;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by GALIH ADITYO on 3/27/2016.
 */
public class FragmentPatientsSchedule extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patients_schedule, container, false);
        initView(view);
        initVariable();
        return view;
    }

    private void initVariable() {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new AdapterSchedule(getActivity(), getDummy());
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<PatientsScheduleResponse> getDummy() {
        List<PatientsScheduleResponse> data = new LinkedList<>();

        PatientsScheduleResponse data1 = new PatientsScheduleResponse();
        data1.setClinicName("Klinik Prodia");
        data1.setType("Cuci Darah");
        data1.setPackageMedical("Paket A");
        data1.setDate("10 Maret 2010");
        data.add(data1);

        PatientsScheduleResponse data2 = new PatientsScheduleResponse();
        data2.setClinicName("Klinik Cinta");
        data2.setType("Cuci Otak");
        data2.setPackageMedical("Paket B");
        data2.setDate("14 April 2010");
        data.add(data2);

        PatientsScheduleResponse data3 = new PatientsScheduleResponse();
        data3.setClinicName("Klinik Cinta");
        data3.setType("Cuci Otak");
        data3.setPackageMedical("Paket B");
        data3.setDate("14 April 2010");
        data.add(data3);

        PatientsScheduleResponse data4 = new PatientsScheduleResponse();
        data4.setClinicName("Klinik Cinta");
        data4.setType("Cuci Otak");
        data4.setPackageMedical("Paket B");
        data4.setDate("14 April 2010");
        data.add(data4);

        PatientsScheduleResponse data5 = new PatientsScheduleResponse();
        data5.setClinicName("Klinik Cinta");
        data5.setType("Cuci Otak");
        data5.setPackageMedical("Paket B");
        data5.setDate("14 April 2010");
        data.add(data5);

        return data;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);

        FloatingActionButton btnAdd = (FloatingActionButton) view.findViewById(R.id.add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DetailTemplateActivity.class);
                i.putExtra("title", "Add Schedule");
                startActivity(i);
                getActivity().finish();
            }
        });
    }
}

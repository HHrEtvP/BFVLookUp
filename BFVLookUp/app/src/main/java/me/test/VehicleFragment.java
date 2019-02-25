package me.test;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.sax.RootElement;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class VehicleFragment extends Fragment {

    Context context;

    Typeface futura;
    RecyclerView vehicleRecycler;
    List<DetailedModel> vehicleList;

    RootData data;

    HashMap<String,Integer>MaptoDrawable;
    HashMap<String,String>MaptoName;

    public static VehicleFragment newInstance(Bundle bundle){
        VehicleFragment vehicleFragment=new VehicleFragment();
        vehicleFragment.setArguments(bundle);
        return vehicleFragment;
    }

    public VehicleFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=(RootData)getArguments().getSerializable("data");
        context=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_vehicle, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        futura=Typeface.createFromAsset(context.getAssets(),"futura medium bt.ttf");

        setupData(data);
        setupVehicles(view);

        super.onViewCreated(view, savedInstanceState);
    }

    private void setupVehicles(View view){
        vehicleRecycler=view.findViewById(R.id.vehicle_vehicle_list);

        vehicleList.sort(new Comparator<DetailedModel>() {
            @Override
            public int compare(DetailedModel detailedModel, DetailedModel t1) {
                if (Integer.parseInt(detailedModel.detail1.value) >= Integer.parseInt(t1.detail1.value))
                    return -1;
                else
                    return 1;
            }
        });

        vehicleRecycler.setLayoutManager(new LinearLayoutManager(context));
        vehicleRecycler.setAdapter(new DetailedModelAdapter(vehicleList,futura));
    }

    private void setupData(RootData data){
        vehicleList=new ArrayList<>();
        List<Aspect_Vehicles>vehicles=data.data.vehicles;
        int size=vehicles.size();
        int i=0;

        MaptoDrawable=new HashMap<>();
        MaptoName=new HashMap<>();

        MaptoDrawable.put("plBlenMKI0",R.mipmap.blenheim);MaptoDrawable.put("plBlenMKIF0",R.mipmap.blenheim);MaptoDrawable.put("plC47",R.mipmap.c47);
        MaptoDrawable.put("plJU88a0",R.mipmap.ju88);MaptoDrawable.put("plSpitfVA0",R.mipmap.spitfire_va);MaptoDrawable.put("plSpitfVB0",R.mipmap.spitfire_va);
        MaptoDrawable.put("plStukaB10",R.mipmap.stuka_ju87_b1);MaptoDrawable.put("plStukaB20",R.mipmap.stuka_ju87_b2);MaptoDrawable.put("taChuCro",R.mipmap.churchillcrocodile);
        MaptoDrawable.put("taChuGC0",R.mipmap.churchill_guncarrier); MaptoDrawable.put("taChuMkVII0",R.mipmap.churchill_mkvii); MaptoDrawable.put("taFlakpIV0",R.mipmap.ger_aa);
        MaptoDrawable.put("taPazIV0",R.mipmap.panzeriv);MaptoDrawable.put("taPnz380",R.mipmap.panzer38t);MaptoDrawable.put("taStagT17E10",R.mipmap.staghound);
        MaptoDrawable.put("taTigI0",R.mipmap.tiger);MaptoDrawable.put("taTigISturm",R.mipmap.sturmtiger);MaptoDrawable.put("taValAA0",R.mipmap.uk_aa);
        MaptoDrawable.put("taValMkVIII0",R.mipmap.valentine);MaptoDrawable.put("trKett",R.mipmap.kettenkrad);MaptoDrawable.put("trKub",R.mipmap.kubelwagentransport);
        MaptoDrawable.put("trM3Halft",R.mipmap.m3);MaptoDrawable.put("trPakWag",R.mipmap.pakwagenhalftrack);MaptoDrawable.put("trSdKfz",R.mipmap.hanomag_halftrack);
        MaptoDrawable.put("trT48GMC",R.mipmap.m3_t48);MaptoDrawable.put("trUniCarrier",R.mipmap.universalcarrier);MaptoDrawable.put("plBF109g20",R.mipmap.bf109);
        MaptoDrawable.put("plBF109g60",R.mipmap.bf109);

        MaptoName.put("plBlenMKI0","Blenheim MKI");MaptoName.put("plBlenMKIF0","Blenheim MKIF");MaptoName.put("plC47","C47");
        MaptoName.put("plJU88a0","JU88A");MaptoName.put("plSpitfVA0","SpitFire VA");MaptoName.put("plSpitfVB0","SpitFire VB");
        MaptoName.put("plStukaB10","Stuka B1");MaptoName.put("plStukaB20","Stuka B2");MaptoName.put("taChuCro","ChurChill\nCrocodile");
        MaptoName.put("taChuGC0","ChuriChill\nGun Carrier"); MaptoName.put("taChuMkVII0","ChurChill\nMKVII"); MaptoName.put("taFlakpIV0","FlakPanzer IV");
        MaptoName.put("taPazIV0","Panzer IV");MaptoName.put("taPnz380","Panzer38T");MaptoName.put("taStagT17E10","StagHound\nT17E10");
        MaptoName.put("taTigI0","Tiger I");MaptoName.put("taTigISturm","SturmTiger");MaptoName.put("taValAA0","Valentine AA");
        MaptoName.put("taValMkVIII0","Valentine\nMK VIII");MaptoName.put("trKett","Kettenkard");MaptoName.put("trKub","Kubelwagen");
        MaptoName.put("trM3Halft","M3");MaptoName.put("trPakWag","SD.KFZ.251\nPakwagen");MaptoName.put("trSdKfz","SD.KFZ.251\nHalfTrack");
        MaptoName.put("trT48GMC","M3 T48 GMC");MaptoName.put("trUniCarrier","Universal Carrier");MaptoName.put("plBF109g60","BF109 G6");
        MaptoName.put("plBF109g60","BF109 G2");

        for(;i<size;i++){
            Integer mipmapId=MaptoDrawable.get(vehicles.get(i).code);
            String name=MaptoName.get(vehicles.get(i).code);
            if(mipmapId!=null&&name!=null) {
                vehicleList.add(new DetailedModel(mipmapId, name, new Model("Kills", String.format("%d", (int) vehicles.get(i).kills.value)),
                        new Model("KPM", String.format("%.2f", vehicles.get(i).killsPerMinute.value))));
            }else{
                vehicleList.add(new DetailedModel(R.mipmap.unknown, "Unknown Vehicle", new Model("Kills", String.format("%d", (int) vehicles.get(i).kills.value)),
                        new Model("KPM", String.format("%.2f", vehicles.get(i).killsPerMinute.value))));
            }
        }
    }
}

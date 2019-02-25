package me.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivityFragment extends Fragment {


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
                if (msg.getData().getString("INFO").equals("AVATAR OK")){
                    avator.setImageBitmap(avatar);
                }
            }catch(NullPointerException e){
                e.printStackTrace();
            }
        }
    }
    MyHandler handler=new MyHandler();

    //程序相关
    Context context;

    //数据部分,程序无关
    Typeface futura;
    ImageView top_class;
    RecyclerView user_info_recycler;
    RecyclerView class_info_recycler;
    ImageView avator;
    GridView generalGridview;
    private ArrayList<Model>top_list;
    private ArrayList<Model> general_model;
    private ArrayList<DetailedModel> class_info;

    //碎片相关
    RootData data;
    Bitmap avatar;

    public static MainActivityFragment newInstance(Bundle bundle){
        MainActivityFragment mainActivityFragment=new MainActivityFragment();
        mainActivityFragment.setArguments(bundle);
        return mainActivityFragment;
    }

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context=getActivity();
        data = (RootData) getArguments().getSerializable("data");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        futura = Typeface.createFromAsset(context.getAssets(), "futura medium bt.ttf");
        avator=view.findViewById(R.id.avator);
        getAvatar();

        setupData(data);

        setupTopGrid(view);

        setupGeneralGrid(view,context);

        setupClassInfo(view,context);

        setupFonts(view);

        super.onViewCreated(view, savedInstanceState);
    }

    private void setupTopGrid(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        user_info_recycler = view.findViewById(R.id.user_info_recyler);
        user_info_recycler.setAdapter(new ModelAdapter(top_list, futura, R.layout.grid_recycler_layout));
        user_info_recycler.setLayoutManager(gridLayoutManager);
    }

    private void setupGeneralGrid(View view ,Context context){
        generalGridview = view.findViewById(R.id.general_grid);
        generalGridview.setAdapter(new GridAdapter(general_model, context, futura));
    }

    private void setupClassInfo(View view,Context context){
        class_info.sort(new Comparator<DetailedModel>() {
            @Override
            public int compare(DetailedModel detailedModel, DetailedModel t1) {
                if (Double.parseDouble(detailedModel.detail2.value) >= Double.parseDouble(t1.detail2.value))
                    return -1;
                else
                    return 1;
            }
        });

        top_class = view.findViewById(R.id.top_class);
        top_class.setImageResource(class_info.get(0).imageId);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        class_info_recycler = view.findViewById(R.id.class_info_recycler);
        class_info_recycler.setNestedScrollingEnabled(false);
        class_info_recycler.setAdapter(new DetailedModelAdapter(class_info, futura));
        class_info_recycler.setLayoutManager(linearLayoutManager);
    }

    public void setupFonts(View view) {
        TextView t1 = view.findViewById(R.id.onetime_general);
        t1.setTypeface(futura);
        TextView t2 = view.findViewById(R.id.onetime_class);
        t2.setTypeface(futura);
    }

    private void setupData(RootData data){

        top_list=new ArrayList<>();
        general_model=new ArrayList<>();
        class_info=new ArrayList<>();

        MainObject mainObject=data.data;
        setupData_Top(data);

        Aspect_Stats aspect_stats=mainObject.stats;
        setupData_General(aspect_stats);

        List<Aspect_Classes>classes=mainObject.classes;
        setupData_Classes(classes);

    }

    private void setupData_General(Aspect_Stats stats){
        general_model.add(new Model(stats.kills.displayName,String.format("%d",(int)stats.kills.value)));
        general_model.add(new Model(stats.deaths.displayName,String.format("%d",(int)stats.deaths.value)));
        general_model.add(new Model(stats.wins.displayName,String.format("%d",(int)stats.wins.value)));
        general_model.add(new Model(stats.losses.displayName,String.format("%d",(int)stats.losses.value)));
        general_model.add(new Model(stats.kdRatio.displayName,String.format("%.2f",stats.kdRatio.value)));
        general_model.add(new Model(stats.wlPercentage.displayName,String.format("%.2f",stats.wlPercentage.value)+"%"));
    }

    private void setupData_Classes(List<Aspect_Classes> classes){
        Aspect_Classes assault=classes.get(0);
        Aspect_Classes medic=classes.get(1);
        Aspect_Classes support=classes.get(4);
        Aspect_Classes recon=classes.get(3);
        class_info.add(new DetailedModel(R.mipmap.assault_icon, "Assault", new Model("SPM",String.format("%d",(int)assault.scorePerMinute.value)),
                new Model("Score",String.format("%d",(int)assault.score.value))));
        class_info.add(new DetailedModel(R.mipmap.medic_icon, "Medic", new Model("SPM",String.format("%d",(int)medic.scorePerMinute.value)), new Model("Score",String.format("%d",(int)medic.score.value))));
        class_info.add(new DetailedModel(R.mipmap.support_icon, "Support", new Model("SPM",String.format("%d",(int)support.scorePerMinute.value)), new Model("Score",String.format("%d",(int)support.score.value))));
        class_info.add(new DetailedModel(R.mipmap.scout_icon, "Scout", new Model("SPM",String.format("%d",(int)recon.scorePerMinute.value)), new Model("Score",String.format("%d",(int)recon.score.value))));
    }

    private void setupData_Top(RootData data){
        top_list.add(new Model("ID",data.platformUserHandle));
        top_list.add(new Model("PlatFormID",String.valueOf(data.platformId)));
        top_list.add(new Model("Rank",String.format("%d",(int)data.data.stats.rank.value)));
        top_list.add(new Model("TOW Level","20"));
    }

    private void getAvatar(){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(data.avatarUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(10000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode() == 200){
                        InputStream inputStream = conn.getInputStream();
                        avatar = BitmapFactory.decodeStream(inputStream);
                    }
                    Message message=new Message();
                    Bundle bundle=new Bundle();
                    bundle.putString("INFO","AVATAR OK");
                    message.setData(bundle);
                    handler.sendMessage(message);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread=new Thread(runnable);
        thread.start();
    }

}

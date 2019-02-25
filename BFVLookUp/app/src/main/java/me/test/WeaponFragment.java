package me.test;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
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
public class WeaponFragment extends Fragment {

    Context context;

    Typeface futura;
    List<DetailedModel>weaponList;
    RecyclerView weaponRecycler;

    RootData data;
    HashMap<String,Integer>MaptoDrawable;
    HashMap<String,String>MaptoName;


    public static WeaponFragment newInstance(Bundle bundle){
        WeaponFragment weaponFragment=new WeaponFragment();
        weaponFragment.setArguments(bundle);
        return weaponFragment;
    }


    public WeaponFragment() {
        // Required empty public constructor
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
        return inflater.inflate(R.layout.fragment_weapon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        futura=Typeface.createFromAsset(context.getAssets(),"futura medium bt.ttf");

        setupData(data);

        setupWeapon(view);

        super.onViewCreated(view, savedInstanceState);
    }

    private void setupWeapon(View view){

        weaponList.sort(new Comparator<DetailedModel>() {
            @Override
            public int compare(DetailedModel detailedModel, DetailedModel t1) {
                if (Integer.parseInt(detailedModel.detail1.value) >= Integer.parseInt(t1.detail1.value))
                    return -1;
                else
                    return 1;
            }
        });

        weaponRecycler=view.findViewById(R.id.weapon_weapon_list);
        weaponRecycler.setAdapter(new DetailedModelAdapter(weaponList,futura));
        LinearLayoutManager manager=new LinearLayoutManager(context);
        weaponRecycler.setLayoutManager(manager);
    }

    private void setupData(RootData data){
        weaponList=new ArrayList<>();
        List<Aspect_Weapons>weapons=data.data.weapons;
        int size=weapons.size();
        int i=0;

        MaptoDrawable=new HashMap<>();
        MaptoName=new HashMap<>();

        MaptoDrawable.put("gctdac",R.mipmap.supply_ammo_crate);MaptoDrawable.put("gctddyns",R.mipmap.deployable_dynamite_sticky);MaptoDrawable.put("gctdfap",R.mipmap.supply_medical_pouch);
        MaptoDrawable.put("gctdmineat",R.mipmap.deployable_at_mine);MaptoDrawable.put("gctlgrenpistol",R.mipmap.at_pistol);MaptoDrawable.put("gctlgrensmoke",R.mipmap.grenade_launcher);
        MaptoDrawable.put("gctlrpf",R.mipmap.panzerfist);MaptoDrawable.put("gctlrpiat",R.mipmap.piat);MaptoDrawable.put("gcttbin",R.mipmap.scout_icon);
        MaptoDrawable.put("gctthrwgfi",R.mipmap.fire);MaptoDrawable.put("gctthrwgfrag",R.mipmap.frag);MaptoDrawable.put("gctthrwgimp",R.mipmap.impact);
        MaptoDrawable.put("gctthrwgsti",R.mipmap.sticky);MaptoDrawable.put("gcttndlssyrg",R.mipmap.supply_medical_syringe);MaptoDrawable.put("wtypeam42",R.mipmap.agm42);
        MaptoDrawable.put("wtypebauto5",R.mipmap.utomatic12g);MaptoDrawable.put("wtypebren",R.mipmap.bren);MaptoDrawable.put("wtypedarne",R.mipmap.m1922);
        MaptoDrawable.put("wtypeemp",R.mipmap.emp);MaptoDrawable.put("wtypefg42",R.mipmap.fg42);MaptoDrawable.put("wtypeg43",R.mipmap.gewehr43);
        MaptoDrawable.put("wtypegewm9530",R.mipmap.gewehr9530);MaptoDrawable.put("wtypegusvolar",R.mipmap.sturm_gewehr15);MaptoDrawable.put("wtypegusvolsar",R.mipmap.gewehr15);
        MaptoDrawable.put("wtypelewis",R.mipmap.lewis);MaptoDrawable.put("wtypelugerslr",R.mipmap.selbstlader1906);MaptoDrawable.put("wtypem1car",R.mipmap.m1a1);
        MaptoDrawable.put("wtypem30luftd",R.mipmap.m30);MaptoDrawable.put("wtypemasm1944",R.mipmap.mas44);MaptoDrawable.put("wtypemg34",R.mipmap.mg34);
        MaptoDrawable.put("wtypemg42",R.mipmap.mg42);MaptoDrawable.put("wtypemp28",R.mipmap.mp28);MaptoDrawable.put("wtypemp34s1",R.mipmap.mp34);
        MaptoDrawable.put("wtypems1916",R.mipmap.selbstlader1916);MaptoDrawable.put("wtypemshovel",R.mipmap.shovel);MaptoDrawable.put("wtypep08",R.mipmap.lugerp8);
        MaptoDrawable.put("wtyperib1918",R.mipmap.ribeyrolles);MaptoDrawable.put("wtyperuby",R.mipmap.ruby);MaptoDrawable.put("wtypesigke7",R.mipmap.ke7);
        MaptoDrawable.put("wtypeskp31",R.mipmap.suomi);MaptoDrawable.put("wtypesm1912",R.mipmap.steyrm1912);MaptoDrawable.put("wtypesmle4",R.mipmap.leeenfield);
        MaptoDrawable.put("wtypesten",R.mipmap.sten);MaptoDrawable.put("wtypestg44",R.mipmap.stg44);MaptoDrawable.put("wtypetm1928a1",R.mipmap.thompson);
        MaptoDrawable.put("wtypetsmlesar",R.mipmap.turner);MaptoDrawable.put("wtypevickers",R.mipmap.vgo);MaptoDrawable.put("wtypew1897sho",R.mipmap.m1897sweeper);
        MaptoDrawable.put("wtypew1907ar",R.mipmap.m1907);MaptoDrawable.put("wtypewmkiv",R.mipmap.mkvi);MaptoDrawable.put("wtypewp38",R.mipmap.p38);
        MaptoDrawable.put("wtypezh29slr",R.mipmap.zh29);MaptoDrawable.put("wtypezk383",R.mipmap.zk383);MaptoDrawable.put("wtypm1911",R.mipmap.m1911);
        MaptoDrawable.put("wtypmp40",R.mipmap.mp40);MaptoDrawable.put("wtypekrag",R.mipmap.krag_jorgensen);MaptoDrawable.put("wtypekar98k",R.mipmap.kar98k);
        MaptoDrawable.put("wtypemscoutm1916",R.mipmap.scoutknifem1916);MaptoDrawable.put("wtypecoupe",R.mipmap.coupe_coupe);

        MaptoDrawable.put("gctddecs",R.mipmap.decoy);MaptoDrawable.put("gctdsb",R.mipmap.spawnbeacon);MaptoDrawable.put("gctdtripf",R.mipmap.smine);
        MaptoDrawable.put("gctlfla",R.mipmap.flaregun);MaptoDrawable.put("gctlgrenrifle",R.mipmap.grenade_launcher);MaptoDrawable.put("gctthrwgat",R.mipmap.at_grenade);
        MaptoDrawable.put("gctthrwkn",R.mipmap.throwingknife);MaptoDrawable.put("gctthrwgsmo",R.mipmap.smoke);MaptoDrawable.put("wtypemhatchet",R.mipmap.hatchet);
        MaptoDrawable.put("wtypemkukri",R.mipmap.kukri);MaptoDrawable.put("wtyperemm8slr",R.mipmap.model835);MaptoDrawable.put("wtypemclub",R.mipmap.club);
        MaptoDrawable.put("wtypempickaxe",R.mipmap.pickaxe);

        MaptoName.put("gctdac","Ammo Crate");MaptoName.put("gctddyns","Sticky Dynamite");MaptoName.put("gctdfap","Bandage");
        MaptoName.put("gctdmineat","AT Mine");MaptoName.put("gctlgrenpistol","AT Grenade\nPistol");MaptoName.put("gctlgrensmoke","Smoke Grenade\nRifle");
        MaptoName.put("gctlrpf","PanzerFaust");MaptoName.put("gctlrpiat","PIAT");MaptoName.put("gcttbin","Spotting Scope");
        MaptoName.put("gctthrwgfi","Incendiary Grenade");MaptoName.put("gctthrwgfrag","Frag Grenade");MaptoName.put("gctthrwgimp","Impact Grenade");
        MaptoName.put("gctthrwgsti","Sticky Grenade");MaptoName.put("gcttndlssyrg","Syringe");MaptoName.put("wtypeam42","Ag m/42");
        MaptoName.put("wtypebauto5","12g Automatic");MaptoName.put("wtypebren","Bren");MaptoName.put("wtypedarne","M1922 Darne");
        MaptoName.put("wtypeemp","EMP");MaptoName.put("wtypefg42","FG42");MaptoName.put("wtypeg43","Gewehr 43");
        MaptoName.put("wtypegewm9530","Gewehr M95/30");MaptoName.put("wtypegusvolar","Sturmgewehr 1-5");MaptoName.put("wtypegusvolsar","Gewehr 1-5");
        MaptoName.put("wtypelewis","Lewis Gun");MaptoName.put("wtypelugerslr","Selbstlader\n1906");MaptoName.put("wtypem1car","M1A1");
        MaptoName.put("wtypem30luftd","M30 Drilling");MaptoName.put("wtypemasm1944","MAS44");MaptoName.put("wtypemg34","MG34");
        MaptoName.put("wtypemg42","MG42");MaptoName.put("wtypemp28","MP28");MaptoName.put("wtypemp34s1","MP34");
        MaptoName.put("wtypems1916","Selbstlader\n1916");MaptoName.put("wtypemshovel","Shovel");MaptoName.put("wtypep08","P08");
        MaptoName.put("wtyperib1918","Ribeyrolles\n1918");MaptoName.put("wtyperuby","Ruby");MaptoName.put("wtypesigke7","KE7");
        MaptoName.put("wtypeskp31","Suomi\nKP/-31");MaptoName.put("wtypesm1912","Repetierpistole\nM1912");MaptoName.put("wtypesmle4","Lee-Enfield\nNo.4 Mk I");
        MaptoName.put("wtypesten","STEN");MaptoName.put("wtypestg44","STG44");MaptoName.put("wtypetm1928a1","Thompson");
        MaptoName.put("wtypetsmlesar","Turner SMLE");MaptoName.put("wtypevickers","VGO");MaptoName.put("wtypew1897sho","M1897");
        MaptoName.put("wtypew1907ar","M1907");MaptoName.put("wtypewmkiv","MK VI\nRevolver");MaptoName.put("wtypewp38","P38");
        MaptoName.put("wtypezh29slr","ZH29");MaptoName.put("wtypezk383","ZK383");MaptoName.put("wtypm1911","M1911");
        MaptoName.put("wtypmp40","MP40");MaptoName.put("wtypekrag","Krag-Jorgensen");MaptoName.put("wtypekar98k","kar98K");
        MaptoName.put("wtypemscoutm1916","Scout Knife\nM1916");MaptoName.put("wtypecoupe","Coupe Coupe");

        MaptoName.put("gctddecs","Sniper Decoy");MaptoName.put("gctdsb","Spawn Beacon");MaptoName.put("gctdtripf","S Mine");
        MaptoName.put("gctlfla","Flare Gun");MaptoName.put("gctlgrenrifle","Grenande Rifle");MaptoName.put("gctthrwgat","AT Grenade");
        MaptoName.put("gctthrwkn","Throwing Knife");MaptoName.put("gctthrwgsmo","Smoke Grenade");MaptoName.put("wtypemhatchet","Hatchet");
        MaptoName.put("wtypemkukri","Kukri");MaptoName.put("wtyperemm8slr","Model 8");MaptoName.put("wtypemclub","Club");
        MaptoName.put("wtypempickaxe","PickAxe");


        for(;i<size;i++){
            Integer mipmapId=MaptoDrawable.get(weapons.get(i).code);
            String name=MaptoName.get(weapons.get(i).code);
            if(mipmapId!=null&&name!=null){
                weaponList.add(new DetailedModel(mipmapId,name,new Model("Kills",String.format("%d",(int)weapons.get(i).kills.value))
                        ,new Model("KPM",String.format("%.2f",weapons.get(i).killsPerMinute.value))));
            }
            else{
                weaponList.add(new DetailedModel(R.mipmap.unknown,"Unknown Weapon",new Model("Kills",String.format("%d",(int)weapons.get(i).kills.value))
                        ,new Model("KPM",String.format("%.2f",weapons.get(i).killsPerMinute.value))));
            }
        }

    }
}

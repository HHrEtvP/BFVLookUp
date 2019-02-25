package me.test;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    DrawerLayout drawerLayout;
    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;

    MainActivityFragment mainActivityFragment;
    StatsFragment statsFragment;
    WeaponFragment weaponFragment;
    VehicleFragment vehicleFragment;

    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        fragmentManager = getSupportFragmentManager();

        Intent intent=getIntent();
        final Bundle bundle=intent.getBundleExtra("data");
        if(bundle!=null){
            mainActivityFragment=MainActivityFragment.newInstance(bundle);
            statsFragment=StatsFragment.newInstance(bundle);
            weaponFragment=WeaponFragment.newInstance(bundle);
            vehicleFragment=VehicleFragment.newInstance(bundle);
        }
        URL=intent.getStringExtra("Url");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        viewPager=findViewById(R.id.view_pager);
        viewPager.setAdapter(new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                switch(position){
                    case 0:
                        return mainActivityFragment;
                    case 1:
                        return statsFragment;
                    case 2:
                        return weaponFragment;
                    case 3:
                        return vehicleFragment;
                        default:
                            return mainActivityFragment;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        });

        bottomNavigationView=findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                switch(id){
                    case R.id.menu_general:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_detail:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_weapon:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_vehicle:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.menu_general);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.menu_detail);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.menu_weapon);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.menu_vehicle);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        navigationView=findViewById(R.id.drawer_navi);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.refresh:
                        Intent intent=new Intent(MainActivity.this,LoadingActivity.class);
                        intent.putExtra("Url",URL);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.changeid:
                        Intent intent_=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent_);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

}





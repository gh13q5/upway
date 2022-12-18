package com.mobile.upway.controller;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mobile.upway.R;
import com.mobile.upway.dao.CombinationDAO;
import com.mobile.upway.dao.MenuDAO;
import com.mobile.upway.dto.Combination;
import com.mobile.upway.dto.Menu;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class BestSubActivity extends AppCompatActivity {

    static final String TAG = "BestSubActivity";

    // LIST
    RecyclerView recyclerView;
    CombListAdapter adapter;

    // DAO
    CombinationDAO combDAO;

    // UI
    DrawerLayout drawerLayout;
    NavigationView nav;
    TextView headerTitle;

    // INFLATER
    LayoutInflater inflater;

    // IS MENU OPEN?
    boolean isSandwichOpen = false;
    boolean isBreadOpen = false;
    boolean isCheeseOpen = false;
    boolean isVegeOpen = false;
    boolean isSauceOpen = false;
    boolean isOptionOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comb_list);

        drawerLayout = findViewById(R.id.drawer_layout);
        nav = findViewById(R.id.nav_view);
        headerTitle = findViewById(R.id.sort_result_title);

        inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        combDAO = new CombinationDAO();
        adapter = new CombListAdapter();

        // 리스트 연결
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        displayCombList();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.header_sort_button:
                drawerLayout.openDrawer(GravityCompat.END);
                break;
            case R.id.sandwich_menu_header:
                isSandwichOpen = extendMenu(isSandwichOpen, R.id.sandwich_menu, R.layout.sort_list_sandwich, R.id.sandwich_list);
                break;
            case R.id.bread_menu_header:
                isBreadOpen = extendMenu(isBreadOpen, R.id.bread_menu, R.layout.sort_list_bread, R.id.bread_list);
                break;
            case R.id.cheese_menu_header:
                isCheeseOpen = extendMenu(isCheeseOpen, R.id.cheese_menu, R.layout.sort_list_cheese, R.id.cheese_list);
                break;
            case R.id.vege_menu_header:
                isVegeOpen = extendMenu(isVegeOpen, R.id.vege_menu, R.layout.sort_list_vege, R.id.vege_list);
                break;
            case R.id.sauce_menu_header:
                isSauceOpen = extendMenu(isSauceOpen, R.id.sauce_menu, R.layout.sort_list_sauce, R.id.sauce_list);
                break;
            case R.id.option_menu_header:
                isOptionOpen = extendMenu(isOptionOpen, R.id.option_menu, R.layout.sort_list_options, R.id.options_list);
                break;
            case R.id.BLT:
            case R.id.KBBQ:
            case R.id.chickenBaconAvocado:
            case R.id.chickenSlice:
            case R.id.chickenTeriyaki:
            case R.id.eggMayo:
            case R.id.ham:
            case R.id.italianBMT:
            case R.id.pulledPorkBarbecue:
            case R.id.roastChicken:
            case R.id.rotisserieBarbecueChicken:
            case R.id.shirmp:
            case R.id.spicyBBQ:
            case R.id.spicyItalian:
            case R.id.steaknCheese:
            case R.id.subwayClub:
            case R.id.tuna:
            case R.id.vege:
                changeCombListBySort("menu", ((TextView) v).getText().toString());
                break;
            case R.id.flatbread:
            case R.id.hearty:
            case R.id.honeyOat:
            case R.id.parmesanOregano:
            case R.id.wheat:
            case R.id.white:
                changeCombListBySort("bread", ((TextView) v).getText().toString());
                break;
            case R.id.americanCheese:
            case R.id.mozzarellaCheese:
            case R.id.shreddedCheese:
                changeCombListBySort("cheese", ((TextView) v).getText().toString());
                break;
            case R.id.cucumbers:
            case R.id.jalapenos:
            case R.id.lettuce:
            case R.id.olives:
            case R.id.onions:
            case R.id.peppers:
            case R.id.pickles:
            case R.id.tomatoes:
                changeCombListBySortArray("vegetableList", ((TextView) v).getText().toString());
                break;
            case R.id.blackPepper:
            case R.id.honeyMustard:
            case R.id.horseradish:
            case R.id.hotChilli:
            case R.id.italianDressing:
            case R.id.mayonnaise:
            case R.id.newSouthwestChipotle:
            case R.id.oliveOil:
            case R.id.ranch:
            case R.id.redWineVinaigrette:
            case R.id.salt:
            case R.id.smokeBBQ:
            case R.id.sweetChilli:
            case R.id.sweetOnion:
            case R.id.yellowMustard:
                changeCombListBySortArray("sauceList", ((TextView) v).getText().toString());
                break;
            case R.id.avocado:
            case R.id.bacon:
            case R.id.doubleCheese:
            case R.id.doubleUp:
            case R.id.eggmayo:
            case R.id.omelet:
            case R.id.pepperoni:
                changeCombListBySortArray("optionList", ((TextView) v).getText().toString());
                break;
        }
    }

    public boolean extendMenu(boolean isOpen, int layoutId, int inflateLayoutId, int removeId){
        if(!isOpen){
            Log.d(TAG, "extendMenu start");
            LinearLayout layout = findViewById(layoutId);
            View list = inflater.inflate(inflateLayoutId, null);
            layout.addView(list);
            return true;
        }
        else{
            LinearLayout removeList = this.findViewById(removeId);
            ((ViewGroup) removeList.getParent()).removeView(removeList);
            return false;
        }
    }

    public void changeCombListBySort(String field, String condition){
        headerTitle.setText(condition);
        combDAO.getCombListInclude(field, condition, combList -> adaptList(combList));
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    public void changeCombListBySortArray(String field, String condition){
        headerTitle.setText(condition);
        combDAO.getCombListIncludeInArray(field, condition, combList -> adaptList(combList));
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    public void displayCombList(){
        combDAO.getAllCombOrderByScraps(combList -> adaptList(combList));
    }

    public void adaptList(List combList){
        adapter.setList((ArrayList<Combination>) combList);
        recyclerView.setAdapter(adapter);
    }
}
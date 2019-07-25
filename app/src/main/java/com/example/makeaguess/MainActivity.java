package com.example.makeaguess;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import io.realm.Realm;


public class MainActivity extends AppCompatActivity implements DrawerLocker {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String playername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigator);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_menu, R.string.close_menu);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Menu menu = navigationView.getMenu();
        menu.add(0, 13, 80, "Exit").setIcon(R.drawable.close);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int selected = menuItem.getItemId();
                if (selected == 11) {
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    ProfileFrag profileFrag = new ProfileFrag();
                    Bundle bundle = new Bundle();
                    bundle.putString("Name", playername);
                    profileFrag.setArguments(bundle);
                    replaceFragment(profileFrag);
                    drawerLayout.closeDrawer(Gravity.LEFT);

                }
                if (selected == 12) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Playername", "");
                    bundle.putString("Type", "");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Frag4 frag4 = new Frag4();
                    frag4.setArguments(bundle);
                    replaceFragment(frag4);
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (selected == 13) {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    finish();
                }
                if (selected == 14) {
                    Frag0 frag0 = new Frag0();
                    FragmentManager fm = getSupportFragmentManager();
                    for (int i = 0; i < fm.getBackStackEntryCount(); i++)
                        fm.popBackStack();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.detailscontainer, frag0);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawer(Gravity.LEFT);

                }
                if (selected == 15) {
                    HelpFrag helpFrag = new HelpFrag();
                    replaceFragment(helpFrag);
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                return true;
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.detailscontainer, new Frag0());
        ft.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawerLayout.setDrawerLockMode(lockMode);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(enabled);
    }

    public void setName(String s) {
        playername = s;
    }

    private void replaceFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.detailscontainer, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 1; i < fm.getBackStackEntryCount(); i++)
            fm.popBackStack();
        drawerLayout.closeDrawer(Gravity.LEFT);
    }
}

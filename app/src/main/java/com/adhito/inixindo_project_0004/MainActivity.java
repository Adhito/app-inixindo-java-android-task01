package com.adhito.inixindo_project_0004;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.adhito.inixindo_project_0004.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
    }

    // Menampilkan options Menu pada ToolBar.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_item, menu);
        return super.onCreateOptionsMenu(menu);
    }

    Fragment fragment = null;
    // Salah satu Options dipilih dan action-nya
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                getSupportActionBar().setTitle("Home");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_contact_us:
                fragment = new ContactUsFragment();
                getSupportActionBar().setTitle("Contact Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            case R.id.nav_about_us:
                fragment = new AboutUsFragment();
                getSupportActionBar().setTitle("About Us");
                binding.drawer.closeDrawer(GravityCompat.START);
                callFragment(fragment);
                break;
            default:
                Toast.makeText(this, "No Menu is selected", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        // Custom ToolBar
        setSupportActionBar(binding.toolbar);

        // Default fragment dibuka pertama kali
        getSupportActionBar().setTitle("Home");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout, new HomeFragment())
                .commit();
        binding.navView.setCheckedItem(R.id.nav_home);

        // Membuka drawer
        toggle = new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
                R.string.open, R.string.close);

        // Drawer Back Button
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

        // Sinkronisasi Drawer
        toggle.syncState();
        
        // Salah satu menu navigasi dipilih
        binding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Fragment fragment = null;
            
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Home");
                        callFragment(fragment);
                        break;

                    case R.id.nav_contact_us:
                        fragment = new ContactUsFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("Contact Us");
                        callFragment(fragment);
                        break;

                    case R.id.nav_about_us:
                        fragment = new AboutUsFragment();
                        binding.drawer.closeDrawer(GravityCompat.START);
                        getSupportActionBar().setTitle("About Us");
                        callFragment(fragment);
                        break;

                    case R.id.btn_manage_account:
                        fragment = new ManageAccountFragment();
                        getSupportActionBar().setTitle("Manage Account");
                        callFragment(fragment);
                        break;
                }
                return true;
            }
        });
    }

    private void callFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
        );
        transaction.replace(R.id.frame_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
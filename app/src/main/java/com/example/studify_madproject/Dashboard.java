package com.example.studify_madproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.studify_madproject.databinding.ActivityDashboardBinding;
import com.example.studify_madproject.databinding.ActivityMainBinding;
import com.example.studify_madproject.model.ModelCategory;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    public ArrayList<ModelCategory> categoryArrayList;
    public ViewPagerAdapter viewPagerAdapter;
    BottomNavigationView nav;
    public static FirebaseAuth mAuth;
    public static FirebaseUser us;
    public static String name;
    private ActivityDashboardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        replaceFragment(new HomeFragment());

        mAuth=FirebaseAuth.getInstance();
        us=mAuth.getCurrentUser();
        FirebaseDatabase fdb=FirebaseDatabase.getInstance();
        DatabaseReference rootref = fdb.getReference();

        setupViewPagerAdapter(binding.FrameContainer);
        binding.tabLay

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference nameref = rootref.child("Users").child(user.getUid()).child("Name");

        nameref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name = snapshot.getValue().toString();
                // Toast.makeText(Dashboard.this, "Welcome"+name+"!", Toast.LENGTH_SHORT).show();

                Snackbar.make(findViewById(android.R.id.content), "Hey there, " + name, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        nav = findViewById(R.id.bottomNavigationView);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        replaceFragment(new HomeFragment());
                        return true;

                    case R.id.navigation_notes:
                        replaceFragment(new NotesFragment());
                        return true;

                    case R.id.navigation_reminder:
                        replaceFragment(new ReminderFragment());
                        return true;

                    case R.id.navigation_profile:
                        replaceFragment(new profileFragment());
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameContainer, fragment);
        fragmentTransaction.commit();
    }
    private void setupViewPagerAdapter(ViewPager viewPager)
    {
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,this);
        categoryArrayList=new ArrayList<>();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Category");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoryArrayList.clear();
                ModelCategory modelAll=new ModelCategory("01","All","",1);
                ModelCategory modelMostViewed=new ModelCategory("02","Most Viewed","",1);
                ModelCategory modelMostDownloaded=new ModelCategory("03","Most Downloaded","",1);
            categoryArrayList.add(modelAll);
            categoryArrayList.add(modelMostViewed);
            categoryArrayList.add(modelMostDownloaded);

            viewPagerAdapter.addFragment(HomeFragment.newInstance(""+modelAll.getId(),""+modelAll.getCategory(),""+modelAll.getUid()),modelAll.getCategory());
            viewPagerAdapter.addFragment(HomeFragment.newInstance(""+modelMostViewed.getId(),""+modelMostViewed.getCategory(),""+modelMostViewed.getUid()),modelMostViewed.getCategory());
            viewPagerAdapter.addFragment(HomeFragment.newInstance(""+modelMostDownloaded.getId(),""+modelMostDownloaded.getCategory(),""+modelMostDownloaded.getUid()),modelMostDownloaded.getCategory());
            viewPagerAdapter.notifyDataSetChanged();
            for(DataSnapshot ds:snapshot.getChildren()){
                ModelCategory model=ds.getValue(ModelCategory.class);

                categoryArrayList.add(model);
                viewPagerAdapter.addFragment(HomeFragment.newInstance(
                        ""+model.getId(),
                        ""+modelAll.getCategory(),
                        ""+model.getUid()), model.getCategory());
                viewPagerAdapter.notifyDataSetChanged();
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        viewPager.setAdapter(viewPagerAdapter);

    }

    public class ViewPagerAdapter extends FragmentPagerAdapter{
        private ArrayList<HomeFragment> fragmentList;
        private ArrayList<String> fragmentTitleList=new ArrayList<>();
        private Context context;
        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior,Context context) {
            super(fm, behavior);
            this.context=context;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        private void addFragment(HomeFragment fragment,String title){
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}
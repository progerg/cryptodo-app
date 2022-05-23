package com.example.cryptodo;

import android.os.Bundle;

import com.example.cryptodo.api.UserService;
import com.example.cryptodo.api.in_models.AddUser;
import com.example.cryptodo.api.out_models.AddUserOut;
import com.example.cryptodo.db.DB;
import com.example.cryptodo.ui.dashboard.ContractTypeFragment;
import com.example.cryptodo.ui.dashboard.DashboardAreaFragment;
import com.example.cryptodo.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cryptodo.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private DB mDBConnector;
    private ActivityMainBinding binding;

    private String baseUrl = "https://api.cryptodo.app/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        checkUser();
    }

    private AddUserOut registerUser(String id) {
        AddUserOut userOut;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        UserService service = retrofit.create(UserService.class);
        Call<AddUserOut> call = service.addMobile(new AddUser(id));
        try {
            Response<AddUserOut> userResponse = call.execute();
            userOut = userResponse.body();
        } catch (IOException e) {
            e.printStackTrace();
            userOut = new AddUserOut();
        }
        return userOut;
    }

    public void checkUser() {
        mDBConnector = new DB(this);
        AddUser user = mDBConnector.getUser();
        if (user.userId.isEmpty()) {
            registerUser(mDBConnector.generateId());
        }

    }

    public Fragment getVisibleFragment(){
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if(fragments != null){
            for(Fragment fragment : fragments){
                if(fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getVisibleFragment();
        getSupportFragmentManager().beginTransaction().replace(currentFragment.getId(), new DashboardFragment())
                .setReorderingAllowed(true).addToBackStack(null).commit();

    }

}
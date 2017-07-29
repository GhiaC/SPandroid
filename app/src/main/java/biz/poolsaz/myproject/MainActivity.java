package biz.poolsaz.myproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import biz.poolsaz.myproject.fragment.*;
import biz.poolsaz.myproject.tools.Person;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Demonstrates the use of custom animations in a FragmentTransaction when
 * pushing and popping a stack.
 */
public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    fragment_main myFragmentMain = new fragment_main();
    fragment_profile myFragmentProfile ;
    fragment_showprofiles myFragmentShowProfile = new fragment_showprofiles();

    Fragment init;

    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.MainFrag);

        // get an instance of FragmentTransaction from your Activity
        fragmentManager = getFragmentManager();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.MainFrag, myFragmentMain);
        init = myFragmentMain;
        fragmentTransaction.commit();
    }

    public void showProfile(Person person){
        myFragmentProfile = new fragment_profile();
        Bundle bundle = new Bundle();
        bundle.putInt("SPid", person.getSPid());
        bundle.putInt("Cid", person.getCid());
        bundle.putString("SPname", person.getName());
        bundle.putString("SPprofileImg", person.getProfileImg());
        bundle.putString("SPdiscreption", person.getDiscreption());
        bundle.putString("SPpictures", person.getPictures());
        bundle.putString("SPstartWorkTime", person.getStartWorkTime());
        bundle.putString("SPendWorkTime", person.getEndWorkTime());
        bundle.putString("SPphoneNumber", person.getPhoneNumber());
        bundle.putInt("SPvote", person.getVote());
        bundle.putInt("SPbusy", person.getBusy());
        bundle.putInt("SPstatus", person.getStatus());
        myFragmentProfile.setArguments(bundle);
        loadFragment(myFragmentProfile,true);
    }

    public void loadFragment(final Fragment fragment, boolean addToBackStack) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(init);
        fragmentTransaction.add(R.id.MainFrag, fragment);
        init = fragment;

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }

    public void home(View v) {
        loadFragment(myFragmentMain,true);
    }

    public void seeProfile(View v) {
        myFragmentProfile = new fragment_profile();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", 1234);


        myFragmentProfile.setArguments(bundle);
        loadFragment(myFragmentProfile,true);
    }

    public void test(View v) {
        loadFragment(myFragmentShowProfile,true);
    }
}

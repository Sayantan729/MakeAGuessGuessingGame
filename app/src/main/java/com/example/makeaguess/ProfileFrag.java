package com.example.makeaguess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link ProfileFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFrag extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView profilename,ranka,rankf,animalcurrent,animalbest,flowercurrent,flowerbest;



    public ProfileFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFrag newInstance(String param1, String param2) {
        ProfileFrag fragment = new ProfileFrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        profilename=view.findViewById(R.id.profilename);
        ranka=view.findViewById(R.id.ranka);
        rankf=view.findViewById(R.id.rankf);
        animalbest=view.findViewById(R.id.animalbest);
        animalcurrent=view.findViewById(R.id.animalcurrent);
        flowerbest=view.findViewById(R.id.flowerbest);
        flowercurrent=view.findViewById(R.id.flowercurrent);
        Realm realm=Realm.getDefaultInstance();
        String name=getArguments().getString("Name");
        Player playerb=realm.where(Player.class).equalTo("id",name+"flowers").findFirst();
        Player playera=realm.where(Player.class).equalTo("id",name+"animals").findFirst();
        RealmResults<Player> a=realm.where(Player.class).equalTo("gametype","animals")
                .findAll().sort("score", Sort.DESCENDING);
        RealmResults<Player> b=realm.where(Player.class).equalTo("gametype","flowers")
                .findAll().sort("score", Sort.DESCENDING);
        profilename.setText(name);
        if(playera==null)
        {
            ranka.setText("Animals\nNone");
            //rankf.setText("Flowers\nNone");
            animalcurrent.setText("0");
            animalbest.setText("0");
        }
        else
        {
            int rank=a.indexOf(playera);
            ranka.setText("Animals\n"+(rank+1));
            animalcurrent.setText(""+playera.getScore());
            animalbest.setText(""+playera.getBestScore());
        }
        if(playerb==null)
        {

            rankf.setText("Flowers\nNone");
            flowercurrent.setText("0");
            flowerbest.setText("0");
        }
        else
        {
            int rank=b.indexOf(playerb);
            rankf.setText("Flowers\n"+(rank+1));
            flowercurrent.setText(""+playerb.getScore());
            flowerbest.setText(""+playerb.getBestScore());
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}

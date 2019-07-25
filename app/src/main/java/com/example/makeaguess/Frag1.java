package com.example.makeaguess;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;

import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link Frag1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag1 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView animals,flowers;
    private NavigationView navigationView;




    public Frag1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag1.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag1 newInstance(String param1, String param2) {
        Frag1 fragment = new Frag1();
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
        View view=inflater.inflate(R.layout.fragment_frag1, container, false);
        ((DrawerLocker)getActivity()).setDrawerEnabled(true);
        animals=view.findViewById(R.id.Animals);
        flowers=view.findViewById(R.id.Flowers);
        navigationView=getActivity().findViewById(R.id.navigator);
        Menu menu=navigationView.getMenu();
        menu.clear();
        menu.add(0,11,20,"Profile").setIcon(R.drawable.person);
        menu.add(0,12,50,"Scoreboard").setIcon(R.drawable.scoreboard);
        menu.add(0,14,70,"Log Out").setIcon(R.drawable.logout);
        menu.add(0,15,80,"Help").setIcon(R.drawable.iicon);
        menu.add(0,13,90,"Exit").setIcon(R.drawable.close);
        animals.setOnClickListener(this);
        flowers.setOnClickListener(this);

        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onClick(View view) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Bundle bundle=getArguments();

        if(view.getId()==animals.getId())
        {
            bundle.putString("Type","animals");
            Frag2 frag2=new Frag2();
            frag2.setArguments(bundle);
            ft.replace(R.id.detailscontainer,frag2);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack("Frag2");
            ft.commit();

        }
        if(view.getId()==flowers.getId())
        {
            bundle.putString("Type","flowers");
            Frag3 frag3=new Frag3();
            frag3.setArguments(bundle);
            ft.replace(R.id.detailscontainer,frag3);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack("Frag3");
            ft.commit();

        }



    }




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
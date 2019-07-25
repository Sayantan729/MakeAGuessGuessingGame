package com.example.makeaguess;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link } interface
 * to handle interaction events.
 * Use the {@link Frag2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag2 extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button B1,B2,B3,B4;
    private TextView timer;
    private ImageButton imageButton;
    private ImageView imageView;
    private ImageView heart1,heart2,heart3,heart4,heart5;
    private Integer a[]={R.drawable.bear,R.drawable.beaver,
            R.drawable.bison,R.drawable.blackbuck,R.drawable.camel,
            R.drawable.cheetah,R.drawable.chimpanzee,R.drawable.chinkara,
            R.drawable.elephant, R.drawable.gharial,R.drawable.gorilla,
            R.drawable.hangul,R.drawable.hyena,R.drawable.jackal,
            R.drawable.leopard,R.drawable.lion,R.drawable.lynx,
            R.drawable.nilgai,R.drawable.orangutan,R.drawable.rhino,
            R.drawable.snowleopard,R.drawable.tiger,R.drawable.wildebeest,
            R.drawable.zebra, R.drawable.wolf};
    private Integer a1[]={R.drawable.bear2,R.drawable.beaver2,
            R.drawable.bison2,R.drawable.blackbuck2,R.drawable.camel2,
            R.drawable.cheetah2,R.drawable.chimpanzee2,R.drawable.chinkara2,
            R.drawable.elephant2, R.drawable.gharial2,R.drawable.gorilla2,
            R.drawable.hangul2,R.drawable.hyena2,R.drawable.jackal2,
            R.drawable.leopard2,R.drawable.lion2,R.drawable.lynx2,
            R.drawable.nilgai2,R.drawable.orangutan2,R.drawable.rhino2,
            R.drawable.snowleopard2,R.drawable.tiger2,R.drawable.wildebeest2,
            R.drawable.zebra2, R.drawable.wolf2};
    private String b[]={"Bear","Beaver",
            "Bison","Blackbuck","Camel",
            "Cheetah","Chimpanzee","Chinkara",
            "Elephant","Gharial","Gorilla",
            "Hangul","Hyena","Jackal",
            "Leopard","Lion","Lynx",
            "Nilgai","Orangutan","Rhino",
            "Snowleopard","Tiger","Wildebeest",
            "Zebra","Wolf",
            "Panther","Puma","Fox"};
    ArrayList<Integer> imageArr=new ArrayList<>(Arrays.asList(a));
    ArrayList<Integer> imageArr2=new ArrayList<>(Arrays.asList(a1));
    ArrayList<String> nameArr=new ArrayList<>(Arrays.asList(b));
    ArrayList<Button> buttonArrayList=new ArrayList<>();
    ArrayList<ImageView> imageViewArrayList=new ArrayList<>();
    private int i,j;
    public int c;
    public int lives=4;
    public int streak=0,maxstreak=0,correct=0,timebonus=0;

    CountDownTimer ct;
    private String gameType="animals";




    public Frag2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag2.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag2 newInstance(String param1, String param2) {
        Frag2 fragment = new Frag2();
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
        View view= inflater.inflate(R.layout.fragment_frag2, container, false);
        ((DrawerLocker)getActivity()).setDrawerEnabled(false);
        B1=view.findViewById(R.id.Ba1);
        B2=view.findViewById(R.id.Ba2);
        B3=view.findViewById(R.id.Ba3);
        B4=view.findViewById(R.id.Ba4);
        heart1=view.findViewById(R.id.hearta1);
        heart2=view.findViewById(R.id.hearta2);
        heart3=view.findViewById(R.id.hearta3);
        heart4=view.findViewById(R.id.hearta4);
        heart5=view.findViewById(R.id.hearta5);
        imageButton=view.findViewById(R.id.nexta);
        imageView=view.findViewById(R.id.imga);
        timer=view.findViewById(R.id.timera);
        B1.setOnClickListener(this);
        B2.setOnClickListener(this);
        B3.setOnClickListener(this);
        B4.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        imageButton.setVisibility(View.INVISIBLE);
        imageButton.setClickable(false);
        buttonArrayList.add(B1);
        buttonArrayList.add(B2);
        buttonArrayList.add(B3);
        buttonArrayList.add(B4);
        imageViewArrayList.add(heart1);
        imageViewArrayList.add(heart2);
        imageViewArrayList.add(heart3);
        imageViewArrayList.add(heart4);
        imageViewArrayList.add(heart5);
        //first image
        int limit=imageArr.size();
        Random random=new Random();
        i=random.nextInt(limit);
        j=random.nextInt(4);
        imageView.setImageResource(imageArr2.get(i));
        buttonArrayList.get(j).setText(nameArr.get(i));
        int l=1;
        for (Button k:buttonArrayList)
        {
            k.setBackgroundColor(Color.rgb(33,33,33));
            k.setTextColor(Color.WHITE);
            if(k!=buttonArrayList.get(j))
            {
                k.setText(nameArr.get((i+l)%(limit+3)));
                l++;
            }

        }
        c=5;
        ct=new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(c));
                c--;
            }

            @Override
            public void onFinish() {
                timer.setText(String.valueOf(0));
                streak=0;
                imageView.setImageResource(imageArr.get(i));
                if(lives!=0) {
                    imageViewArrayList.get(lives).setVisibility(View.INVISIBLE);
                    lives--;
                    buttonArrayList.get(j).setBackgroundColor(Color.rgb(0,200,83));
                    buttonArrayList.get(j).setTextColor(Color.BLACK);
                    imageArr.remove(i);
                    imageArr2.remove(i);
                    nameArr.remove(i);
                    for (Button k:buttonArrayList)
                    {
                        k.setClickable(false);
                    }
                    imageButton.setVisibility(View.VISIBLE);
                    imageButton.setClickable(true);
                    Random random=new Random();
                    if(!imageArr.isEmpty())
                        i=random.nextInt(imageArr.size());
                    j=random.nextInt(4);
                }
                else{
                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    //fragmentTransaction.addToBackStack(null);
                    DialogFragment scoreDialog=new ScoreDialog();
                    Bundle data=getArguments();
                    data.putString("Title","Game Over");
                    data.putString("Data1","Maxstreak "+maxstreak+
                            "x 3\nCorrect "+correct+
                            "x 5\nTime Bonus "+timebonus);
                    data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus));
                    data.putString("Type",gameType);
                    Realm realm=Realm.getDefaultInstance();
                    realm.beginTransaction();
                    String name=data.getString("Playername");
                    Player exist=realm.where(Player.class).equalTo("id",name+"animals").findFirst();
                    if(exist==null) {
                        Player player = new Player();
                        player.setId(name+"animals");
                        player.setName(name);
                        player.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                        player.setGametype("animals");
                        player.setBestScore((maxstreak * 3) + (correct * 5) + timebonus);
                        data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus)+"\nNew Personal Best Score!!!");
                        realm.copyToRealm(player);
                    }
                    else
                    {
                        exist.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                        if(((maxstreak * 3) + (correct * 5) + timebonus)>exist.getBestScore()) {
                            exist.setBestScore((maxstreak * 3) + (correct * 5) + timebonus);
                            data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus)+"\nNew Personal Best Score!!!");
                        }
                    }
                    realm.commitTransaction();
                    realm.close();


                    scoreDialog.setArguments(data);
                    scoreDialog.show(fragmentTransaction,"dialog");
                    getFragmentManager().popBackStackImmediate();}

            }
        };
        ct.start();
        return view;
    }
    public void onClick(View view)
    {
        int bid=view.getId();
        int limit=imageArr.size();
        if(maxstreak<streak)
            maxstreak=streak;
        if(bid==imageButton.getId())
        {
            if(imageArr.isEmpty())
            {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                //fragmentTransaction.addToBackStack(null);
                DialogFragment scoreDialog=new ScoreDialog();
                Bundle data=getArguments();
                data.putString("Title","All levels completed");
                data.putString("Data1","Maxstreak "+maxstreak+
                        "x 3\nCorrect "+correct+
                        "x 5\nTime Bonus "+timebonus);
                data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus));
                data.putString("Type",gameType);

                Realm realm=Realm.getDefaultInstance();
                realm.beginTransaction();
                String name=data.getString("Playername");
                Player exist=realm.where(Player.class).equalTo("id",name+"animals").findFirst();
                if(exist==null) {
                    Player player = new Player();
                    player.setId(name+"animals");
                    player.setName(name);
                    player.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                    player.setGametype("animals");
                    player.setBestScore((maxstreak * 3) + (correct * 5) + timebonus);
                    data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus)+"\nNew Personal Best Score!!!");
                    realm.copyToRealm(player);
                }
                else
                {
                    exist.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                    if(((maxstreak * 3) + (correct * 5) + timebonus)>exist.getBestScore()) {
                        exist.setBestScore((maxstreak * 3) + (correct * 5) + timebonus);
                        data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus)+"\nNew Personal Best Score!!!");
                    }
                }

                realm.commitTransaction();
                realm.close();
                scoreDialog.setArguments(data);
                scoreDialog.show(fragmentTransaction,"dialog");
                getFragmentManager().popBackStackImmediate();
            }
            else
            { c=5;
                imageView.setImageResource(imageArr2.get(i));
                buttonArrayList.get(j).setText(nameArr.get(i));
                int l=1;
                for (Button k:buttonArrayList)
                {
                    k.setBackgroundColor(Color.rgb(33,33,33));
                    k.setTextColor(Color.WHITE);
                    k.setClickable(true);
                    if(k!=buttonArrayList.get(j))
                    {
                        k.setText(nameArr.get((i+l)%(limit+3)));
                        l++;
                    }

                }
                ct.start();
            }
            imageButton.setVisibility(View.INVISIBLE);
            imageButton.setClickable(false);
        }
        else {
            imageView.setImageResource(imageArr.get(i));

            if(bid!=buttonArrayList.get(j).getId())
            {

                streak=0;

                view.findViewById(bid).setBackgroundColor(Color.rgb(213,0,0));
                ((Button)view.findViewById(bid)).setTextColor(Color.BLACK);
                if(lives!=0) {
                    imageViewArrayList.get(lives).setVisibility(View.INVISIBLE);
                    lives--;
                }
                else
                {

                    FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                    //fragmentTransaction.addToBackStack(null);
                    DialogFragment scoreDialog=new ScoreDialog();
                    Bundle data=getArguments();
                    data.putString("Title","Game Over");
                    data.putString("Data1","Maxstreak "+maxstreak+
                            "x 3\nCorrect "+correct+
                            "x 5\nTime Bonus "+timebonus);
                    data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus));
                    data.putString("Type",gameType);
                    Realm realm=Realm.getDefaultInstance();
                    realm.beginTransaction();
                    String name=data.getString("Playername");
                    Player exist=realm.where(Player.class).equalTo("id",name+"animals").findFirst();
                    if(exist==null) {
                        Player player = new Player();
                        player.setId(name+"animals");
                        player.setName(name);
                        player.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                        player.setGametype("animals");
                        player.setBestScore((maxstreak * 3) + (correct * 5) + timebonus);
                        data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus)+"\nNew Personal Best Score!!!");
                        realm.copyToRealm(player);
                    }
                    else
                    {
                        exist.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                        if(((maxstreak * 3) + (correct * 5) + timebonus)>exist.getBestScore()) {
                            exist.setBestScore((maxstreak * 3) + (correct * 5) + timebonus);
                            data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus)+"\nNew Personal Best Score!!!");
                        }
                    }
                    realm.commitTransaction();
                    realm.close();
                    scoreDialog.setArguments(data);
                    scoreDialog.show(fragmentTransaction,"dialog");
                    getFragmentManager().popBackStackImmediate();

                }
            }
            else
            {

                correct++;
                streak++;
                timebonus+=(c+1)*10;
            }
            buttonArrayList.get(j).setBackgroundColor(Color.rgb(0,200,83));
            buttonArrayList.get(j).setTextColor(Color.BLACK);
            imageArr.remove(i);
            imageArr2.remove(i);
            nameArr.remove(i);
            for (Button k:buttonArrayList)
            {
                k.setClickable(false);
            }
            imageButton.setVisibility(View.VISIBLE);
            imageButton.setClickable(true);
            Random random=new Random();
            if(!imageArr.isEmpty())
                i=random.nextInt(imageArr.size());
            j=random.nextInt(4);
            ct.cancel();

        }
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
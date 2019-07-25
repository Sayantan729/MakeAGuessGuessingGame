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
 * Use the {@link Frag3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag3 extends Fragment implements View.OnClickListener {
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
    private Integer a[]={R.drawable.bluebell,R.drawable.cherryblossom,R.drawable.chrysanthemum,
            R.drawable.daffodil,R.drawable.dahlia, R.drawable.geranium,
            R.drawable.heather,R.drawable.hibiscus,R.drawable.iris,
            R.drawable.jasmine,R.drawable.lavender,R.drawable.lily,
            R.drawable.lotus,R.drawable.magnolia,R.drawable.marigold,
            R.drawable.orchid,R.drawable.peony,R.drawable.petunia,
            R.drawable.poppy,R.drawable.rose,R.drawable.snowdrop,
            R.drawable.sunflower,R.drawable.tulip,R.drawable.violet,R.drawable.waterlily};
    private Integer a1[]={R.drawable.bluebell2,R.drawable.cherryblossom2,R.drawable.chrysanthemum2,
            R.drawable.daffodil2,R.drawable.dahlia2, R.drawable.geranium2,
            R.drawable.heather2,R.drawable.hibiscus2,R.drawable.iris2,
            R.drawable.jasmine2,R.drawable.lavender2,R.drawable.lily2,
            R.drawable.lotus2,R.drawable.magnolia2,R.drawable.marigold2,
            R.drawable.orchid2,R.drawable.peony2,R.drawable.petunia2,
            R.drawable.poppy2,R.drawable.rose2,R.drawable.snowdrop2,
            R.drawable.sunflower2,R.drawable.tulip2,R.drawable.violet2,R.drawable.waterlily2};
    private String b[]={"Bluebell","Cherryblossom","Chrysanthemum",
            "Daffodil","Dahlia", "Geranium",
            "Heather","Hibiscus","Iris",
            "Jasmine","Lavender","Lily",
            "Lotus","Magnolia","Marigold",
            "Orchid","Peony","Petunia",
            "Poppy","Rose","Snowdrop",
            "Sunflower", "Tulip","Violet","Waterlily",
            "Dandelion","Daisy","Hyacinth"};
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
    private String gameType="flowers";




    public Frag3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag3.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag3 newInstance(String param1, String param2) {
        Frag3 fragment = new Frag3();
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
        View view= inflater.inflate(R.layout.fragment_frag3, container, false);
        ((DrawerLocker)getActivity()).setDrawerEnabled(false);
        B1=view.findViewById(R.id.Bf1);
        B2=view.findViewById(R.id.Bf2);
        B3=view.findViewById(R.id.Bf3);
        B4=view.findViewById(R.id.Bf4);
        heart1=view.findViewById(R.id.heartf1);
        heart2=view.findViewById(R.id.heartf2);
        heart3=view.findViewById(R.id.heartf3);
        heart4=view.findViewById(R.id.heartf4);
        heart5=view.findViewById(R.id.heartf5);
        imageButton=view.findViewById(R.id.nextf);
        imageView=view.findViewById(R.id.imgb);
        timer=view.findViewById(R.id.timerf);
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
                    Player exist=realm.where(Player.class).equalTo("id",name+"flowers").findFirst();
                    if(exist==null) {
                        Player player = new Player();
                        player.setId(name+"flowers");
                        player.setName(name);
                        player.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                        player.setGametype("flowers");
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
                data.putString("Data1","Maxstreak "+maxstreak+
                        "x 3\nCorrect "+correct+
                        "x 5\nTime Bonus "+timebonus);
                data.putString("Data2","Score "+((maxstreak*3)+(correct*5)+timebonus));
                data.putString("Type",gameType);
                Realm realm=Realm.getDefaultInstance();
                realm.beginTransaction();
                String name=data.getString("Playername");
                Player exist=realm.where(Player.class).equalTo("id",name+"flowers").findFirst();
                if(exist==null) {
                    Player player = new Player();
                    player.setId(name+"flowers");
                    player.setName(name);
                    player.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                    player.setGametype("flowers");
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
                data.putString("Title","All levels completed");
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
                    Player exist=realm.where(Player.class).equalTo("id",name+"flowers").findFirst();
                    if(exist==null) {
                        Player player = new Player();
                        player.setId(name+"flowers");
                        player.setName(name);
                        player.setScore((maxstreak * 3) + (correct * 5) + timebonus);
                        player.setGametype("flowers");
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
    /*

     */

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

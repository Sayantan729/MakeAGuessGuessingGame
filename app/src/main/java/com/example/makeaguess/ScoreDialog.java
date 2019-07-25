package com.example.makeaguess;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class ScoreDialog extends DialogFragment implements View.OnClickListener {
    private TextView textView,textView2,textView3;
    private Dialog dl;
    private Button button1,button2;
    private Bundle data;
    public ScoreDialog()
    {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.score_dialog,container,false);
        textView=view.findViewById(R.id.scoretext);
        textView2=view.findViewById(R.id.scoretext1);
        textView3=view.findViewById(R.id.heading);
        button1=view.findViewById(R.id.scorego);
        button2=view.findViewById(R.id.scoreok);
        dl=getDialog();
        data=getArguments();
        textView3.setText(data.getString("Title"));

        textView.setText(data.getString("Data1"));
        textView2.setText(data.getString("Data2"));
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.scorego){
            FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
            Frag4 frag4=new Frag4();
            frag4.setArguments(data);
            fragmentTransaction.replace(R.id.detailscontainer,frag4,"Scoreboard");
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack("Frag4");
            fragmentTransaction.commit();}
        onDismiss(dl);//dismiss();
    }


}

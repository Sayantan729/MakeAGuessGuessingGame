package com.example.makeaguess;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

public class ConfirmDialog extends DialogFragment implements View.OnClickListener {
    private Button cont,back;


    public ConfirmDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirmfragment, container, false);
        cont=view.findViewById(R.id.cont);
        back=view.findViewById(R.id.back);
        back.setOnClickListener(this);
        cont.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        switch (view.getId())
        {
            case R.id.back:

                DialogFragment loginDialog=new LoginDialog();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                loginDialog.show(fragmentTransaction,null);
                dismiss();
                break;
            case R.id.cont:
                Frag1 frag1=new Frag1();
                frag1.setArguments(getArguments());
                fragmentTransaction.replace(R.id.detailscontainer,frag1);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.addToBackStack("Frag1");
                fragmentTransaction.commit();
                ((MainActivity)getActivity()).setName(getArguments().getString("Playername"));
                dismiss();
        }
    }
}
package com.example.makeaguess;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import io.realm.Realm;

public class LoginDialog extends DialogFragment implements View.OnClickListener {
    private EditText editText;
    private Button button;
    public LoginDialog(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.logindialog,container,false);
        editText=view.findViewById(R.id.entername);
        button=view.findViewById(R.id.confirm);
        button.setOnClickListener(this);
        editText.requestFocus();
        editText.setHintTextColor(Color.rgb(245,0,87));
        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.confirm) {
            if (!editText.getText().toString().replaceAll(" ", "").equals("")) {
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putString("Playername", editText.getText().toString());
                Realm realm=Realm.getDefaultInstance();
                Player player=realm.where(Player.class).equalTo("name",editText.getText().toString()).findFirst();
                if(player!=null){

                    DialogFragment confirmDialog=new ConfirmDialog();
                    confirmDialog.setArguments(bundle);
                    confirmDialog.setCancelable(false);
                    confirmDialog.show(fragmentTransaction,null);}
                else
                {
                    Frag1 frag1=new Frag1();
                    frag1.setArguments(bundle);
                    fragmentTransaction.replace(R.id.detailscontainer,frag1);
                    fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    fragmentTransaction.addToBackStack("Frag1");
                    fragmentTransaction.commit();
                    ((MainActivity)getActivity()).setName(editText.getText().toString());
                }
                dismiss();

            }
        }


    }

}


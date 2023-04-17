package com.example.projetopager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Primeiro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Primeiro extends Fragment {
    Button botaosomar, botaosubtrair;
    Button botaolimpar;
    EditText horaInicial, horaFinal, minutoInicial, minutoFinal;
    TextView resultadoHora, resultadoMinuto;
    int horaI;
    int horaF;
    int minutoI;
    int minutoF;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Primeiro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Primeiro.
     */
    // TODO: Rename and change types and number of parameters
    public static Primeiro newInstance(String param1, String param2) {
        Primeiro fragment = new Primeiro();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_primeiro, container, false);
        horaInicial = v.findViewById(R.id.horaInicial);
        horaFinal = v.findViewById(R.id.horaFinal);
        minutoInicial = v.findViewById(R.id.minutoInicial);
        minutoFinal = v.findViewById(R.id.minutoFinal);
        resultadoHora = v.findViewById(R.id.resultadoHora);
        resultadoMinuto = v.findViewById(R.id.resultadoMinuto);
        botaosomar = v. findViewById(R.id.botaosomar);
        botaosubtrair = v. findViewById(R.id.botaosubtrair);
        botaolimpar = v. findViewById(R.id.botaolimpar);
        botaosomar.setOnClickListener(click ->somar());
        botaosubtrair.setOnClickListener(click ->subtrair());
        botaolimpar.setOnClickListener(click ->limpar());
        return v;
    }
    @SuppressLint("SetTextI18n")
    public void somar() {
        verificaCampo();

        int rH = horaI + horaF;
        int rM = minutoI + minutoF;

        while (rM > 59) {
            rM -= 60;
            rH++;
        }

        resultadoHora.setText(rH + "");
        resultadoMinuto.setText(rM + "");

    }

    public void subtrair() {
        String mensagem = "Subtração realizada com sucesso!!!";

        verificaCampo();


        while (horaI > 0) {
            horaI--;
            minutoI += 60;
        }
        while (horaF > 0) {
            horaF--;
            minutoF += 60;
        }
        int rH = horaI - horaF;
        int rM = minutoI - minutoF;

        if (minutoF > minutoI) {
            rM = minutoF - minutoI;
        }
        if (horaF > horaI) {
            rH = horaF - horaI;
        }

        while (rM < 0) {
            rM += 60;
            rH--;
        }

        while (rM > 59) {
            rM -= 60;
            rH++;
        }

        while (minutoF > 59) {
            minutoF -= 60;
            horaF++;
        }

        while (rM > 59) {
            rM -= 60;
            horaF++;
        }


        resultadoHora.setText(rH + " ");
        resultadoMinuto.setText(rM + " ");

    }

    public void limpar() {
        String mensagem = "Os campos ja estao limpos!";

        if (horaInicial.getText().length() == 0
                || horaFinal.getText().length() == 0
                || minutoInicial.getText().length() == 0
                || minutoFinal.getText().length() == 0) {
            Toast.makeText(getContext(), mensagem, Toast.LENGTH_SHORT).show();
        } else {
            horaInicial.setText(null);
            horaFinal.setText(null);
            minutoInicial.setText(null);
            minutoFinal.setText(null);
            resultadoHora.setText(null);
            resultadoMinuto.setText(null);
        }
    }

    public void verificaCampo() {

        try {
            horaI = Integer.parseInt(horaInicial.getText().toString());
            horaF = Integer.parseInt(horaFinal.getText().toString());
            minutoI = Integer.parseInt(minutoInicial.getText().toString());
            minutoF = Integer.parseInt(minutoFinal.getText().toString());
        } catch (Exception e) {
            horaI = 0;
            horaF = 0;
            minutoI = 0;
            minutoF = 0;
            Toast.makeText(getContext(), "Preencha os campos corretamente!", Toast.LENGTH_SHORT).show();
        }
    }
}
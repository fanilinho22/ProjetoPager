package com.example.projetopager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Terceiro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Terceiro extends Fragment {
    ImageView imager;
    Button button3, button4;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Terceiro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Terceiro.
     */
    // TODO: Rename and change types and number of parameters
    public static Terceiro newInstance(String param1, String param2) {
        Terceiro fragment = new Terceiro();
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
        View v = inflater.inflate(R.layout.fragment_terceiro, container, false);
        imager = v.findViewById(R.id.imager);
        button3 = v.findViewById(R.id.button3);
        button4 = v.findViewById(R.id.button4);

        button3.setOnClickListener(click -> {
            tirarFoto();
        });

        button4.setOnClickListener(click -> {
            pegarFoto();
        });
        return v;
    }

    public void tirarFoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        abrirCamera.launch(i);
    }

    public void pegarFoto() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        abrirGaleria.launch(i);
    }

    ActivityResultLauncher<Intent> abrirCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            Bundle dado = data.getExtras();
            Bitmap imagem = (Bitmap) dado.get("data");
            imager.setImageBitmap(imagem);
        }
    });

    ActivityResultLauncher<Intent> abrirGaleria = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();
            Uri imagemSelecionada = data.getData();
            String[] caminho = {MediaStore.Images.Media.DATA}; //Caminho onde estão minhas imagens/Encontrare endereço da memória
            Cursor c = getActivity().getContentResolver().query(imagemSelecionada, caminho, null, null, null); //.query = comandos do sql
            c.moveToFirst();
            int coluna = c.getColumnIndex(caminho[0]);
            String caminhoFisico = c.getString(coluna);
            c.close();
            Bitmap imagem = (BitmapFactory.decodeFile(caminhoFisico));
            imager.setImageBitmap(imagem);
        }
    });

}
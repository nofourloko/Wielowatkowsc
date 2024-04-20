package com.example.wielowatkowosc10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
/**
 * Fragment3 reprezentuje fragment aplikacji odpowiedzialny za wybór jednostek czasu dla stopera i odliczania.
 * @author Jakub Miśta 4F
 */
public class Fragment3 extends Fragment {
    private RadioGroup radio_odliczanie, radio_stoper;

    /**
     * Metoda wywoływana przy tworzeniu fragmentu
     * @param savedInstanceState Zapisany stan fragmentu, jeśli jest.
     * @author Jakub Miśta 4F
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Metoda wywoływana w celu utworzenia interfejsu użytkownika dla fragmentu.
     * @param inflater Inflater używany do inflate'a widoku.
     * @param container Kontener widoku nadrzędnego, jeśli istnieje.
     * @param savedInstanceState Zapisany stan fragmentu, jeśli jest.
     * @return Utworzony widok dla fragmentu.
     * @author Jakub Miśta 4F
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        DataViewModel dataVM = new ViewModelProvider(requireActivity()).get(DataViewModel.class);
        radio_odliczanie = view.findViewById(R.id.radio_odliczanie);
        radio_stoper = view.findViewById(R.id.radio_stoper);

        radio_odliczanie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = view.findViewById(i);
                dataVM.setOdliczanie_jednostka_czasu(String.valueOf(radioButton.getText()));
            }
        });

        radio_stoper.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = view.findViewById(i);
                dataVM.setStoper_jednostka_czasu(String.valueOf(radioButton.getText()));
            }
        });
        return view;
    }
}
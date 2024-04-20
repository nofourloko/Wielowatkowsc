package com.example.wielowatkowosc10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment2 reprezentuje fragment aplikacji odpowiedzialny za wyświetlanie wartości stopera i odliczania.
 * @author Jakub Miśta 4F
 */
public class Fragment2 extends Fragment {
    private TextView text_view_stoper, text_view_odliczanie;
    private DataViewModel dataVM;

    /**
     * Metoda wywoływana przy tworzeniu fragmentu.
     * @param savedInstanceState Zapisany stan fragmentu, jeśli jest.
     * @author Jakub Miśta 4F
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Metoda wywoływana w celu utworzenia interfejsu użytkownika dla fragmentu.
     *
     * @param inflater Inflater używany do inflate'a widoku.
     * @param container Kontener widoku nadrzędnego, jeśli istnieje.
     * @param savedInstanceState Zapisany stan fragmentu, jeśli jest.
     * @return Utworzony widok dla fragmentu.
     * @author Jakub Miśta 4F
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        text_view_stoper = view.findViewById(R.id.text_view_stoper);
        text_view_odliczanie = view.findViewById(R.id.text_view_odliczanie);

        DataViewModel dataVM = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        dataVM.getStoperWartosc().observe(getViewLifecycleOwner(), v -> {
            text_view_stoper.setText(String.valueOf(v));
        });

        dataVM.getOdliczanieWartosc().observe(getViewLifecycleOwner(), v -> {
            text_view_odliczanie.setText(String.valueOf(v));
        });

        return view;
    }
}

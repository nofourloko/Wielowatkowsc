package com.example.wielowatkowosc10;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Fragment4 reprezentuje fragment aplikacji odpowiedzialny za wyświetlanie odstępów czasu dla stopera i odliczania.
 */
public class Fragment4 extends Fragment {
    private TextView odstepy_czasu_stoper, odstepy_czasu_odliczanie;
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
        View view = inflater.inflate(R.layout.fragment_4, container, false);
        odstepy_czasu_stoper = view.findViewById(R.id.odstepy_czasu_stoper);
        odstepy_czasu_odliczanie = view.findViewById(R.id.odstepy_czasu_odliczanie);

        DataViewModel dataVM = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        dataVM.getStoperJednostaCzasu().observe(getViewLifecycleOwner(), param -> {
            String wartosc = jednostkaDoNazwy(param);
            odstepy_czasu_stoper.setText("Odstepy czasu dla interwalu: " +wartosc);
        });

        dataVM.getOdliczanieJenostkaCzasu().observe(getViewLifecycleOwner(), param -> {
            String wartosc = jednostkaDoNazwy(param);
            odstepy_czasu_odliczanie.setText("Odstepy czasu dla odliczania: " + wartosc);
        });

        return view;
    }
    /**
     * Metoda konwertująca jednostkę czasu na odpowiednią nazwę.
     *
     * @param param Jednostka czasu do konwersji.
     * @return Nazwa jednostki czasu.
     * @author Jakub Miśta 4F
     */
    public String jednostkaDoNazwy(int param){
        String wartoscKonwertowana = "";
        switch (param){
            case 1000:
                wartoscKonwertowana = "Sekundy";
                break;
            case 1000 * 60:
                wartoscKonwertowana = "Minuty";
                break;
            case 1:
                wartoscKonwertowana = "Milisekundy";
                break;
        }

        return  wartoscKonwertowana;
    }
}
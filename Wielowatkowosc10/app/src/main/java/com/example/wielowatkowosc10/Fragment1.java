package com.example.wielowatkowosc10;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
/**
 * Fragment1 reprezentuje fragment aplikacji odpowiedzialny za obsługę stopera i odliczania.
 */
public class Fragment1 extends Fragment {
    private Button stoper_start, stoper_stop, start_odliczanie, stop_odliczanie;
    private EditText editTextOdliczanie;
    private Thread pierwszy_watek, drugi_watek;
    private int wartosc_stoper;
    private int wartosc_odliczanie;
    private int jednostkaCzasuStoper;
    private int jednostkaCzasuOdliczanie;
    private int wartosc_startowa;
    private DataViewModel dataVM;
    private boolean czy_dziala_pierwszy_watek = true;
    private boolean czy_dziala_drugi_watek = true;
    /**
     * Metoda wywoływana w celu utworzenia interfejsu użytkownika dla fragmentu.
     * Metoda ta również inicjuje obserwowanie ViewModelu w celu monitorowania zmian wartości jednostek czasu.
     * Dodatkowo, ustawia słuchaczy dla przycisków oraz
     * implementuje logikę obsługi tych zdarzeń.
     *
     * @param inflater Inflater używany do inflate'a widoku.
     * @param container Kontener widoku nadrzędnego, jeśli istnieje.
     * @param savedInstanceState Zapisany stan fragmentu, jeśli jest.
     * @return Utworzony widok dla fragmentu.
     * @author Jakub Miśta 4F
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        dataVM = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        // Inicjalizacja widoków (przycisków, pól tekstowych itp.)
        editTextOdliczanie = view.findViewById(R.id.editTextOdliczanie);
        stoper_start = view.findViewById(R.id.stoper_start);
        stoper_stop = view.findViewById(R.id.stoper_stop);
        start_odliczanie = view.findViewById(R.id.start_odliczanie);
        stop_odliczanie = view.findViewById(R.id.stop_odliczanie);

        stoper_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopStoper();
            }
        });

        stop_odliczanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopOdliczanie();
            }
        });

        stoper_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStoper();
            }
        });

        start_odliczanie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startOdliczanie();
            }
        });

        editTextOdliczanie.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                wartosc_odliczanie = Integer.parseInt(charSequence.toString());
                wartosc_startowa = wartosc_odliczanie;
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        obserwujViewModel();

        return view;
    }
    /**
     * Metoda obserwująca zmiany w ViewModelu.
     * @author Jakub Miśta 4F
     */
    private void obserwujViewModel() {
        dataVM.getOdliczanieJenostkaCzasu().observe(getViewLifecycleOwner(), param -> {
            jednostkaCzasuOdliczanie = param;
        });

        dataVM.getStoperJednostaCzasu().observe(getViewLifecycleOwner(), param -> {
            jednostkaCzasuStoper = param;
        });
    }
    /**
     * Metoda rozpoczynająca działanie stopera.
     *
     * Ta metoda jest odpowiedzialna za rozpoczęcie działania stopera. Sprawdza, czy
     * jednostka czasu stopera została ustawiona poprawnie. Następnie tworzy nowy wątek
     * (`pierwszy_watek`), w którym stoper jest uruchamiany. Wątek ten inkrementuje
     * wartość stopera i aktualizuje interfejs użytkownika co określony czas, odpowiadający
     * jednostce czasu stopera
     * @author Jakub Miśta 4F
     */
    private void startStoper() {
        if (jednostkaCzasuStoper < 1) {
            Toast.makeText(getActivity(), "Proszę podać jednostkę czasu", Toast.LENGTH_SHORT).show();
            return;
        }

        pierwszy_watek = new Thread() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stoper_start.setEnabled(false);
                    }
                });
                czy_dziala_pierwszy_watek = true;
                while (czy_dziala_pierwszy_watek) {
                    wartosc_stoper++;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dataVM.setStoperWartosc(wartosc_stoper);
                        }
                    });

                    try {
                        Thread.sleep(jednostkaCzasuStoper);
                    } catch (InterruptedException v) {
                        System.out.println(v);
                    }
                }
            }
        };

        pierwszy_watek.start();
    }
    /**
     * Zatrzymuje liczenie czasu przez stoper.
     * @author Jakub Miśta 4F
     */
    private void stopStoper() {
        czy_dziala_pierwszy_watek = false;
        wartosc_stoper = 0;
        stoper_start.setEnabled(true);
        dataVM.setStoperWartosc(wartosc_stoper);
    }
    /**
     * Rozpoczyna odliczanie.
     *
     * Ta metoda inicjuje proces odliczania. Najpierw sprawdza, czy jednostka czasu
     * odliczania oraz wartość startowa zostały ustawione. Następnie tworzy nowy wątek
     * (`drugi_watek`), w którym odliczanie jest uruchamiane. Wątek ten dekrementuje
     * wartość odliczania i aktualizuje interfejs użytkownika co określony czas,
     * odpowiadający jednostce czasu odliczania, dopóki wartość odliczania nie osiągnie -1.
     * @author Jakub Miśta 4F
     */
    private void startOdliczanie() {
        if (jednostkaCzasuOdliczanie < 1 && wartosc_startowa < 1) {
            Toast.makeText(getActivity(), "Proszę podać jednostkę czasu oraz wartość", Toast.LENGTH_SHORT).show();
            return;
        }

        wartosc_odliczanie = wartosc_startowa;

        drugi_watek = new Thread() {
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        start_odliczanie.setEnabled(false);
                    }
                });
                czy_dziala_drugi_watek = true;
                while (czy_dziala_drugi_watek) {
                    if (wartosc_odliczanie == -1) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                start_odliczanie.setEnabled(true);
                            }
                        });
                        czy_dziala_drugi_watek = false;
                    } else {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataVM.setOdliczanie_wartosc(wartosc_odliczanie);
                            }
                        });
                        try {
                            Thread.sleep(jednostkaCzasuOdliczanie);
                        } catch (InterruptedException v) {
                            System.out.println(v);
                        }
                        wartosc_odliczanie--;
                    }
                }
            }
        };

        drugi_watek.start();
    }
    /**
     * Zatrzymuje odliczanie.
     * @author Jakub Miśta 4F
     */
    private void stopOdliczanie() {
        czy_dziala_drugi_watek = false;
        wartosc_odliczanie = wartosc_startowa;
        start_odliczanie.setEnabled(true);
        dataVM.setOdliczanie_wartosc(wartosc_odliczanie);
    }
}

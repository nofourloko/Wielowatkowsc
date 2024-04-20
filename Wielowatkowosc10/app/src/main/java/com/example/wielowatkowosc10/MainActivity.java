package com.example.wielowatkowosc10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 1.Uruchamianie i Zatrzymywanie Stopera:
     * Aby uruchomić stoper, naciśnij przycisk "Start Stoper".
     * Aby zatrzymać stoper, naciśnij przycisk "Stop Stoper".
 * 2.Uruchamianie i Zatrzymywanie Odliczania:
     * Aby uruchomić odliczanie, wprowadź wartość początkową w polu tekstowym "Odliczanie" i naciśnij przycisk "Start Odliczanie".
     * Aby zatrzymać odliczanie, naciśnij przycisk "Stop Odliczanie".
 * 3.Określanie Jednostki Czasu:
     * Przed uruchomieniem stopera lub odliczania upewnij się, że określiłeś jednostkę czasu dla każdej operacji.
 * @author Jakub Miśta 4F
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


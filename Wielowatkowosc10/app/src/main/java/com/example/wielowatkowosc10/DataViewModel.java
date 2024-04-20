package com.example.wielowatkowosc10;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    private final MutableLiveData<Integer> stoper_wartosc = new MutableLiveData<>();
    private final MutableLiveData<Integer> odliczanie_wartosc = new MutableLiveData<>();
    private final MutableLiveData<Integer> odliczanie_jednostka_czasu = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> stoper_jednostka_czasu = new MutableLiveData<>(0);
    public LiveData<Integer> getStoperJednostaCzasu() {
        return stoper_jednostka_czasu;
    }

    public void setStoper_jednostka_czasu(String param){
        int jednostka = przeliczenieStringaNaJednostkeCzasu(param);
        stoper_jednostka_czasu.setValue(jednostka);
    }

    public LiveData<Integer> getOdliczanieJenostkaCzasu() {
        return odliczanie_jednostka_czasu;
    }

    public void setOdliczanie_jednostka_czasu(String param){
        int jednostka = przeliczenieStringaNaJednostkeCzasu(param);
        odliczanie_jednostka_czasu.setValue(jednostka);
    }
    public LiveData<Integer> getOdliczanieWartosc() {
        return odliczanie_wartosc;
    }

    public void  setOdliczanie_wartosc(int param){odliczanie_wartosc.setValue(param);}
    public LiveData<Integer> getStoperWartosc() {
        return stoper_wartosc;
    }

    public void setStoperWartosc(int param) {
        stoper_wartosc.setValue(param);
    }

    private int przeliczenieStringaNaJednostkeCzasu(String param){
        int result = 1;
        switch (param){
            case "Minuty":
                result = 60 * 1000;
                break;
            case "Sekundy":
                result = 1000;
                break;
        }
        return result;
    }
}

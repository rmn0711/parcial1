package com.parcialDNT.parcialDNT;

import android.os.Parcel;
import android.os.Parcelable;

public class Contacto implements Parcelable {
    private String nombre;
    private String telefono;
    private String grupo;

    public Contacto(String nombre, String telefono, String grupo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.grupo = grupo;
    }

    protected Contacto(Parcel in) {
        nombre = in.readString();
        telefono = in.readString();
        grupo = in.readString();
    }

    public static final Creator<Contacto> CREATOR = new Creator<Contacto>() {
        @Override
        public Contacto createFromParcel(Parcel in) {
            return new Contacto(in);
        }

        @Override
        public Contacto[] newArray(int size) {
            return new Contacto[size];
        }
    };

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = nombre;
    }

    public String getPhone() {
        return telefono;
    }

    public void setPhone(String phone) {
        this.telefono = phone;
    }

    public String getGroup() {
        return grupo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(telefono);
        dest.writeString(grupo);
    }
}

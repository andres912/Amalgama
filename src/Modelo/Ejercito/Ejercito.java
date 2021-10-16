package Modelo.Ejercito;

import Modelo.Unidad.Unidad;
import Modelo.Unidad.UnidadArquero;
import Modelo.Unidad.UnidadCaballero;
import Modelo.Unidad.UnidadPiquero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.min;

public abstract class Ejercito {
    protected int oro_disponible = 1000;
    protected List<UnidadPiquero> piqueros = new ArrayList<UnidadPiquero>();
    protected List<UnidadArquero> arqueros  = new ArrayList<UnidadArquero>();
    protected List<UnidadCaballero> caballeros  = new ArrayList<UnidadCaballero>();

    public int cantidadDeOroDisponible() { return oro_disponible;}

    protected void agregarPiqueros(int cantidad) {
        for (int i = 0; i < cantidad; ++i) {
            piqueros.add(new UnidadPiquero());
        }
    }
    protected void agregarArqueros(int cantidad) {
        for (int i = 0; i < cantidad; ++i) {
            arqueros.add(new UnidadArquero());
        }
    }
    protected void agregarCaballeros(int cantidad) {
        for (int i = 0; i < cantidad; ++i) {
            caballeros.add(new UnidadCaballero());
        }
    }

    public int fuerza() {
        ArrayList<Unidad> unidades = unidades();
        int fuerza = 0;
        for (Unidad unidad : unidades) {
            fuerza += unidad.fuerza();
        }
        return fuerza;
    }

    protected ArrayList<Unidad> unidades() {
        ArrayList<Unidad> unidades = new ArrayList<Unidad>();
        unidades.addAll(piqueros);
        unidades.addAll(arqueros);
        unidades.addAll(caballeros);
        return unidades;
    }

    public void afrontarVictoria() {
        oro_disponible += 100;
    }

    public void afrontarDerrota() {
        int unidades_a_eliminar = 2;
        int caballeros_eliminados = eliminarCaballeros();
        unidades_a_eliminar -= caballeros_eliminados;
        if (unidades_a_eliminar == 0) return;
        int arqueros_eliminados = eliminarArqueros(unidades_a_eliminar);
        unidades_a_eliminar -= arqueros_eliminados;
        if (unidades_a_eliminar == 0) return;
        eliminarPiqueros(unidades_a_eliminar);
    }

    private void eliminarPiqueros(int unidades_a_eliminar) {
        int cant_iteraciones = min(unidades_a_eliminar, piqueros.size());
        for (int i = 0; i < cant_iteraciones; ++i) {
            piqueros.remove(0);
        }
    }

    private int eliminarArqueros(int unidades_a_eliminar) {
        int cant_iteraciones = min(unidades_a_eliminar, arqueros.size());
        for (int i = 0; i < cant_iteraciones; ++i) {
            arqueros.remove(0);
        }
        return cant_iteraciones;
    }

    private int eliminarCaballeros() {
        int cant_iteraciones = min(2, caballeros.size());
        for (int i = 0; i < cant_iteraciones; ++i) {
            caballeros.remove(0);
        }
        return cant_iteraciones;
    }

    public int cantidadDePiqueros() { return piqueros.size();}
    public int cantidadDeArqueros() { return arqueros.size();}
    public int cantidadDeCaballeros() { return caballeros.size();}
}

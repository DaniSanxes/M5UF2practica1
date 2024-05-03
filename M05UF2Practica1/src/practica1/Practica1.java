/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author esther
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {

// L'alumnat d'aquest grup el conformen 40 persones
// L'avaluació consistirà en fer un control i una pràctica i la nota final serà la mitja.
        final int NALUMNES = 40;

        //vector de notes generades
        int[] control = new int[NALUMNES];
        int maxNota = 0;
        int minNota = 0;
        int indMaxNota, indMinNota, postEval;

        generarNotesRandom(control);

        //Es busca la nota més baixa //error trobat en el comentari
        minNota = buscarNotaMin(control);
        //Es busca la nota més alta //error trobat en el comentari
        maxNota = buscarNotaMax(control);

        //Alumnes de la classe
        int[] listaClase = new int[NALUMNES];
        generarLlista(listaClase);

        //LLista per a facilitar us de index
        List notes = Arrays.asList(control);
        indMinNota = notes.indexOf(minNota) + 1;
        indMaxNota = notes.indexOf(maxNota) + 1;
       
        
        //Comprobem
        System.out.println("Mínim és: " + minNota);
        System.out.println("Màxim és: " + maxNota);
        System.out.println(
                "Index del mínim és : " + indMinNota);
        System.out.println(
                "Index del màxim és : " + indMaxNota);
        System.out.println(
                "Lista de classe :" + Arrays.toString(listaClase));
        System.out.println(
                "Array de Notes :" + Arrays.toString(control));

        //notes "practiques"
        int[] pract = new int[NALUMNES];
        generarNotesRandom(pract);  // aprofitem el mètode que ja tenim

        //qualificacions
        float[] notaFinal = new float[NALUMNES];
        calcularNotaFinal(notaFinal, pract, control);

        //estadística de qualif
        mostrarEstadistica(notaFinal);

        //Aprovats i suspesos
        int[] aprovats = new int[NALUMNES];
        int[] suspesos = new int[NALUMNES];

        int cAprovats = generarAprovats(aprovats, notaFinal);
        int cSuspesos = generarSuspesos(suspesos, notaFinal);

        resumAprovats(aprovats, cAprovats);
        resumSuspesos(suspesos, cSuspesos);

    }

    public static void generarNotesRandom(int[] control) {

        //Genera notes random entre 1 i 10
        for (int i = 0; i < control.length; i++) {
            control[i] = (int) (Math.random() * 11);
        }
    }

    public static int buscarNotaMin(int[] control) {
        int postEval = 11;
        int minNota = 0;
        for (int i = 0; i < control.length; i++) {
            int preEval = control[i];
            if (preEval < postEval) {
                minNota = preEval;
                postEval = control[i];
            }
        }
        return minNota;
    }

    public static int buscarNotaMax(int[] control) {

        int postEval = 0, maxNota = 0;
        for (int i = 0; i < control.length; i++) {
            int preEval = control[i];
            if (preEval > postEval) {
                maxNota = preEval;
                postEval = control[i];
            }
        }
        return maxNota;
    }

    public static void generarLlista(int[] llista) {
        for (int i = 0; i < llista.length; i++) {
            llista[i] = i + 1;
        }
    }

    public static void calcularNotaFinal(float[] promedi, int[] pract, int[] control) {

        for (int i = 0; i < promedi.length; i++) {
            promedi[i] = (((float) control[i]
                    + (float) pract[i])
                    / 2);
        }

        System.out.println("Práctiques      :" + Arrays.toString(pract));
        System.out.println("Qualificacio Final promedi :" + Arrays.toString(promedi));
    }

    public static void mostrarEstadistica(float[] notaFinal) {
        float[] estadistica = new float[10];

        for (int i = 0; i < estadistica.length; i++) {

            float count = 0;
            float sum = 0;
            for (int j = 0; j < notaFinal.length; j++) {
                if ((i < notaFinal[j]) && ((i + 1) >= notaFinal[j])) {
                    sum += notaFinal[j];
                    count += 1;
                }
            }
            if (count != 0) {
                estadistica[i] = ((float) count / notaFinal.length);
            } else {
                estadistica[i] = 0;
            }

            double sol = (Math.round(estadistica[i] * 10000.0)) / 100.0;
            System.out.println("Estadística nota tram <="
                    + (i + 1) + " = "
                    + sol + "%");
        }

    }

    public static int generarAprovats(int[] aprovats, float[] notaFinal) {

        int cAprovats = 0;
        for (int i = 0; i < notaFinal.length; i++) {
            if (notaFinal[i] >= 5) {    // error trobat! Els aprovats son notes >=5
                aprovats[i] = i + 1;      // error trobat! i --> i+1  L'alumne 40 no sortia a les llistes
                cAprovats += 1;

            }
        }
        System.out.println("Relació d'aprovats per nº de llista: "
                + Arrays.toString(aprovats));

        return cAprovats;
    }

    public static int generarSuspesos(int[] suspesos, float[] notaFinal) {

        int cSuspesos = 0;
        for (int i = 0; i < notaFinal.length; i++) {
            if (notaFinal[i] < 5) {
                suspesos[i] = i + 1;       // error trobat! i --> i+1  L'alumne 40 no sortia a les llistes
                cSuspesos += 1;
            }
        }

        System.out.println("Relació de suspesos per nº de lista: "
                + Arrays.toString(suspesos));  // error trobat en el print
        return cSuspesos;
    }

    public static void resumAprovats(int[] aprovats, int cAprovats) {
        //Resum d'aprovats
        int i = 0;
        int x = 0;
        int[] alumnatAprovat = new int[cAprovats];
        while (i < aprovats.length) {
            if (aprovats[i] != 0) {
                alumnatAprovat[x] = aprovats[i];
                i++;
                x++;
            } else {
                i++;
            }
        }
        System.out.println(
                "Resum aprovats per nº de llista: "
                + Arrays.toString(alumnatAprovat));
    }

    public static void resumSuspesos(int[] suspesos, int cSuspesos) {

//Resum de suspesos
        int i = 0;
        int x = 0;
        int[] alumnatSuspes = new int[cSuspesos];
        while (i < suspesos.length) {
            if (suspesos[i] != 0) {
                alumnatSuspes[x] = suspesos[i];
                i++;
                x++;
            } else {
                i++;
            }
        }

        System.out.println(
                "Resum suspesos per nº de llista: "
                + Arrays.toString(alumnatSuspes));
    }
}
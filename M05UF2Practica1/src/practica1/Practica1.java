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
        // TODO code application logic here
               int num = 40;

        //vector de notes generades
        Integer[] control = new Integer[num];
        int max = 0;
        int min = 0;
        int indMaxNota, indMinNota;
        int postEval;

        //Genera notes random entre 1 i 10
        for (int i = 0; i < control.length; i++) {
            control[i] = (int) (Math.random() * 11);
        }
        //Es busca la mes gran
        postEval = 11;
        for (int i = 0; i < control.length; i++) {
            int preEval = control[i];
            if (preEval < postEval) {
                min = preEval;
                postEval = control[i];
            }
        }
        //Es busca la mes petita
        postEval = 0;
        for (int i = 0; i < control.length; i++) {
            int preEval = control[i];
            if (preEval > postEval) {
                max = preEval;
                postEval = control[i];
            }
        }
        //Alumnes de la classe
        int[] listaClase = new int[num];
        for (int i = 0; i < num; i++) {
            listaClase[i] = i + 1;
        }
        //LLista per a facilitar us de index
        List notes = Arrays.asList(control);
        indMinNota = notes.indexOf(min) + 1;
        indMaxNota = notes.indexOf(max) + 1;

        //Comprobem
        System.out.println("Mínim és: " + min);
        System.out.println("Màxim és: " + max);
        System.out.println("Index del mínim és : " + indMinNota);
        System.out.println("Index del màxim és : " + indMaxNota);
        System.out.println("Lista de classe :" + Arrays.toString(listaClase));
        System.out.println("Array de Notes :" + notes);

        //notes "practiques"
        int[] pract = new int[num];
        for (int i = 0; i < pract.length; i++) {
            pract[i] = (int) (Math.random() * 11);
        }
        //qualificacions
        float[] qualif = new float[num];
        for (int i = 0; i < control.length; i++) {
            qualif[i]
                    = (((float) control[i]
                    + (float) pract[i])
                    / 2);
        }
        System.out.println("Práctiques      :" + Arrays.toString(pract));
        System.out.println("Qualificacions :" + Arrays.toString(qualif));

        //estadística de qualif
        float[] estadistica = new float[10];

        for (int i = 0; i < 10; i++) {
            float count = 0;
            float sum = 0;
            for (int j = 0; j < control.length; j++) {
                if ((i < qualif[j]) && ((i + 1) >= qualif[j])) {
                    sum += qualif[j];
                    count += 1;
                }
            }
            if (count != 0) {
                estadistica[i] = ((float) count / num);
            } else {
                estadistica[i] = 0;
            }
            double sol = (Math.round(estadistica[i] * 10000.0)) / 100.0;
            System.out.println("Estadística nota tram <="
                    + (i + 1) + " = "
                    + sol + "%");
        }
        //Aprovats i suspesos
        int[] aprovats = new int[num];
        int[] supesos = new int[num];
        int cAprovats = 0;
        int cSuspesos = 0;
        for (int i = 0; i < num; i++) {
            if (qualif[i] < 5) {
                aprovats[i] = i;
                cAprovats += 1;
            } else {
                supesos[i] = i;
                cSuspesos += 1;
            }
        }
        System.out.println("Relació d'aprovats per nº de llista: "
                + Arrays.toString(aprovats));
        System.out.println("Relació d'aprovats per nº de lista: "
                + Arrays.toString(supesos));

        //Resumen de aprovats i supesos
        int i = 0;
        int x = 0;
        int[] a = new int[cAprovats];
        int[] s = new int[cSuspesos];
        while (i < aprovats.length) {
            if (aprovats[i] != 0) {
                a[x] = aprovats[i];
                i++;
                x++;
            } else {
                i++;
            }
        }

        i = x = 0;
        while (i < supesos.length) {
            if (supesos[i] != 0) {
                s[x] = supesos[i];
                i++;
                x++;
            } else {
                i++;
            }
        }
        System.out.println("Resum aprovats per nº de llista: "
                + Arrays.toString(a));
        System.out.println("Resum suspesos per nº de llista: "
                + Arrays.toString(s));

    }
}

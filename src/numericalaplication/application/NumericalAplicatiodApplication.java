/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numericalaplication.application;

import java.util.ArrayList;
import java.util.Scanner;

public class NumericalAplicatiodApplication {

    public static Scanner input = new Scanner(System.in);
    public static double batasBawah[] = new double[1000];
    public static double batasAtas[] = new double[1000];
    public static double batasTengah[] = new double[1000];
    public static double erorToleransi;
    public static double fungsiBawah[] = new double[1000];
    public static double fungsiTengah[] = new double[1000];
    public static String checkRoot[] = new String[1000];
    public static double erorRelative[] = new double[1000];
    public static String iteration[] = new String[1000];

    public static int nElemnt = 1;

    /**
     * f(x) = x^exp(-x) + 1 <-- fungsi
     */
    public static String[] start(double bb, double ba, double et) {

        batasBawah[nElemnt] = bb;
        batasAtas[nElemnt] = ba;
        erorToleransi = et;
        
        ArrayList<String> value = new ArrayList<>();

        erorRelative[0] = 0;

//        inputRequirment();
        boolean cont = true;
        while (cont) {
//			batas tengah
            batasTengah[nElemnt] = (batasBawah[nElemnt] + batasAtas[nElemnt]) / 2;

//			cari fungsi
            fungsiBawah[nElemnt] = cariFungBatas(batasBawah[nElemnt]);
            fungsiTengah[nElemnt] = cariFungBatas(batasTengah[nElemnt]);

            if ((fungsiBawah[nElemnt] * fungsiTengah[nElemnt]) < 0) {
//					log.info("Root has detected");
                batasBawah[nElemnt + 1] = batasBawah[nElemnt];
                batasAtas[nElemnt + 1] = batasTengah[nElemnt];
                checkRoot[nElemnt] = "YA";
            } else {
//					log.info("Root denied");
                batasBawah[nElemnt + 1] = batasTengah[nElemnt];
                batasAtas[nElemnt + 1] = batasAtas[nElemnt];
                checkRoot[nElemnt] = "TIDAK";
            }

            cariErorRelative();

            if (isNext() == false) {
                iteration[nElemnt] = "Stop Iteration";
//					System.out.println(iteration[nElemnt]);
                if (nElemnt <= 1) {
                    value.add("| " + nElemnt + " | " + batasBawah[nElemnt] + " | " + batasAtas[nElemnt] + " | " + batasTengah[nElemnt] + " | " + fungsiBawah[nElemnt] + " | " + fungsiTengah[nElemnt] + " | " + checkRoot[nElemnt] + " | - | - |\n");
                } else {
                    value.add("| " + nElemnt + " | " + batasBawah[nElemnt] + " | " + batasAtas[nElemnt] + " | " + batasTengah[nElemnt] + " | " + fungsiBawah[nElemnt] + " | " + fungsiTengah[nElemnt] + " | " + checkRoot[nElemnt] + " | " + erorRelative[nElemnt] + " | " + iteration[nElemnt] + " |\n");
                }
                cont = false;
            } else {
                iteration[nElemnt] = "Next Iteration";
//					System.out.println(iteration[nElemnt]);
                if (nElemnt <= 1) {
                    value.add("| " + nElemnt + " | " + batasBawah[nElemnt] + " | " + batasAtas[nElemnt] + " | " + batasTengah[nElemnt] + " | " + fungsiBawah[nElemnt] + " | " + fungsiTengah[nElemnt] + " | " + checkRoot[nElemnt] + " | - | - |\n");
                } else {
                    value.add("| " + nElemnt + " | " + batasBawah[nElemnt] + " | " + batasAtas[nElemnt] + " | " + batasTengah[nElemnt] + " | " + fungsiBawah[nElemnt] + " | " + fungsiTengah[nElemnt] + " | " + checkRoot[nElemnt] + " | " + erorRelative[nElemnt] + " | " + iteration[nElemnt] + " |\n");
                }
                nElemnt++;
            }

        }
        value.add("ROOT = " + batasTengah[nElemnt]);
        
        String[] array = value.toArray(new String[value.size()]);
        
        return array;
    }

    /**
     * input
     */
    public static void inputRequirment() {
        System.out.print("Input batas bawah \t: ");
        batasBawah[nElemnt] = input.nextDouble();
        System.out.println("nilai bawah berhasil di input");

        System.out.print("\nInput batas atas \t: ");
        batasAtas[nElemnt] = input.nextDouble();
        System.out.println("nilai atas berhasil di input");

        System.out.print("\nInput eror toleransi \t: ");
        erorToleransi = input.nextDouble();
        System.out.println("nilai eror toleransi berhasil di input\n");
    }

    public static void cariErorRelative() {
//		double hasil = ((batasTengah[nElemnt] - batasTengah[nElemnt-1])/ batasTengah[nElemnt]) * 100;
        double hasil = (batasTengah[nElemnt] - batasTengah[nElemnt - 1]) / batasTengah[nElemnt];
        if (nElemnt != 0) {
            if (hasil >= 0) {
                hasil = (hasil * 100);
            } else {
                hasil = hasil * (-1);
                hasil = (hasil * 100);
            }
        }
        erorRelative[nElemnt] = hasil;
    }

    public static boolean isNext() {
        if (erorRelative[nElemnt] < erorToleransi) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * f(x) = x * exp^(-x) + 1 <-- fungsi
     */
    public static double cariFungBatas(double value) {
        double pangkatMinus = value * (-1);
        double EXP = Math.pow(2.71828183, pangkatMinus);
        double kembalian = (value * EXP) + 1;
        return kembalian;
    }
}

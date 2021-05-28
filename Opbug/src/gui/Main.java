/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Fasie
 */
public class Main {


    public static void main(String[] args) {
       try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (!"Metal".equals(info.getName())) continue;
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(new Runnable(){

            @Override
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
        public static String sumacaract(String suma) {
        String sumacar = "";
        int lensum = suma.length();
        int pozv = suma.indexOf(".");
        System.out.println(pozv);
        int pozvl = suma.lastIndexOf(".");
        String sumaintc = "";
        sumaintc = pozv > 0 ? suma.substring(0, pozv) : suma;
        int ll = sumaintc.length();
        Integer zecimi = null;
        zecimi = pozv > 0 ? Integer.valueOf(Integer.parseInt(suma.substring(pozv + 1, lensum))) : Integer.valueOf(0);
        String zecim = String.valueOf(zecimi);
        if (zecimi <= 9) {
            zecim = "0" + zecim;
        }
        String sutec = "";
        sutec = ll >= 3 ? sumaintc.substring(ll - 3, ll) : sumaintc;
        Integer sute = Integer.parseInt(sutec);
        String miic = "";
        miic = ll > 3 ? (ll < 6 ? sumaintc.substring(0, ll - 3) : sumaintc.substring(ll - 6, ll - 3)) : "0";
        Integer mii = Integer.parseInt(miic);
        String milic = "";
        milic = ll > 6 ? (ll < 9 ? sumaintc.substring(0, ll - 6) : sumaintc.substring(ll - 9, ll - 6)) : "0";
        Integer mili = Integer.parseInt(milic);
        String miliac = "";
        miliac = ll > 9 ? (ll < 12 ? sumaintc.substring(0, ll - 9) : sumaintc.substring(ll - 12, ll - 9)) : "0";
        Integer milia = Integer.parseInt(miliac);
        String sc1 = "";
        String sc2 = "";
        String sc3 = "";
        String sc4 = "";
        String sc5 = "";
        sc1 = milia != 0 ? Main.csute(milia, "milia") + "miliarde" : "";
        sc2 = mili != 0 ? Main.csute(mili, "mili") + "milioane" : "";
        sc3 = mii != 0 ? Main.csute(mii, "mii") + "mii" : "";
        sc4 = sute != 0 ? Main.csute(sute, "sute") : "";
        sc4 = sute != 0 ? Main.csute(sute, "sute") : "";
        sumacar = sc1 + sc2 + sc3 + sc4 + "lei ." + zecim;
        return sumacar;
    }

    public static String u_100_999(int u) {
        String[] uu = new String[]{"unasuta", "douasute", "treisute", "patrusute", "cincisute", "sasesute", "saptesute", "optsute", "nouasute"};
        String sumasu = uu[u - 1];
        return sumasu;
    }

    public static String u_20_99(int u) {
        String[] uu = new String[]{"", "doua", "trei", "patru", "cinci", "sase", "sapte", "opt", "noua"};
        String sumasu = uu[u - 1] + "zeci";
        return sumasu;
    }

    public static String u_11_19(int u) {
        String[] uu = new String[]{"unu", "doi", "trei", "patru", "cinci", "sase", "sapte", "opt", "noua"};
        String sumasu = u > 0 ? uu[u - 1] : "";
        return sumasu;
    }

    public static String u_1_10(int u, String ordinm) {
        String uu1;
        String uu2;
        if (ordinm.equalsIgnoreCase("mii") || ordinm.equalsIgnoreCase("mili") || ordinm.equalsIgnoreCase("milia")) {
            uu1 = "una";
            uu2 = "doua";
        } else {
            uu1 = "unu";
            uu2 = "doi";
        }
        String[] uu = new String[]{uu1, uu2, "trei", "patru", "cinci", "sase", "sapte", "opt", "noua", "zece"};
        String sumasu = u > 0 ? uu[u - 1] : "";
        return sumasu;
    }

    public static String csute(Integer vsute, String ordinm) {
        String sumacar = "";
        String sc_22_99_1 = "";
        String sc_100_999_1 = "";
        String sc_100_999_2 = "";
        String sc_100_999_31 = "";
        String sc_100_999_32 = "";
        String sc_100_999_33 = "";
        String sc_100_999_3 = "";
        String czeci = "";
        String sumas = String.valueOf(vsute);
        Integer vsuteint = (int)vsute;
        if (vsute <= 10) {
            sumacar = Main.u_1_10(vsuteint, ordinm) + "";
        } else if (vsuteint > 10 && vsuteint <= 19) {
            sumacar = Main.u_11_19(Integer.parseInt(sumas.substring(1, 2))) + "sprezece";
        } else if (vsuteint >= 20 && vsuteint <= 99) {
            sc_22_99_1 = Integer.parseInt(sumas.substring(1, 2)) != 0 ? "si" : "";
            sumacar = Main.u_20_99(Integer.parseInt(sumas.substring(0, 1))) + sc_22_99_1 + Main.u_11_19(Integer.parseInt(sumas.substring(1, 2))) + "";
        } else if (vsuteint >= 100 && vsuteint <= 999) {
            czeci = sumas.substring(1, 3);
            int vszeci = Integer.parseInt(czeci);
            sc_100_999_1 = vszeci <= 10 ? Main.u_1_10(vszeci, ordinm) : "";
            sc_100_999_2 = vszeci > 10 && vszeci <= 19 ? Main.u_11_19(Integer.parseInt(czeci.substring(1, 2))) + "sprezece" : "";
            if (vszeci >= 20 && vszeci <= 99) {
                sc_100_999_31 = Main.u_20_99(Integer.parseInt(czeci.substring(0, 1)));
                sc_100_999_32 = czeci.substring(1, 2).equals("0") ? "" : "si";
                sc_100_999_33 = Main.u_11_19(Integer.parseInt(czeci.substring(1, 2)));
                sc_100_999_3 = sc_100_999_31 + sc_100_999_32 + sc_100_999_33;
            } else {
                sc_100_999_3 = "";
            }
            sumacar = Main.u_100_999(Integer.parseInt(sumas.substring(0, 1))) + sc_100_999_1 + sc_100_999_2 + sc_100_999_3;
        }
        return sumacar;
    }

    public static int verif_cui(String ncod_fiscal) {
        String ucf;
        int wsuman;
        String cif;
        String b = "753217532";
        double wsuma = 0.0;
        if (ncod_fiscal.length() > 10 || ncod_fiscal.length() == 0) {
            return 1;
        }
        double dcod_fiscal = Double.parseDouble(ncod_fiscal);
        if (dcod_fiscal < 0.0) {
            return 1;
        }
        wsuma = 0.0;
        String wcod = ncod_fiscal;
        for (int i = 1; i <= wcod.length() - 1; ++i) {
            double ws1 = Double.parseDouble(b.substring(9 - i, 10 - i));
            double ws2 = Double.parseDouble(wcod.substring(wcod.length() - i - 1, wcod.length() - i));
            wsuma += ws1 * ws2;
        }
        if ((wsuma = wsuma * 10.0 % 11.0) == 10.0) {
            wsuma = 0.0;
        }
        if (!(cif = String.valueOf(wsuman = (int)wsuma)).equalsIgnoreCase(ucf = wcod.substring(wcod.length() - 1, wcod.length()))) {
            return 2;
        }
        return 0;
    }
}

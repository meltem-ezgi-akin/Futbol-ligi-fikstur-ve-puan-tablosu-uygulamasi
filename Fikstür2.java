package fikstür2;
import java.util.Scanner;

public class Fikstür2 {
    public static void main(String[] args) {
        Scanner oku = new Scanner(System.in);
        int secim;
        int sayi = 0;
        String[] takimlar = null;
        String[][] tumMaclar = new String[380][2];
        int[][] macSonuclari = new int[380][2];
        int macSayaci = 0;
        
        String[] takimIsimleri = new String[20];
        int[] puanlar = new int[20];
        int[] galibiyetler = new int[20];
        int[] beraberlikler = new int[20];
        int[] maglubiyetler = new int[20];
        int[] atilanGoller = new int[20];
        int[] yenilenGoller = new int[20];
        int[] oynananMaclar = new int[20];
        System.out.println("-----MERHABA MELTEM LIGI FIKSTURUNE HOSGELDINIZ-----");
        do {  
            System.out.println("\n1-Takim Sayisi Belirleyiniz.");
            System.out.println("2-Takim isimlerini Giriniz.");
            System.out.println("3-Fikstur Olusturma.");
            System.out.println("4-Skor");
            System.out.println("5-Puan Tablosu");
            System.out.println("6-Cikis");
            System.out.print("Yapmak istediginiz islemi seciniz..");
            secim = oku.nextInt();
            
            if (secim == 1) {
                do {
                    System.out.print("Takim Sayisi Giriniz: ");
                    sayi = oku.nextInt();
                    
                    if (sayi < 1 || sayi > 20) {
                        System.out.println("Hatali giris! Takim sayisi 1 ile 20 arasinda olmali.");
                    }
                } while (sayi < 1 || sayi > 20);
                
                takimlar = new String[sayi];
                oku.nextLine();
            } else if (secim == 2) {
                if (takimlar == null) {
                    System.out.println("Lutfen once takim sayisini belirleyin.");
                } else {
                    for (int i = 0; i < sayi; i++) {
                        System.out.print((i + 1) + ". takimin ismini giriniz: ");
                        if (i == 0) oku.nextLine();//enteri temizler
                        takimlar[i] = oku.nextLine();
                        takimIsimleri[i] = takimlar[i];
                        puanlar[i] = 0;
                        galibiyetler[i] = 0;
                        beraberlikler[i] = 0;
                        maglubiyetler[i] = 0;
                        atilanGoller[i] = 0;
                        yenilenGoller[i] = 0;
                        oynananMaclar[i] = 0;
                    }
                }
            } else if (secim == 3) {
                if (takimlar == null) {
                    System.out.println("Lutfen once takim sayisini ve isimlerini giriniz..");
                } else {
                    macSayaci = 0;
                    int takimSayisi = takimlar.length;//Takımların toplam sayısı, takimlar dizisinin uzunluğundan alınır
                    boolean tekSayidaTakim = takimSayisi % 2 != 0;
                    
                    if (tekSayidaTakim){
                        takimSayisi++;
                    }//takim sayısı tek ise takim sayisi 1 arttırılır böylece bay değişkeni eklenmiş olur
                    
                    int haftaSayisi = (takimSayisi - 1) * 2;
                    int macSayisi = takimSayisi / 2;
                    
                    String[] aktifTakimlar = new String[takimSayisi];
                    for (int i = 0; i < takimlar.length; i++) {
                    aktifTakimlar[i] = takimlar[i];
                    }
                    if (tekSayidaTakim) {
                    aktifTakimlar[takimSayisi - 1] = "BAY";
                    }//takim sayisi tek ise son elemana bay eklenir.
                    
                    for (int hafta = 1; hafta <= haftaSayisi; hafta++) {
                        System.out.println("\nHafta " + hafta + ":");  //Her hafta başında hafta numarası ekrana yazdırıR
                        
                        for (int mac = 0; mac < macSayisi; mac++) {
                            String evSahibi = aktifTakimlar[mac];  // İlk takım evSahibi
                            String deplasman = aktifTakimlar[takimSayisi - 1 - mac];  //son takim deplasman
                           
                            if (evSahibi=="BAY") {
                                System.out.println(deplasman + " bu hafta BAY geciyor");  
                            } else if (deplasman=="BAY") {
                                System.out.println(evSahibi + " bu hafta BAY geciyor"); //ev sahibi veya deplasman takımı "BAY" ise, diğer takımın o hafta maç yapmayaz
                            } else {
                                if (hafta > (haftaSayisi / 2)) {
                                    System.out.println(deplasman + " vs " + evSahibi);
                                    tumMaclar[macSayaci][0] = deplasman;
                                    tumMaclar[macSayaci][1] = evSahibi;
                                } else {
                                    System.out.println(evSahibi + " vs " + deplasman);
                                    tumMaclar[macSayaci][0] = evSahibi;
                                    tumMaclar[macSayaci][1] = deplasman;
                                }  //deplasman ve ev sahibi rolleri ters çevrilir.
                                macSayaci++;
                            }
                        }
                        
                        String sonTakim = aktifTakimlar[takimSayisi - 1];
                        for (int i = takimSayisi - 1; i > 1; i--) {
                            aktifTakimlar[i] = aktifTakimlar[i - 1];
                        }  //Takımlar sırasıyla kaydırılarak yeni eşleşmeler yapılır
                        aktifTakimlar[1] = sonTakim;  //son takım geçici bir değişkende tutulur
                    }
                }
            } else if (secim == 4) {
                if (takimlar == null || macSayaci == 0) {
                    System.out.println("Lutfen once fikstur olusturun.");
                } else {
                    int haftaSayisi;
                    if (takimlar.length % 2 == 0) {
                    haftaSayisi = (takimlar.length - 1) * 2;
                    } else {
                    haftaSayisi = takimlar.length * 2;
                    }
                    System.out.print("Hangi haftanin skorlarini girmek istiyorsunuz? (1-" + haftaSayisi + "): ");
                    int hafta = oku.nextInt();
                    
                    if (hafta < 1 || hafta > haftaSayisi) {
                        System.out.println("Gecersiz hafta numarasi!");
                        continue;
                    }

                    int maclarBaslangic = (hafta - 1) * (takimlar.length / 2);
                    int maclarBitis = maclarBaslangic + (takimlar.length / 2);

                    System.out.println("\nHafta " + hafta + " maclari:");
                    for (int i = maclarBaslangic; i < maclarBitis && i < macSayaci; i++) {
                        if (macSonuclari[i][0] != 0 || macSonuclari[i][1] != 0) {
                    // Eğer skorlar zaten girilmişse kullanıcıyı uyarır tekrar girilmez
                          System.out.println("\n" + tumMaclar[i][0] + " vs " + tumMaclar[i][1] +
                                   " macinin skoru zaten girildi!");
                          continue; // Bir sonraki maça geç
                          }
                        System.out.println("\n" + tumMaclar[i][0] + " vs " + tumMaclar[i][1]);
                        
                        System.out.print(tumMaclar[i][0] + " kac gol atti: "); 
                        int evSahibiGol = oku.nextInt();
                        System.out.print(tumMaclar[i][1] + " kac gol atti: ");
                        int deplasmanGol = oku.nextInt();
                        
                        macSonuclari[i][0] = evSahibiGol;
                        macSonuclari[i][1] = deplasmanGol;
                        
                        int evSahibiIndex = -1, deplasmanIndex = -1;
                        for (int j = 0; j < takimIsimleri.length; j++) {
                            if (takimIsimleri[j] != null) {
                                if (takimIsimleri[j].equals(tumMaclar[i][0])) evSahibiIndex = j;
                                if (takimIsimleri[j].equals(tumMaclar[i][1])) deplasmanIndex = j;
                            }
                        }
                        
                        if (evSahibiIndex != -1 && deplasmanIndex != -1) {
                            oynananMaclar[evSahibiIndex]++;
                            oynananMaclar[deplasmanIndex]++;
                            atilanGoller[evSahibiIndex] += evSahibiGol;
                            yenilenGoller[evSahibiIndex] += deplasmanGol;
                            atilanGoller[deplasmanIndex] += deplasmanGol;
                            yenilenGoller[deplasmanIndex] += evSahibiGol;
                            
                            if (evSahibiGol > deplasmanGol) {
                                puanlar[evSahibiIndex] += 3;
                                galibiyetler[evSahibiIndex]++;
                                maglubiyetler[deplasmanIndex]++;
                                System.out.println("Mac Sonucu: " + tumMaclar[i][0] + " kazandi!");
                            } else if (evSahibiGol < deplasmanGol) {
                                puanlar[deplasmanIndex] += 3;
                                galibiyetler[deplasmanIndex]++;
                                maglubiyetler[evSahibiIndex]++;
                                System.out.println("Mac Sonucu: " + tumMaclar[i][1] + " kazandi!");
                            } else {
                                puanlar[evSahibiIndex]++;
                                puanlar[deplasmanIndex]++;
                                beraberlikler[evSahibiIndex]++;
                                beraberlikler[deplasmanIndex]++;
                                System.out.println("Mac Sonucu: Berabere!");
                            }
                        }
                    }
                }
            } else if (secim == 5) {
                if (takimlar == null) {
                    System.out.println("Lutfen once fikstur olusturun ve skorlari girin.");
                } else {
                    System.out.println("\nPuan Tablosu:");
                    System.out.println("Takim\t\tO\tG\tB\tM\tAG\tYG\tAV\tP");
                    System.out.println("------------------------------------------------");
                    
                    for (int i = 0; i < sayi - 1; i++) {
                        for (int j = 0; j < sayi - i - 1; j++) {           //takımlar sıralanır
                            if (puanlar[j] < puanlar[j + 1] || 
                               (puanlar[j] == puanlar[j + 1] && 
                                (atilanGoller[j] - yenilenGoller[j]) < (atilanGoller[j + 1] - yenilenGoller[j + 1]))) {
                               
                                String tempTakim = takimIsimleri[j];         //takımlar yer değiştirir
                                takimIsimleri[j] = takimIsimleri[j + 1];
                                takimIsimleri[j + 1] = tempTakim;
                                
                                int temp = puanlar[j];                //takımların istatistikleri yer değiştirir
                                puanlar[j] = puanlar[j + 1];
                                puanlar[j + 1] = temp;
                                
                                temp = galibiyetler[j];              
                                galibiyetler[j] = galibiyetler[j + 1];
                                galibiyetler[j + 1] = temp;
                                
                                temp = beraberlikler[j];
                                beraberlikler[j] = beraberlikler[j + 1];
                                beraberlikler[j + 1] = temp;
                                
                                temp = maglubiyetler[j];
                                maglubiyetler[j] = maglubiyetler[j + 1];
                                maglubiyetler[j + 1] = temp;
                                
                                temp = atilanGoller[j];
                                atilanGoller[j] = atilanGoller[j + 1];
                                atilanGoller[j + 1] = temp;
                                
                                temp = yenilenGoller[j];
                                yenilenGoller[j] = yenilenGoller[j + 1];
                                yenilenGoller[j + 1] = temp;
                                
                                temp = oynananMaclar[j];
                                oynananMaclar[j] = oynananMaclar[j + 1];
                                oynananMaclar[j + 1] = temp;
                            }
                        }
                    }
                    
                    for (int i = 0; i < sayi; i++) {
                        if (takimIsimleri[i] != null) {
                            int averaj = atilanGoller[i] - yenilenGoller[i];
                            System.out.printf("%-15s %d\t%d\t%d\t%d\t%d\t%d\t%d\t%d%n",
                                    takimIsimleri[i], oynananMaclar[i], galibiyetler[i],
                                    beraberlikler[i], maglubiyetler[i], atilanGoller[i],
                                    yenilenGoller[i], averaj, puanlar[i]);
                        }
                    }
                }
            } else if (secim == 6) {
                System.out.println("Cikis yapiliyor...");
            } else {
                System.out.println("Lutfen gecerli bir secim yapin (1-6 arasi).");
            }
        } while (secim != 6);
        
        oku.close();
    }
}
import java.util.LinkedList;

public class TugasStrukturData {
    public static void main(String[] args) {
        
        float StrukturBaris = 10.5f;
        System.out.println("1. Output Float: " + StrukturBaris);

        String SusunanKataBaru = "Pemrograman Struktur Data Java";
        System.out.println("2. Output String: " + SusunanKataBaru);

        System.out.println("3. Array 1 Dimensi: ");
        int[] DelapanAngka = {10, 12 ,18, 23, 33, 58, 67, 82};
        for (int angka : DelapanAngka) {
            System.out.print(angka + " ");
        }
        System.out.println();

        System.out.println("4. Array 2 Dimensi: ");
        String[][] DuaAngka = {
            {"2", "4", "6"},
            {"8", "10", "12"},
            {"14", "16", "18"}
        };
        for (int i = 0; i < DuaAngka.length; i++) {
            for (int j = 0; j < DuaAngka.length; j++) {
                System.out.printf("%-4s", DuaAngka[i][j]);
            }
            System.out.println();
        }

        System.out.println("5. Array LinkedList: ");
        LinkedList<Integer> UrutanListAngka = new LinkedList<>();
        UrutanListAngka.add(15);
        UrutanListAngka.add(28);
        UrutanListAngka.add(33);
        UrutanListAngka.add(47);
        UrutanListAngka.add(59);

        for(Integer angka : UrutanListAngka) {
            System.out.print(angka + " -> ");
        }
        System.out.println("Selesai");
    }
}

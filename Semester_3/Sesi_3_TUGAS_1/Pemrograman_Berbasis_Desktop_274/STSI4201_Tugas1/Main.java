import java.util.Scanner;

public class Main {

    private static final double PAJAK = 0.10;
    private static final double BIAYA_PELAYANAN = 20000;

    private static Menu[] daftarMenu = {
        new Menu("Nasi Padang", 25000, "Makanan"),
        new Menu("Mie Goreng", 20000, "Makanan"),
        new Menu("Ayam Bakar", 30000, "Makanan"),
        new Menu("Sate Ayam", 28000, "Makanan"),

        new Menu("Es Teh Manis", 8000, "Minuman"),
        new Menu("Jus Jeruk", 12000, "Minuman"),
        new Menu("Kopi Susu", 15000, "Minuman"),
        new Menu("Air Mineral", 5000, "Minuman")
    };

    private static Menu[] pesananMenu = new Menu[4];
    private static int[] pesananJumlah = new int[4];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        tampilkanDaftarMenu();

        System.out.println("\n========================================");
        System.out.println("           INPUT PESANAN PELANGGAN");
        System.out.println("========================================");
        System.out.println("Ketik nama menu sesuai daftar.");
        System.out.println("Ketik 0 jika tidak ingin mengisi pesanan berikutnya.\n");

        inputPesanan(input, 0);
        inputPesanan(input, 1);
        inputPesanan(input, 2);
        inputPesanan(input, 3);

        cetakStruk();

        input.close();
    }

    public static void tampilkanDaftarMenu() {
        System.out.println("========================================");
        System.out.println("          DAFTAR MENU RESTORAN");
        System.out.println("========================================");

        System.out.println("\n--- MAKANAN ---");
        tampilkanSatuMenu(daftarMenu[0]);
        tampilkanSatuMenu(daftarMenu[1]);
        tampilkanSatuMenu(daftarMenu[2]);
        tampilkanSatuMenu(daftarMenu[3]);

        System.out.println("\n--- MINUMAN ---");
        tampilkanSatuMenu(daftarMenu[4]);
        tampilkanSatuMenu(daftarMenu[5]);
        tampilkanSatuMenu(daftarMenu[6]);
        tampilkanSatuMenu(daftarMenu[7]);
    }

    public static void tampilkanSatuMenu(Menu menu) {
        switch (menu.getKategori()) {
            case "Makanan":
                System.out.printf("%-15s : Rp%,.0f%n",
                        menu.getNama(), menu.getHarga());
                break;

            case "Minuman":
                System.out.printf("%-15s : Rp%,.0f%n",
                        menu.getNama(), menu.getHarga());
                break;

            default:
                System.out.println("Kategori menu tidak tersedia.");
                break;
        }
    }

    public static void inputPesanan(Scanner input, int indeks) {
        System.out.println("Pesanan ke-" + (indeks + 1));

        System.out.print("Nama menu : ");
        String namaMenu = input.nextLine();

        if (namaMenu.equalsIgnoreCase("0")) {
            System.out.println("Slot pesanan ini dikosongkan.\n");
            return;
        }

        Menu menuDipilih = cariMenu(namaMenu);

        if (menuDipilih != null) {
            System.out.print("Jumlah     : ");
            int jumlah = input.nextInt();
            input.nextLine();

            if (jumlah > 0) {
                pesananMenu[indeks] = menuDipilih;
                pesananJumlah[indeks] = jumlah;

                System.out.println("Pesanan berhasil ditambahkan.\n");
            } else {
                System.out.println("Jumlah pesanan harus lebih dari 0.");
                System.out.println("Pesanan tidak disimpan.\n");
            }
        } else {
            System.out.println("Menu tidak ditemukan.");
            System.out.println("Pesanan tidak disimpan.\n");
        }
    }

    public static Menu cariMenu(String namaMenu) {
        if (namaMenu.equalsIgnoreCase(daftarMenu[0].getNama())) {
            return daftarMenu[0];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[1].getNama())) {
            return daftarMenu[1];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[2].getNama())) {
            return daftarMenu[2];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[3].getNama())) {
            return daftarMenu[3];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[4].getNama())) {
            return daftarMenu[4];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[5].getNama())) {
            return daftarMenu[5];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[6].getNama())) {
            return daftarMenu[6];
        } else if (namaMenu.equalsIgnoreCase(daftarMenu[7].getNama())) {
            return daftarMenu[7];
        } else {
            return null;
        }
    }

    public static double hitungTotalItem(Menu menu, int jumlah) {
        if (menu != null) {
            return menu.getHarga() * jumlah;
        }

        return 0;
    }

    public static double hitungSubtotal() {
        return hitungTotalItem(pesananMenu[0], pesananJumlah[0])
                + hitungTotalItem(pesananMenu[1], pesananJumlah[1])
                + hitungTotalItem(pesananMenu[2], pesananJumlah[2])
                + hitungTotalItem(pesananMenu[3], pesananJumlah[3]);
    }

    public static double hitungPromoSatuItem(Menu menu, int jumlah) {
        if (menu != null && menu.getKategori().equalsIgnoreCase("Minuman")) {
            int jumlahGratis = jumlah / 2;
            return jumlahGratis * menu.getHarga();
        }

        return 0;
    }

    public static double hitungPromoMinuman(double subtotal) {

        if (subtotal > 50000) {
            return hitungPromoSatuItem(pesananMenu[0], pesananJumlah[0])
                    + hitungPromoSatuItem(pesananMenu[1], pesananJumlah[1])
                    + hitungPromoSatuItem(pesananMenu[2], pesananJumlah[2])
                    + hitungPromoSatuItem(pesananMenu[3], pesananJumlah[3]);
        }

        return 0;
    }

    public static double hitungDiskonSepuluhPersen(double subtotal) {
        if (subtotal > 100000) {
            return subtotal * 0.10;
        }

        return 0;
    }

    public static void cetakSatuPesanan(Menu menu, int jumlah) {
        if (menu != null && jumlah > 0) {
            double totalItem = hitungTotalItem(menu, jumlah);

            System.out.printf("%-15s %2d x Rp%,8.0f = Rp%,10.0f%n",
                    menu.getNama(),
                    jumlah,
                    menu.getHarga(),
                    totalItem);
        }
    }

    public static void cetakStruk() {
        double subtotal = hitungSubtotal();
        double diskonSepuluhPersen = hitungDiskonSepuluhPersen(subtotal);
        double promoMinuman = hitungPromoMinuman(subtotal);
        double biayaPajak = subtotal * PAJAK;

        double totalBayar = subtotal
                - diskonSepuluhPersen
                - promoMinuman
                + biayaPajak
                + BIAYA_PELAYANAN;

        System.out.println("========================================");
        System.out.println("              STRUK PESANAN");
        System.out.println("========================================");

        cetakSatuPesanan(pesananMenu[0], pesananJumlah[0]);
        cetakSatuPesanan(pesananMenu[1], pesananJumlah[1]);
        cetakSatuPesanan(pesananMenu[2], pesananJumlah[2]);
        cetakSatuPesanan(pesananMenu[3], pesananJumlah[3]);

        System.out.println("----------------------------------------");
        System.out.printf("Subtotal                  : Rp%,10.0f%n", subtotal);
        System.out.printf("Pajak 10%%                 : Rp%,10.0f%n", biayaPajak);
        System.out.printf("Biaya pelayanan           : Rp%,10.0f%n", BIAYA_PELAYANAN);

        if (diskonSepuluhPersen > 0) {
            System.out.printf("Diskon 10%%                : -Rp%,9.0f%n",
                    diskonSepuluhPersen);
        } else {
            System.out.println("Diskon 10%                : Tidak berlaku");
        }

        if (promoMinuman > 0) {
            System.out.printf("Promo beli 1 gratis 1     : -Rp%,9.0f%n",
                    promoMinuman);
        } else {
            System.out.println("Promo beli 1 gratis 1     : Tidak berlaku");
        }

        System.out.println("----------------------------------------");
        System.out.printf("TOTAL PEMBAYARAN          : Rp%,10.0f%n", totalBayar);
        System.out.println("========================================");
        System.out.println("Terima kasih telah memesan.");
    }
}

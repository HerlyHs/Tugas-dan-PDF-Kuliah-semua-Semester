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

    private static Pesanan[] daftarPesanan = new Pesanan[100];
    private static int pesananCount = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("          APLIKASI RESTORAN");
            System.out.println("========================================");
            System.out.println("1. Menu Pelanggan");
            System.out.println("2. Menu Pemilik");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan = getValidInput(1, 3);

            switch (pilihan) {
                case 1:
                    menuPelanggan();
                    break;
                case 2:
                    menuPemilik();
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan aplikasi kami.");
                    return;
            }
        }
    }

    public static void menuPelanggan() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("          MENU PELANGGAN");
            System.out.println("========================================");
            System.out.println("1. Lihat Menu");
            System.out.println("2. Pesan Menu");
            System.out.println("3. Hapus Pesanan Terakhir");
            System.out.println("4. Cetak Struk");
            System.out.println("5. Kembali");
            System.out.print("Pilih menu: ");

            int pilihan = getValidInput(1, 5);

            switch (pilihan) {
                case 1:
                    tampilkanDaftarMenu();
                    break;
                case 2:
                    pesanMenu();
                    break;
                case 3:
                    hapusPesananTerakhir();
                    break;
                case 4:
                    cetakStruk();
                    break;
                case 5:
                    return;
            }
        }
    }

    public static void menuPemilik() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("          MENU PEMILIK");
            System.out.println("========================================");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali");
            System.out.print("Pilih menu: ");

            int pilihan = getValidInput(1, 4);

            switch (pilihan) {
                case 1:
                    tambahMenu();
                    break;
                case 2:
                    ubahHargaMenu();
                    break;
                case 3:
                    hapusMenu();
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void tampilkanDaftarMenu() {
        System.out.println("\n========================================");
        System.out.println("          DAFTAR MENU RESTORAN");
        System.out.println("========================================");

        System.out.println("\n--- MAKANAN ---");
        for (Menu menu : daftarMenu) {
            if (menu != null && menu.getKategori().equals("Makanan")) {
                System.out.printf("%-15s : Rp%,.0f%n",
                        menu.getNama(), menu.getHarga());
            }
        }

        System.out.println("\n--- MINUMAN ---");
        for (Menu menu : daftarMenu) {
            if (menu != null && menu.getKategori().equals("Minuman")) {
                System.out.printf("%-15s : Rp%,.0f%n",
                        menu.getNama(), menu.getHarga());
            }
        }
    }

    public static void pesanMenu() {
        System.out.println("\n========================================");
        System.out.println("           INPUT PESANAN PELANGGAN");
        System.out.println("========================================");
        System.out.println("Ketik nama menu sesuai daftar.");
        System.out.println("Ketik 'selesai' jika sudah selesai memesan.\n");

        while (true) {
            System.out.print("Nama menu: ");
            String namaMenu = scanner.nextLine();

            if (namaMenu.equalsIgnoreCase("selesai")) {
                break;
            }

            Menu menuDipilih = cariMenu(namaMenu);

            if (menuDipilih != null) {
                System.out.print("Jumlah: ");
                int jumlah = getValidInput(1, Integer.MAX_VALUE);

                daftarPesanan[pesananCount] = new Pesanan(menuDipilih, jumlah);
                pesananCount++;

                System.out.println("Pesanan berhasil ditambahkan.\n");
            } else {
                System.out.println("Menu tidak ditemukan. Silakan coba lagi.\n");
            }
        }
    }

    public static void hapusPesananTerakhir() {
        if (pesananCount > 0) {
            pesananCount--;
            System.out.println("Pesanan terakhir berhasil dihapus.");
        } else {
            System.out.println("Tidak ada pesanan yang dapat dihapus.");
        }
    }

    public static void cetakStruk() {
        if (pesananCount == 0) {
            System.out.println("Belum ada pesanan yang dicetak.");
            return;
        }

        double subtotal = hitungSubtotal();
        double diskonSepuluhPersen = hitungDiskonSepuluhPersen(subtotal);
        double promoMinuman = hitungPromoMinuman(subtotal);
        double biayaPajak = subtotal * PAJAK;

        double totalBayar = subtotal
                - diskonSepuluhPersen
                - promoMinuman
                + biayaPajak
                + BIAYA_PELAYANAN;

        System.out.println("\n========================================");
        System.out.println("              STRUK PESANAN");
        System.out.println("========================================");

        for (int i = 0; i < pesananCount; i++) {
            Pesanan pesanan = daftarPesanan[i];
            Menu menu = pesanan.getMenu();
            int jumlah = pesanan.getJumlah();
            double totalItem = menu.getHarga() * jumlah;

            System.out.printf("%-15s %2d x Rp%,8.0f = Rp%,10.0f%n",
                    menu.getNama(),
                    jumlah,
                    menu.getHarga(),
                    totalItem);
        }

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

    public static void tambahMenu() {
        System.out.println("\n========================================");
        System.out.println("           TAMBAH MENU BARU");
        System.out.println("========================================");

        System.out.print("Nama menu: ");
        String nama = scanner.nextLine();

        System.out.print("Harga: ");
        double harga = getValidInput(0, Double.MAX_VALUE);

        System.out.println("Kategori:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.print("Pilih kategori: ");
        int kategoriPilihan = getValidInput(1, 2);

        String kategori = (kategoriPilihan == 1) ? "Makanan" : "Minuman";

        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i] == null) {
                daftarMenu[i] = new Menu(nama, harga, kategori);
                System.out.println("Menu berhasil ditambahkan.");
                return;
            }
        }

        System.out.println("Daftar menu sudah penuh. Tidak dapat menambahkan menu baru.");
    }

    public static void ubahHargaMenu() {
        tampilkanDaftarMenu();

        System.out.println("\n========================================");
        System.out.println("           UBAH HARGA MENU");
        System.out.println("========================================");

        System.out.print("Masukkan nama menu yang ingin diubah: ");
        String namaMenu = scanner.nextLine();

        Menu menuDipilih = cariMenu(namaMenu);

        if (menuDipilih != null) {
            System.out.print("Harga baru: ");
            double hargaBaru = getValidInput(0, Double.MAX_VALUE);

            System.out.print("Apakah Anda yakin ingin mengubah harga? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine();

            if (konfirmasi.equalsIgnoreCase("Ya")) {
                menuDipilih.setHarga(hargaBaru);
                System.out.println("Harga menu berhasil diubah.");
            } else {
                System.out.println("Perubahan harga dibatalkan.");
            }
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }

    public static void hapusMenu() {
        tampilkanDaftarMenu();

        System.out.println("\n========================================");
        System.out.println("           HAPUS MENU");
        System.out.println("========================================");

        System.out.print("Masukkan nama menu yang ingin dihapus: ");
        String namaMenu = scanner.nextLine();

        Menu menuDipilih = cariMenu(namaMenu);

        if (menuDipilih != null) {
            System.out.print("Apakah Anda yakin ingin menghapus menu? (Ya/Tidak): ");
            String konfirmasi = scanner.nextLine();

            if (konfirmasi.equalsIgnoreCase("Ya")) {
                // Cari indeks menu yang akan dihapus
                for (int i = 0; i < daftarMenu.length; i++) {
                    if (daftarMenu[i] == menuDipilih) {
                        // Geser elemen-elemen setelahnya ke kiri
                        for (int j = i; j < daftarMenu.length - 1; j++) {
                            daftarMenu[j] = daftarMenu[j + 1];
                        }
                        daftarMenu[daftarMenu.length - 1] = null;
                        System.out.println("Menu berhasil dihapus.");
                        return;
                    }
                }
            } else {
                System.out.println("Penghapusan menu dibatalkan.");
            }
        } else {
            System.out.println("Menu tidak ditemukan.");
        }
    }

    public static Menu cariMenu(String namaMenu) {
        for (Menu menu : daftarMenu) {
            if (menu != null && menu.getNama().equalsIgnoreCase(namaMenu)) {
                return menu;
            }
        }
        return null;
    }

    public static double hitungSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < pesananCount; i++) {
            Pesanan pesanan = daftarPesanan[i];
            subtotal += pesanan.getMenu().getHarga() * pesanan.getJumlah();
        }
        return subtotal;
    }

    public static double hitungDiskonSepuluhPersen(double subtotal) {
        if (subtotal > 100000) {
            return subtotal * 0.10;
        }
        return 0;
    }

    public static double hitungPromoMinuman(double subtotal) {
        if (subtotal > 50000) {
            double promoValue = 0;
            for (int i = 0; i < pesananCount; i++) {
                Pesanan pesanan = daftarPesanan[i];
                if (pesanan.getMenu().getKategori().equals("Minuman")) {
                    int freeQty = pesanan.getJumlah() / 2;
                    promoValue += freeQty * pesanan.getMenu().getHarga();
                }
            }
            return promoValue;
        }
        return 0;
    }

    public static int getValidInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.print("Input tidak valid. Silakan masukkan nilai antara " + min + " dan " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid. Silakan masukkan angka: ");
            }
        }
    }

    public static double getValidInput(double min, double max) {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.print("Input tidak valid. Silakan masukkan nilai antara " + min + " dan " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Input tidak valid. Silakan masukkan angka: ");
            }
        }
    }
}

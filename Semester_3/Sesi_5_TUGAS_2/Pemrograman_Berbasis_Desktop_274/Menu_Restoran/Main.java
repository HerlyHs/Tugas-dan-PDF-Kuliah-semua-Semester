import java.util.Scanner;

public class Main {
    // Menggunakan Array untuk menyimpan data menu restoran
    static Menu[] daftarMenu = {
        // Makanan (index 0 - 3)
        new Menu("Nasi Goreng Spesial", 25000, "makanan"),
        new Menu("Mie Goreng Ayam", 20000, "makanan"),
        new Menu("Ayam Bakar Madu", 30000, "makanan"),
        new Menu("Sate Ayam Madura", 28000, "makanan"),
        // Minuman (index 4 - 7)
        new Menu("Es Teh Manis", 5000, "minuman"),
        new Menu("Es Jeruk Peras", 7000, "minuman"),
        new Menu("Jus Alpukat", 15000, "minuman"),
        new Menu("Kopi Hitam", 10000, "minuman")
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pilihanUtama;

        // Struktur pengulangan do-while untuk navigasi menu utama
        do {
            System.out.println("\n=== APLIKASI RESTORAN SEDERHANA ===");
            System.out.println("1. Menu Pelanggan (Pemesanan)");
            System.out.println("2. Menu Pengelolaan (Owner Restoran)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");
            
            pilihanUtama = scanner.nextInt();
            scanner.nextLine(); // membersihkan buffer

            // Struktur keputusan switch-case
            switch (pilihanUtama) {
                case 1:
                    prosesPemesanan(scanner);
                    break;
                case 2:
                    manajemenMenu(scanner);
                    break;
                case 3:
                    System.out.println("Terima kasih telah menggunakan aplikasi!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-3.");
            }
        } while (pilihanUtama != 3);

        scanner.close();
    }

    // Method untuk menampilkan daftar menu dikelompokkan berdasarkan kategori
    public static void tampilkanDaftarMenu() {
        System.out.println("\n--- DAFTAR MENU RESTORAN ---");
        System.out.println("=== MAKANAN ===");
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].kategori.equalsIgnoreCase("makanan")) {
                System.out.println((i + 1) + ". " + daftarMenu[i].nama + " - Rp " + daftarMenu[i].harga);
            }
        }

        System.out.println("\n=== MINUMAN ===");
        for (int i = 0; i < daftarMenu.length; i++) {
            if (daftarMenu[i].kategori.equalsIgnoreCase("minuman")) {
                System.out.println((i + 1) + ". " + daftarMenu[i].nama + " - Rp " + daftarMenu[i].harga);
            }
        }
    }

    // Method untuk memproses pesanan pelanggan
    public static void prosesPemesanan(Scanner scanner) {
        int[] jumlahPesanan = new int[daftarMenu.length];
        String inputan;

        System.out.println("\n=== SILAKAN MELAKUKAN PEMESANAN ===");
        System.out.println("Ketik nama menu yang ingin dipesan atau ketik 'selesai' untuk mengakhiri.");

        // Struktur pengulangan while/for dengan validasi input
        while (true) {
            tampilkanDaftarMenu();
            System.out.print("\nMasukkan nama menu (atau 'selesai'): ");
            inputan = scanner.nextLine();

            if (inputan.equalsIgnoreCase("selesai")) {
                break;
            }

            // Mencari apakah menu ada di dalam array
            boolean ditemukan = false;
            int indexMenu = -1;
            for (int i = 0; i < daftarMenu.length; i++) {
                if (daftarMenu[i].nama.equalsIgnoreCase(inputan)) {
                    ditemukan = true;
                    indexMenu = i;
                    break;
                }
            }

            // Struktur keputusan if-else untuk validasi input di luar menu
            if (ditemukan) {
                System.out.print("Masukkan jumlah porsi/gelas untuk " + daftarMenu[indexMenu].nama + ": ");
                int qty = scanner.nextInt();
                scanner.nextLine(); // bersihkan buffer
                jumlahPesanan[indexMenu] += qty;
                System.out.println("Pesanan berhasil ditambahkan!");
            } else {
                // Catatan soal: jika menginput nilai/teks di luar pilihan, sistem akan terus meminta input kembali
                System.out.println("[PERINGATAN] Menu tidak ditemukan! Silakan masukkan nama menu yang valid.");
            }
        }

        hitungkanDanCetakStruk(jumlahPesanan);
    }

    // Method untuk menghitung biaya, diskon, pajak, dan mencetak struk
    public static void hitungkanDanCetakStruk(int[] pesanan) {
        double totalBiaya = 0;
        boolean adaMinuman = false;
        double hargaMinumanGratisTermurah = 0;
        boolean dapatBeliSatuGratisSatu = false;

        System.out.println("\n========================================");
        System.out.println("              STRUK PESANAN             ");
        System.out.println("========================================");

        for (int i = 0; i < daftarMenu.length; i++) {
            if (pesanan[i] > 0) {
                double subTotal = daftarMenu[i].harga * pesanan[i];
                totalBiaya += subTotal;
                System.out.println(daftarMenu[i].nama + " x" + pesanan[i] + " = Rp " + subTotal);

                // Cek kategori minuman untuk penawaran khusus
                if (daftarMenu[i].kategori.equalsIgnoreCase("minuman")) {
                    adaMinuman = true;
                    hargaMinumanGratisTermurah = daftarMenu[i].harga; // contoh sederhana
                }
            }
        }

        if (totalBiaya == 0) {
            System.out.println("Tidak ada pesanan yang dilakukan.");
            return;
        }

        System.out.println("----------------------------------------");
        System.out.println("Total Sementara: Rp " + totalBiaya);

        // Struktur keputusan untuk Diskon & Penawaran Khusus
        double diskon = 0;
        if (totalBiaya > 100000) {
            diskon = 0.10 * totalBiaya;
            System.out.println("Diskon 10% (> Rp 100.000): -Rp " + diskon);
        } else if (totalBiaya > 50000 && adaMinuman) {
            dapatBeliSatuGratisSatu = true;
            System.out.println("Penawaran Beli Satu Gratis Satu Minuman Aktif!");
        }

        double totalSetelahDiskon = totalBiaya - diskon;
        double pajak = 0.10 * totalSetelahDiskon;
        double biayaPelayanan = 20000;
        double totalKeseluruhan = totalSetelahDiskon + pajak + biayaPelayanan;

        System.out.println("Pajak (10%): Rp " + pajak);
        System.out.println("Biaya Pelayanan: Rp " + biayaPelayanan);
        System.out.println("========================================");
        System.out.println("TOTAL KESELURUHAN: Rp " + totalKeseluruhan);
        System.out.println("========================================");
    }

    // Method untuk manajemen menu oleh pemilik restoran
    public static void manajemenMenu(Scanner scanner) {
        int pilihan;
        do {
            System.out.println("\n=== MANAJEMEN MENU RESTORAN ===");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih (1-4): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            // Struktur keputusan switch-case untuk manajemen menu
            switch (pilihan) {
                case 1:
                    System.out.println("Fitur tambah menu baru dapat diimplementasikan di sini.");
                    break;
                case 2:
                    tampilkanDaftarMenu();
                    System.out.print("Masukkan nomor menu yang ingin diubah harganya: ");
                    int idxUbah = scanner.nextInt() - 1;
                    scanner.nextLine();
                    
                    if (idxUbah >= 0 && idxUbah < daftarMenu.length) {
                        System.out.print("Konfirmasi ubah harga? (ketik 'Ya' untuk lanjut): ");
                        String konfirmasi = scanner.nextLine();
                        // Struktur keputusan if untuk konfirmasi
                        if (konfirmasi.equalsIgnoreCase("Ya")) {
                            System.out.print("Masukkan harga baru: ");
                            double hargaBaru = scanner.nextDouble();
                            scanner.nextLine();
                            daftarMenu[idxUbah].harga = hargaBaru;
                            System.out.println("Harga berhasil diubah!");
                        } else {
                            System.out.println("Perubahan dibatalkan.");
                        }
                    } else {
                        System.out.println("Nomor menu tidak valid.");
                    }
                    break;
                case 3:
                    System.out.println("Fitur hapus menu dapat disesuaikan dengan logika array Anda.");
                    break;
                case 4:
                    System.out.println("Kembali ke menu utama...");
                    break;
                default:
                    // Catatan soal: jika input di luar pilihan, sistem meminta input kembali
                    System.out.println("[PERINGATAN] Pilihan tidak valid! Silakan masukkan angka 1-4.");
            }
        } while (pilihan != 4);
    }
}
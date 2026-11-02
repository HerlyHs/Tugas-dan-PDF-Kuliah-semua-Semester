public class Menu {
    // Atribut
    String nama;
    double harga;
    String kategori; // "makanan" atau "minuman"

    // Konstruktor
    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Method untuk menampilkan info menu
    public void tampilkanInfo() {
        System.out.println("- " + nama + " (Rp " + harga + ")");
    }
}
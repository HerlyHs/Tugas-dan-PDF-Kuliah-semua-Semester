# Nomor 2 - Jawaban a, b, c

windows()

nilai <- c(60, 67, 69, 72, 74, 77, 80, 83, 87, 70)
frekuensi <- c(3, 5, 6, 8, 9, 2, 6, 8, 1, 4)

# a. Grafik Sebaran Nilai dan Frekuensi (Scatter Plot)
par(mar = c(5, 4, 4, 2) + 0.1)
plot(nilai, frekuensi,
     main = "Grafik Sebaran Nilai dan Frekuensi",
     xlab = "Nilai",
     ylab = "Frekuensi",
     pch = 20,
     col = "blue")

# b. Membuat Pie Chart
par(mar = c(5, 4, 4, 2) + 0.1)
pie(frekuensi,
    labels = nilai,
    main = "Distribusi Frekuensi Nilai Mahasiswa")

# c. Membuat Boxplot
par(mar = c(5, 4, 4, 2) + 0.1)
boxplot(nilai,
        main = "Boxplot Nilai Mahasiswa",
        xlab = "Nilai")
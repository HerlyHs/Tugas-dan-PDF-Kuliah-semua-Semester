#Nomor 1
#a. Menambahkan kolom ke 4 dengan nama M1 dan ke 1 dengan nama M2

M <- matrix (
  c(
    10, 5, 6,
    2, 6, 8,
    4, 2, 7),
  nrow = 3,
  byrow = TRUE
)

M1 <- cbind(M, c(7, 4, 5))
M2 <- cbind(c(7, 4 , 5), M)

perkalian <- M1 * M2
penjumlahan <- M1 + M2

M1
M2
perkalian
penjumlahan
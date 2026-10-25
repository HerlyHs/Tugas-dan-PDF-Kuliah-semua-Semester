import java.util.*;

public class Tugas2 {

    public static void main(String[] args) {

        System.out.println("1. IMPLEMENTASI STACK LIFO ");
        Stack<Integer> stack = new Stack<>();
        int[] dataStack = {10, 20, 30, 40, 50, 60, 70, 80};

        System.out.println("Proses Push: ");
        for (int d : dataStack) {
            stack.add(d);
            System.out.print(d + " ");
        }

        System.out.println("\nIsi stack sebelum Pop: " + stack);
        System.out.println("Proses Pop (Elemen Keluar): ");

        while (!stack.isEmpty()) {
            int poppedElement = stack.pop();
            System.out.println("Pop Elemen: " + poppedElement + "-> Sisa tumpukan saat ini: " + stack);
        }

        System.out.println("2. IMPLEMENTASI QUEUE FIFO ");
        Queue<Integer> queue = new LinkedList<>();
        int[] dataQueue = {11, 22, 33, 44, 55, 66, 77, 88};

        System.out.println("Proses Enqueue: ");
        for (int d : dataQueue) {
            queue.add(d);
            System.out.println(d + " ");
        }

        System.out.println("\nIsi Queue sebelum Dequeue: " + queue);
        System.out.println("Proses Dequeue (Elemen Keluar) ");

        while (!queue.isEmpty()) {
            int dequeuedElement = queue.poll();
            System.out.println("Dequeue element: " + dequeuedElement + "-> Sisa antrean saat ini: " + queue);
        }

        System.out.println("Isi Queue sesudah semua di proses Dequeue: " + queue + "\n");

        System.out.println("3. IMPLEMENTASI QUICK SHORT ");
        int[] dataShort = {45, 12, 89,33, 21, 56, 4, 60};
        System.out.println("Data sebelum diurutkan: " + Arrays.toString(dataShort));

        quickShort(dataShort, 0, dataShort.length - 1);
        System.out.println("Data sesudah diurutkan: " + Arrays.toString(dataShort));
    }

    public static void quickShort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickShort(arr, low, pi - 1);
            quickShort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low -1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
} 
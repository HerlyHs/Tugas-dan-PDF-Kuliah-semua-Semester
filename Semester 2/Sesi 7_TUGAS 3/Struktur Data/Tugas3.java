import java.util.*;

public class Tugas3 {

    private Map<String, List<String>> adjList = new HashMap<>();

    public void addEdge(String u, String v) {
        adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adjList.computeIfAbsent(v, k -> new ArrayList<>()).add(u); 
    }

    public static void main(String[] args) {
        Tugas3 graph = new Tugas3();

        graph.addEdge("a0", "a1");
        graph.addEdge("a0", "a2");
        graph.addEdge("a1", "a3");
        graph.addEdge("a1", "a4");
        graph.addEdge("a2", "a5");
        graph.addEdge("a2", "a6");
        graph.addEdge("a3", "a7");
        graph.addEdge("a4", "a7");
        graph.addEdge("a5", "a8");
        graph.addEdge("a6", "a9");

        String nodeAwal = "a0";
        String targetPencarian = "a7"; 

        graph.dfsSearch(nodeAwal, targetPencarian);
        graph.bfsSearch(nodeAwal, targetPencarian);
    }

    public boolean dfsSearch(String start, String target) {
        Set<String> visited = new LinkedHashSet<>();
        Stack<String> stack = new Stack<>();

        stack.push(start);
        System.out.println("MEmulai Pencarian DFS");
        System.out.println("Target yang dicari (n): " + target + "\n");

        int step = 1;
        while (!stack.isEmpty()) {
            String currentNode = stack.pop();

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                
                System.out.printf("Langkah %d:\n", step++);
                System.out.println("  Node aktif dikunjungi   : " + currentNode);
                System.out.println("  Isi Stack saat ini      : " + stack);
                System.out.println("  Daftar Terpilih (Visited): " + visited);

                if (currentNode.equals(target)) {
                    System.out.println("\n-> SUKSES: Target '" + target + "' ditemukan melalui DFS!");
                    return true;
                }

                List<String> neighbors = adjList.getOrDefault(currentNode, new ArrayList<>());
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    String neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
                System.out.println("  Stack setelah input: " + stack);
            }
        }
        System.out.println("\n-> GAGAL: Target '" + target + "' tidak ditemukan melalui DFS.");
        return false;
    }

    public boolean bfsSearch(String start, String target) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        System.out.println("Memulai Pencarian BFS");
        System.out.println("Target yang dicari (n): " + target + "\n");

        int step = 1;
        while (!queue.isEmpty()) {
            String currentNode = queue.poll();

            System.out.printf("Langkah %d:\n", step++);
            System.out.println("  Node aktif dikunjungi   : " + currentNode);
            System.out.println("  Isi Queue saat ini      : " + queue);
            System.out.println("  Daftar Terpilih (Visited): " + visited);

            if (currentNode.equals(target)) {
                System.out.println("\n SUKSES: Target '" + target + "' ditemukan melalui BFS!");
                return true;
            }

            List<String> neighbors = adjList.getOrDefault(currentNode, new ArrayList<>());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
            System.out.println("  Queue setelah input: " + queue);
        }
        System.out.println("\n GAGAL: Target '" + target + "' tidak ditemukan melalui BFS.");
        return false;
    }
}
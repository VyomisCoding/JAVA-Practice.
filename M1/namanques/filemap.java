import java.util.*;

// Class to store version details
class Version {
    String name;
    int size;

    Version(String name, int size) {
        this.name = name;
        this.size = size;
    }
}

// Handles all operations
class StorageManager {

    // fileName → list of versions
    Map<String, List<Version>> map = new HashMap<>();


    // UPLOAD
    public void upload(String file, String version, int size) {

        // create list if file not present
        map.putIfAbsent(file, new ArrayList<>());

        List<Version> list = map.get(file);

        // check duplicate version
        for (Version v : list) {
            if (v.name.equals(version)) {
                return;
            }
        }

        list.add(new Version(version, size));
    }


    // FETCH
    public void fetch(String file) {

        if (!map.containsKey(file)) {
            System.out.println("File Not Found");
            return;
        }

        List<Version> list = new ArrayList<>(map.get(file));

        // sort by size then version name
        Collections.sort(list, (a, b) -> {
            if (a.size != b.size) {
                return a.size - b.size;
            }
            return a.name.compareTo(b.name);
        });

        for (Version v : list) {
            System.out.println(file + " " + v.name + " " + v.size);
        }
    }


    // LATEST
    public void latest(String file) {

        if (!map.containsKey(file)) {
            System.out.println("File Not Found");
            return;
        }

        List<Version> list = map.get(file);

        Version v = list.get(list.size() - 1);

        System.out.println(file + " " + v.name + " " + v.size);
    }


    // TOTAL STORAGE
    public void totalStorage(String file) {

        if (!map.containsKey(file)) {
            System.out.println("File Not Found");
            return;
        }

        int total = 0;

        for (Version v : map.get(file)) {
            total += v.size;
        }

        System.out.println(file + " " + total);
    }
}


// Main class (only input/output)
public class filemap {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        StorageManager sm = new StorageManager();

        for (int i = 0; i < n; i++) {

            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String op = parts[0];

            if (op.equals("UPLOAD")) {

                String file = parts[1];
                String version = parts[2];
                int size = Integer.parseInt(parts[3]);

                sm.upload(file, version, size);

            } else if (op.equals("FETCH")) {

                sm.fetch(parts[1]);

            } else if (op.equals("LATEST")) {

                sm.latest(parts[1]);

            } else if (op.equals("TOTAL_STORAGE")) {

                sm.totalStorage(parts[1]);
            }
        }
    }
}
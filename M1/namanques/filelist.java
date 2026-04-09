import java.util.*;

class file {
    String fileName;
    String version;
    int size;

    file(String fileName, String version, int size) {
        this.fileName = fileName;
        this.version = version;
        this.size = size;
    }
}

class filesystem {

    List<file> fill = new ArrayList<>();

    // UPLOAD
    public void upload(String fileName, String version, int size) {

        for (file f : fill) {
            if (f.fileName.equals(fileName) && f.version.equals(version)) {
                return;
            }
        }

        fill.add(new file(fileName, version, size));
    }

    // FETCH
    public void fetch(String fileName) {

        List<file> temp = new ArrayList<>();

        for (file f : fill) {
            if (f.fileName.equals(fileName)) {
                temp.add(f);
            }
        }

        if (temp.size() == 0) {
            System.out.println("File Not Found");
            return;
        }

        Collections.sort(temp, (a, b) -> {
            if (a.size != b.size) {
                return a.size - b.size;
            }
            return a.version.compareTo(b.version);
        });

        for (file f : temp) {
            System.out.println(f.fileName + " " + f.version + " " + f.size);
        }
    }

    // LATEST
    public void latest(String fileName) {

        for (int i = fill.size() - 1; i >= 0; i--) {

            file f = fill.get(i);

            if (f.fileName.equals(fileName)) {
                System.out.println(f.fileName + " " + f.version + " " + f.size);
                return;
            }
        }

        System.out.println("File Not Found");
    }

    // TOTAL STORAGE
    public void total(String fileName) {

        int total = 0;
        boolean found = false;

        for (file f : fill) {

            if (f.fileName.equals(fileName)) {
                total += f.size;
                found = true;
            }
        }

        if (!found) {
            System.out.println("File Not Found");
            return;
        }

        System.out.println(fileName + " " + total);
    }
}

public class filelist {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        filesystem fi = new filesystem();

        int n = sc.nextInt();
        sc.nextLine();

        while (n-- > 0) {

            String s = sc.nextLine();
            String[] p = s.split(" ");
            String com = p[0];

            if (com.equals("UPLOAD")) {

                String a = p[1];
                String b = p[2];
                int c = Integer.parseInt(p[3]);

                fi.upload(a, b, c);

            } else if (com.equals("FETCH")) {

                fi.fetch(p[1]);

            } else if (com.equals("LATEST")) {

                fi.latest(p[1]);

            } else if (com.equals("TOTAL_STORAGE")) {

                fi.total(p[1]);
            }
        }
    }
}
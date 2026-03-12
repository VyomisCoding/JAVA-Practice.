import java.util.LinkedList;
import java.util.Scanner;

public class PlaylistManager{

    private static LinkedList<String> playlist = new LinkedList<>();

    private static void addSong(String songId){     // add operation
        if(!playlist.contains((songId))){
            playlist.add(songId);
        }
    }

    private static void removeSong(String songId){    // remove operation
        playlist.remove(songId);
    }

    private static void moveToTop(String songId){     // Move To TOP operation
        if(playlist.contains(songId)){
            playlist.remove(songId);
            playlist.addFirst(songId);
        }
    }

    private static void printPlaylist(){               // PRINT operation
        for(String s : playlist){
            System.out.println(s + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();      // number of operations
        sc.nextLine();             // consume newline

        for (int i = 0; i < N; i++) {

            String line = sc.nextLine().trim();
            String[] parts = line.split(" ");

            String command = parts[0];

            switch (command) {
                case "ADD":
                    addSong(parts[1]);
                    break;

                case "REMOVE":
                    removeSong(parts[1]);
                    break;

                case "TOP":
                    moveToTop(parts[1]);
                    break;

                case "PRINT":
                    printPlaylist();
                    break;

                default:
                    System.out.println("Invalid Command");
            }
        }

        sc.close();
    }

}


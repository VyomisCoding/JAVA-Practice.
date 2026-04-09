

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class Song{
    String songId;
    int duration;

    Song(String songId, int duration){
        this.songId = songId;
        this.duration = duration;
    }
}

class Playlist{
    String name;
    LinkedHashMap<String, Song> songs = new LinkedHashMap<>();

    Playlist(String name){
        this.name = name;
    }
}

class PlaylistSystem {
    Map<String, Playlist> map = new HashMap<>();

    // ADD SONG
    public void addSong(String playlistName, String songId, int duration){
        map.putIfAbsent(playlistName, new Playlist(playlistName));
        Playlist p = map.get(playlistName);

        if(!p.songs.containsKey(songId)){
            p.songs.put(songId, new Song(songId, duration));
        }
    }

    // PlayList
    public void printPlaylist(String name){
        Playlist p = map.get(name);
        
        if(p == null){
            System.out.println("Playlist Not found");
            return;
        }

        List<Song> list = new ArrayList<>(p.songs.values());

        list.sort((a, b) -> {
            if (a.duration != b.duration)
                return a.duration - b.duration;
            return a.songId.compareTo(b.songId);
        });

        for (Song s : list) {
            System.out.println(name + " " + s.songId + " " + s.duration);
        }
    }

    // recent song
    public void recentSong(String name){
        Playlist p = map.get(name);

        if(p==null || p.songs.isEmpty()){
            System.out.println("Playlist Not Found");
            return;
        }

        Song last = null;
        for (Song s : p.songs.values()) {
            last = s;
        }
        System.out.println(last.songId + " " + last.duration);
    }

    // TOTAL DURATION
    public void totalDuration(String name){
        Playlist p = map.get(name);

        if(p == null){
            System.out.println("Playlist Not Found");
            return;
        }

        int total = 0;
        for (Song s : p.songs.values()) {
            total += s.duration;
        }
        System.out.println(name + " " + total);
    }
}

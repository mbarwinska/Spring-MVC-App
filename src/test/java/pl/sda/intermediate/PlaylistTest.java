package pl.sda.intermediate;

import org.junit.jupiter.api.Test;
import pl.sda.intermediate.playlists.Movie;
import pl.sda.intermediate.playlists.Music;
import pl.sda.intermediate.playlists.PlayMode;
import pl.sda.intermediate.playlists.Playlist;

class PlaylistTest {
    @Test
    void shouldPlaySequentially() {
        //given
        Playlist playlist = new Playlist();
        Music song1 = new Music("Iron Maiden", "Aces High");
        Movie movie1 = new Movie("Take the lead");
        Movie movie2 = new Movie("Star Wars IV: New Hope");
        playlist.add(song1);
        playlist.add(movie1);
        playlist.add(movie2);

        Playlist subPlaylist = new Playlist();
        Music song11 = new Music("Kazik", "Baranek");
        Movie movie11 = new Movie("Pan Tadeusz");
        Movie movie22 = new Movie("Przedwiośnie");
        playlist.add(song11);
        playlist.add(movie11);
        playlist.add(movie22);
        playlist.add(subPlaylist);

        //when
        System.out.println(playlist.play());

    }
    @Test
    void shouldPlayRandomly() {
        //given
        Playlist playlist = new Playlist();
        playlist.setOrder(PlayMode.RANDOM);
        Music song1 = new Music("Iron Maiden", "Aces High");
        Movie movie1 = new Movie("Take the lead");
        Movie movie2 = new Movie("Star Wars IV: New Hope");
        playlist.add(song1);
        playlist.add(movie1);
        playlist.add(movie2);

        Playlist subPlaylist = new Playlist();
        Music song11 = new Music("Kazik", "Baranek");
        Movie movie11 = new Movie("Pan Tadeusz");
        Movie movie22 = new Movie("Przedwiośnie");
        playlist.add(song11);
        playlist.add(movie11);
        playlist.add(movie22);
        playlist.add(subPlaylist);

        //when
        System.out.println(playlist.play());

    }
    @Test
    void shouldPlayLoop() {
        //given
        Playlist playlist = new Playlist();
        playlist.setOrder(PlayMode.LOOP);
        Music song1 = new Music("Iron Maiden", "Aces High");
        Movie movie1 = new Movie("Take the lead");
        Movie movie2 = new Movie("Star Wars IV: New Hope");
        playlist.add(song1);
        playlist.add(movie1);
        playlist.add(movie2);

        Playlist subPlaylist = new Playlist();
        subPlaylist.setOrder(PlayMode.LOOP);
        Music song11 = new Music("Kazik", "Baranek");
        Movie movie11 = new Movie("Pan Tadeusz");
        Movie movie22 = new Movie("Przedwiośnie");
        playlist.add(song11);
        playlist.add(movie11);
        playlist.add(movie22);
        playlist.add(subPlaylist);

        //when
        System.out.println(playlist.play());

    }

}
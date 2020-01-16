package pl.sda.intermediate.playlists;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Music extends Playable {
    private String artist;
    private String title;

    @Override
    public String play() {
        return artist + " " + title;
    }
}

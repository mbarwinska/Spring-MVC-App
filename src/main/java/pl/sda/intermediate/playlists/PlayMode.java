package pl.sda.intermediate.playlists;

public enum PlayMode {
    RANDOM("losowo"),
    SEQUENTIAL("kolejno"),
    LOOP("zapętlone");

    private String plName;

    PlayMode(String name) {
        this.plName = name;
    }


}

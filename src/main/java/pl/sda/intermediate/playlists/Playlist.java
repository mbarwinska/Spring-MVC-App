package pl.sda.intermediate.playlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist extends Playable {

    public static final int OUR_DEFINITION_OF_LOOP = 10;
    private List<Playable> playableList = new ArrayList<>();
    private PlayMode playMode = PlayMode.SEQUENTIAL;


    public void add(Playable playable) {
        playableList.add(playable);
    }

    public void setOrder(PlayMode playMode) {
        this.playMode = playMode;
    }

    @Override
    public String play() {

        if (PlayMode.SEQUENTIAL == playMode) {
            return playElements(playableList);
        } else if (PlayMode.RANDOM == playMode) {
            List<Playable> playableListCopy = new ArrayList<>(playableList);
            Collections.shuffle(playableListCopy);
            return playElements(playableListCopy);
        } else {
            String listOut = "";
            for (int i = 0; i < OUR_DEFINITION_OF_LOOP; i++) {
                listOut += playElements(playableList);
            }
            return listOut;
        }
    }

    private String playElements(List<Playable> listToPlay) {
        return listToPlay.stream()
                .map(x -> x.play())
                .collect(Collectors.joining("\n","",""));

/*        String playedList = "";
        for (Playable playable : listToPlay) {
            playedList += playable.play();
        }
        return playedList;*/
    }
}

package pl.sda.intermediate.proteinChains;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ProteinChainsComparator {
    private static final String PROTEIN_CHAINS_FILE = "protein_chains.txt";

    public boolean changePossible(String s1, String s2) {
        if (s1.length() != s2.length()){
            return false;
        }
        if (s1.equals(s2)){
            return true;
        }
        String[] firstWord = s1.split("");//char[] split1 = p1.toCharArray(); - char mniej waży
        String[] secondWord = s2.split("");
        Arrays.sort(firstWord);
        Arrays.sort(secondWord);
        return Arrays.equals(firstWord, secondWord);
    }

    public List<String> readProteinChains() {
        try {
            return Files.readAllLines(Paths.get(PROTEIN_CHAINS_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

}

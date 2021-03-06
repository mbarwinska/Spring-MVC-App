package pl.sda.intermediate.proteinChains;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProteinChainsComparatorTest {
    private ProteinChainsComparator proteinChainsComparator;

    @BeforeEach
    void setUp() {
        proteinChainsComparator = new ProteinChainsComparator();
    }

    @Test
    void shouldReturnTrueWhenProteinChainsContainTheSameLetters() {
        //given
        String s1 = "BDDFPOPFRRAGCHPOPDKJKPEQAAER";
        String s2 = "BDDFPOPFRRAGCHPOPDKJKPEQAAER";
        //when
        boolean areTheSame = proteinChainsComparator.changePossible(s1, s2);
        //then
        assertTrue(areTheSame);
    }

    @Test
    void shouldReturnFalseWhenProteinChainsContainDifferentLetters() {
        //given
        String s1 = "BDDFPQPFRRAGCHPOPDKJKPEQAAER";
        String s2 = "BDDFPQPFRRAGGHPOPDKJKPEQAAER";
        //when
        boolean areTheSame = proteinChainsComparator.changePossible(s1, s2);
        //then
        assertFalse(areTheSame);
    }

    @Test
    void shouldCheckIfPairOfChainsAreTheSame() {

        List<String> proteinChains = proteinChainsComparator.readProteinChains();

        for (int i = 0; i < proteinChains.size(); i += 2) {
            boolean areTheSame = proteinChainsComparator.changePossible(proteinChains.get(i), proteinChains.get(i + 1));
            System.out.println(areTheSame);
        }
    }
}

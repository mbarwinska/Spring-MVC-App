package pl.sda.intermediate.app;

import lombok.Getter;

@Getter
public enum Countries {
    POLAND("Polska", "PL"),
    USA("USA", "US"),
    GERMANY("Niemcy", "DE"),
    FRANCE("Francja", "FR");

    String polishName;
    String symbol;

    Countries(String polishName, String symbol) {
        this.polishName = polishName;
        this.symbol = symbol;
    }
}

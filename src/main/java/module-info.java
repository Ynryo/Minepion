module fr.ynryo.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens fr.ynryo.tictactoe to javafx.fxml;
    exports fr.ynryo.tictactoe;
}
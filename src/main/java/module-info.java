module fr.ynryo.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;

    opens fr.ynryo.tictactoe to javafx.fxml;

    exports fr.ynryo.tictactoe;
    exports fr.ynryo.tictactoe.controllers;

    opens fr.ynryo.tictactoe.controllers to javafx.fxml;

    exports fr.ynryo.tictactoe.stageManager;

    opens fr.ynryo.tictactoe.stageManager to javafx.fxml;
}
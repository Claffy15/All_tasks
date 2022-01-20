import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.util.Iterator;
import java.util.Map;

import static javafx.application.Application.launch;

public class History extends Application{
    private int ScreenWidth;
    private int ScreenHeight;

    private Color RectColor;
    @Override
    public void init() throws Exception {
        super.init();
        ScreenHeight = 500;
        ScreenWidth = 1500;
        RectColor = new Color(Math.random(),Math.random(),Math.random(),1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        var root = new Pane();

        WebView webView = new WebView();
        webView.getEngine().load("http://google.com");
        VBox vBox = new VBox(webView);
        vBox.setPrefSize(ScreenWidth/2,0);
        vBox.setLayoutX(ScreenWidth/2);
        vBox.setLayoutY(0);
        vBox.prefHeightProperty().bind(root.heightProperty());
        root.getChildren().add(vBox);

        Text text = new Text();
        text.setLayoutX(0);
        text.setLayoutY(40);
        var Button = new Button("Show history!");
        Button.setLayoutX(0);
        Button.setLayoutY(0);
        Button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WebEngine webEngine = webView.getEngine();
                WebHistory history = webEngine.getHistory();

                ObservableList<WebHistory.Entry> entries = history.getEntries();

                Iterator<WebHistory.Entry> iterator = entries.iterator();
                var info = "";
                while(iterator.hasNext()){
                    WebHistory.Entry entry = iterator.next();
                    var lastText = entry.getTitle() + " " + entry.getLastVisitedDate();
                    info = info + lastText + "\n";
                    text.setText(info);
                }
            }
        });
        root.getChildren().add(Button);
        root.getChildren().add(text);

        var scene = new Scene(root, ScreenWidth, ScreenHeight);
        primaryStage.setTitle("History");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //launch app
    public static void main(String[] args) {
        launch(args);
    }
}

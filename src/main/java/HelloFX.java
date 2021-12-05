import java.text.SimpleDateFormat;
import java.util.Date;
import ru.g905.bars.Document;
import ru.g905.bars.DocumentsController;
import java.util.List;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HelloFX extends Application {
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Bars test app");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        
        Scene scene = setScene();
        
        stage.setScene(scene);
        stage.show();
    }

    private Scene setScene() {
        TableView tv = new TableView();
        
        TableColumn<Document, String> columnNumber = new TableColumn<>("Номер");
        columnNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        
        TableColumn<Document, String> columnName = new TableColumn<>("Название");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        TableColumn<Document, Date> columnDate = new TableColumn<>("Создан");
        columnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        columnDate.setCellFactory(column -> {
    TableCell<Document, Date> cell = new TableCell<Document, Date>() {
        private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        @Override
        protected void updateItem(Date item, boolean empty) {
            super.updateItem(item, empty);
            if(empty) {
                setText(null);
            }
            else {
                setText(format.format(item));
            }
        }
    };

    return cell;
});
        
        TableColumn columnActual = new TableColumn("Актуальный?");
        columnActual.setMinWidth(200);
        columnActual.setStyle("-fx-alignment: center;");
        
        columnActual.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Document, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Document, CheckBox> p) {
                Document d = p.getValue();
                CheckBox cb = new CheckBox();
                //cb.setDisable(true);
                cb.selectedProperty().setValue(d.isActive());
                
                return new SimpleObjectProperty<CheckBox>(cb);
            }
        });
        
        tv.getColumns().add(columnNumber);
        tv.getColumns().add(columnName);
        tv.getColumns().add(columnDate);
        tv.getColumns().add(columnActual);
        
        DocumentsController dc = new DocumentsController();
        List<Document> dl = dc.getAllDocuments();
        
        for (int i = 0; i < dl.size(); i++) {
            tv.getItems().add(dl.get(i));
        }
        
        VBox hbox = new VBox(tv);
        
        
        Scene scene = new Scene(hbox, 640, 480);
        scene.getStylesheets().add("bars_styles.css");
        return scene;
    }
    
    public static void main(String[] args) {
        launch();
    }

}
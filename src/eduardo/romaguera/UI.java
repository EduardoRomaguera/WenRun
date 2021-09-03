package eduardo.romaguera;

import eduardo.romaguera.bl.logic.UsuarioGestor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class UI extends Application {
    String url;
    UsuarioGestor usuarioGestor = new UsuarioGestor();
    @Override
    public void start(Stage primaryStage) throws Exception {
    boolean administrador;
    administrador = ((usuarioGestor.verificarAdministrador()).size() > 0);
    if (administrador) {
        url = "ui/Index.fxml";
        }
    else{
        url = "ui/registrarAdministrador.fxml";
    }
        Parent root = FXMLLoader.load(getClass().getResource(url));
        primaryStage.setTitle("We Run INC");
        primaryStage.setScene(new Scene(root, 1280, 720));
        Image icon = new Image("/eduardo/romaguera/ui/resources/icon.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
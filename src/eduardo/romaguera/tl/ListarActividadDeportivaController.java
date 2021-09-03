package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.ActividadDeportivaGestor;
import eduardo.romaguera.bl.logic.RetoGestor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListarActividadDeportivaController implements Initializable {
    private ActividadDeportivaGestor gestorActividadDeportiva = new ActividadDeportivaGestor();

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Usuario usuarioLogueado;
    @FXML
    private Rectangle sqrItemID;
    // Obtener el scene de un elemento
    // stage = (Stage)sqrItemID.getScene().getWindow();

    @FXML
    private TableView tblPaises;

    @FXML
    private TableColumn col1;

    @FXML
    private TableColumn col2;

    @FXML
    private TableColumn col3;


    ObservableList<Object> datos = FXCollections.observableArrayList();

    @FXML
    private Label txtNombreAdministrador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPaises.getItems().clear();

        try {
            gestorActividadDeportiva.listarActividadDeportiva().forEach(item -> datos.addAll(item));
        } catch (Exception e) {
            e.printStackTrace();
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Icono"));

        tblPaises.setItems(datos);
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void menu() throws IOException {
        redireccionar("menu");
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("menu")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/indexAdministrador.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            IndexAdministradorController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void salir() {
        System.exit(0);
    }
}



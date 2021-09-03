package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.MetodoPagoGestor;
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
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListarMetodoPagoController implements Initializable {
    private MetodoPagoGestor gestorMetodoPago = new MetodoPagoGestor();

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

    @FXML
    private TableColumn col4;

    @FXML
    private TableColumn col5;

    @FXML
    private TableColumn col6;

    ObservableList<Object> datos = FXCollections.observableArrayList();

    @FXML
    private Label txtNombreUsuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPaises.getItems().clear();
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreUsuario.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void inicializarTabla(Usuario usuario) {
        try {
            gestorMetodoPago.listarMetodoPago(usuarioLogueado).forEach(item -> datos.addAll(item));
        } catch (Exception e) {
            e.printStackTrace();
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        col2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col3.setCellValueFactory(new PropertyValueFactory<>("numeroTarjeta"));
        col4.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        col5.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        col6.setCellValueFactory(new PropertyValueFactory<>("cvv"));

        tblPaises.setItems(datos);
    }

    public void menu() throws IOException {
        redireccionar("menu");
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("menu")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/indexAtleta.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            IndexAtletaController index = loader.getController();
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



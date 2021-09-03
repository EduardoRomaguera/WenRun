package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.hito.Hito;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.HitoGestor;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListarHitoController implements Initializable {
    private HitoGestor gestorHito = new HitoGestor();
    private RetoGestor gestorReto = new RetoGestor();

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

    @FXML
    private TableColumn col7;

    ObservableList<Object> datos = FXCollections.observableArrayList();
    ObservableList<Object> datos2 = FXCollections.observableArrayList();

    @FXML
    private Label txtNombreAdministrador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Reto> listaRetos = new ArrayList<>();
        ArrayList<Hito> listaHitos = new ArrayList<>();
        tblPaises.getItems().clear();

        try {
            listaHitos = gestorHito.listarHitos();
            for (Hito hito : listaHitos) {
                listaRetos = gestorReto.listarRetos();
                for (Reto reto : listaRetos) {
                    if (reto.getCodigo().equals(hito.getReto())) {
                        hito.setReto(reto.getNombre());
                    }
                }
                datos.add(hito);
            }

            col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
            col2.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            col3.setCellValueFactory(new PropertyValueFactory<>("kilometros"));
            col4.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            col5.setCellValueFactory(new PropertyValueFactory<>("link"));
            col6.setCellValueFactory(new PropertyValueFactory<>("imagen"));
            col7.setCellValueFactory(new PropertyValueFactory<>("reto"));

            tblPaises.setItems(datos);
        } catch (Exception e) {
            e.printStackTrace();
        }
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



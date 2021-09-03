package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.hito.Hito;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.entities.retoMatriculado.RetoMatriculado;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.RetoGestor;
import eduardo.romaguera.bl.logic.RetoMatriculadoGestor;
import javafx.beans.property.SimpleStringProperty;
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

public class ListarRetoMatriculadoController implements Initializable {
    private RetoMatriculadoGestor gestorRetoMatriculado = new RetoMatriculadoGestor();

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

//    @FXML
//    private TableView tblPaises2;
//
//    @FXML
//    private TableColumn col1b;

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

    @FXML
    private TableColumn col8;

    @FXML
    private TableColumn col9;

    ObservableList<Object> datos = FXCollections.observableArrayList();
    ObservableList<Object> datos2 = FXCollections.observableArrayList();

    @FXML
    private Label txtNombreAdministrador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPaises.getItems().clear();

    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void menu() throws IOException {
        redireccionar("menu");
    }

    public void inicializarTabla(Usuario usuario) throws IOException {
        ArrayList<RetoMatriculado> listaRetosMatriculados = new ArrayList<>();
        ArrayList<Atleta> listaAtletas = new ArrayList<>();
        ArrayList<String> grupo = new ArrayList<>();

        try {
            listaRetosMatriculados = gestorRetoMatriculado.listarRetosMatriculados(usuarioLogueado);
            for (RetoMatriculado retoM : listaRetosMatriculados) {
                listaAtletas = retoM.getGrupo();
                for(Atleta atleta : listaAtletas) {
                    String datos = "";
                    datos = atleta.getNombre();
                    datos.concat("-");
                    datos.concat(atleta.getEmail());
                    datos.concat("  ");
                    grupo.add(datos);
                    datos2.addAll(grupo);
                }
                datos.addAll(retoM);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        col3.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        col4.setCellValueFactory(new PropertyValueFactory<>("kilometros"));
        col5.setCellValueFactory(new PropertyValueFactory<>("pais"));
        col6.setCellValueFactory(new PropertyValueFactory<>("medalla"));
        col7.setCellValueFactory(new PropertyValueFactory<>("estado"));
        col8.setCellValueFactory(new PropertyValueFactory<>("avance"));
        col9.setCellValueFactory(new PropertyValueFactory<>("grupo"));
//        Atleta atleta = new Atleta();
//        atleta = new PropertyValueFactory<>("grupo");
//        col1b.setCellValueFactory(new PropertyValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0))));
        tblPaises.setItems(datos);
//        tblPaises2.setItems(datos2);
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



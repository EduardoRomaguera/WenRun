package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.actividadDeportiva.ActividadDeportiva;
import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.hito.Hito;
import eduardo.romaguera.bl.entities.retoMatriculado.RetoMatriculado;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.ActividadDeportivaGestor;
import eduardo.romaguera.bl.logic.RetoMatriculadoGestor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarAvanceRetoMatriculadoControllerOLD implements Initializable {
    private RetoMatriculadoGestor gestorRetoMatriculado = new RetoMatriculadoGestor();
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

    @FXML
    private TextField txtKilometros;

    @FXML
    private ComboBox<String> txtActividad;

    ObservableList<String> actividadesLista;

    ObservableList<Object> datos = FXCollections.observableArrayList();

    @FXML
    private Label txtNombreAdministrador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPaises.getItems().clear();
        cargarActividades();
    }

    public void cargarActividades(){
        ArrayList<ActividadDeportiva> listaActividades1 = new ArrayList<>();
        ArrayList<String> listaActividades = new ArrayList<>();
        try {
            listaActividades1 = gestorActividadDeportiva.listarActividadDeportiva();
            for (ActividadDeportiva actividad: listaActividades1) {
                listaActividades.add(actividad.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        actividadesLista = FXCollections.observableArrayList();
        actividadesLista.addAll(listaActividades);
        txtActividad.setItems(actividadesLista);
    }


    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void inicializarTabla(Usuario usuario) throws IOException {
        try {
            gestorRetoMatriculado.listarRetosMatriculados(usuarioLogueado).forEach(item -> datos.addAll(item));
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
//        col9.setCellValueFactory(new PropertyValueFactory<>("amigos"));

        tblPaises.setItems(datos);

    }

    public void agregar() throws Exception {
        RetoMatriculado nuevoReto = (RetoMatriculado) tblPaises.getSelectionModel().getSelectedItem();
        Atleta atleta = new Atleta();
        atleta.setEmail(usuarioLogueado.getEmail());
        ArrayList<Atleta> grupo= new ArrayList<>();
        grupo.add(atleta);
        nuevoReto.setGrupo(grupo);
        String Registro;
        Registro = gestorRetoMatriculado.registrarAmigoRetoMatriculado(nuevoReto);
        if (Registro.equals("Good")) {
            redireccionar("menu");
        }
    }

    public void menu() throws Exception {
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



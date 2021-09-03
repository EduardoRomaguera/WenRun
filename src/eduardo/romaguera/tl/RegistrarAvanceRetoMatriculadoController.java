package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.Avance.Avance;
import eduardo.romaguera.bl.entities.actividadDeportiva.ActividadDeportiva;
import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.email.Email;
import eduardo.romaguera.bl.entities.retoMatriculado.RetoMatriculado;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.ActividadDeportivaGestor;
import eduardo.romaguera.bl.logic.AvanceGestor;
import eduardo.romaguera.bl.logic.EmailGestor;
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
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarAvanceRetoMatriculadoController implements Initializable {
    private RetoMatriculadoGestor gestorRetoMatriculado = new RetoMatriculadoGestor();
    private AvanceGestor gestorAvance = new AvanceGestor();
    private ActividadDeportivaGestor gestorActividadDeportiva = new ActividadDeportivaGestor();
    private EmailGestor cartero = new EmailGestor();

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

    @FXML
    private TextField txtKilometros;

    @FXML
    private ComboBox<String> txtActividad;

    ObservableList<String> actividadesLista;

    ObservableList<Object> datos = FXCollections.observableArrayList();
    ObservableList<Object> datos2 = FXCollections.observableArrayList();

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

    public void agregar() throws Exception {
        RetoMatriculado nuevoReto = (RetoMatriculado) tblPaises.getSelectionModel().getSelectedItem();
        Avance avance = new Avance();
        avance.setCodigoM(nuevoReto.getCodigoM());
        avance.setKilometros(Integer.valueOf(txtKilometros.getText()));
        ActividadDeportiva actividad = new ActividadDeportiva();
        actividad.setNombre(txtActividad.getValue());
        avance.setActividadDeportiva(actividad);
        String Registro;
        Registro = gestorAvance.registrarAvance(avance);
        send(nuevoReto.getNombre(), Double.valueOf(txtKilometros.getText()));
        if (Registro.equals("Good")) {
            redireccionar("menu");
        }
    }

    public void send(String nombreReto, double kilometros) throws Exception {
        String direccion = usuarioLogueado.getEmail();
        Email email = new Email(direccion, "Has registrado un avance en el reto: "+nombreReto, "" +
                "Hola "+usuarioLogueado.getNombre()+
                "   Se han registrado.  "+kilometros+
                " kilometros en el reto: "+nombreReto+
                "   ¡Ejercitarte nunca fue tan fácil!  "+
                "");
        cartero.sendEmail(email);
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



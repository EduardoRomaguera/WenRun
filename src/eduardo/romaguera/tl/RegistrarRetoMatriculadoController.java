package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.actividadDeportiva.ActividadDeportiva;
import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.email.Email;
import eduardo.romaguera.bl.entities.metodoPago.MetodoPago;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.entities.retoMatriculado.RetoMatriculado;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

public class RegistrarRetoMatriculadoController implements Initializable {
    private RetoGestor gestorReto = new RetoGestor();
    private RetoMatriculadoGestor gestorRetoMatriculado = new RetoMatriculadoGestor();
    private EmailGestor cartero = new EmailGestor();
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

    @FXML
    private TableColumn col7;

    @FXML
    private ComboBox<String> txtMetodo;

    ObservableList<Object> datos = FXCollections.observableArrayList();
    ObservableList<String> metodosLista;

    @FXML
    private Label txtNombreAdministrador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tblPaises.getItems().clear();

        try {
            gestorReto.listarRetos().forEach(item -> datos.addAll(item));
        } catch (Exception e) {
            e.printStackTrace();
        }

        col1.setCellValueFactory(new PropertyValueFactory<>("Codigo"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        col3.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        col4.setCellValueFactory(new PropertyValueFactory<>("kilometros"));
        col5.setCellValueFactory(new PropertyValueFactory<>("pais"));
        col6.setCellValueFactory(new PropertyValueFactory<>("medalla"));
        col7.setCellValueFactory(new PropertyValueFactory<>("fotografia"));

        tblPaises.setItems(datos);
    }

    public void cargarMetodos(){
        ArrayList<MetodoPago> lista = new ArrayList<>();
        ArrayList<String> listaString = new ArrayList<>();
        try {
            lista = gestorMetodoPago.listarMetodoPago(usuarioLogueado);
            for (MetodoPago metodo: lista) {
                listaString.add(String.valueOf(metodo.getNumeroTarjeta()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        metodosLista = FXCollections.observableArrayList();
        metodosLista.addAll(listaString);
        txtMetodo.setItems(metodosLista);
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }

    public void agregar() throws Exception {
        RetoMatriculado nuevoReto = new RetoMatriculado((Reto) tblPaises.getSelectionModel().getSelectedItem());
        Atleta atleta = new Atleta();
        atleta.setEmail(usuarioLogueado.getEmail());
        ArrayList<Atleta> grupo= new ArrayList<>();
        grupo.add(atleta);
        nuevoReto.setGrupo(grupo);
        nuevoReto.setEmail(usuarioLogueado.getEmail());
        nuevoReto.setEstado("pendientePago");
        String Registro;
        Registro = gestorRetoMatriculado.registrarRetoMatriculado(nuevoReto);
        send(nuevoReto.getNombre(), nuevoReto.getKilometros());
        if (Registro.equals("Good")) {
            redireccionar("menu");
        }
    }

    public void send(String nombreReto, double kilometros) throws Exception {
        String direccion = usuarioLogueado.getEmail();
        Email email = new Email(direccion, "A por todo en el reto: "+nombreReto, "" +
                "Hola "+usuarioLogueado.getNombre()+
                "   Se ha registrado el reto: "+nombreReto+
                "   Este reto tiene.  "+kilometros+" kilometros."+
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



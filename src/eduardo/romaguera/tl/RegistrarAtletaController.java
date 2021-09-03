package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.atleta.Atleta;
import eduardo.romaguera.bl.entities.canton.Canton;
import eduardo.romaguera.bl.entities.distrito.Distrito;
import eduardo.romaguera.bl.entities.email.Email;
import eduardo.romaguera.bl.entities.metodoPago.MetodoPago;
import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.provincia.Provincia;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class RegistrarAtletaController implements Initializable {

    private AtletaGestor gestorAtleta = new AtletaGestor();
    private PaisGestor gestorPais = new PaisGestor();
    private ProvinciaGestor gestorProvincia = new ProvinciaGestor();
    private CantonGestor gestorCanton = new CantonGestor();
    private DistritoGestor gestorDistrito = new DistritoGestor();
    private EmailGestor cartero = new EmailGestor();
    private String imagenURL;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Rectangle sqrItemID;
    // Obtener el scene de un elemento
    // stage = (Stage)sqrItemID.getScene().getWindow();

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtIdentificacion;
    @FXML
    private ComboBox<String> txtPais;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> txtGenero;
    @FXML
    private DatePicker txtFechaNacimiento;
    @FXML
    private ComboBox<String> txtProvincia;
    @FXML
    private ComboBox<String> txtCanton;
    @FXML
    private ComboBox<String> txtDistrito;
    @FXML
    private TextArea txtOtrasSenas;
    @FXML
    private TextField txtContrasena;
    @FXML
    private ImageView avatar;

    ObservableList<String> paisesLista;
    ObservableList<String> generosLista;
    ObservableList<String> provinciasLista;
    ObservableList<String> cantonesLista;
    ObservableList<String> distritosLista;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> listaPaises = new ArrayList<>();
        ArrayList<String> listaGeneros = new ArrayList<>();
        try {
            listaPaises = gestorPais.listarPaises();
        } catch (Exception e) {
            e.printStackTrace();
        }
        paisesLista = FXCollections.observableArrayList();
        paisesLista.addAll(listaPaises);
        txtPais.setItems(paisesLista);

        generosLista = FXCollections.observableArrayList();
        generosLista.addAll("Femenino", "Masculino", "Otro");
        txtGenero.setItems(generosLista);

        txtProvincia.setDisable(true);
        txtDistrito.setDisable(true);
        txtCanton.setDisable(true);

        cargarProvincias();
        }

        public void inicializarCostaRica(ActionEvent event) throws IOException {
            if(!txtPais.getValue().equals("Costa Rica")){
                txtProvincia.setValue(null);
                txtCanton.setValue(null);
                txtDistrito.setValue(null);
                txtProvincia.setDisable(true);
                txtCanton.setDisable(true);
                txtDistrito.setDisable(true);
                cargarProvincias();
            }else {
                txtProvincia.setDisable(false);
                txtCanton.setDisable(false);
                txtDistrito.setDisable(false);
            }
        }

        public void cargarProvincias(){
            ArrayList<String> listaProvincias = new ArrayList<>();
        try {
            listaProvincias = gestorProvincia.listarProvincias();
        } catch (Exception e) {
            e.printStackTrace();
        }
        provinciasLista = FXCollections.observableArrayList();
        provinciasLista.addAll(listaProvincias);
        txtProvincia.setItems(provinciasLista);
        }

        public void cargarCantones(ActionEvent event) throws IOException {
            ArrayList<Canton> listaCantones = new ArrayList<>();
            ArrayList<String> listaCantones2 = new ArrayList<>();
        try {
            listaCantones = gestorCanton.listarCantones();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Canton canton : listaCantones) {
        if (canton.getNombreProvincia().equals(txtProvincia.getValue())) {
            listaCantones2.add(canton.getNombre());
        }
        }
        cantonesLista = FXCollections.observableArrayList();
        cantonesLista.addAll(listaCantones2);
        txtCanton.setItems(cantonesLista);
        }

        public void cargarDistritos(ActionEvent event) throws IOException {
            ArrayList<Distrito> listaDistritos = new ArrayList<>();
            ArrayList<String> listaDistritos2 = new ArrayList<>();
            try {
                listaDistritos = gestorDistrito.listarDistritos();
            } catch (Exception e) {
                e.printStackTrace();
            }
            for (Distrito distrito : listaDistritos) {
                if (distrito.getNombreCanton().equals(txtCanton.getValue())) {
                    listaDistritos2.add(distrito.getNombre());
                }
            }
            distritosLista = FXCollections.observableArrayList();
            distritosLista.addAll(listaDistritos2);
            txtDistrito.setItems(distritosLista);
        }

    public void registrarNuevoAtleta() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            String Apellidos = txtApellidos.getText();
            String Identificacion = txtIdentificacion.getText();
            Pais Pais = new Pais((String) txtPais.getValue());
            String Email = txtEmail.getText();
            String Genero = (String) txtGenero.getValue();
            LocalDate Nacimiento = txtFechaNacimiento.getValue();
            Provincia Provincia = new Provincia((String) txtProvincia.getValue());
            Canton Canton = new Canton((String) txtCanton.getValue());
            Distrito Distrito = new Distrito((String) txtDistrito.getValue());
            String Senas = txtOtrasSenas.getText();
            String Contrasena = txtContrasena.getText();
            LocalDate presente = LocalDate.now();
            Period tiempo = Period.between(Nacimiento, presente);
            int Edad = tiempo.getYears();

            Atleta nuevoAtleta = new Atleta(Nombre, Apellidos, Identificacion, Pais, Email, Contrasena, Genero, Nacimiento, Edad, Pais, Senas, Provincia, Canton, Distrito);
            nuevoAtleta.setAvatar(imagenURL);
            String Registro;
            Registro = gestorAtleta.registrarAtleta(nuevoAtleta);
            send();
            if (Registro.equals("Good")) {
                redireccionar("login");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void avatar() throws Exception {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(stage);
        if (file != null) {
            imagenURL = file.toString().replace("\\", "/");
            File fileI = new File(imagenURL);
            Image image = new Image(fileI.toURI().toString());
            avatar.setImage(image);
        }}

    public void send() throws Exception {
        String direccion = txtEmail.getText();
        Email email = new Email(direccion, "Bienvenido a WeRun "+txtNombre.getText(), "" +
                "Bienvenido a WeRun "+txtNombre.getText()+
                "   Ahora eres parte de la plataforma para retos fit más grande del mundo.  "+
                "   Tu contraseña es: "+txtContrasena.getText()+
                "   ¡Ejercitarte nunca fue tan fácil!  "+
                "");
        cartero.sendEmail(email);
    }

    public void login(MouseEvent actionEvent) throws IOException {
        redireccionar("login");
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("login")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/index.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void salir() {
        System.exit(0);
    }

}



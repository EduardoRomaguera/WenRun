package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.email.Email;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.EmailGestor;
import eduardo.romaguera.bl.logic.UsuarioGestor;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IndexAtletaController implements Initializable {
    private UsuarioGestor gestorUsuario = new UsuarioGestor();
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Rectangle sqrItemID;
    private Usuario usuarioLogueado;
    @FXML
    private Label txtNombreUsuario;
    @FXML
    private Label txtNombreUsuario2;
    @FXML
    private  TextField txtmail;
    @FXML
    private Button send;
    @FXML
    private ImageView avatar;


    private EmailGestor cartero = new EmailGestor();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setUsuarioLogueado(Usuario usuario) throws IOException { // Setting the client-object in ClientViewController
        this.usuarioLogueado = usuario;
        txtNombreUsuario.setText(usuarioLogueado.getNombre());
        txtNombreUsuario2.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
        cargarAvatar();
    }

    public void registrarMetodoPago(MouseEvent actionEvent) throws IOException {
        redireccionar("metodoPago");
    }

    public void cargarAvatar() throws IOException {
        File fileI = new File(usuarioLogueado.getAvatar());
        Image image = new Image(fileI.toURI().toString());
        avatar.setImage(image);
    }

    public void listarMetodoPago(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarMetodoPago");
    }

    public void registrarRetoMatriculado(MouseEvent actionEvent) throws IOException {
        redireccionar("retoMatriculado");
    }

    public void listarRetoMatriculado(MouseEvent mouseEvent) throws IOException {
        redireccionar("listarRetoMatriculado");
    }

    public void invitarAmigo(MouseEvent mouseEvent) throws IOException {
        redireccionar("invitarAmigo");
    }

    public void registrarAvance(MouseEvent mouseEvent) throws IOException {
        redireccionar("registrarAvance");
    }

    public void cerrarSesion(MouseEvent actionEvent) throws IOException {
        redireccionar("login");
    }

    public void send(MouseEvent actionEvent) throws Exception {
        String direccion = txtmail.getText();
        Email email = new Email(direccion, "knock knock neo", "The Matrix has you");
        cartero.sendEmail(email);
    }

    public void redireccionar(String menu) throws IOException {
        if(menu.equals("metodoPago")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarMetodoPago.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarMetodoPagoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarMetodoPago")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarMetodoPago.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarMetodoPagoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            index.inicializarTabla(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("retoMatriculado")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarRetoMatriculado.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarRetoMatriculadoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            index.cargarMetodos();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("registrarAvance")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/registrarAvanceRetoMatriculado.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            RegistrarAvanceRetoMatriculadoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            index.inicializarTabla(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("listarRetoMatriculado")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/listarRetoMatriculado.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            ListarRetoMatriculadoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            index.inicializarTabla(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        if(menu.equals("invitarAmigo")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/InvitarAmigoRetoMatriculado.fxml"));
            root = loader.load();
            stage = (Stage)sqrItemID.getScene().getWindow();
            InvitarAmigoRetoMatriculadoController index = loader.getController();
            index.setUsuarioLogueado(usuarioLogueado);
            index.inicializarTabla(usuarioLogueado);
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
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



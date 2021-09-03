package eduardo.romaguera.tl;

import eduardo.romaguera.bl.entities.hito.Hito;
import eduardo.romaguera.bl.entities.pais.Pais;
import eduardo.romaguera.bl.entities.reto.Reto;
import eduardo.romaguera.bl.entities.usuario.Usuario;
import eduardo.romaguera.bl.logic.HitoGestor;
import eduardo.romaguera.bl.logic.PaisGestor;
import eduardo.romaguera.bl.logic.RetoGestor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrarHitoController implements Initializable {
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
    private TextField txtNombre;
    @FXML
    private TextField txtLink;
    @FXML
    private TextField txtKilometros;
    @FXML
    private ComboBox<String> txtReto;
    @FXML
    private TextField txtImagen;
    @FXML
    private TextArea txtDescripcion;

    @FXML
    private Label txtNombreAdministrador;

    ObservableList<String> retosLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<Reto> listaRetos = new ArrayList<>();
        ArrayList<String> listaRetos2 = new ArrayList<>();
        try {
            listaRetos = gestorReto.listarRetos();
            for (Reto reto: listaRetos) {
                listaRetos2.add(reto.getCodigo()+"--"+reto.getNombre());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        retosLista = FXCollections.observableArrayList();
        retosLista.addAll(listaRetos2       );
        txtReto.setItems(retosLista);
    }

    public void setUsuarioLogueado(Usuario usuario) {
        this.usuarioLogueado = usuario;
        txtNombreAdministrador.setText(usuarioLogueado.getNombre()+" "+usuarioLogueado.getApellidos());
    }
    public void registrarHito() throws Exception {
        try {
            String Nombre = txtNombre.getText();
            double Kilometros = Double.parseDouble(txtKilometros.getText());
            String Descripcion = txtDescripcion.getText();
            String Link = txtLink.getText();
            String Imagen = txtImagen.getText();
            String Reto;
            String[] Reto2 = txtReto.getValue().split("--");
            Reto = Reto2[0];
            Hito nuevoHito = new Hito(Nombre, Kilometros, Descripcion, Link, Imagen, Reto);
            String Registro;
            Registro = gestorHito.registrarHito(nuevoHito);
            if (Registro.equals("Good")) {
                redireccionar("menu");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
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



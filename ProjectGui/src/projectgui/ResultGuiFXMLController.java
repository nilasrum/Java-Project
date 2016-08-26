package projectgui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import static projectgui.Main.lim;
import static projectgui.Main.StudentReg;
import static projectgui.Main.dataOne;
import static projectgui.Main.dataThree;
import static projectgui.Main.dataTwo;
import static projectgui.Main.dataFour;
import static projectgui.Main.missAssi;
import static projectgui.Main.myMap;

/**
 *<h1>Interface to show processed data</h1>
 * Shows the matched/unmatched result as a matrix
 * @author Mursalin
 * @version 1.0.0
 */
public class ResultGuiFXMLController implements Initializable {

    static Color settcol[][][];
    static int settsrt[][][]; //assi tab txf
    static int settend[][][];
    private static Stage window;
    private int SavedColorSett[][];
    private int currassi;
    private int currtab;
    private Color black = Color.BLACK;
    
    /**
     * number of assignment
     */
    public static  int assinum;

    @FXML
    private ListView AssiNameList;
    @FXML
    private TableView<String[]> matrixOne;
    @FXML
    private TableView<String[]> matrixTwo;
    @FXML
    private TableView<String[]> matrixThree;
    @FXML
    private TableView<String[]> matrixFour;
    @FXML
    private ListView MissingAssiList;
    @FXML
    private Label missLabel;
    @FXML
    Tab tab1;
    @FXML
    Tab tab2;
    @FXML
    Tab tab3;
    @FXML
    Tab tab4;
    @FXML
    Tab tab5;
    @FXML
    TextField txField1;
    @FXML
    TextField txField2;
    @FXML
    TextField txField3;
    @FXML
    TextField txField4;
    @FXML
    TextField txField5;
    @FXML
    ColorPicker color1;
    @FXML
    ColorPicker color2;
    @FXML
    ColorPicker color3;
    @FXML
    ColorPicker color4;
    @FXML
    ColorPicker color5;
    @FXML
    Button okbutton;
    @FXML
    Button defbuton;
    
    /**
     * Initialize/clear the color settings
     */
    public ResultGuiFXMLController() {
        
        settcol = new Color[30][10][10];
        settsrt = new int[30][10][10];
        settend = new int[30][10][10];
        
        for(int i=0;i<30;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<10;k++){
                    settcol[i][j][k]=black;
                    settsrt[i][j][k] = -1;
                    settend[i][j][k] = -1;
                }
            }
        }
        this.SavedColorSett = new int[10][10];
    }

    /**
     * Initialize the Scene to show result
     * @throws Exception
     */
    public void ResScene() throws Exception {
        window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlpack/ResFXML.fxml"));
        Scene scene = new Scene(root);
        String css = ProjectGui.class.getResource("/res/DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.getIcons().add(new Image("res/frameicon.png"));
        window.show();
    }

    /**
     * Output the current table data as exel file
     */
    @FXML
    public void SaveCurrentTable(){
        DirectoryChooser  dc = new DirectoryChooser();
        File file = dc.showDialog(window);
        String path;
        if(file!=null){
                path = file.getAbsolutePath();
                OutPutExel outPutExel = new OutPutExel();
                if(tab1.isSelected())currtab=1;
                if(tab2.isSelected())currtab=2;
                if(tab3.isSelected())currtab=3;
                if(tab4.isSelected())currtab=4;
                if(tab5.isSelected())currtab=5;
                try {
                    outPutExel.OutputCurrentTable(currtab, currassi, path);
                } catch (Exception ex) {
                    Logger.getLogger(ResultGuiFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }
    
    /**
     * output all data as exel file
     */
    @FXML
    public void SaveAllTable(){
        DirectoryChooser  dc = new DirectoryChooser();
        File file = dc.showDialog(window);
        String path;
        currtab=0;
        if(file!=null){
            try {
                path = file.getAbsolutePath();
                OutPutExel outPutExel = new OutPutExel();
                outPutExel.OutPutAllTable(path);
            } catch (Exception ex) {
                Logger.getLogger(ResultGuiFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    void ActivateDefaultColor(){
        if (tab1.isSelected()) {
                
                SavedColorSett[currassi][1]=0;
                InitMatrixOne(currassi, false);
            }
            else if (tab2.isSelected()) {
               
                SavedColorSett[currassi][2]=0;
                InitMatrixTwo(currassi, false);
            }
            else if (tab4.isSelected()) {
                
                SavedColorSett[currassi][4]=0;
                InitMatrixFour(currassi);
                
            }else if(tab3.isSelected()){
                
                SavedColorSett[currassi][3]=0;
                InitMatrixThree(currassi,false);
                
            }
    }
    
    
    @FXML
    void ActivateNewColor(){
        if(tab1.isSelected())currtab = 1;
        if(tab2.isSelected())currtab = 2;
        if(tab3.isSelected())currtab = 3;
        if(tab4.isSelected())currtab = 4;
        if(tab5.isSelected())currtab = 5;
        if(currtab == 4|| currtab==5)return;
        if(!isOk())return;
        ChangeColor();
    }
    
    boolean isOk(){
        //check 
        String ret="";
        int upper = 100,low = 0;
        ret = txField1.getText()+txField2.getText()+txField3.getText()+txField4.getText()+txField5.getText();
        if(ret.isEmpty())return false;
        ret = txField1.getText();
        if(!ret.isEmpty()){
            for(int i=0;i<ret.length();i++){
                if(ret.charAt(i)>='0' && ret.charAt(i)<='9')continue;
                else {
                    settsrt[currassi][currtab][1] = -1;
                    settend[currassi][currtab][1] = -1;
                    settcol[currassi][currtab][1] = black;
                    return false;
                }
            }
            low = Integer.parseInt(ret);
            if(low>100){
                settsrt[currassi][currtab][1] = -1;
                settend[currassi][currtab][1] = -1;
                settcol[currassi][currtab][1] = black;
                return false;
            }
            settsrt[currassi][currtab][1] = low;
            settend[currassi][currtab][1] = upper;
            settcol[currassi][currtab][1] = color1.getValue();
            upper = low;
        }else{
            settsrt[currassi][currtab][1] = -1;
            settend[currassi][currtab][1] = -1;
            settcol[currassi][currtab][1] = black;
        }
        ret = txField2.getText();
        if(!ret.isEmpty()){
            for(int i=0;i<ret.length();i++){
                if(ret.charAt(i)>='0' && ret.charAt(i)<='9')continue;
                else {
                    settsrt[currassi][currtab][2] = -1;
                    settend[currassi][currtab][2] = -1;
                    settcol[currassi][currtab][2] = black;
                    return false;
                }
            }
            low = Integer.parseInt(ret);
            if(low>100){
                settsrt[currassi][currtab][2] = -1;
                settend[currassi][currtab][2] = -1;
                settcol[currassi][currtab][2] = black;
                return false;
            }
            settsrt[currassi][currtab][2] = low;
            settend[currassi][currtab][2] = upper;
            settcol[currassi][currtab][2] = color2.getValue();
            upper = low;
        }else{
            settsrt[currassi][currtab][2] = -1;
            settend[currassi][currtab][2] = -1;
            settcol[currassi][currtab][2] = black;
        }
        ret = txField3.getText();
        if(!ret.isEmpty()){
            for(int i=0;i<ret.length();i++){
                if(ret.charAt(i)>='0' && ret.charAt(i)<='9')continue;
                else {
                    settsrt[currassi][currtab][3] = -1;
                    settend[currassi][currtab][3] = -1;
                    settcol[currassi][currtab][3] = black;
                    return false;
                }
            }
            low = Integer.parseInt(ret);
            if(low>100){
                settsrt[currassi][currtab][3] = -1;
                settend[currassi][currtab][3] = -1;
                settcol[currassi][currtab][3] = black;
                return false;
            }
            settsrt[currassi][currtab][3] = low;
            settend[currassi][currtab][3] = upper;
            settcol[currassi][currtab][3] = color3.getValue();
            upper = low;
        }
        else{
            settsrt[currassi][currtab][3] = -1;
            settend[currassi][currtab][3] = -1;
            settcol[currassi][currtab][3] = black;
        }
        ret = txField4.getText();
        if(!ret.isEmpty()){
            for(int i=0;i<ret.length();i++){
                if(ret.charAt(i)>='0' && ret.charAt(i)<='9')continue;
                else {
                    settsrt[currassi][currtab][4] = -1;
                    settend[currassi][currtab][4] = -1;
                    settcol[currassi][currtab][4] = black;
                    return false;
                }
            }
            low = Integer.parseInt(ret);
            if(low>100){
                settsrt[currassi][currtab][4] = -1;
                settend[currassi][currtab][4] = -1;
                settcol[currassi][currtab][4] = black;
                return false;
            }
            settsrt[currassi][currtab][4] = low;
            settend[currassi][currtab][4] = upper;
            settcol[currassi][currtab][4] = color4.getValue();
            upper = low;
        }else{
            settsrt[currassi][currtab][4] = -1;
            settend[currassi][currtab][4] = -1;
            settcol[currassi][currtab][4] = black;
        }
        ret = txField5.getText();
        if(!ret.isEmpty()){
            for(int i=0;i<ret.length();i++){
                if(ret.charAt(i)>='0' && ret.charAt(i)<='9')continue;
                else {
                    settsrt[currassi][currtab][5] = -1;
                    settend[currassi][currtab][5] = -1;
                    settcol[currassi][currtab][5] = black;
                    return false;
                }
            }
            low = Integer.parseInt(ret);
            if(low>100){
                settsrt[currassi][currtab][5] = -1;
                settend[currassi][currtab][5] = -1;
                settcol[currassi][currtab][5] = black;
                return false;
            }
            settsrt[currassi][currtab][5] = low;
            settend[currassi][currtab][5] = upper;
            settcol[currassi][currtab][5] = color5.getValue();
            upper = low;
        }else{
            settsrt[currassi][currtab][5] = -1;
            settend[currassi][currtab][5] = -1;
            settcol[currassi][currtab][5] = black;
        }
        return true;
    }
    
    /**
     * change the color of the current tab
     */
    public void ChangeColor() {
        
        if (tab1.isSelected()) {
                
            SavedColorSett[currassi][1]=1;
            InitMatrixOne(currassi, true);
        }
        else if (tab2.isSelected()) {
               
            SavedColorSett[currassi][2]=1;
            InitMatrixTwo(currassi, true);
        }
        else if (tab3.isSelected()) {
                
            SavedColorSett[currassi][3]=1;
            InitMatrixThree(currassi,true);
        }
    }

    /**
     * reset all data of tab one 
     * @param p current assignment
     * @param flag default/user defined color settings
     */
    public void InitMatrixOne(int p, boolean flag) {

        matrixOne.getColumns().clear();
        String[][] matData = new String[lim][lim];

        for (int i = 1; i <= StudentReg.size(); i++) {
            matData[0][i] = matData[i][0] = (String) StudentReg.get(i - 1);
        }

        DoubleStringConverter sdc = new DoubleStringConverter();

        for (int i = 1; i <= StudentReg.size(); i++) {
            for (int j = 1; j <= StudentReg.size(); j++) {
                matData[i][j] = sdc.toString(dataOne[i - 1][j - 1][p]);
            }
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(matData));
        data.remove(0);//remove titles from data
        for (int i = 0; i < StudentReg.size() + 1; i++) {
            TableColumn tc = new TableColumn(matData[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setMinWidth(75);
            matrixOne.getColumns().add(tc);

            //color begin
            tc.setCellFactory(column -> {
                return new TableCell<String, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {
                            TableCell tableCell = this;
                            int k = tableCell.getIndex();
                            Tooltip.install(tableCell, new Tooltip(matData[0][k + 1]));

                            this.setAlignment(Pos.CENTER);
                            setText(item);

                            double d = sdc.fromString(item);
                            int id = (int) d;

                            if (flag == true) {
                                Color usercolor = getColor(id,1,currassi);

                                if (id >= 2013331000) {
                                    this.setTextFill(Color.SNOW);
                                    setStyle("-fx-background-color: #383838");
                                }
                                else if (usercolor.equals(black)) {
                                    this.setTextFill(Color.BLACK);
                                    setStyle("");
                                } else {
                                    this.setTextFill(usercolor);
                                    setStyle(" -fx-font-weight:bold");
                                }
                            }else{
                                if (id >= 2013331000) {
                                    this.setTextFill(Color.SNOW);
                                    setStyle("-fx-background-color: #383838");
                                }else if (id >= 80) {
                                    this.setTextFill(Color.RED);
                                    setStyle(" -fx-font-weight:bold");
                                } else if (id >= 70) {
                                    this.setTextFill(Color.RED);
                                    setStyle(" -fx-font-weight:bold");
                                } else {
                                    this.setTextFill(Color.GREEN);
                                    setStyle("");
                                }
                            }
                        }
                    }
                };
            });

            //color end*/
        }
        matrixOne.setItems(data);
    }

    /**
     * reset all data of tab two
     * @param p current assignment
     * @param flag default/user defined color settings
     */
    public void InitMatrixTwo(int p, boolean flag) {

        matrixTwo.getColumns().clear();

        String[][] matData = new String[lim][lim];

        for (int i = 1; i <= StudentReg.size(); i++) {
            matData[0][i] = matData[i][0] = (String) StudentReg.get(i - 1);
        }

        DoubleStringConverter sdc = new DoubleStringConverter();

        for (int i = 1; i <= StudentReg.size(); i++) {
            for (int j = 1; j <= StudentReg.size(); j++) {
                matData[i][j] = sdc.toString(dataTwo[i - 1][j - 1][p]);
            }
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(matData));
        data.remove(0);//remove titles from data
        for (int i = 0; i < StudentReg.size() + 1; i++) {
            TableColumn tc = new TableColumn(matData[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setMinWidth(75);
            matrixTwo.getColumns().add(tc);

            //color begin
            tc.setCellFactory(column -> {
                return new TableCell<String, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {

                            TableCell tableCell = this;
                            int k = tableCell.getIndex();
                            Tooltip.install(tableCell, new Tooltip(matData[0][k + 1]));
                            //this.setTextFill(Color.RED);
                            this.setAlignment(Pos.CENTER);
                            setText(item);

                            double d = sdc.fromString(item);
                            int id = (int) d;

                            if (flag == true) {
                                Color usercolor = getColor(id,2,currassi);

                                if (id >= 2013331000) {
                                    this.setTextFill(Color.SNOW);
                                    setStyle("-fx-background-color: #383838");
                                }
                                else if (usercolor.equals(black)) {
                                    this.setTextFill(Color.BLACK);
                                    setStyle("");
                                } else {
                                    this.setTextFill(usercolor);
                                    setStyle(" -fx-font-weight:bold");
                                }
                            }else{
                                if (id >= 2013331000) {
                                    this.setTextFill(Color.SNOW);
                                    setStyle("-fx-background-color: #383838");
                                }else if (id >= 80) {
                                    this.setTextFill(Color.RED);
                                    setStyle(" -fx-font-weight:bold");
                                } else if (id >= 70) {
                                    this.setTextFill(Color.RED);
                                    setStyle(" -fx-font-weight:bold");
                                } else {
                                    this.setTextFill(Color.GREEN);
                                    setStyle("");
                                }
                            }
                        }
                    }
                };
            });

            //color end*/
        }
        matrixTwo.setItems(data);
    }

    /**
     * reset all data of tab four
     * @param p current assignment
     */
    public void InitMatrixFour(int p) {

        matrixFour.getColumns().clear();

        String[][] matData = new String[lim][lim];

        for (int i = 1; i <= StudentReg.size(); i++) {
            matData[0][i] = matData[i][0] = (String) StudentReg.get(i - 1);
        }

        DoubleStringConverter sdc = new DoubleStringConverter();
        
        for (int i = 1; i <= StudentReg.size(); i++) {
            for (int j = 1; j <= StudentReg.size(); j++) {
                matData[i][j] = dataFour[i - 1][j - 1][p];
            }
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(matData));
        data.remove(0);//remove titles from data
        for (int i = 0; i < StudentReg.size() + 1; i++) {
            TableColumn tc = new TableColumn(matData[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setMinWidth(75);
            matrixFour.getColumns().add(tc);

            //color begin
            tc.setCellFactory(column -> {
                return new TableCell<String, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {

                            //this.setTextFill(Color.RED);
                            this.setAlignment(Pos.CENTER);
                            setText(item);

                            double d = sdc.fromString(item);
                            int id = (int) d;

                            if (id >= 2013331000) {
                                this.setTextFill(Color.SNOW);
                                setStyle("-fx-background-color: #383838");
                            } else if (id == 1) {
                                setText("MATCH");
                                this.setTextFill(Color.RED);
                                setStyle("-fx-font-weight:bold");
                            } else {
                                setText("N/A");
                                this.setTextFill(Color.GREEN);
                                setStyle("");
                            }
                        }
                    }
                };
            });

            //color end*/
        }
        matrixFour.setItems(data);
    }

    /**
     * reset all data of tab three
     * @param p current assignment
     * @param flag default/user defined color settings
     */
    public void InitMatrixThree(int p, boolean flag) {

        matrixThree.getColumns().clear();

        String[][] matData = new String[lim][lim];

        for (int i = 1; i <= StudentReg.size(); i++) {
            matData[0][i] = matData[i][0] = (String) StudentReg.get(i - 1);
        }

        DoubleStringConverter sdc = new DoubleStringConverter();

        for (int i = 1; i <= StudentReg.size(); i++) {
            for (int j = 1; j <= StudentReg.size(); j++) {
                matData[i][j] = sdc.toString(dataThree[i - 1][j - 1][p]);
            }
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(matData));
        data.remove(0);//remove titles from data
        for (int i = 0; i < StudentReg.size() + 1; i++) {
            TableColumn tc = new TableColumn(matData[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setMinWidth(75);
            matrixThree.getColumns().add(tc);

            //color begin
            tc.setCellFactory(column -> {
                return new TableCell<String, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setStyle("");
                        } else {

                            TableCell tableCell = this;
                            int k = tableCell.getIndex();
                            Tooltip.install(tableCell, new Tooltip(matData[0][k + 1]));
                            //this.setTextFill(Color.RED);
                            this.setAlignment(Pos.CENTER);
                            setText(item);

                            double d = sdc.fromString(item);
                            int id = (int) d;

                            if (flag == true) {
                                Color usercolor = getColor(id,3,currassi);

                                if (id >= 2013331000) {
                                    this.setTextFill(Color.SNOW);
                                    setStyle("-fx-background-color: #383838");
                                }
                                else if (usercolor.equals(black)) {
                                    this.setTextFill(Color.BLACK);
                                    setStyle("");
                                } else {
                                    this.setTextFill(usercolor);
                                    setStyle(" -fx-font-weight:bold");
                                }
                            }else{
                                if (id >= 2013331000) {
                                    this.setTextFill(Color.SNOW);
                                    setStyle("-fx-background-color: #383838");
                                }else if (id >= 80) {
                                    this.setTextFill(Color.RED);
                                    setStyle(" -fx-font-weight:bold");
                                } else if (id >= 70) {
                                    this.setTextFill(Color.RED);
                                    setStyle(" -fx-font-weight:bold");
                                } else {
                                    this.setTextFill(Color.GREEN);
                                    setStyle("");
                                }
                            }
                        }
                    }
                };
            });

            //color end*/
        }
        matrixThree.setItems(data);
    }

    /**
     * reset all data of tab five
     * @param p current assignment
     */
    public void InitMissing(int p) {
        ObservableList<String> missdata = FXCollections.observableArrayList();
        AllAssignment asl = new AllAssignment();
        ArrayList names = asl.AssiNames(assinum);
        for (int i = 0; i < StudentReg.size(); i++) {
            if (missAssi[i][p] == 1) {
                missdata.add((String) StudentReg.get(i));
            }
        }
        if (missdata.isEmpty() == true) {
            missLabel.setText("Everybody Submitted This Assignment");
            missLabel.getStyleClass().add("label-miss");
            missLabel.setVisible(true);
        } else {
            MissingAssiList.setItems(missdata);
            missLabel.setVisible(false);
        }
    }

    /**
     * get number of assignment
     * @param n number of assignment
     */
    public void GetAssiNum(int n) {
        assinum = n;
    }

    /**
     * set data in all tab of a single assignment 
     * @param ind assignment index
     */
    void InitAll(int ind) {

        Thread[] th = new Thread[5];
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                th[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                InitMatrixOne(ind, SavedColorSett[ind][1]==1);
                            }
                        });
                    }
                });
            }
            if (i == 1) {
                th[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                InitMatrixTwo(ind, SavedColorSett[ind][2]==1);
                            }
                        });

                    }
                });
            }
            if (i == 2) {
                th[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                InitMatrixThree(ind,SavedColorSett[ind][3]==1);
                            }
                        });
                    }
                });
            }
            if (i == 3) {
                th[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                InitMissing(ind);
                            }
                        });
                    }
                });
            }
            if (i == 4) {
                th[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                InitMatrixFour(ind);
                            }
                        });
                    }
                });
            }
            th[i].setPriority(Thread.MAX_PRIORITY);
            th[i].start();
        }

        for (int i = 0; i < 5; i++) {
            try {
                th[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(ResultGuiFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Finds saved color for given value from color settings
     * @param id value
     * @param tb tab number
     * @param as assignment number
     * @return Color
     */
    public Color getColor(int id,int tb,int as) {
        for (int i = 0; i < 5; i++) {
            if (id >= settsrt[as][tb][i] && id <= settend[as][tb][i]) {
                return settcol[as][tb][i];
            }
        }
        return black;
    }
    
    /**
     * set data to First assignment
     * get number of assignment
     * @param url URL
     * @param rb ResourceBundle
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        currassi = 0;
        currtab = 1;
        InitMatrixOne(0, SavedColorSett[0][1]==1);
        InitMatrixTwo(0, SavedColorSett[0][2]==1);
        InitMatrixThree(0, SavedColorSett[0][3]==1);
        InitMatrixFour(0);
        InitMissing(0);
        
        ObservableList<String> ndata = FXCollections.observableArrayList();
        AllAssignment asl = new AllAssignment();
        ArrayList names = asl.AssiNames(assinum);
        for (int i = 0; i < assinum; i++) {
            ndata.add((String) names.get(i));
        }
        AssiNameList.setItems(ndata);

        //icon for list
        AssiNameList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

            @Override
            public ListCell<String> call(ListView<String> arg0) {
                ListCell<String> cell = new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            Image img;
                            if (myMap.get(item) == null) {
                                img = new Image("res/icon.png");
                            } else {
                                img = new Image("res/redicon.png");
                            }
                            ImageView imgview = new ImageView(img);
                            setGraphic(imgview);
                            setText(item);
                        }
                    }
                };
                return cell;
            }
        });

        AssiNameList.setItems(ndata);

        AssiNameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String str = newValue;
                for (int i = 0; i < ndata.size(); i++) {
                    if (str.equals(ndata.get(i))) {
                        currassi = i;
                        //System.out.println("is it right "+currassi);
                        InitAll(i);
                    }
                }
            }
        });
    }
}

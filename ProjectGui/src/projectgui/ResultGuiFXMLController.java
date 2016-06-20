package projectgui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import static projectgui.Main.StudentReg;
import static projectgui.Main.dataOne;
import static projectgui.Main.dataThree;
import static projectgui.Main.dataTwo;
import static projectgui.Main.missAssi;
import static projectgui.Main.myMap;

public class ResultGuiFXMLController implements Initializable {

    private static Stage window;
    public static int assinum;

    @FXML
    private ListView AssiNameList;
    @FXML
    private TableView<String[]> matrixOne;
    @FXML
    private TableView<String[]> matrixTwo;
    @FXML
    private TableView<String[]> matrixThree;
    @FXML
    private ListView MissingAssiList;
    @FXML
    private Label missLabel;

    public void ResScene() throws Exception {
        window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ResFXML.fxml"));
        Scene scene = new Scene(root);
        String css = ProjectGui.class.getResource("DarkTheme.css").toExternalForm();
        scene.getStylesheets().add(css);
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void InitMatrixOne(int p) {

        matrixOne.getColumns().clear();

        String[][] matData = new String[100][100];

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

                            //this.setTextFill(Color.RED);
                            this.setAlignment(Pos.CENTER);
                            setText(item);

                            double d = sdc.fromString(item);
                            int id = (int) d;

                            if (id >= 2013331000) {
                                this.setTextFill(Color.SNOW);
                                setStyle("-fx-background-color: #383838");
                            } else if (id >= 80) {
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
                };
            });

            //color end*/
        }
        matrixOne.setItems(data);
    }

    public void InitMatrixTwo(int p) {

        matrixTwo.getColumns().clear();

        String[][] matData = new String[100][100];

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

                            //this.setTextFill(Color.RED);
                            this.setAlignment(Pos.CENTER);
                            setText(item);

                            double d = sdc.fromString(item);
                            int id = (int) d;

                            if (id >= 2013331000) {
                                this.setTextFill(Color.SNOW);
                                setStyle("-fx-background-color: #383838");
                            } else if (id >= 80) {
                                this.setTextFill(Color.RED);
                                setStyle("-fx-font-weight:bold");
                            } else if (id >= 70) {
                                this.setTextFill(Color.YELLOW);
                                setStyle(" -fx-font-weight:bold");
                            } else {
                                this.setTextFill(Color.GREEN);
                                setStyle("");
                            }
                        }
                    }
                };
            });

            //color end*/
        }
        matrixTwo.setItems(data);
    }

    public void InitMatrixThree(int p) {

        matrixThree.getColumns().clear();

        String[][] matData = new String[100][100];

        for (int i = 1; i <= StudentReg.size(); i++) {
            matData[0][i] = matData[i][0] = (String) StudentReg.get(i - 1);
        }

        for (int i = 1; i <= StudentReg.size(); i++) {
            for (int j = 1; j <= StudentReg.size(); j++) {
                matData[i][j] = dataThree[i - 1][j - 1][p];
            }
        }

        DoubleStringConverter sdc = new DoubleStringConverter();
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
        matrixThree.setItems(data);
    }

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

    public void GetAssiNum(int n) {
        assinum = n;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        InitMatrixOne(0);
        InitMatrixTwo(0);
        InitMatrixThree(0);
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
                            int ck = myMap.get(item);
                            Image img;
                            if (ck == 1) {
                                img = new Image("projectgui/redicon.png");
                            } else {
                                img = new Image("projectgui/icon.png");
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

        //icon for list*/
        AssiNameList.setItems(ndata);

        AssiNameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String str = newValue;
                for (int i = 0; i < ndata.size(); i++) {
                    if (str.equals(ndata.get(i))) {
                        //call all three;
                        InitMatrixOne(i);
                        InitMatrixTwo(i);
                        InitMatrixThree(i);
                        InitMissing(i);
                    }
                }

            }
        }
        );

    }

}

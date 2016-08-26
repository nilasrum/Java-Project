package projectgui;

import java.io.File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.converter.DoubleStringConverter;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import static projectgui.Main.dataOne;
import static projectgui.Main.dataThree;
import static projectgui.Main.dataTwo;
import static projectgui.Main.dataFour;
import static projectgui.Main.StudentReg;
import static projectgui.Main.missAssi;
import static projectgui.ResultGuiFXMLController.assinum;

/**
 * <h1>Output Data</h1>
 * output current/all processed assignment data as exel file
 * @author Mursalin
 * @version 1.0.0
 */
public class OutPutExel {

    /**
     * Output the current tab data as exel file
     * @param tab current tab number
     * @param assignment current assignment number 
     * @param path path to save file
     * @throws Exception
     */
    public void OutputCurrentTable(int tab, int assignment, String path) throws Exception {
        AllAssignment asl = new AllAssignment();
        ArrayList names = asl.AssiNames(assinum);
        DoubleStringConverter dsc = new DoubleStringConverter();
        String name = (String) names.get(assignment);
        File file = new File(path + "/Assignment_" + name + "_match_" + tab + ".xls");
        System.out.println(path + "/Assignment_" + name + "_match_" + tab + ".xls");
        WritableWorkbook myexel = Workbook.createWorkbook(file);
        WritableSheet mysheet = myexel.createSheet(name, 0);
        Label label, label1;
        if (tab == 5) {
            ObservableList<String> missdata = FXCollections.observableArrayList();
            for (int i = 0; i < StudentReg.size(); i++) {
                if (missAssi[i][assignment] == 1) {
                    missdata.add((String) StudentReg.get(i));
                }
            }
            if (missdata.size() == 0) {
                label = new Label(0, 0, "Submitted By All");
                mysheet.addCell(label);
                myexel.write();
                myexel.close();
                return;
            }
            for (int i = 1; i < missdata.size() + 1; i++) {
                label = new Label(0, i - 1, (String) StudentReg.get(i - 1));
                mysheet.addCell(label);
            }
            myexel.write();
            myexel.close();
            return;
        }
        for (int i = 1; i < StudentReg.size() + 1; i++) {
            label = new Label(0, i, (String) StudentReg.get(i - 1));
            label1 = new Label(i, 0, (String) StudentReg.get(i - 1));
            mysheet.addCell(label);
            mysheet.addCell(label1);
        }
        for (int i = 0; i < StudentReg.size(); i++) {
            for (int j = 0; j < StudentReg.size(); j++) {
                double num = 0.0;
                if (tab == 4) {
                    if (dataFour[j][i][assignment] == "0") {
                        name = "N/A";
                    } else {
                        name = "MATCH";
                    }
                    label = new Label(j + 1, i + 1, name);
                    mysheet.addCell(label);
                } else {
                    if (tab == 1) {
                        num = dataOne[j][i][assignment];
                    }
                    if (tab == 2) {
                        num = dataTwo[j][i][assignment];
                    }
                    if (tab == 3) {
                        num = dataThree[j][i][assignment];
                    }

                    mysheet.addCell(new Number(j + 1, i + 1, num));
                }
            }
        }
        myexel.write();
        myexel.close();
    }

    /**
     * Output All data as exel file
     * @param path path so save output
     * @throws Exception
     */
    public void OutPutAllTable(String path) throws Exception {
        AllAssignment asl = new AllAssignment();
        ArrayList names = asl.AssiNames(assinum);
        DoubleStringConverter dsc = new DoubleStringConverter();
        int reg, reg1;
        Label label, label1;
        double num = 0.0;
        for (int l = 0; l < assinum; l++) {
            String name = (String) names.get(l);
            File file = new File(path + "/Assignment_" + name + ".xls");
            WritableWorkbook myexel = Workbook.createWorkbook(file);

            WritableSheet mysheet1 = myexel.createSheet("ChaToChar", 0);
            WritableSheet mysheet2 = myexel.createSheet("CaseInsensitive", 1);
            WritableSheet mysheet3 = myexel.createSheet("IgnoreSpace", 2);
            WritableSheet mysheet4 = myexel.createSheet("Hash", 3);
            WritableSheet mysheet5 = myexel.createSheet("missing", 4);
            for (int k = 1; k <= 4; k++) {

                WritableSheet mysheet = myexel.getSheet(k - 1);
                for (int i = 1; i < StudentReg.size() + 1; i++) {

                    label = new Label(0, i, (String) StudentReg.get(i - 1));
                    label1 = new Label(i, 0, (String) StudentReg.get(i - 1));
                    mysheet.addCell(label);
                    mysheet.addCell(label1);
                }

                for (int i = 0; i < StudentReg.size(); i++) {
                    for (int j = 0; j < StudentReg.size(); j++) {
                        if (k == 4) {
                            if (dataFour[j][i][l] == "0") {
                                name = "N/A";
                            } else {
                                name = "MATCH";
                            }
                            label = new Label(j + 1, i + 1, name);
                            mysheet.addCell(label);
                        } else {
                            if (k == 1) {
                                num = dataOne[j][i][l];
                            }
                            if (k == 2) {
                                num = dataTwo[j][i][l];
                            }
                            if (k == 3) {
                                num = dataThree[j][i][l];
                            }
                            mysheet.addCell(new Number(j + 1, i + 1, num));
                        }
                    }
                }
            }
            ObservableList<String> missdata = FXCollections.observableArrayList();
            WritableSheet mysheet = myexel.getSheet(4);
            for (int i = 0; i < StudentReg.size(); i++) {
                if (missAssi[i][l] == 1) {
                    missdata.add((String) StudentReg.get(i));
                }
            }
            if (missdata.size() == 0) {
                label = new Label(0, 0, "Submitted By All");
                mysheet.addCell(label);
            }
            else{
                for (int i = 1; i < missdata.size() + 1; i++) {
                    label = new Label(0, i - 1, (String) missdata.get(i - 1));
                    mysheet.addCell(label);
                }
            }
            myexel.write();
            myexel.close();
        }
    }
}

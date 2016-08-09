package projectgui;

import java.util.*;
import javafx.concurrent.Task;
import javafx.stage.Stage;

/**
 * Main entry point to processing
 */

public class Main implements Runnable {

    public int N;
    public String pathToInputFolder = ""; //defined by user
    private FolderContent fc = new FolderContent();
    private AllAssignment als = new AllAssignment();

    static double dataOne[][][];
    static double dataTwo[][][];
    static double dataThree[][][];
    static String dataFour[][][];
    static int missAssi[][];
    static Map<String, Integer> myMap = new HashMap<String, Integer>();
    static int alim;
    static int lim;
    static ArrayList StudentReg;

    public void solve() {

        myMap.clear();
        for (int i = 0; i < lim; i++) {
            for (int j = 0; j < lim; j++) {
                for (int k = 0; k < alim; k++) {
                    dataOne[i][j][k] = 0.0;
                    dataTwo[i][j][k] = 0.0;
                    dataThree[i][j][k] = 0.0;
                    dataFour[i][j][k] = "0";
                }
            }
        }
        //load progressbar window
        ProgressWindow pw = new ProgressWindow();
        pw.LoadProgressBar(Calculationtask);//Binds progress bar progress property with task progress property
        Calculationtask.setOnSucceeded(event -> {
            AlertFXMLController alert = new AlertFXMLController();
            alert.getNum(N);
            try {
                alert.TestScene(pw.window);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread th = new Thread(Calculationtask);
        th.start();
        pw.window.setOnCloseRequest(e->ForceClose(th,pw.window));
    }

    @Override
    public void run() {
        ArrayList names = new ArrayList<String>();
        StudentReg = FolderContent.InFolder(pathToInputFolder);
        lim = StudentReg.size()+2;
        alim = N+2;
        dataOne = new double[lim][lim][alim];
        dataTwo = new double[lim][lim][alim];
        dataThree = new double[lim][lim][alim];
        dataFour = new String[lim][lim][alim];
        missAssi = new int[lim][alim];
        solve();
    }

    Task Calculationtask = new Task<Void>() {
        @Override
        public Void call() {

            ArrayList FileOne;
            ArrayList FileTwo;
            ArrayList AllAssi = als.AssiNames(N);
            String one, two, name;
            boolean f1, f2;
            CheckMissingAssi ckm = new CheckMissingAssi();
            ProcessCharToChar CharProcess = new ProcessCharToChar();
            MatchCharToChar CharMatch = new MatchCharToChar();
            CaseInsensitive CaseProcess = new CaseInsensitive();
            MatchCaseInsensitive CaseMatch = new MatchCaseInsensitive();
            HashMatchProcess HashProcess = new HashMatchProcess();
            HashMatch hashmatch = new HashMatch();
            ProcessIgnoreSpace processIgnoreSpace = new ProcessIgnoreSpace();
            MatchIgnoreSpace matchIgnoreSpace = new MatchIgnoreSpace();
            int firstname, secondname;
            GetAssignmentName gan = new GetAssignmentName();

            int ttl = 0, neu = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < StudentReg.size(); j++) {
                    ttl++;
                }
            }
            updateProgress(0, ttl);
            myMap.clear();
            for (int i = 0; i < N; i++) {
                name = (String) AllAssi.get(i);
                for (int j = 0; j < StudentReg.size(); j++) {
                    FileOne = fc.InFolder(pathToInputFolder + "/" + StudentReg.get(j));
                    f1 = ckm.isMissing(FileOne, name);
                    if (f1 == true) {
                        missAssi[j][i] = 1;
                        myMap.put(name, 1);
                    }
                    for (int k = j + 1; k < StudentReg.size(); k++) {
                        FileTwo = fc.InFolder(pathToInputFolder + "/" + StudentReg.get(k));

                        f2 = ckm.isMissing(FileTwo, name);
                        if (f2 == true) {
                            missAssi[k][i] = 1;
                            myMap.put(name, 1);
                        }
                        if (!f1 && !f2) {
                            firstname = gan.GetNameIndex(FileOne, name);
                            secondname = gan.GetNameIndex(FileTwo, name);
                            missAssi[j][i] = missAssi[k][i] = 0;
                            one = CharProcess.CharToChar(pathToInputFolder + "/" + StudentReg.get(j) + "/" + FileOne.get(firstname));
                            two = CharProcess.CharToChar(pathToInputFolder + "/" + StudentReg.get(k) + "/" + FileTwo.get(secondname));
                            dataOne[j][k][i] = dataOne[k][j][i] = CharMatch.FindMatch(one, two);
                            one = CaseProcess.tolowerall(pathToInputFolder + "/" + StudentReg.get(j) + "/" + FileOne.get(firstname));
                            two = CaseProcess.tolowerall(pathToInputFolder + "/" + StudentReg.get(k) + "/" + FileTwo.get(secondname));
                            dataTwo[j][k][i] = dataTwo[k][j][i] = CaseMatch.FindMatchCase(one, two);
                            one = HashProcess.ProcessHashMatch(pathToInputFolder + "/" + StudentReg.get(j) + "/" + FileOne.get(firstname));
                            two = HashProcess.ProcessHashMatch(pathToInputFolder + "/" + StudentReg.get(k) + "/" + FileTwo.get(secondname));
                            dataFour[j][k][i] = dataFour[k][j][i] = hashmatch.RabinKarp(one, two);
                            one = processIgnoreSpace.IgnoreSpace(pathToInputFolder + "/" + StudentReg.get(j) + "/" + FileOne.get(firstname));
                            two = processIgnoreSpace.IgnoreSpace(pathToInputFolder + "/" + StudentReg.get(k) + "/" + FileTwo.get(secondname));
                            dataThree[j][k][i] = dataThree[k][j][i] = matchIgnoreSpace.FindIgnoreSpaceMatch(one, two);
                        }
                    }
                    updateProgress(neu, ttl);
                    neu++;
                }
            }
            updateProgress(ttl, ttl);
            return null;
        }
    };
    
    void ForceClose(Thread t,Stage stage){
        stage.close();
        t.stop();
    }
}

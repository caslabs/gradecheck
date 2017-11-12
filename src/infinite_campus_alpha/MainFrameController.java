/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinite_campus_alpha;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author jeraldy
 */
public class MainFrameController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML
    private Text LatinStatus;
    @FXML
    private Text avgGPA_TEXT;
    @FXML
    private Accordion classList;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Label WelcomeLabel;

    @FXML 
    private Text missingID;
    
    @FXML
    private BarChart<?, ?> ProgressGrade;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    
    
    //init class list in classes tab
    @FXML
    public void initClass(Map<String, String> e, Map<String, Collection<String>> f) {
        
        e.entrySet().forEach(new Consumer<Map.Entry<String, String>>() {
            @Override
            public void accept(Map.Entry<String, String> entry) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                //if class exist & equals to the class w/ missign assignments, then add MISSING amount into the titledpane
                f.entrySet().stream().filter((entry1) -> (key.equals(entry1.getKey()) && entry1.getValue() != null)).forEachOrdered((entry1) -> {
                    BorderPane ClassInfo = new BorderPane();
                    Label GradeValue = new Label(value);
                    ClassInfo.setLeft(GradeValue);
                    VBox vb = new VBox();
                    vb.setAlignment(Pos.CENTER);
                    Text Title = new Text("Current Missing");
                    Title.setFill(Color.RED);
                    if (entry1.getValue().size() > 0) {
                        vb.getChildren().add(Title);
                        
                        //improve code later. testing 
                        for (String testing : entry1.getValue()) {
                            Text MissingVal = new Text(testing);
                            vb.getChildren().add(MissingVal);
                            ClassInfo.setCenter(vb);
                        }
                        TitledPane tp = new TitledPane(key + " : MISSING " + entry1.getValue().size() + " ASSIGNMENTS", ClassInfo);
                        classList.getPanes().add(tp);
                    } else {
                        //otherwise, just add classname only
                        vb.getChildren().add(new Text("Under Construction"));
                        ClassInfo.setCenter(vb);
                        TitledPane tp = new TitledPane(key, ClassInfo);
                        classList.getPanes().add(tp);
                        
                    }
                });
            }
        });
    }

    
    //status of missing assignments
    @FXML
    public void Missing(int data) {
        if (data==0) {
            missingID.setFill(Color.GREEN);
            missingID.setText("No Missing Assignments Detected");
        } else {
            missingID.setFill(Color.RED);
            missingID.setText("Total Missing Assignments : " + String.valueOf(data));
        }
    }
    
    //display name on main
    public void GrabName(String user) {
        WelcomeLabel.setMinWidth(500);
        WelcomeLabel.setPrefWidth(500);
        WelcomeLabel.setMaxWidth(500);
        WelcomeLabel.setText("Welcome " + user);
        WelcomeLabel.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            WelcomeLabel.setPrefWidth(WelcomeLabel.getText().length() * 7); // idk what this does, but it worked. 
        });
        
     //interesting fading
     FadeTransition ft = new FadeTransition(Duration.millis(7000), WelcomeLabel);
     ft.setFromValue(0.0);
     ft.setToValue(1.0);
     ft.setCycleCount(1);
     ft.setAutoReverse(true);
     ft.play();
 
    }
    

    //creates bar graph 
    public void makePlot(Map<String, String> Graph) {
                // TODO
                // Make it User Customizable
                // Make Inital Horizonatal Bar Graph Horizontally
        XYChart.Series set1 = new XYChart.Series<>();
        x.tickLabelFontProperty().set(Font.font(8));
        Graph.entrySet().forEach((entry) -> {
            String key = entry.getKey();
            String percentage = entry.getValue();
            String[] myClass = key.split(" ");
            
            ArrayList<String> list = new ArrayList<>();
            
            list.addAll(Arrays.asList(myClass));
            
            //removes class ID string
            list.remove(0);
            
            String shortClass = String.join(" ", list);
            // regex percentage number then turns into actual float number
            Pattern p = Pattern.compile("\\d+\\.\\d+");
            Matcher m = p.matcher(percentage);
            m.find();
            set1.getData().add(new XYChart.Data(shortClass, Float.valueOf(m.group())));
            
        });
        int ClassAmount = 0;
        double sumGPA = 0;
        for ( Map.Entry<String, String> entry : Graph.entrySet() ) {
            ClassAmount++;
            String percentage = entry.getValue();
            Pattern p = Pattern.compile("\\d+\\.\\d+");
            Matcher m = p.matcher(percentage);
            m.find();
            System.out.println(m.group());
            sumGPA += (Float.valueOf(m.group())/20)-1;
}
        double avgGPA = sumGPA/ClassAmount;
        System.out.println(avgGPA);
        DecimalFormat df = new DecimalFormat("#.##"); 
        avgGPA = Double.valueOf(df.format(avgGPA));
        avgGPA_TEXT.setText("Estimated GPA : " + String.valueOf(avgGPA));
        
        //sets latin gpa status
        if (avgGPA < 3.50) {
            
            LatinStatus.setText("Your " + Double.valueOf(df.format(3.50-avgGPA)) + " away from cum laude!");
        } else if (avgGPA >= 3.5 && avgGPA <= 3.7) {
            LatinStatus.setFill(Color.web("#A54BAF"));
            LatinStatus.setText("Cum Laude");
        } else if (avgGPA >= 3.8 && avgGPA <=3.99) {
            LatinStatus.setFill(Color.web("#507CED"));
            LatinStatus.setText("Magma Cum Laude");
        } else {
            LatinStatus.setFill(Color.web("#800080"));
            LatinStatus.setText("Summa Cum Laude");
        }
        ProgressGrade.getData().addAll(set1);
        
    }
    
    //Logging Out -> LoginFrame e
        @FXML
    private void LogOutAct(ActionEvent event) throws IOException {
        try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginFrame.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        
                       Stage stage = new Stage();
                       stage.setScene(new Scene(root1));
                       stage.show();

        }
        catch (IOException e) {
            System.out.println("Error");
        }
            Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinite_campus_alpha;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import java.util.Set;
import java.util.TreeSet;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.animation.PathTransitionBuilder;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 *
 * @author jeraldy
 */
public class LoginController implements Initializable {
    @FXML
    private Rectangle userLine;
    @FXML
    private Text testBox1;
    @FXML
    private Text testBox;
    @FXML
    private StackPane stack;
    @FXML
    private Rectangle rect;
    @FXML
    private Label userLabel;
    
    @FXML
    private ImageView exitButton;
    
    @FXML
    private Rectangle sceneBar;
    
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane1;
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField password;
    
    @FXML
    private Label statusCode;
    
    @FXML
    private Hyperlink schoolLink;
    
    @FXML
    private AnchorPane rootpane;
    
    @FXML
    private VBox loginScene;
    
    @FXML
    private Button LoginButton;
    
    @FXML
    private AnchorPane butroot;
            
    Element student;
    



    @FXML
    private void Logging(ActionEvent e) throws IOException {
        
        /**
                LoginButton.setOnMouseMoved(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent me) {
                //log mouse move to console, method listed below
                System.out.println("Mouse moved, x: " + me.getX() + ", y: " + me.getY() );
            }
        });
        * **/
        
        LoginButton.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent me) {
                //the event will be passed only to the circle which is on front
                Circle circle = new Circle(10.0, Color.BLUE);
                circle.setCenterX(me.getX());
                circle.setCenterY(me.getY());  
               
                System.out.println(butroot.getLayoutBounds());
                final FadeTransition ft = new FadeTransition(Duration.millis(300), circle);
                         ft.setFromValue(1.0);
                         ft.setToValue(0.0);
                        ft.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            butroot.getChildren().remove(circle);
                        }
                        });
               
                     ScaleTransition st = new ScaleTransition(Duration.millis(220), circle);
                     st.setByX(1.38f);
                     st.setByY(1.38f);
                     st.setCycleCount((int) 1.0);
                     st.setAutoReverse(true);
                     st.setOnFinished(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
			ft.play();
		};
                     });   
                     st.play();
                     butroot.getChildren().add(circle);
            }
                });
                
        
        
        
        /**
        if (username.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            statusCode.setText("Login Failed! Please fill out both username & password");
        } else {
            //connects to HAWAII Infinite Campus 
            final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36"; 
            String HawaiiCampus = "https://hawaii.infinitecampus.org/campus/hawaii.jsp";
            Connection.Response loginFormResponse;
            loginFormResponse = Jsoup.connect(HawaiiCampus)
                    .method(Method.GET)
                    .execute();
            //selects form element from HawaiiCampus
            FormElement loginForm = (FormElement)loginFormResponse.parse()
                    .select("#form_signin").first();
            
           
            //add values into form fields
            Element loginField = loginForm.select("#username").first();
            loginField.val(username.getText());
            
            Element passwordField = loginForm.select("#password").first();
            passwordField.val(password.getText());
            
            Connection.Response loginActionResponse = loginForm.submit()
                    .cookies(loginFormResponse.cookies())
                    .userAgent(USER_AGENT)
                    .execute();
           
            Document doc2 = Jsoup.connect("https://hawaii.infinitecampus.org/campus/portal/portalOutlineWrapper.xsl?x=portal.PortalOutline&contentType=text/xml&lang=en")
                    .cookies(loginActionResponse.cookies())
                    .get();
            
            String doc3 = doc2.html();
            
            Document docXML = Jsoup.parse(doc3, "", Parser.xmlParser());
            
            Element student = docXML.select("Student").first();
            Element Calendar = docXML.select("Calendar").first();
            Element Structure = docXML.select("ScheduleStructure").first();
            
            
            
            if (student != null && !student.equals("")) {
                statusCode.setText("Login Sucess!");
                //init database
                String firstName = student.attr("firstName");
                String lastName = student.attr("lastName");
                String personID = student.attr("personID");
                String schoolID = Calendar.attr("schoolID");
                String calendarID = Calendar.attr("calendarID");
                String CalenderName = Calendar.attr("calendarName");
                String Schedule = Structure.attr("structureID");
                CalenderName.replaceAll("\\s+","%20");
                
                System.out.println(CalenderName);
                String GradeLink = "https://hawaii.infinitecampus.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&personType=student&context=" + personID + "-" + calendarID + "-" + Schedule + "&personID=" + personID + "&studentFirstName=" + firstName + "&lastName=" + lastName + "&firstName=" + firstName+ "&schoolID=" + schoolID + "&calendarID=" + calendarID + "&structureID=" + Schedule + "&calendarName=" + CalenderName + "&mode=grades&x=portal.PortalGrades";
                String url = "https://hawaii.infinitecampus.org/campus/portal/portal.xsl?x=portal.PortalOutline&lang=en&personType=student&context=" + personID + "-" + calendarID + "-" + Schedule + "&personID=" + personID + "&studentFirstName=" + firstName + "&lastName=" + lastName + "&firstName=" + firstName+ "&schoolID=" + schoolID + "&calendarID=" + calendarID + "&structureID=" + Schedule + "&calendarName=" + URLEncoder.encode(CalenderName, "UTF-8") + "&mode=grades&x=portal.PortalGrades";
                Connection.Response GradeResponse;
                System.out.println(url);
                
                GradeResponse = Jsoup.connect(url)
                    .cookies(loginActionResponse.cookies())
                    .method(Method.GET)
                    .execute();
                
                Document GradeResponseDoc = GradeResponse.parse();
                Element tables = GradeResponseDoc.select("table").get(5);
                Elements SchoolClasses = tables.select("fieldset");
                Elements SchoolClassess = SchoolClasses.select("div");
                Map<String, String> Classes = new HashMap<String, String>();
                
                String baseGrade = "https://hawaii.infinitecampus.org/campus/";
                List<String> gradeLink = new ArrayList<>();
                
                for (int i=2; i< SchoolClassess.size(); i++) {
                    Element ThisClass = SchoolClassess.get(i).select("td").get(1);
                    Elements ClassInfo= ThisClass.select("a");
                    Elements GradeInfo = SchoolClassess.get(i).select("td.inProgressGrade");
                    for (Element GradeInfo1 : GradeInfo) {
                        gradeLink.add(baseGrade + ClassInfo.get(0).attr("href"));
                        Classes.put(ClassInfo.get(0).text(), GradeInfo1.text());
                    }
                }
                Set<String> treesetList = new TreeSet<String>(gradeLink);
                //System.out.println(.size());
                System.out.println(Classes);
                
                //finds missing assignments
                int MissingCounter = 0;
                
                Map<String, Collection<String>> multiMissing = new HashMap<String, Collection<String>>();
                
                for (String link : treesetList) {
                    System.out.println(link);
                    Connection.Response toGradeURL = Jsoup.connect(link)
                    .cookies(loginActionResponse.cookies())
                    .execute();
                    Document ClassPage = toGradeURL.parse();
                    Elements MissAs = ClassPage.select("span");
                    Element ClassName = ClassPage.select("div.gridTitle").first();
                    multiMissing.put(ClassName.text(), new ArrayList<String>());
                    for (Element SpecAs : MissAs) {
                        
                        if (SpecAs.text().equals("*Missing")) {
                             MissingCounter++;
                            // System.out.println(SpecAs.parent().parent().child(0).text());
                             
                             multiMissing.get(ClassName.text()).add(new String(SpecAs.parent().parent().child(0).text()));
                        }
                    }
                }
                
                    try {
                       
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFrame.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        MainFrameController main=fxmlLoader.getController();
                        main.GrabName(firstName);
                        main.makePlot(Classes);
                        main.Missing(MissingCounter);
                        main.initClass(Classes, multiMissing);
                        
                       Stage stage = new Stage();
                       stage.setScene(new Scene(root1));
                       stage.show();

                } catch (IOException e) {
                    System.out.println("Error");
                }
    
              
                
                Stage stage = (Stage) LoginButton.getScene().getWindow();
                stage.close();
    } else {
                //bad login status
                statusCode.setText("Login Failed! Invalid credentials");
            }
    }
    **/
    }
    
    @FXML
    private void MouseClick(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://hawaii.infinitecampus.org/campus/hawaii.jsp"));
        
    }
    
    @FXML
    private void MouseClick2(ActionEvent event) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://hawaii.infinitecampus.org/campus/hawaii.jsp")); //make official browser
        
    }
    
           
    
           
    boolean checkMark = false;
    
    
    public void registerStage(Stage stage) {
  EffectUtilities.makeDraggable(stage, sceneBar);
    }
    
    public void ExitScene() {
        
        final FadeTransition ft = new FadeTransition(Duration.millis(1000), rootpane);
        
                         ft.setFromValue(1.0);
                         ft.setToValue(0.0);
                        ft.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Platform.exit();
                        }
                        });
                        ft.play();
                        
                RotateTransition rt = new RotateTransition(Duration.millis(500), exitButton);
                rt.setByAngle(180);
                rt.setCycleCount(4);
                rt.setAutoReverse(true);
 
     rt.play();
    }
    
private PathTransition pathTransition;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //disable field write init
        Platform.runLater( () -> loginScene.requestFocus() );
        
        ScaleTransition test = new ScaleTransition(Duration.millis(4000), pane1);
                     test.setByY(0.5f);
                     test.setCycleCount(Animation.INDEFINITE);
                     test.setAutoReverse(true);
        
                     
        ScaleTransition test1 = new ScaleTransition(Duration.millis(4000), pane2);
                     test1.setByY(0.5f);
                     test1.setCycleCount(Animation.INDEFINITE);
                     test1.setAutoReverse(true);
                     
   test.play();
   test1.play();
   
                        ScaleTransition st = new ScaleTransition(Duration.millis(2220), userLine);
                     st.setByX(1.38f);
                     st.setByY(1.38f);
                     st.setCycleCount((int) 1.0);
                     st.setAutoReverse(true);
                     
   username.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent me) {
                //the event will be passed only to the circle which is on front

            final Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(true);
            final KeyValue kv = new KeyValue(testBox.yProperty(), -29);
            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
                       timeline.getKeyFrames().add(kf);
                        timeline.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //future reference
                        }
                        });
            timeline.play();
            
    }
   });
   
    password.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            public void handle(javafx.scene.input.MouseEvent me) {
                //the event will be passed only to the circle which is on front
                System.out.println("test");
             
                     
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            timeline.setAutoReverse(true);
            final KeyValue kv = new KeyValue(testBox1.yProperty(), -29);
            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
                        timeline.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            //future reference
                        }
                        });
            timeline.play();

            
    }
   });
}
}

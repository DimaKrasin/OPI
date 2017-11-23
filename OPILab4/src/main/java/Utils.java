import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    private final static String url = "jdbc:mysql://localhost:3306/OPI.Lab4";
    private final static String login = "root";
    private final static String password = "root";

    private static Connection connection;
    private static Statement statement;

    public static Statement getStatement() {

        try {
        if(connection==null){
                connection = DriverManager.getConnection(url,login,password);
        }

        if(statement==null){
                statement = connection.createStatement();
        }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Проблемы с getStatement");
        }

        return statement;
    }

    public void closeConnection(){
        try {
            //statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Label createLabel(String text,double x,double y,double width,double height ){
        Label label = new Label();
        label.setPrefSize(width,height);
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setText(text);

        return label;
    }

    public static Button createButton(String name, double x, double y){
        Button button = new Button();
        button.setText(name);
        button.setPrefSize(80,20);
        button.setTranslateX(x);
        button.setTranslateY(y);

        return button;
    }

    public static TextField createTextField(double x,double y,double width,double height ){
        TextField textField = new TextField();
        textField.setTranslateX(x);
        textField.setTranslateY(y);
        textField.prefHeight(height);
        textField.prefWidth(width);

        return textField;
    }

    public static SplitMenuButton createSplitMenuButton(String[] menuitems,String name,double x,double y){

        MenuItem[] menuItem= new MenuItem[menuitems.length];

        for(int i=0;i<menuitems.length;i++){
            menuItem[i] = new MenuItem(menuitems[i].toString());
        }

        SplitMenuButton splitMenuButton = new SplitMenuButton(menuItem);

        for(int i=0;i<menuitems.length;i++){

            final int j = i;
            menuItem[i].setOnAction(event -> {
                splitMenuButton.setText(menuItem[j].getText());
            });
        }

        splitMenuButton.setText(name);
        splitMenuButton.setTranslateX(x);
        splitMenuButton.setTranslateY(y);
        splitMenuButton.setWrapText(true);

        return splitMenuButton;
    }

    public static DatePicker createDatePicker(double x,double y) {
        DatePicker datePicker = new DatePicker();
        datePicker.setTranslateX(x);
        datePicker.setTranslateY(y);

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy.MM.dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("yyyy.mm.dd");

        return datePicker;
    }

}

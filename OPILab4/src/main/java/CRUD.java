import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.Date;

public class CRUD {

    DatePicker datePicker;

    public void deleteReport(ObservableList<ReportEntity> list, int index){

        try {
            Utils.getStatement().executeUpdate("DELETE FROM report WHERE id = " + list.get(index).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        list.remove(index);
    }

    public void createReport(Stage owner,ObservableList<ReportEntity> list){
        Stage createStage = new Stage();
        createStage.setTitle("Create Person");
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.initOwner(owner);

        AnchorPane root = new AnchorPane();

        TextField project_name = Utils.createTextField(10,30,20,80);
        TextField related_version = Utils.createTextField(10,70,20,80);
        TextField corrected_version = Utils.createTextField(10,110,20,80);
        TextField performer = Utils.createTextField(10,150,20,80);
        TextField project_status = Utils.createTextField(10,190,20,80);
        TextField description = Utils.createTextField(10,230,20,80);

        String[] project_type_mas = {"баг","задача"};
        SplitMenuButton project_type = Utils.createSplitMenuButton(project_type_mas,"тип меню", 250,10);
        String[] priority_mas  = {"критический","высокий","средний","низкий"};
        SplitMenuButton priority = Utils.createSplitMenuButton(priority_mas,"приоритет", 250,30);
        String[] strictness_mas ={"критическая","большая","средняя","низкая"};
        SplitMenuButton strictness = Utils.createSplitMenuButton(strictness_mas,"строгость", 250,50);
        String[] test_environment_mas ={"SIT","UAT","PDN"};
        SplitMenuButton test_environment = Utils.createSplitMenuButton(test_environment_mas,"среда тестирования", 250,70);

        datePicker = Utils.createDatePicker(250,250);

        Button btnExit = Utils.createButton("Exit",250,150);
        btnExit.setOnAction(event -> {
            createStage.close();
        });

        Button btnSave = Utils.createButton("Save",250,180);
        btnSave.setOnAction(event -> {

            String corectDatePicker = "null";

            if(datePicker.getValue() != null){
                corectDatePicker = "'"+datePicker.getValue()+"'";}

            String sql = "INSERT INTO report (" +"project_name," +
                    "project_type, priority," + "related_version," + "corrected_version," +
                    "final_date," + "performer," + "strictness," + "test_environment," +
                    "project_status," + "description" + " ) VALUES ('" + project_name.getText() +
                    "','"+ project_type.getText() +"','"+ priority.getText() +"','"+ related_version.getText()
                    +"','"+ corrected_version.getText() +"',"+ corectDatePicker +",'"+ performer.getText() +
                    "','"+ strictness.getText() +"','"+ test_environment.getText() +"','"+ project_status.getText() +
                    "','"+ description.getText() +"')";

            ReportEntity report = btnSaveAction(sql,project_name, related_version, corrected_version,performer,
                    project_status, description, project_type,
                    priority, strictness, test_environment, datePicker,list,createStage);
            list.add(report);

            corectDatePicker = "null";
        });

        root.getChildren().addAll(
                project_name, related_version, corrected_version,performer, project_status, description, project_type,
                priority, strictness, test_environment, datePicker, btnExit, btnSave,

                Utils.createLabel("Название проекта",10,10,200,10),
                Utils.createLabel("Связаная версия",10,50,200,10),
                Utils.createLabel("Исправления версия",10,90,200,10),
                Utils.createLabel("Исполнитель",10,130,200,10),
                Utils.createLabel("Статус",10,170,200,10),
                Utils.createLabel("Описание",10,210,200,10),
                Utils.createLabel("Финальная дата",250,120,200,10)
        );



        Scene scene = new Scene(root,500,300);
        createStage.setScene(scene);

        createStage.show();
    }

    public void updateReport(Stage owner,ObservableList<ReportEntity> list, int index){

        //Об'являем пустые поля

        Stage createStage = new Stage();
        createStage.setTitle("Edit Person");
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.initOwner(owner);
        AnchorPane root = new AnchorPane();
        ResultSet resultSet ;

        TextField project_name = Utils.createTextField(10,30,20,80);
        TextField related_version = Utils.createTextField(10,70,20,80);
        TextField corrected_version = Utils.createTextField(10,110,20,80);
        TextField performer = Utils.createTextField(10,150,20,80);
        TextField project_status = Utils.createTextField(10,190,20,80);
        TextField description = Utils.createTextField(10,230,20,80);

        String value_project_type = null;
        String value_priority = null;
        String value_strictness = null;
        String value_test_environment = null;

        datePicker = Utils.createDatePicker(250,250);

        //Даём значения полям

        try {
            resultSet = Utils.getStatement().executeQuery("SELECT * FROM report WHERE id = " + list.get(index).getId());

            while (resultSet.next()){
                project_name.setText(resultSet.getString(1+1));
                related_version.setText(resultSet.getString(4+1));
                corrected_version.setText(resultSet.getString(5+1));
                performer.setText(resultSet.getString(7+1));
                project_status.setText(resultSet.getString(10+1));
                description.setText(resultSet.getString(11+1));

                value_project_type = resultSet.getString(2);
                value_priority = resultSet.getString(3);
                value_strictness = resultSet.getString(8);
                value_test_environment = resultSet.getString(9);

                datePicker.setValue(resultSet.getDate(7).toLocalDate());
            }


        }catch (SQLException e){System.out.println(e);}

        String[] project_type_mas = {"баг","задача"};
        SplitMenuButton project_type = Utils.createSplitMenuButton(project_type_mas,value_project_type, 250,10);
        String[] priority_mas  = {"критический","высокий","средний","низкий"};
        SplitMenuButton priority = Utils.createSplitMenuButton(priority_mas,value_priority, 250,30);
        String[] strictness_mas ={"критическая","большая","средняя","низкая"};
        SplitMenuButton strictness = Utils.createSplitMenuButton(strictness_mas,value_strictness, 250,50);
        String[] test_environment_mas ={"SIT","UAT","PDN"};
        SplitMenuButton test_environment = Utils.createSplitMenuButton(test_environment_mas,value_test_environment, 250,70);

        Button btnExit = Utils.createButton("Exit",250,150);
        btnExit.setOnAction(event -> {
            createStage.close();
        });

        Button btnSave = Utils.createButton("Save",250,180);
        btnSave.setOnAction(event -> {
            String corectDatePicker = "null";

            if(datePicker.getValue() != null){
                corectDatePicker = "'"+datePicker.getValue()+"'";}

            String sql = "update report set project_name = '"+project_name
                    +"',project_type = '"+project_type
                    +"',priority = '"+ priority
                    +"',related_version = '"+ related_version
                    +"',corrected_version = '"+ corrected_version
                    +"',final_date ="+ corectDatePicker
                    +",performer = '"+performer
                    +"',strictness ='"+ strictness
                    +"',test_environment='"+ test_environment
                    +"',project_status'"+project_status
                    +"',description='"+ description+"'"
                    +"where id = "+list.get(index).getId();

            ReportEntity report = btnSaveAction(sql,project_name, related_version, corrected_version,performer,
                    project_status, description, project_type,
                    priority, strictness, test_environment, datePicker,list,createStage);

            corectDatePicker = "null";

            list.remove(index);
            list.add(index,report);
        });

        root.getChildren().addAll(
                project_name,related_version,corrected_version,performer,project_status,description,
                project_type,priority,strictness,test_environment,
                datePicker
        );


        Scene scene = new Scene(root,500,300);
        createStage.setScene(scene);

        createStage.show();
    }

    private boolean correctReport(String project_name, String project_type, String priority,
                                         String corrected_version, String performer, String strictness,
                                         String test_environment, String project_status, String description
                                         ){
        boolean corect = true;
        if(project_name == null ) corect = false;
        if(project_type != "задача" && project_type != "баг") corect = false;
        if(priority != "критический" && priority != "высокий" && priority !="средний" && priority !="низкий") corect = false;
        if(corrected_version == null ) corect = false;
        if(performer == null ) corect = false;
        if (strictness != "критическая" && strictness !="большая"&& strictness !="средняя" && strictness !="низкая") corect = false;
        if(test_environment != "SIT" && test_environment != "UAT" && test_environment !="PDN") corect = false;
        if(project_status == null ) corect = false;
        if(description == null ) corect = false;



        return corect;
    }

    private ReportEntity btnSaveAction(String sql, TextField project_name, TextField related_version,
                                       TextField corrected_version,TextField performer,
                                       TextField project_status, TextField description,  SplitMenuButton  project_type,
                                       SplitMenuButton priority,  SplitMenuButton strictness,  SplitMenuButton test_environment,
                                       DatePicker datePicker,ObservableList<ReportEntity> list, Stage createStage){

        ReportEntity report = null;

        if(
                correctReport(project_name.getText(),project_type.getText(),priority.getText(),corrected_version.getText(),
                        performer.getText(),strictness.getText(),test_environment.getText(),
                        project_status.getText(),description.getText())
                ){

            Date date = null;
            if(datePicker.getValue() != null){

                date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                System.out.println(date);
            }

            try {
                Utils.getStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            report = new ReportEntity(project_name.getText(),project_type.getText(),priority.getText(),
                    related_version.getText(),corrected_version.getText(), date,performer.getText(),
                    strictness.getText(),test_environment.getText(),project_status.getText(),description.getText());



        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(createStage);
            alert.setTitle("Ahtung!");
            alert.setHeaderText("Обязательные поля не заполнены!");


            alert.showAndWait();
        }
        createStage.close();

        return report;
    }
}

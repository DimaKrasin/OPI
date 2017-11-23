import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
            if(
            correctReport(project_name.getText(),project_type.getText(),priority.getText(),corrected_version.getText(),
                    performer.getText(),strictness.getText(),test_environment.getText(),
                    project_status.getText(),description.getText())
            ){

                Date date = null;
                String corectDatePicker = "null";
                if(datePicker.getValue() != null){
                    corectDatePicker = "'"+datePicker.getValue()+"'";
                    date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                }

                try {
                   Utils.getStatement().executeUpdate("INSERT INTO report (" +"project_name," +
                           "project_type, priority," + "related_version," + "corrected_version," +
                           "final_date," + "performer," + "strictness," + "test_environment," +
                           "project_status," + "description" + " ) VALUES ('" + project_name.getText() +
                           "','"+ project_type.getText() +"','"+ priority.getText() +"','"+ related_version.getText()
                           +"','"+ corrected_version.getText() +"',"+ corectDatePicker +",'"+ performer.getText() +
                           "','"+ strictness.getText() +"','"+ test_environment.getText() +"','"+ project_status.getText() +
                           "','"+ description.getText() +"')");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                ReportEntity report = new ReportEntity(project_name.getText(),project_type.getText(),priority.getText(),
                        related_version.getText(),corrected_version.getText(), date,performer.getText(),
                        strictness.getText(),test_environment.getText(),project_status.getText(),description.getText());

                list.addAll(report);

            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(createStage);
                alert.setTitle("Ahtung!");
                alert.setHeaderText("ТЫ");
                alert.setContentText("МУДЕНЬ!!!!!!");

                alert.showAndWait();
            }

            createStage.close();
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
        Stage createStage = new Stage();
        createStage.setTitle("Edit Person");
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.initOwner(owner);
        AnchorPane root = new AnchorPane();

        


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
}

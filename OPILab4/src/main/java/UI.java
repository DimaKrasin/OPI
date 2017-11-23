import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class UI extends Application{

    private CRUD crud = new CRUD();

    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        stage.setTitle("Bag tracker");

        TableView<ReportEntity> table = createTableView();
        ObservableList<ReportEntity> list = readDB();
        table.setItems(list);

        Button btnUpdate = Utils.createButton("Update",700,550);
        btnUpdate.setOnAction(event -> {
            try {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if(selectedIndex != 0){crud.updateReport(stage,list,selectedIndex);}
            }catch (ArrayIndexOutOfBoundsException e){

            }
        });


        Button btnCreate = Utils.createButton("Create",600,550);
        btnCreate.setOnAction(event -> {
            crud.createReport(stage,list);
        });

        Button btnDelete = Utils.createButton("Delete",800,550);
        btnDelete.setOnAction(event -> {
            try {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if(selectedIndex != 0){crud.deleteReport(list,selectedIndex);}
            }catch (ArrayIndexOutOfBoundsException e){

            }
        });

        root.getChildren().addAll(table,btnCreate,btnUpdate,btnDelete);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.show();
    }

    private static TableView<ReportEntity> createTableView(){

        TableView<ReportEntity> table = new TableView<ReportEntity>();

        TableColumn<ReportEntity, String> project_name
                = new TableColumn<ReportEntity, String>("Название");
        TableColumn<ReportEntity, String> project_type
                = new TableColumn<ReportEntity, String>("Тип");
        TableColumn<ReportEntity, String> priority
                = new TableColumn<ReportEntity, String>("Приоритет");
        TableColumn<ReportEntity, String> related_version
                = new TableColumn<ReportEntity, String>("Связаная версия");
        related_version.setPrefWidth(138);

        TableColumn<ReportEntity, String> corected_version
                = new TableColumn<ReportEntity, String>("Исправленая версия");
        corected_version.setPrefWidth(138);

        TableColumn<ReportEntity, Date> final_date
                = new TableColumn<ReportEntity, Date>("Конечная дата");
        final_date.setPrefWidth(138);

        TableColumn<ReportEntity, String> performer
                = new TableColumn<ReportEntity, String>("Исполнитель");
        performer.setPrefWidth(138);

        TableColumn<ReportEntity, String> strictness
                = new TableColumn<ReportEntity, String>("Строгость");
        TableColumn<ReportEntity, String> test_environment
                = new TableColumn<ReportEntity, String>("Среда тестирования");
        test_environment.setPrefWidth(138);

        TableColumn<ReportEntity, String> project_status
                = new TableColumn<ReportEntity, String>("Статус");
        TableColumn<ReportEntity, String> description
                = new TableColumn<ReportEntity, String>("Описание");

        table.getColumns().addAll(
                project_name,
                project_type,
                priority,
                related_version,
                corected_version,
                final_date,
                performer,
                strictness,
                test_environment,
                project_status,
                description
                );

        project_name.setCellValueFactory(new PropertyValueFactory<>("project_name"));
        project_type.setCellValueFactory(new PropertyValueFactory<>("project_type"));
        priority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        related_version.setCellValueFactory(new PropertyValueFactory<>("related_version"));
        corected_version.setCellValueFactory(new PropertyValueFactory<>("corrected_version"));
        final_date.setCellValueFactory(new PropertyValueFactory<>("final_date"));
        strictness.setCellValueFactory(new PropertyValueFactory<>("strictness"));
        test_environment.setCellValueFactory(new PropertyValueFactory<>("test_environment"));
        project_status.setCellValueFactory(new PropertyValueFactory<>("project_status"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        performer.setCellValueFactory(new PropertyValueFactory<>("performer"));

        return table;
    }

    private static ObservableList<ReportEntity>  readDB(){

        ObservableList<ReportEntity> list = FXCollections.observableArrayList();

        String sqlGetAll = "Select * From report ; ";

        try {
            ResultSet resultSet = Utils.getStatement().executeQuery(sqlGetAll);
            while (resultSet.next()){
                ReportEntity report = new ReportEntity(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12)
                        );
                list.add(report);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



}

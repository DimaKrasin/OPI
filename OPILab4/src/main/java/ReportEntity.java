import java.util.Date;

public class ReportEntity {

    private int id;
    private String project_name;
    private String project_type;
    private String priority;
    private String related_version;
    private String corrected_version;
    private Date final_date;
    private String performer;
    private String strictness;
    private String test_environment;
    private String project_status;
    private String description;

    public ReportEntity(int id,
                        String project_name,
                        String project_type,
                        String priority,
                        String related_version,
                        String corrected_version,
                        Date final_date,
                        String performer,
                        String strictness,
                        String test_environment,
                        String project_status,
                        String description) {
        this.id = id;
        this.project_name = project_name;
        this.project_type = project_type;
        this.priority = priority;
        this.related_version = related_version;
        this.corrected_version = corrected_version;
        this.final_date = final_date;
        this.performer = performer;
        this.strictness = strictness;
        this.test_environment = test_environment;
        this.project_status = project_status;
        this.description = description;
    }

    public ReportEntity(String project_name,
                        String project_type,
                        String priority,
                        String related_version,
                        String corrected_version,
                        Date final_date,
                        String performer,
                        String strictness,
                        String test_environment,
                        String project_status,
                        String description) {
        this.project_name = project_name;
        this.project_type = project_type;
        this.priority = priority;
        this.related_version = related_version;
        this.corrected_version = corrected_version;
        this.final_date = final_date;
        this.performer = performer;
        this.strictness = strictness;
        this.test_environment = test_environment;
        this.project_status = project_status;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getRelated_version() {
        return related_version;
    }

    public void setRelated_version(String related_version) {
        this.related_version = related_version;
    }

    public String getCorrected_version() {
        return corrected_version;
    }

    public void setCorrected_version(String corrected_version) {
        this.corrected_version = corrected_version;
    }

    public Date getFinal_date() {
        return final_date;
    }

    public void setFinal_date(Date final_date) {
        this.final_date = final_date;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getStrictness() {
        return strictness;
    }

    public void setStrictness(String strictness) {
        this.strictness = strictness;
    }

    public String getTest_environment() {
        return test_environment;
    }

    public void setTest_environment(String test_environment) {
        this.test_environment = test_environment;
    }

    public String getProject_status() {
        return project_status;
    }

    public void setProject_status(String project_status) {
        this.project_status = project_status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", project_type='" + project_type + '\'' +
                ", priority='" + priority + '\'' +
                ", related_version='" + related_version + '\'' +
                ", corrected_version='" + corrected_version + '\'' +
                ", final_date=" + final_date +
                ", performer='" + performer + '\'' +
                ", strictness='" + strictness + '\'' +
                ", test_environment='" + test_environment + '\'' +
                ", project_status='" + project_status + '\'' +
                ", description='" + description + '\'' +
                '}'+"\n";
    }
}

package entity.request.employeePayloads;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.ArrayList;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Data{
    public String firstName;
    public String middleName;
    public String lastName;
    public String locationId;
    public String joinedDate;
    public boolean autoGenerateEmployeeId;
    public Object employeeId;
    public String emp_gender;
    public String emp_marital_status;
    public String nation_code;
    public String ssn;
    public String emp_birthday;
    public Object emp_dri_lice_exp_date;
    public String joined_date;
    public String probation_end_date;
    public String date_of_permanency;
    public String job_title_id;
    public String employment_status_id;
    public String job_category_id;
    public String subunit_id;
    public String location_id;
    public String work_schedule_id;
    public String cost_centre_id;
    public String has_contract_details;
    public Object contract_start_date;
    public Object contract_end_date;
    public String comment;
    public String effective_date;
    public String event_id;
    public Data data;
    public String eventTemplate;
    public String dueDate;
    public ArrayList<String> owners;
    public String licenseNo;
    public String otherId;
}
package entity.request.employeePayloads;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EmployeDeletePayload {
    public String reason;
    public String date;
    public String time;
    public String timezone;
    public String note;
    public String flaged;
    public String terminateNow;
}

package entity.request.employeePayloads;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class EmployeeCreatePayload{
    public String endpoint;
    public String method;
    public Data data;
    public Params params;
}
package entity.response.employeeList;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class EmployeeDataRoot{
    public ArrayList<Datum> data;
    public Meta meta;
}

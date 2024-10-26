package services;

import base.CommonServices;
import constants.EmployeeAPIEndPoint;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class EmployeeListServices extends CommonServices {


    public Response getListOfEmployee() {
//        Map<String, String> queryParam = new HashMap<>();
//        queryParam.put("include","supervisors,jobTitle,locations,costCentre");
//        queryParam.put("page[limit]","200");

//        setQueryParameters(queryParam);

        return executeGetAPI(EmployeeAPIEndPoint.GET_EMPLOYEE_LIST);
    }

    public Response createEmployee(Object payload) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(EmployeeAPIEndPoint.CREATE_EMPLOYEE);
    }

    public Response updateDetailsEmployee(Object payload, String employeeNumber) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(String.format(EmployeeAPIEndPoint.UPDATE_EMPLOYEE, employeeNumber));
    }


    public Response deleteEmpDetails(Object payload, String employeeNumber) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(String.format(EmployeeAPIEndPoint.DELETE_EMP_DETAILS, employeeNumber));
    }


}

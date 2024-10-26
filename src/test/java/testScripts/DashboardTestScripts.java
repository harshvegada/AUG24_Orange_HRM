package testScripts;

import base.CommonServices;
import entity.response.employeeList.EmployeeDataRoot;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.DashboardServices;
import services.EmployeeListServices;

import java.util.List;

public class DashboardTestScripts {

    DashboardServices dashboardServices = new DashboardServices();
    EmployeeListServices employeeListServices = new EmployeeListServices();

    @BeforeMethod
    public void testSetup() {
        CommonServices.generateTokenFor("admin", "@sWFA0Umt7");
    }

    @Test
    public void widgetAPI() {
        Response response = dashboardServices.getWidget();
        List<String> stringList = response.jsonPath().getList("data.title");
        List value = response.jsonPath().get("data.findAll { it.widgetGlobalConfig.isEnabled == true }.title");
        System.out.println(value);
    }


    @Test
    public void employeeTest() {
        Response response = employeeListServices.getListOfEmployee();
        System.out.println("Total Employee In System : " + response.jsonPath().get("data.size()").toString());

//          If empNumber is in numeric
//        List<String> listOfName = response.jsonPath().get("data.findAll { it.empNumber > 150 }.firstName");

//        If empNumber is not in numeric
//        for (int i = 0; i < Integer.parseInt(response.jsonPath().get("data.size()").toString()); i++) {
//            int empNumber = Integer.parseInt(response.jsonPath().getString("data[" + i + "].empNumber"));
//            if(empNumber > 150){
//                System.out.println(response.jsonPath().getString("data[" + i + "].firstName"));
//            }
//        }
//        System.out.println(listOfName);


//        De-seralization -> jakson-datbind & lombok
        EmployeeDataRoot employeeDataRoot = response.as(EmployeeDataRoot.class);

        for (int i = 0; i < employeeDataRoot.getData().size(); i++) {
            int empNumber = Integer.parseInt(employeeDataRoot.getData().get(i).empNumber);
            if (empNumber > 150) {
                System.out.println(employeeDataRoot.getData().get(i).firstName);
            }
        }
    }
}
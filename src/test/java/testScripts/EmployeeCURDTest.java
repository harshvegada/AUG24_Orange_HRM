package testScripts;

import base.CommonServices;
import constants.HttpStatusCode;
import constants.StatusCode;
import entity.request.employeePayloads.Data;
import entity.request.employeePayloads.EmployeDeletePayload;
import entity.request.employeePayloads.EmployeeCreatePayload;
import entity.request.employeePayloads.Params;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.EmployeeListServices;
import utility.TestData;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCURDTest {

    EmployeeListServices employeeListServices = new EmployeeListServices();

    @BeforeMethod
    public void testSetup() {
        CommonServices.generateTokenFor("admin", "@sWFA0Umt7");
    }

    @Test
    public void employeeCreation() {
        String firstName = TestData.getFirstName();
        String middleName = TestData.getMiddleName();
        String lastName = TestData.getLastName();
        String empBirthDay = TestData.getBirthdayDate();
        String ssn = TestData.getSSN();
        String joinDate = "2024-10-26";
        String licenceNumber = TestData.getLicenceNumber();
        String futureDate = TestData.getFutureDate();

        List<EmployeeCreatePayload> payload = new ArrayList<>();

        ArrayList<String> ownerList = new ArrayList<>();
        ownerList.add("29");
        ownerList.add("38");
        ownerList.add("40");
        Data subData = Data.builder().eventTemplate("1").dueDate("2024-11-10").owners(ownerList).build();

        EmployeeCreatePayload firstObject = EmployeeCreatePayload.builder().endpoint("employees").method("POST").params(Params.builder().build()).data(Data.builder().autoGenerateEmployeeId(true).firstName(firstName).middleName(middleName).lastName(lastName).locationId("3").joinedDate(joinDate).employeeId(null).build()).build();
        EmployeeCreatePayload secondObject = EmployeeCreatePayload.builder().endpoint("employees/<EMPNUMBER>").method("PATCH").params(Params.builder().build()).data(Data.builder().firstName(firstName).middleName(middleName).lastName(lastName).emp_gender("1").emp_marital_status("Married").nation_code("82").ssn(ssn).emp_birthday(empBirthDay).emp_dri_lice_exp_date(null).build()).build();
        EmployeeCreatePayload thirdObject = EmployeeCreatePayload.builder().endpoint("employees/<EMPNUMBER>/job").method("PATCH").params(Params.builder().build()).data(Data.builder().joined_date(joinDate).probation_end_date("2024-10-31").date_of_permanency("2024-11-04").job_title_id("48").employment_status_id("3").job_category_id("3").subunit_id("3").location_id("3").work_schedule_id("11").cost_centre_id("10").has_contract_details("1").contract_start_date(null).contract_end_date(null).comment("Job Detailed Updated").effective_date("2024-10-26").event_id("1").build()).build();
        EmployeeCreatePayload fourthObject = EmployeeCreatePayload.builder().endpoint("employeeOnboarding/<EMPNUMBER>/create").method("POST").params(Params.builder().build()).data(Data.builder().data(subData).build()).build();

        payload.add(firstObject);
        payload.add(secondObject);
        payload.add(thirdObject);
        payload.add(fourthObject);

        Allure.step("Creating Employee");
        //Create Employee
        Response response = employeeListServices.createEmployee(payload);
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.statusCode(), HttpStatusCode.OK.getStatusCode());

        String empNumber = response.jsonPath().getString("data.empNumber");
        String employeeId = response.jsonPath().getString("data.employeeId");

//        response.then().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePaths.CREATE_EMP_SCHEMA)));

        //Update Employee Details
        Allure.step("Updating Employee " + empNumber);
        Data updateEmployeeDataPayload = Data.builder().firstName(firstName).middleName(middleName).lastName(lastName).otherId("").emp_gender("1").emp_marital_status("Married").nation_code("82").licenseNo(licenceNumber).employeeId(employeeId).ssn(ssn).emp_birthday(empBirthDay).emp_dri_lice_exp_date(futureDate).build();

        Response updateEmpResponse = employeeListServices.updateDetailsEmployee(updateEmployeeDataPayload, empNumber);
        Assert.assertEquals(updateEmpResponse.statusCode(), HttpStatusCode.OK.getStatusCode());
        Assert.assertEquals(updateEmpResponse.jsonPath().getString("messages"), "Successfully Saved");

//        updateEmpResponse.then().body(JsonSchemaValidator.matchesJsonSchema(new File(FilePaths.CREATE_EMP_SCHEMA)));

        Allure.step("Deleting Employee " + empNumber);
        EmployeDeletePayload deleteEmpPayload = EmployeDeletePayload.builder()
                .reason("11")
                .date(joinDate)
                .time("10:48")
                .timezone("Etc/GMT+5")
                .note(String.valueOf(System.currentTimeMillis()))
                .flaged("0")
                .terminateNow("1").build();


        Response deletePayload = employeeListServices.deleteEmpDetails(deleteEmpPayload, empNumber);
        Assert.assertEquals(deletePayload.statusCode(), HttpStatusCode.CREATED.getStatusCode());
        Assert.assertEquals(deletePayload.jsonPath().getString("messages.success"), "Successfully Saved");
    }
}

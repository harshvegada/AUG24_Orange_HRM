package testScripts;

import base.CommonServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.HttpStatusCode;
import entity.request.skillPayload.CreateSkillPayload;
import entity.request.skillPayload.DeleteSkillPayload;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import services.SkillServices;
import utility.TestData;

import java.util.ArrayList;
import java.util.List;

public class SkillTestScripts {

    SkillServices skillServices = new SkillServices();

    @BeforeMethod
    public void testSetup() {
        CommonServices.generateTokenFor("admin", "@sWFA0Umt7");
    }

    @Test
    public void skillCURDOperation() throws JsonProcessingException {
        Allure.step("Creating New SKill");
        CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name(TestData.getSkillName()).description(TestData.getSkillDescription()).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(createSkillPayload);

        Response createSkillResponse = skillServices.createSkill(payload);
        String skillID = createSkillResponse.jsonPath().getString("data.id");

        System.out.println("Skill ID : " + skillID);
        Allure.step("SKill ID : "+ skillID);

        Allure.step("Getting All Skills");
        Response getSkilResponse = skillServices.getSkillList();

        List<String> skillIds = getSkilResponse.jsonPath().getList("data.id");
        Assert.assertListContainsObject(skillIds, skillID, "Create skill id not present in List of skills");
        Assert.assertTrue(skillIds.contains(skillID), "Create skill id not present in List of skills");

        Response getSkillResponse = skillServices.getSkillList();
        List<String> skillIDList = getSkillResponse.jsonPath().getList("data.id");
        Assert.assertListContainsObject(skillIDList, skillID, "Skill not present");
        Assert.assertTrue(skillIDList.contains(skillID), "Skill not present");
        Assert.assertEquals(getSkillResponse.statusCode(), HttpStatusCode.OK.getStatusCode());
        System.out.println("New Skill successfully retrieved !");

        Allure.step("Updated Skills for "+ skillID);
        Response updateSkillResponse = skillServices.updateSkill(payload, skillID);
        System.out.println(updateSkillResponse.asPrettyString());
        Assert.assertEquals(updateSkillResponse.statusCode(), HttpStatusCode.OK.getStatusCode());
        System.out.println("Skill successfully updated ! Skill ID : " + skillID);


        ArrayList<String> skillList = new ArrayList();
        skillList.add(skillID);
        DeleteSkillPayload deleteSkillPayload = DeleteSkillPayload.builder().data(skillList).build();

        Allure.step("Deleting Skills");
        Response deleteSkillResponse = skillServices.deleteSkill(deleteSkillPayload);
        Response getSkillResponseDeleted = skillServices.getSkillList();
        List<String> skillIDListDeleted = getSkillResponseDeleted.jsonPath().getList("data.id");
        Assert.assertListNotContainsObject(skillIDListDeleted, skillID, "Skill not deleted, still present in the list");
        Assert.assertFalse(skillIDListDeleted.contains(skillID), "Skill not deleted, still present in the list");
        Assert.assertEquals(deleteSkillResponse.statusCode(), HttpStatusCode.NO_CONTENT.getStatusCode());
        System.out.println("Skill successfully deleted ! Skill ID : " + skillID);
    }


}

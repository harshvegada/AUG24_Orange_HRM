package services;

import base.BaseServices_Modular;
import base.CommonServices;
import constants.DashboardAPIEndPoint;
import io.restassured.response.Response;

public class DashboardServices extends BaseServices_Modular {


    public Response getWidget(){
//        setContentTypeAsURLENC();
        return executeGetAPI(DashboardAPIEndPoint.WIDGET_API);
    }

}

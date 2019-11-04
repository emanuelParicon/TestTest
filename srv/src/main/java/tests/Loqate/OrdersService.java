package tests.Loqate;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.service.prov.api.*;
import com.sap.cloud.sdk.service.prov.api.annotations.*;
import com.sap.cloud.sdk.service.prov.api.exits.*;
import com.sap.cloud.sdk.service.prov.api.request.*;
import com.sap.cloud.sdk.service.prov.api.response.*;
import org.slf4j.*;
import com.sap.cloud.sdk.service.prov.api.annotations.Function;
import com.sap.cloud.sdk.service.prov.api.exception.DatasourceException;
import com.sap.cloud.sdk.service.prov.api.request.OperationRequest;
import com.sap.cloud.sdk.service.prov.api.response.OperationResponse;
import com.sap.cloud.sdk.service.prov.api.ExtensionHelper;

public class OrdersService {

    private static final Logger LOG = CloudLoggerFactory.getLogger(OrdersService.class.getName());

    @BeforeRead(entity = "Orders", serviceName = "CatalogService")
    public BeforeReadResponse beforeReadOrders(ReadRequest req, ExtensionHelper h) {
        LOG.error("##### Orders - beforeReadOrders ########");
        return BeforeReadResponse.setSuccess().response();
    }

    @AfterRead(entity = "Orders", serviceName = "CatalogService")
    public ReadResponse afterReadOrders(ReadRequest req, ReadResponseAccessor res, ExtensionHelper h) {
        EntityData ed = res.getEntityData();
        EntityData ex = EntityData.getBuilder(ed).addElement("amount", 1000).buildEntityData("Orders");
        return ReadResponse.setSuccess().setData(ex).response();
    }

    @AfterQuery(entity = "Orders", serviceName = "CatalogService")
    public QueryResponse afterQueryOrders(QueryRequest req, QueryResponseAccessor res, ExtensionHelper h) {
        List<EntityData> dataList = res.getEntityDataList(); // original list
        List<EntityData> modifiedList = new ArrayList<EntityData>(dataList.size()); // modified list
        for (EntityData ed : dataList) {
            EntityData ex = EntityData.getBuilder(ed).addElement("amount", 1000).buildEntityData("Orders");
            modifiedList.add(ex);
        }
        return QueryResponse.setSuccess().setData(modifiedList).response();
    }
    
     @Function(Name="greed",serviceName="CatalogService")
    public String greedTest(OperationRequest functionRequest, ExtensionHelper extensionHelper){
    	Map<String, Object> parameters = functionRequest.getParameters();
    	DataSourceHandler handler = extensionHelper.getHandler();
    	String name =(String) parameters.get("to");
    	
    	System.out.println("Hello " + name + "!!! hahahahaha");
    	return "hello world";
    }
    //e.g. function sap help costum logik functions
    @Function(Name="getApprovalStatus",serviceName="CatalogService")
	public OperationResponse getApprovalStatus(OperationRequest functionRequest, ExtensionHelper extensionHelper )
	{
		Map<String, Object> parameters = functionRequest.getParameters();
		DataSourceHandler handler = extensionHelper.getHandler();

		Map<String, Object> keys = new HashMap<String, Object>();
		keys.put("ID", String.valueOf(parameters.get("ID")));

		List<String> properties = new ArrayList<>();
		properties.add("studiengang");

		EntityData entityData = null;
		try {
			
			entityData = handler.executeRead("Student", keys, properties);
			System.out.println("test test here");
			Boolean approvalStatus = true; 
			//Integer.parseInt(entityData.getElementValue("Approved").toString())==1?true:false;
			OperationResponse response = OperationResponse.setSuccess().setPrimitiveData(Arrays.asList(approvalStatus)).response();

			return response;
		} 
		catch (DatasourceException e) {
		// Return an instance of OperationResponse containing the error in
		// case of failure
			ErrorResponse errorResponse = ErrorResponse.getBuilder()
					.setMessage(e.getMessage())
					.setCause(e)
					.response();

			return OperationResponse.setError(errorResponse);
		}
	}
}
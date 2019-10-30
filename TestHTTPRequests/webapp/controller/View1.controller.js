sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/base/Log"
], function (Controller, Log) {
	"use strict";

	return Controller.extend("lets.test.HTTP.TestHTTPRequests.controller.View1", {
		onInit: function () {
			//var oModel = this.getOwnerComponent().getModel();
			Log.info("view has been initialised");
			this._oODataModel = this.getOwnerComponent().getModel();
			
		},

		//try with default datasource manifest.json
		onGreed: function (oEvent) {
			Log.info("onGreed has been called");
			Log.info("sURL _oODataModel: " + this._oODataModel.sServiceUrl);
			var oparameters = {
				name: "Test"
			};
			this._oODataModel.callFunction("/greed", // function import name
				"GET", // http method
				oparameters, // function import parameters
				null,
				function (oData, response) {
					Log.info("GET succes");
				}, // callback function for success
				function (oError) {
					Log.error(oError.toString());
				}); // callback function for error
			Log.info("finished greed");
		},

		//try with hard coded url
		onCallGreedFunction: function (oEvent) {
			Log.info("onCallGreedFunction was called");
			var url = "wknxtlfavvgcbekh-testloqate-srv.cfapps.eu10.hana.ondemand.com/odata/v2/CatalogService";
			var oModel = new sap.ui.model.odata.ODataModel(url);
			sap.ui.getCore().setModel(oModel, "");
			var oparameters = {
				name: "Test"
			};
			oModel.callFunction("greed", "GET", oparameters, null, function (
				oData, oResponse) {
				Log.info("GET success");
			}, function (oError) {
				Log.error(oError.toString());
			});
		}
	});
});
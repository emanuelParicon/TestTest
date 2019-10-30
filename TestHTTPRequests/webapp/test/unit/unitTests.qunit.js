/* global QUnit */
QUnit.config.autostart = false;

sap.ui.getCore().attachInit(function () {
	"use strict";

	sap.ui.require([
		"lets/test/HTTP/TestHTTPRequests/test/unit/AllTests"
	], function () {
		QUnit.start();
	});
});
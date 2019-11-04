using { my.bookshop, sap.common } from '../db/data-model';

service CatalogService {
  
  entity Student as projection on bookshop.Student;
  
  
  //functions
  function greed(to : String) returns String;
  //e.g.function sap help
  function getApprovalStatus(customerID:Integer) returns Boolean;
  
  
  //actions
  
  
}
using { my.bookshop, sap.common } from '../db/data-model';

service CatalogService {
  
  entity Student as projection on bookshop.Student;
  
  
  //functions
  function greed(name : String) returns String;
  
  //actions
  
  
}
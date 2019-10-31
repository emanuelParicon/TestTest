namespace my.bookshop;
using { User, Country, managed, cuid } from '@sap/cds/common';


entity Student : managed {
	key ID : Integer;
	firstName : String;
	lastName : String;
	semester : Integer;
	studiengang : String;
	
}
package com.galvanize.simple_autos;

public class AutosControllerTests {

//-Get:/api/autos Searches for autos
// Get:/api/autos no autos in db returns 204 no content
// Get:/api/autos?color=RED Returns red cars
// Get:/api/autos?make=Ford Returns Ford cars
// Get:/api/autos?make=Ford&color=RED Returns red Ford cars
//-Get:/api/autos/{vin} Finds an automobile by its vin

//-Post:/api/autos Adds an automobile
// -Post:/api/autos returns error message due to bad request (400)

//-Patch:/api/autos/{vin}  Updates owner, or color of vehicle
// -Patch:/api/autos/{vin}  Returns NoContent auto not found
// -Patch:/api/autos/{vin}  Returns 400 bad request (no payload, no changes or already done)

//-Delete:/api/autos/{vin}   Deletes an automobile by its vin/returns 202
// -Delete:/api/autos/{vin}   Returns 204, vehicle not found


}

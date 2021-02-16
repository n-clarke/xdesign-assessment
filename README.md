# XDesign Assessment

## Date

15-02-2021

## Author

n-clarke

## Description

This is a submission for the "XDesign : Munro Library Challenge" details regarding the assessment are outlined below:

### Details 

Create a API which other software can use to sort and filter the munro data.

The API should provide the following functionality:
```
● Filtering of search by hill category (i.e. Munro, Munro Top or either). If this information is not provided by the user it should default to either. This should use the “post 1997” column and if it is blank the hill should be always excluded from results.
● The ability to sort the results by height in meters and alphabetically by name. For both options it should be possibly to specify if this should be done in ascending or descending order.
● The ability to limit the total number of results returned, e.g. only show the top 10
● The ability to specify a minimum height in meters
● The ability to specify a maximum height in meters
● Queries may include any combination of the above features and none are mandatory.
● Suitable error responses for invalid queries (e.g. when the max height is less than the minimum height)`
● The query results should be returned as a list of items using JSON. Each item should contain the name, height in meters, hill category and grid reference (e.g. NN773308). Other fields should not be included

```
Notes:
```
● Parsed Munro data should be held in memory, without the use of a database.
● The munro data should be loaded from the local CSV file on API startup.
● There is no need to add authentication or rate limiting to endpoints developed.
● Please include any testing code you write in your submitted solution.
```

## REQUIREMENTS BROKEN DOWN

### Configure Controller, Base URL and MDC Values
**Endpoint Base:**
```
/api/v1/getMunroData
```
**Query Parameters:**
```
Param: filterByHillCategory
Values:  ("munro", "top", "either") [Not Case Sensitive]
Required: No
Default: ("either")
Description: Filtering of search by hill category (i.e. Munro, Munro Top or either).
If this information is not provided by the user it should default to either. This should use the “post 1997”column and if it is blank the hill should be always excluded from results.
```
```
Param: sortByHeight
Values: ("ASC", "DESC") [Not Case Sensitive]
Required: No
Description: sort the results by height in meters
```
```
Param : sortByName
Values: ("ASC", "DESC") [Not Case Sensitive]
Required: No
Description: sort the results alphabetically by name 
```
```
Param: limit
Value: Integer
Required: No
Description: The ability to limit the total number of results returned, e.g. only show the top 10
```
```
Param : minHeight
Value: double
Required: No
Description: specify a minimum height in meters
```
```
Param : maxHeight
Value: double
Required: No
Description: specify a maximum height in meters
```

### Create Models to Store Required Data
Required list of hill fields ("name", "height", "hill_category", "grid_reference") for model to be based upon.

### Load the CSV Data Into Memory
1. Convert Excel File into CSV
2. Load CSV data into MunroModel
3. The munro data should be loaded from the local CSV file on API startup.

### Develop Functionality for Core Features

Key: () = to-do, - [x] = complete

- [x] Filtering of search by hill category (i.e. Munro, Munro Top or either). If this information is not provided by the user it should default to either. This should use the “post 1997” column and if it is blank the hill should be always excluded from results.
- [x] The ability to sort the results by height in meters and alphabetically by name. For both options it should be possibly to specify if this should be done in ascending or descending order.
- [x] The ability to limit the total number of results returned, e.g. only show the top 10
- [x] The ability to specify a minimum height in meters
- [x] The ability to specify a maximum height in meters

## Technologies Used
```
Java 11
Maven 
junit
Lombok
```
## Pre-Requisites

### Open Project
Open the pom.xml as a project:
```
Path from repository source : munro-library-challenge/pom.xml
```

### Dependencies
To install the required dependence's run:
```
mvn clean install
```

### Application Runner
The application runner is at the following path:
```
Path from repository source : munro-library-challenge/src/main/java/com/xdesign/service/api/Application.java
```

### Swagger-UI Docs
When the program is running you can view the swagger-ui docs at the following url. *Base server port is 8080
```
http://localhost:8080/swagger-ui.html
```

### Postman Collection
The postman collection for the application can be found at:
```
Path from repository source : postman-collection/MunroChallenge.postman_collection.json
```

## Example Responses

Base Port : http://localhost:8080/


Endpoint | Response
--- | ---
/api/v1/getMunroData | [RESPONSE_NO_QUERY_PARAMETERS](munro-library-challenge/src/test/resources/expected_api_response_no_query_parameters.json)
/api/v1/getMunroData?filterByHillCategory=top | [RESPONSE_QUERY_PARAMETERS_CATEGORY_TOP](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_category_top.json) 
/api/v1/getMunroData?filterByHillCategory=mun | [RESPONSE_QUERY_PARAMETERS_CATEGORY_MUN](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_category_mun.json)
/api/v1/getMunroData?filterByHillCategory=either | [RESPONSE_QUERY_PARAMETERS_CATEGORY_EITHER](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_category_either.json)
/api/v1/getMunroData?sortByHeight=asc | [RESPONSE_QUERY_PARAMETERS_HEIGHT_ASC](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_sort_by_height_asc.json)
/api/v1/getMunroData?sortByHeight=desc | [RESPONSE_QUERY_PARAMETERS_HEIGHT_DESC](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_sort_by_height_desc.json) 
/api/v1/getMunroData?sortByName=asc | [RESPONSE_QUERY_PARAMETERS_NAME_ASC](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_sort_by_name_asc.json)
/api/v1/getMunroData?sortByName=desc | [RESPONSE_QUERY_PARAMETERS_NAME_DESC](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_sort_by_name_desc.json) 
/api/v1/getMunroData?limit=1 | [RESPONSE_QUERY_PARAMETERS_LIMIT_ONE](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_limit_results_to_one.json) 
/api/v1/getMunroData?limit=10 | [RESPONSE_QUERY_PARAMETERS_LIMIT_TEN](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_limit_results_to_ten.json) 
/api/v1/getMunroData?maxHeight=2 | [RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_TWO](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_filter_by_max_height_of_two.json)
/api/v1/getMunroData?maxHeight=10 | [RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_TEN](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_filter_by_max_height_of_ten.json) 
/api/v1/getMunroData?maxHeight=17.1 | [RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_SEVENTEEN_POINT_ONE](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_filter_by_max_height_of_seventeen_point_one.json) 
/api/v1/getMunroData?minHeight=2 | [RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_TWO](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_filter_by_min_height_of_two.json)
/api/v1/getMunroData?minHeight=10 | [RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_TEN](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_filter_by_min_height_of_ten.json)
/api/v1/getMunroData?minHeight=17.1 | [RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_SEVENTEEN_POINT_ONE](munro-library-challenge/src/test/resources/expected_api_response_query_parameters_filter_by_min_height_of_seventeen_point_one.json) 
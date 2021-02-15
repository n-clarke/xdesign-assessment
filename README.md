TASKS

//  Configure  Controller and Base URL
Endpoint Base:
/api/v1/getMunroData

Query Parameters:

Param: filterByHillCategory
Values:  ("munro", "munrotop", "either") [Not Case Sensitive]
Required: No
Default: ("either")
Description: Filtering of search by hill category (i.e. Munro, Munro Top or either).
If this information is not provided by the user it should default to either. This should use the “post 1997”column and if it is blank the hill should be always excluded from results.

Param: sortByHeight
Values: ("ASC", "DESC") [Not Case Sensitive]
Required: No
Description: sort the results by height in meters

Param : sortByName
Values: ("ASC", "DESC") [Not Case Sensitive]
Required: No
Description: sort the results alphabetically by name 

Param: limit
Value: Integer
Required: No
Description: The ability to limit the total number of results returned, e.g. only show the top 10

Param : minHeight
Value: Integer
Required: No
Description: specify a minimum height in meters

Param : maxHeight
Value: Integer
Required: No
Description: specify a maximum height in meters

// Load Data
Convert Excel File into CSV
-> Make Optimised CSV file for Quicker Loading 
Load CSV data into MunroModel
The munro data should be loaded from the local CSV file on API startup.

// Swagger-UI Docs

http://localhost:8080/swagger-ui.html

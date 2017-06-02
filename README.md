##### Rockville MD

##Task
Imlement a RESTFul API spring-boot application that provides thr following APIs: 
- API to upload a file with a few meta-data fields. Persist meta-data in persistence store (In memory DB or file system and store the content on a file system)
- API to get file meta-data 
- (Optional) API to download content stream 
- (Optional) API to search for file IDs with a search criterion 
- (Optional) Write a scheduler in the same app to poll for new items in the last hour and send an email

##Pages
/files - default page
 
##Api
POST /files - upload file
- Parameters
    - file

GET /api/file/stream/{fileId} - get stream of file

GET /api/file/meta/{fileId} - get meta data of file 

GET /api/file/filter - get list of files with filter
- Parameters
    - name (filter by name ignore case sensitive)
    - minSize (minimal file size)
    - maxSize (maximal file size)

##Start project
mvn spring-boot:run


#####p.s.
I did not implement only last point.




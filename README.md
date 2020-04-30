# pvt-dogpark

GET https://pvt-dogpark.herokuapp.com/dogpark/find?all  

/find?id=xxx  

/find?name=yyyy   

/find?latitude=xxxx&longitude=yyyyy&distance=zzzzz


response-format: JSON

{
        "latitude": 17.913921015193534,
        "longitude": 59.29489656045211,
        "name": "Varpaängen",
        "description": "Hundrastområde. \\nVarpaängen, norr om varvet."
        }

##So you want to register a profile
https://pvt-dogpark.herokuapp.com/authenticate

then send like this in json format


 {
 "username":"xxxxx",
 "password":"yyyyy"
 }       

mind that everyting will be lowercase

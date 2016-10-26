var express = require('express');
var app = express();
var request = require('request');
app.get('/getdata/:id', function (req, res) {
    var result={
        'body': []
    };

    request('https://api.foursquare.com/v2/venues/search?client_id=Q0ENF1YHFTNPJ31DCF13ALLENJW0P5MTH13T1SA0ZP1MUOCI&client_secret=ZH4CRZNEWBNTALAE3INIB5XG0QI12R4DT5HKAJLWKYE1LHOG&v=20160215&limit=5&near=Kansas&query=tops/'+req.params.id, function (error,response,body) {
        if(error){
            return console.log('Error:', error);
        }

        if(response.statusCode !== 200){
            return console.log('Invalid Status Code Returned:', response.statusCode);
        }
        body = JSON.parse(body);
        var ven = body.response.venues;
        for(var i=0;i<ven.length;i++)
        {
            result.venue.push({'name': ven[i].name,
                                'address':ven[i].location.formattedAddress.toString()});
        }
        res.contentType('application/json');
        res.write(JSON.stringify(result));
        

        request('https://api.uclassify.com/v1/uclassify/genderanalyzer_v5/Classify?readkey=sfvA14Vws29a&text=', function (error, response, body1) {

            if (error) {
                return console.log('Error:', error);
            }

            if (response.statusCode !== 200) {
                return console.log('Invalid Status Code Returned:', response.statusCode);
            }

            body1 = JSON.parse(body1);
            weath = body1;

                result.body.push({"positive": data.positive,"negative": data.negative});

            res.contentType('application/json');
            res.write(JSON.stringify(result));
            res.end();

        });
    });

})
var server = app.listen(8081, function () {
    var host = server.address().address
    var port = server.address().port

})
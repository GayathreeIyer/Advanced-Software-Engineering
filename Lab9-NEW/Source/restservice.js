var express = require('express');
var app = express();
var request = require('request');
app.get('/getdata/:id', function (req, res) {
    var result={
        'body': []
    };

    request('http://apilayer.net/api/validate?access_key=8c9269e142a8a2dd8c44b6bb8e90fc9a&number='+req.params.id, function (error,response,body) {
        if(error){
            return console.log('Error:', error);
        }

        if(response.statusCode !== 200){
            return console.log('Invalid Status Code Returned:', response.statusCode);
        }
        body = JSON.parse(body);
        conti = body;
        console.log(conti);
            sentiment = conti.valid;
        

        request('https://api.uclassify.com/v1/uclassify/sentiment/Classify?readkey=MD03b83ppKsp&text='+sentiment, function (error, response, body1) {

            if (error) {
                return console.log('Error:', error);
            }

            if (response.statusCode !== 200) {
                return console.log('Invalid Status Code Returned:', response.statusCode);
            }

            body1 = JSON.parse(body1);
            weath = body1;
console.log(body1);
                result.body.push({"valid": conti.valid,"number": conti.number,"local_format": conti.local_format,"international_format": conti.international_format,"country_prefix": conti.country_prefix,"country_code": conti.country_code,"country_name": conti.country_name,"location": conti.location,"carrier": conti.carrier,"line_type": conti.line_type,"positive":(weath.positive*100).toFixed(2),"negative":(weath.negative*100).toFixed(2)});

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
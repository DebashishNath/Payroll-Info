const express = require("express");

const app = express();
const cors=require('cors')
var nodemailer = require('nodemailer');

app.use(express.json());
app.use(cors())

app.post(("/mail"),async (req,res)=>{
  const frommail=req.body.frommail
  const password = req.body.password
  const tomail=req.body.tomail
  var transporter = nodemailer.createTransport({
    service: 'gmail',

    auth: {
      user: frommail,
      pass: password
    }
  });
  
  var mailOptions = {
    from: frommail,
    to: tomail,
    subject: 'Sending Email using Node.js',
    text: `sending mail using Node.js was running successfully. Hope it help you. For more code and project Please Refer my github page`
    // html: '<h1>Hi Smartherd</h1><p>Your Messsage</p>'        
  };
  
  transporter.sendMail(mailOptions, function(error, info){
    if (error) {
      res.json({
        msg: 'fail'
      });
    } 
    else{
      res.json({
        msg: 'success'
      })
    }
  });

})

app.listen(3001, () => {
  console.log("Server is Running");
});
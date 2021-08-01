import React, { useEffect,useState}  from "react";
import { BrowserRouter as Router, Route, useHistory } from "react-router-dom";
import { Paper, Grid, TextField, Button, FormControlLabel, Checkbox,Avatar, 
  Typography,Link } from '@material-ui/core';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';
import MainMenuForm from "../MenuComponents/MainMenuForm";
import moment from 'moment';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

function LoginContent() 
{
    const [showMessageBox, setShowMessageBox] = useState(false);
    const [title, setTitle] = useState('');
    const [displayMessage, setDisplayMessage] = useState('');
           
    const paperStyle={padding:20,height:'60vh',width:280,margin:"60px auto"}
    const avatarStyle={backgroundColor:'green'} 
    const btnStyle={margin:'8px 0'}

    let history = useHistory();

    async function doLogin() {
      
      await setShowMessageBox(false);
      await setTitle('');
      await setDisplayMessage('');

      const requestOptions = {
        crossDomain:true,
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "username" : document.getElementById("uname").value,
            "password" : document.getElementById("pwd").value
          })
      };
    
      var url=HelperMethods.GetServerIP() + 'api/auth/signin';
      try 
      {
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        var returnData=data.returnMessage?data.returnMessage.code:-1;
        if (returnData === 0)
        {
          localStorage.setItem('userId',data.id);
          localStorage.setItem('userName',data.username);
          localStorage.setItem('tokenValue',data.accessToken);
          let todayDate=new Date();
          localStorage.setItem('TodayDate',moment(todayDate).format('DD-MMM-YYYY'));

          history.push({ 
            pathname: '/MainMenuForm'
          });
        }
        else
        {
          await setShowMessageBox(true);
          await setTitle('Error Information');
          await setDisplayMessage(data.returnMessage.message);
        }
      }
      catch(err) {
        await setShowMessageBox(true);
        await setTitle('Error Information');
        await setDisplayMessage('Please contact system administrator to check the web server');
      }
    }

    useEffect(() => {
      document.getElementById("uname").focus(); 
    }, [])

    return (
    <Paper elevation={10} style={paperStyle}>
        <Grid align="center">
          <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
          <h2>Sign In</h2>
          {showMessageBox ?
          <div>
            <MessageBoxForm title={title}>
              {displayMessage}
            </MessageBoxForm>
          </div> : null}
        </Grid>
        <TextField id="uname" label='Username' placeholder='Enter Username' variant='outlined' fullWidth required></TextField>
        <br /><br />
        <TextField id="pwd" label='Password' placeholder='Enter Password'  variant='outlined' type='password' fullWidth required></TextField>
        <FormControlLabel
          control={
            <Checkbox
              name="checked8"
              color="primary"
            />
          }
          label="Remember me"
        />
        <Button type='submit' color='primary' variant='contained' style={btnStyle} 
            fullWidth required onClick={() => { doLogin() }}>Log In</Button>
        
        <Typography>
          <Link href="#">Forgot Password ?</Link>
        </Typography>
        <Typography> Do you have an account ?
          <Link href="#">Sign Up</Link>
        </Typography>
      </Paper> 
    );
}

function LoginForm() 
{
  return (
    <Router>
      <Route path="/MainMenuForm" exact component={MainMenuForm} />
      <Route path="/" exact component={LoginContent} />
    </Router>
  );
}

export default LoginForm;
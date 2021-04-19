import './App.css';
import { Paper, withStyles, Grid, TextField, Button, FormControlLabel, Checkbox,Avatar, Typography,Link } from '@material-ui/core';
import LockOutlinedIcon from '@material-ui/icons/LockOutlined';


async function doLogin() {

  const requestOptions = {
    crossDomain:true,
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        "username" : "Sandip",
        "password" : "sandip123"
      })
  };

  var url='http://192.168.43.241:8086/api/auth/signin';
  // Storing response
  const response = await fetch(url,requestOptions);
  
  try 
  {
    var data = await response.json();
    alert(data.returnMessage?data.returnMessage.message:'Failed to fetch')
  }
  catch(err) {
    alert(err.message);
  }
}

const App=(props) => {
  
  const paperStyle={padding:20,height:'60vh',width:280,margin:"20px auto"}
  const avatarStyle={backgroundColor:'green'} 
  const btnStyle={margin:'8px 0'}
  
  return (
    <Paper elevation={10} style={paperStyle}>
      <Grid align="center">
        <Avatar style={avatarStyle}><LockOutlinedIcon/></Avatar>
        <h2>Sign In</h2>
      </Grid>
      <TextField label='Username' placeholder='Enter Username' fullWidth required></TextField>
      <TextField label='Password' placeholder='Enter Password' type='password' fullWidth required></TextField>
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
          fullWidth required>Log In</Button>
      
      <Typography>
        <Link href="#">Forgot Password ?</Link>
      </Typography>
      <Typography> Do you have an account ?
        <Link href="#">Sign Up</Link>
      </Typography>
    </Paper> 
  );
}

export default App;

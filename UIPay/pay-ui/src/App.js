import logo from './logo.svg';
import './App.css';
import Button from '@material-ui/core/Button'

function doLogin(){
    const requestOptions = {
    crossDomain:true,
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
        "username" : "Sandip",
        "password" : "sandip123"
      })
  };

  fetch('http://192.168.43.241:8086/api/auth/signin',requestOptions)
  .then(response=>{return response.json();
  }).then(result =>{
      this.setState(state => ({ user: Object.assign({}, state.user, 
          { id: result.id,
            username:result.username,
            email:result.email,
            roles:result.roles,
            returnMessage:result.returnMessage,
            tokenType:result.tokenType,
            accessToken:result.accessToken
          })}));
  }).catch((error) => {
      //alert(error);
    });
    alert('Successful login');
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Button onClick={()=>doLogin()} variant="contained" color="secondary">
            Login
        </Button>
        <img src={logo} className="App-logo" alt="logo" />
       </header>
    </div>
  );
}

export default App;

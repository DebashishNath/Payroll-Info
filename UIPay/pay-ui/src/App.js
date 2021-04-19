import logo from './logo.svg';
import './App.css';
import Button from '@material-ui/core/Button'

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
  
  // Storing data in form of JSON
  var data = await response.json();
  alert(data.returnMessage.message);
  
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

import  { Component } from 'react';

class LogoutForm extends Component 
{
    componentDidMount(){
        localStorage.clear();
        window.location.href = '/';
    }
}

export default LogoutForm;
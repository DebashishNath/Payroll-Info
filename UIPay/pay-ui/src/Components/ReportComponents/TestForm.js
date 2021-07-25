import React from 'react'
import { Button,Paper } from '@material-ui/core';

class TestForm extends React.Component {
    constructor(props) 
    {
        super(props);
    }
    render() { 
        const paperStyle={padding:20,height:200,width:200,margin:"40px 100px",border: '2px solid brown'}
        return(
            <Paper style={paperStyle} variant="outlined">
            <div>
                <Button color='primary' variant='contained' >Add</Button>
            </div>
            </Paper>
        );
    }
    /*showAlert() {
        this.messageBox.alert("Alert", "This is alert!").ok(() => {
            console.log("Alert ok!");
        });
    }
 
    showConfirm() {
        this.messageBox.confirm("Confirm", "This is confirm!").ok(() => {
            console.log("Confirm ok!");
        }).cancle(() => {
            console.log("Confirm cancle!");
        });
    }
 
    render() {
        return (
            <div>
                <Button onClick={this.showAlert.bind(this)}>Alert Box</Button>
                <Button onClick={this.showConfirm.bind(this)}>Confirm Box</Button>
                <MessageBox ref="messageBox"/>
            </div>
        );
    }*/
}

export default TestForm;
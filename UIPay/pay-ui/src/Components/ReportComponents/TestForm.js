import React from 'react'
import { Button,Paper,Table,TextField,label } from '@material-ui/core';

class TestForm extends React.Component {
    constructor(props) 
    {
        super(props);
        this.doEditRecord=this.doEditRecord.bind(this);
    }
    
    validateControls()
    {
        if(document.getElementById("code").value.trim().length === 0)
        {
            alert("Code cannot be blank");
            document.getElementById("code").focus();
            return false;
        }
        if(document.getElementById("name").value.trim().length === 0)
        {
            alert("Name cannot be blank");
            document.getElementById("name").focus();
            return false;
        }
        return true;
    }

    doEditRecord()
    {
        if(!this.validateControls())
        {
            return;
        }
        alert("Record(s) Saved");
    }
    render() { 
        const paperStyle={padding:20,height:200,width:300,margin:"40px 100px",border: '2px solid brown'}
        return(
            <Paper style={paperStyle} variant="outlined">
            <div>
                <Table>
                    <tr><td><label>Code</label></td>
                        <td><TextField id="code" variant='outlined' required inputProps={{ maxLength: 5 }}></TextField></td>
                    </tr>
                    <br/>
                    <tr><td><label>Name</label></td>
                        <td><TextField id="name" variant='outlined' required inputProps={{ maxLength: 25 }}></TextField></td>
                    </tr>
                    <br/>
                    <tr>
                        <td></td>
                        <td><Button color='primary' variant='contained' onClick={() => { this.doEditRecord() }} >Add</Button></td>
                    </tr>
                </Table>
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
import React from 'react'
import { Button,Paper,Table,TextField,label } from '@material-ui/core';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';

class TestForm extends React.Component {
    constructor(props) 
    {
        super(props);
        this.state = 
        {
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        
        this.doEditRecord=this.doEditRecord.bind(this);
    }
    
    async componentDidMount()
    {
        await document.getElementById("code").focus();
    }

    async showMessage(isShowMsgBox,messageBoxtitle,dispMessage,controlName) 
    {
        if(controlName.trim().length > 0)
        {
            document.getElementById(controlName).focus();
        }
        await this.setState({
            showMessageBox:isShowMsgBox,
            title: messageBoxtitle,
            displayMessage: dispMessage
        });
    }

    async validateControls()
    {
        await this.showMessage(false,'','','');

        if(document.getElementById("code").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Code cannot be blank','code');
            return false;
        }
        if(document.getElementById("name").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Name cannot be blank','name');
            return false;
        }
        return true;
    }

    async doEditRecord()
    {
        //Implementation of helper methods
        //document.getElementById("name").value=HelperMethods.printSum(6,7);

        if(await this.validateControls())
        {
            await this.showMessage(true,'Save Information','Record(s) Saved Successfully','');
        }
    }
    render() { 
        const paperStyle={padding:20,height:200,width:300,margin:"40px 100px",border: '2px solid brown'}
        return(
            <Paper style={paperStyle} variant="outlined">
            <div>
                {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                            {this.state.displayMessage}
                        </MessageBoxForm>
                    </div> 
                : null }
                <Table>
                    <tr><td><label>Code</label></td>
                        <td><TextField id="code" variant='outlined' required style ={{width: '30%'}} inputProps={{ maxLength: 5 }}></TextField></td>
                    </tr>
                    <br/>
                    <tr><td><label>Name</label></td>
                        <td><TextField id="name" variant='outlined' required inputProps={{ maxLength: 25 }}></TextField></td>
                    </tr>
                    <br/>
                    <tr>
                        <td></td>
                        <td><Button color='primary' variant='contained' onClick={() => { this.doEditRecord() }}>Save</Button></td>
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
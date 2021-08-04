import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,MenuItem,TextField } from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';
import ClearAllIcon from '@material-ui/icons/ClearAll';
import EditIcon from '@material-ui/icons/Edit';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class EarnDedComponentsForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state = {
            earnDedTypesToDisplay:[],
            earnDedComponentsToDisplay:[],
            earnDedId:0,
            earnDedType:"",
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.doUpdateEarnDedComponent=this.doUpdateEarnDedComponent.bind(this);
        this.earnDedTypeComboChange=this.earnDedTypeComboChange.bind(this);
        this.displayOfEarnDedComponent=this.displayOfEarnDedComponent.bind(this);
        this.doClearControls=this.doClearControls.bind(this);
    }

    async componentDidMount()
    {
        let initialDataToDisplay = [];
        initialDataToDisplay.push(<MenuItem value={"E"}>{"Earning"}</MenuItem>)
        initialDataToDisplay.push(<MenuItem value={"D"}>{"Deduction"}</MenuItem>)
        this.setState({earnDedTypesToDisplay:initialDataToDisplay});
        this.displayEarnDeductions();
        await document.getElementById("earnDedCode").focus();
    }

    doClearControls()
    {
        this.setState({ earnDedId : 0 });
        document.getElementById("earnDedCode").value="";
        document.getElementById("earnDedName").value="";
        this.setState({ earnDedType : "" });
        document.getElementById("earnDedPriority").value="";
        document.getElementById("earnDedCode").focus();
    }

    displayOfEarnDedComponent(earnDedId,earnDedCode,earnDedName,earnDedType,earnDedPriority)
    {
        this.setState({ earnDedId : earnDedId });
        document.getElementById("earnDedCode").value=earnDedCode;
        document.getElementById("earnDedName").value=earnDedName;
        this.setState({ earnDedType : earnDedType });
        document.getElementById("earnDedPriority").value=earnDedPriority;
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

        if(document.getElementById("earnDedCode").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', 'Earn Deduction code cannot be blank','earnDedCode');
            return false;
        }
        if(document.getElementById("earnDedName").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', 'Earn Deduction name cannot be blank','earnDedName');
            return false;
        }
        if(this.state.earnDedType === "" )
        {
            await this.showMessage(true,'Error Information', 'Select Earn Deduction type','earnDedTypeCombo');
            return false;
        }
        if(document.getElementById("earnDedPriority").value.trim().length ===0)
        {
            await this.showMessage(true,'Error Information', 'Priority cannot be blank','earnDedPriority');
            return false;
        }
        return true;
    }
    async doUpdateEarnDedComponent()
    {
        if(!await this.validateControls())
        {
            return;
        }
        const requestOptions = {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            body: JSON.stringify({
                "earn_ded_id": this.state.earnDedId,
                "earn_ded_code": document.getElementById("earnDedCode").value,
                "earn_ded_name" : document.getElementById("earnDedName").value,
                "earn_ded_type": this.state.earnDedType,
                "earn_ded_priority" : document.getElementById("earnDedPriority").value
              })
        };
        
        var url= HelperMethods.GetServerIP() + 'api/update_earn_deduction';
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            
            if (data.code === 0)
            {
                this.displayEarnDeductions();
                await this.showMessage(true,'Save Information', data.message,'');
                if(this.state.earnDedId === 0)
                {
                    this.doClearControls();
                }
            }
            else
            {
                await this.showMessage(true,'Error Information', data.message,'');
            }
        }catch(err) 
        { 
            await this.showMessage(true,'Error Information', err.message,'');
        }
    }

    earnDedTypeComboChange(event)
    {
        this.setState({ earnDedType : event.target.value });
    }

    async displayEarnDeductions()
    {
        let initialDataToDisplay = [];
        
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url= HelperMethods.GetServerIP() + 'api/earn_deductions';
        try 
        {
          const response = await fetch(url,requestOptions);
          var data = await response.json();
          
          if(data!=null && data.length>0)
          {
            initialDataToDisplay.push(<tr>
                 <th>Slno</th><th style={{display:'none'}}>Id</th><th>Code</th>
                 <th>Name</th><th>Type</th><th>Priority</th><th></th></tr>);
            for(var i=0;i<=data.length-1;i++)
            {
                let earnDedId=data[i].earn_ded_id;
                let earnDedCode=data[i].earn_ded_code;
                let earnDedName=data[i].earn_ded_name;
                let earnDedType=data[i].earn_ded_type;
                let earnDedTypeName='Earning';
                if(data[i].earn_ded_type === "D")
                {
                    earnDedTypeName='Deduction';
                }
                let earnDedPriority=data[i].earn_ded_priority;
                initialDataToDisplay.push(
                    <tr key={earnDedId}>
                    <td>{i+1}</td>
                    <td style={{display:'none'}}>{earnDedId}</td>
                    <td>{earnDedCode}</td>
                    <td>{earnDedName}</td>
                    <td>{earnDedTypeName}</td>
                    <td>{earnDedPriority}</td>
                    <td align='center'><Button color="primary" variant="contained" size="small" startIcon={<EditIcon/>} onClick={() => 
                        { this.displayOfEarnDedComponent(earnDedId,earnDedCode,earnDedName,earnDedType,earnDedPriority) }}>Edit</Button></td>
                    </tr>);
                }
            }
            else
            {
                await this.showMessage(true,'Information', 'No Earn and Deduction data to display','');
            }
            this.setState({ earnDedComponentsToDisplay: initialDataToDisplay });
        }catch(err) 
        {
            await this.showMessage(true,'Error Information',err.message,'');
        }
    }

    render()
    {
        const paperStyle={padding:30,height:'80vh',width:600,margin:"40px 100px",border: '5px solid brown'}
        const divStyle = {
            border: '5px solid green',
            height: '45vh',
            overflow: 'auto'
          };
        return (
            <Paper style={paperStyle} variant="outlined">
                {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                        {this.state.displayMessage}
                        </MessageBoxForm>
                </div> : null}
                <div>
                    <Table>
                        <tr>
                            <td>Code</td>
                            <td><TextField id="earnDedCode" variant='outlined' style ={{width: '30%'}}></TextField></td>
                            <td></td><td></td>
                        </tr>
                        <br/>
                        <tr>
                            <td>Name</td>
                            <td><TextField id="earnDedName" variant='outlined'></TextField></td>
                            <td>Type</td>
                            <td><Select id="earnDedTypeCombo" value={this.state.earnDedType} onChange={this.earnDedTypeComboChange}
                                    style={{ border: '1px solid' ,width:'150px' }}>
                                    {this.state.earnDedTypesToDisplay}
                                </Select>
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>Priority</td>
                            <td><TextField id="earnDedPriority" variant='outlined' style ={{width: '30%'}}></TextField></td>
                            <td><Button color="primary" variant="contained" size="small" startIcon={<SaveIcon/>} onClick={() => { this.doUpdateEarnDedComponent() }}>Save</Button></td>
                            <td><Button color="primary" variant="contained" size="small" startIcon={<ClearAllIcon/>} onClick={() => { this.doClearControls() }}>Reset</Button></td>
                        </tr>
                    </Table>
                </div><br/>
                <div style={divStyle}>
                    <Table border='1'>
                        {this.state.earnDedComponentsToDisplay}
                    </Table>
                </div>
            </Paper>
        );
    }
}

export default EarnDedComponentsForm;
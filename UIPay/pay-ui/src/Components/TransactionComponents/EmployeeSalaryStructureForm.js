import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,MenuItem,TextField } from '@material-ui/core';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class EmployeeSalaryStructureForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state = {
            employeesToDisplay:[],
            earnDedToDisplay:[],
            employeeStructId:0,
            earnDedComponentsToDisplay:[],
            earnDedId:0,
            paperHeight:'25vh',
            showEmpSalStruct:false,
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.employeesComboChange=this.employeesComboChange.bind(this);
        this.earnDedComponentsComboChange=this.earnDedComponentsComboChange.bind(this);
        this.displayOfEmpComponent=this.displayOfEmpComponent.bind(this);
        this.clearControls=this.clearControls.bind(this);
        this.doUpdateEmpComponent=this.doUpdateEmpComponent.bind(this);
    }

    componentDidMount()
    {
        var url= HelperMethods.GetServerIP() + 'api/employees'
        this.populateCombos('Employee',url);
        url= HelperMethods.GetServerIP() + 'api/earn_deductions'
        this.populateCombos('EarnDedComponent',url);
    }

    displayOfEmpComponent(earnDedId,earnDedAmount)
    {
        document.getElementById("earnDedAmount").value=earnDedAmount;
        this.setState({ earnDedId : earnDedId });
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
        
        if(this.state.employeeStructId === 0)
        {
            await this.showMessage(true, 'Error Information','Select employee for creating salary structure','employeesCombo');
            return false;
        }

        if(this.state.earnDedId === 0)
        {
            await this.showMessage(true, 'Error Information','Select earn deduction component for creating salary structure','earnDedCombo');
            return false;
        }
        
        if(document.getElementById("earnDedAmount").value.trim().length === 0)
        {
            await this.showMessage(true, 'Error Information','Enter earn deduction amount for creating salary structure','earnDedAmount');
            return false;
        }
        if(isNaN(document.getElementById("earnDedAmount").value.trim()))
        {
            await this.showMessage(true,'Error Information', 'Enter numeric value for Earn Deduction Amount','earnDedAmount');
            return false;
        }
        return true;
    }

    async doUpdateEmpComponent()
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
                "empEmpEarnDedIdentity": { "emp_id": this.state.employeeStructId,
	                                       "earn_ded_id": this.state.earnDedId },
                "earn_ded_amount" : document.getElementById("earnDedAmount").value
              })
        };
        
        var url= HelperMethods.GetServerIP() + 'api/updateEmpSalStruct';
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            
            if (data.code === 0)
            {
                this.displayEmpEarnDeduction( this.state.employeeStructId)
                await this.showMessage(true, 'Save Information',data.message,'');
            }
            else
            {
                await this.showMessage(true, 'Error Information',data.message,'');
            }
        }catch(err) 
        { 
            await this.showMessage(true, 'Error Information',err.message,'');
        }
    }

    async populateCombos(comboName,url) 
    {
        let initialDataToDisplay = [];
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        
        try 
        {
          const response = await fetch(url,requestOptions);
          var data = await response.json();
          if(data!=null && data.length>0)
          {
            for(var i=0;i<=data.length-1;i++)
            {
                if(comboName === 'Employee')
                {
                    let emp_name=data[i].emp_code + " - " + data[i].emp_first_name + " " + data[i]?.emp_middle_name + " " + data[i].emp_last_name;
                    initialDataToDisplay.push(<MenuItem value={data[i].emp_id}>{emp_name}</MenuItem>)
                }
                if(comboName === 'EarnDedComponent')
                {
                    initialDataToDisplay.push(<MenuItem value={data[i].earn_ded_id}>{data[i].earn_ded_name}</MenuItem>)
                }
            }
            if(comboName === 'Employee')
            {
                this.setState({ employeesToDisplay: initialDataToDisplay });
            }
            if(comboName === 'EarnDedComponent')
            {
                this.setState({ earnDedComponentsToDisplay: initialDataToDisplay });
            }
          }
        } catch(err) 
        { 
            await this.showMessage(true, 'Error Information',err.message,'');
        }
    }

    employeesComboChange(event)
    {
        this.setState({
            employeeStructId : event.target.value
          });
        this.setState({eearnDedId : 0});
        document.getElementById("earnDedAmount").value="";
        this.displayEmpEarnDeduction(event.target.value);
    }

    earnDedComponentsComboChange(event)
    {
        this.setState({
            earnDedId : event.target.value
        });
        document.getElementById("earnDedAmount").value="";
    }

    async displayEmpEarnDeduction(empId)
    {
        await this.showMessage(false, '','','');
        
        let initialDataToDisplay = [];
        
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url= HelperMethods.GetServerIP() + 'api/empsalstruct_findbyempid/' + empId;
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
          
            if(data!=null && data.length>0)
            {

                initialDataToDisplay.push(<tr>
                    <th>Slno</th><th>Code</th><th>Desc.</th>
                    <th>Type</th><th>Amount</th><th></th></tr>);
                for(var i=0;i<=data.length-1;i++)
                {
                    let earnDedType='Earn';
                    if(data[i].earnDedComponents.earn_ded_type === "D")
                    {
                        earnDedType='Ded';
                    }
                    let earnDedId=data[i].empEmpEarnDedIdentity.earn_ded_id;
                    let earnDedAmt=data[i].earn_ded_amount;
                    initialDataToDisplay.push(
                        <tr key={earnDedId}>
                        <td>{i+1}</td>
                        <td>{data[i].earnDedComponents.earn_ded_code}</td>
                        <td>{data[i].earnDedComponents.earn_ded_name}</td>
                        <td>{earnDedType}</td>
                        <td>{earnDedAmt}</td>
                        <td><Button color="primary" variant="contained" onClick={() => 
                            { this.displayOfEmpComponent(earnDedId,earnDedAmt) }}>Edit</Button></td>
                        </tr>);
                }
                document.getElementById("earnDedAmount").value="";
                document.getElementById("earnDedCombo").focus();
                await this.setState({ earnDedId:0,
                                paperHeight:'70vh',
                                showEmpSalStruct : true,
                                earnDedToDisplay: initialDataToDisplay });
            }
            else
            {
                await this.showMessage(true, 'Information','Salary structure not created for this employeee','employeesCombo');
                await this.setState({ paperHeight:'25vh',
                                showEmpSalStruct : false,
                                earnDedToDisplay: [] });
            }
        } catch(err) 
        { 
            await this.showMessage(true, 'Error Information',err.message,'');
        }
    }

    clearControls()
    {
        this.setState({ 
            paperHeight:'25vh',
            showEmpSalStruct:false,
            employeeStructId:0,
            earnDedId:0,
            earnDedToDisplay:[]
        });
        document.getElementById("earnDedAmount").value="";
        document.getElementById("employeesCombo").focus();
    }

    render()
    {
        const paperStyle={padding:30,height:this.state.paperHeight,width:600,margin:"40px 100px",border: '5px solid brown'}
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
                            <td>Employee</td>
                            <td>
                                <Select id="employeesCombo" value={this.state.employeeStructId} onChange={this.employeesComboChange}
                                    style={{ border: '1px solid' ,width:'250px' }}>
                                    {this.state.employeesToDisplay}
                                </Select>
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>Component</td>
                            <td>
                                <Select id="earnDedCombo" value={this.state.earnDedId} onChange={this.earnDedComponentsComboChange}
                                    style={{ border: '1px solid' ,width:'250px' }}>
                                    {this.state.earnDedComponentsToDisplay}
                                </Select>
                            </td>
                        </tr>
                        <br/>
                        <tr>
                            <td>Amount</td>
                            <td><TextField id="earnDedAmount" variant='outlined' style ={{width: '30%'}} inputProps={{ maxLength: 6 }} size="small"></TextField></td>
                            <td><Button color="primary" variant="contained" onClick={() => { this.doUpdateEmpComponent() }}>Update</Button></td>
                            <td><Button color="primary" variant="contained" onClick={() => { this.clearControls() }}>Reset</Button></td>
                        </tr>
                    </Table>
                </div><br/>
                { this.state.showEmpSalStruct
                  ?
                    <div style={divStyle}>
                        <Table border='1'>
                            {this.state.earnDedToDisplay}
                        </Table>
                    </div> : null
                }
            </Paper>
        );
    }
}

export default EmployeeSalaryStructureForm;
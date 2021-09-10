import React, { PureComponent } from 'react';
import { Table, Button,Paper,TextField } from '@material-ui/core';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class EmployeeTimesheetForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state ={
            empId:0,
            showMessageBox:false,
            title:'',
            displayMessage:''
        };
        this.updateEmployeeAttendance=this.updateEmployeeAttendance.bind(this);
        this.clearControls=this.clearControls.bind(this);
    }

    componentDidMount()
    {
        try
        {
            this.setState({empId:localStorage.getItem('loginEmpId')});
            document.getElementById("empName").innerHTML = localStorage.getItem('loginEmpName');
        }catch(err){}
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

    clearControls()
    {
        document.getElementById("attendanceDate").value="";
        document.getElementById("noHoursWorked").value="";
        document.getElementById("attendanceDate").focus();
    }

    async validateControls()
    {
        await this.showMessage(false,'', '','');

        if((this.state.empId ===0) || 
            (document.getElementById("empName").innerHTML.trim().length === 0))
        {
            await this.showMessage(true,'Error Information', 'No Employee have been selected for entering attendance','');
            return false;
        }
        if(document.getElementById("attendanceDate").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', 'Attendance Date should be in DD-MM-YYYY','attendanceDate');
            return false;
        }
        if(document.getElementById("noHoursWorked").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information', 'No. Hours worked cannot be blank','noHoursWorked');
            return false;
        }
        return true;
    }
    async updateEmployeeAttendance()
    {
        if(!await this.validateControls())
        {
            return;
        }
        var attendanceDate=document.getElementById("attendanceDate").value;
        
        const requestOptions = 
        {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            body: JSON.stringify
            ({
                "attendanceIdentity": 
                { 
                    "month" : attendanceDate.substring(5,7),
                    "year"  : attendanceDate.substring(0,4),
                    "emp_id" : this.state.empId,
                    "attendance_date" : attendanceDate
                },
                "attendanceType" : {"attendance_type_code" : "P" },
                "no_hours_worked" : document.getElementById("noHoursWorked").value
            })
        };
    
        let url= HelperMethods.GetServerIP() + 'api/save_attendance';
        
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            if (data.code === 0)
            {
                await this.showMessage(true,'Save Information', data.message,'');
            }
            else
            {
                await this.showMessage(true,'Error Information', data.message,'');
            }
        } catch(err) 
        { 
            await this.showMessage(true,'Error Information', err.message,'');
        }
    }
    
    render()
    {
        const paperStyle={padding:20,height:'20vh',width:400,margin:"40px 100px",border: '5px solid brown'}
        const btnStyle={margin:'8px 0'}
   
        return(
            <div>
                <Paper style={paperStyle} variant="outlined">
                {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                        {this.state.displayMessage}
                        </MessageBoxForm>
                    </div> : null}
                <Table>
                    <tr>
                        <td><label>Name&nbsp;</label></td>
                        <td colspan='3'><label id ="empName"></label></td>
                    </tr><br/>
                    <tr><td><label>Date&nbsp;</label></td>
                        <td>
                            <TextField id="attendanceDate" label="" type="date" defaultValue="" style ={{width: '90%'}}
                            InputLabelProps={{shrink: true,}} />
                        </td>
                        <td><label>Hours&nbsp;</label></td>
                        <td>
                            <TextField id="noHoursWorked" variant='outlined' required  style ={{width: '40%'}} size="small" />
                        </td>
                    </tr><br/>
                    <tr>
                        <td colSpan='4' align='right'><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                            size="small"  onClick={() => { this.updateEmployeeAttendance() }}>Save</Button>&nbsp;&nbsp;
                            <Button type='submit' color='primary' variant='contained' style={btnStyle} size="small"
                            onClick={() => { this.clearControls() }}>Reset</Button></td>
                    </tr>
                </Table>
                </Paper>
            </div>
        );
    }
}

export default EmployeeTimesheetForm;
import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,TextField,MenuItem } from '@material-ui/core';
import moment from 'moment';

class EmployeeLeaveForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state ={
            approveById:0,
            approveByToDisplay:[],
            leaveTypeCode:"",
            leaveTypesToDisplay:[],
            checked:false
        };
        this.setChecked=this.setChecked.bind(this);
        this.updateEmployeeLeave=this.updateEmployeeLeave.bind(this);
        this.leaveTypeCodeChange=this.leaveTypeCodeChange.bind(this);
        this.approvedByComboChange=this.approvedByComboChange.bind(this);
        this.clearControls=this.clearControls.bind(this);
    }

    componentDidMount()
    {
        var url='http://192.168.43.241:8086/api/leave_types';
        this.populateCombos('LeaveType',url);
        url='http://192.168.43.241:8086/api/employees';
        this.populateCombos('Employee',url);
        var leaveApplicationId=localStorage.getItem('LeaveApplicationId');
        this.PopulateLeaveRecord(leaveApplicationId);
    }

    async PopulateLeaveRecord(leaveApplicationId)
    {
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url='http://192.168.43.241:8086/api/emp_leave/' + leaveApplicationId;
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        if(data!=null)
        {
            document.getElementById("EmpCode").innerHTML=data.emp.emp_code;
            document.getElementById("EmpName").innerHTML=data.emp.emp_first_name + ' ' + data?.emp?.emp_middle_name + ' ' + data.emp.emp_last_name;
            document.getElementById("ApplicationNo").value=data.leave_application_no;
            var dateFormat = require('dateformat');
            document.getElementById("AppDate").value=dateFormat(data.leave_application_date,'yyyy-mm-dd');
            document.getElementById("FromDate").value=dateFormat(data.from_date,'yyyy-mm-dd');
            document.getElementById("ToDate").value=dateFormat(data.to_date,'yyyy-mm-dd');
            let noDays=this.getNoDays(data.from_date,data.to_date);
            document.getElementById("NoDays").innerHTML=noDays;
            document.getElementById("ApplicationDetails").value=data.leave_application_details;
            this.setState({leaveTypeCode : data.leaveType.leave_type_code});
            this.setState({approveById : data.approvedBy.leave_type_code});
            alert(data.is_approved);
            if(data.is_approved === "Y")
            {
                this.setState({checked : true});
            }
            else
            {
                this.setState({checked : false});
            }
        }
    }

    getNoDays(fromDate,toDate)
    {
        let diff =  moment(toDate).diff(moment(fromDate));
        let diffDuration = moment.duration(diff);
        let noDays=diffDuration.days() + 1;
        return noDays;
    }

    async populateCombos(comboName,url) 
    {
        try
        {
            let initialDataToDisplay = [];

            const requestOptions = {
                crossDomain:true,
                method: 'GET',
                headers: { 'Content-Type': 'application/json',
                            'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            };
        
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            if(data!=null && data.length>0)
            {
                for(var i=0;i<=data.length-1;i++)
                {
                    if(comboName === 'LeaveType')
                    {
                        initialDataToDisplay.push(<MenuItem value={data[i].leave_type_code}>{data[i].leave_type_name}</MenuItem>)
                    }
                    if(comboName === 'Employee')
                    {
                        let emp_name=data[i].emp_first_name + ' ' + data[i]?.emp_middle_name + ' ' + data[i].emp_last_name;
                        initialDataToDisplay.push(<MenuItem value={data[i].emp_id}>{emp_name}</MenuItem>)
                    }
                }
                if(comboName === 'LeaveType')
                {
                    this.setState({leaveTypesToDisplay:initialDataToDisplay});
                }
                if(comboName ==="Employee"){
                    this.setState({approveByToDisplay:initialDataToDisplay});
                }
            }
        }
        catch(err) {
        alert(err.message);
        }
    }

    clearControls(){}

    updateEmployeeLeave(){}

    approvedByComboChange(event)
    {
        this.setState({
            approveById : event.target.value
        });
    }

    leaveTypeCodeChange(event) 
    {
        this.setState({
            leaveTypeCode : event.target.value
        });
    }
    
    setChecked(){}

    render()
    {
        const paperStyle={padding:20,height:'75vh',width:700,margin:"40px 100px",border: '5px solid brown'}
        const btnStyle={margin:'8px 0'}
   
        return(
            <div>
                <Paper style={paperStyle} variant="outlined">
                <Table>
                    <tr><td><label>Code</label></td>
                        <td><label id ="EmpCode"></label></td>
                        <td><label>Name</label></td>
                        <td><label id ="EmpName"></label></td>
                    </tr><br/>
                    <tr><td><label>App. No</label></td>
                        <td> <td><TextField id="ApplicationNo" variant='outlined' style={{width :'80%'}}></TextField></td></td>
                        <td><label>App. Date</label></td>
                        <td>
                            <TextField id="AppDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                    </tr><br/>
                    <tr><td><label>Leave Type</label></td>
                        <td>
                        <Select id="leaveTypesCombo" value={this.state.leaveTypeCode} onChange={this.leaveTypeCodeChange}
                            style={{ border: '1px solid' ,width:'180px' }}>
                            {this.state.leaveTypesToDisplay}
                        </Select>
                        </td>
                        <td><label>From Date</label></td>
                        <td>
                            <TextField id="FromDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                    </tr><br/>
                    <tr>
                        <td><label>To Date</label></td>
                        <td>
                            <TextField id="ToDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                        <td><label>No. Days</label></td>
                        <td><td><label id ="NoDays"></label></td>
                        </td>
                    </tr><br/>
                    <tr>
                        <td><label>App. Details</label></td>
                        <td colspan="2"><TextField id="ApplicationDetails" multiline variant='outlined' style={{width :'150%'}}></TextField></td>
                    </tr><br/>
                    <tr><td><label>Approved</label></td>
                        <td><input type="checkbox" defaultChecked={this.state.checked}
                            onChange={() => this.setChecked()} /></td>
                        <td><label>Approved By</label></td>
                        <td>
                            <Select id="approvedByCombo" value={this.state.approveById} 
                                        onChange={this.approvedByComboChange}
                                style={{ border: '1px solid' ,width:'180px' }}>
                                {this.state.approveByToDisplay}
                            </Select>
                        </td>
                    </tr><br/>
                    <tr>
                        <td><label>Remarks</label></td>
                        <td colspan="2"><TextField id="Remarks" multiline variant='outlined' style={{width :'150%'}}></TextField></td>
                    </tr><br/>
                    <tr><td></td><td></td><td></td>
                        <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                            onClick={() => { this.updateEmployeeLeave() }}>Save</Button>&nbsp;&nbsp;
                            <Button type='submit' color='primary' variant='contained' style={btnStyle} 
                            onClick={() => { this.clearControls() }}>Reset</Button></td>
                    </tr>
                </Table>
                </Paper>
            </div>
        );
    }
}

export default EmployeeLeaveForm;
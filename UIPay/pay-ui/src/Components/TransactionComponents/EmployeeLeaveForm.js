import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,TextField,MenuItem } from '@material-ui/core';
import moment from 'moment';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class EmployeeLeaveForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state ={
            leaveApplicationId:0,
            empId:0,
            approveById:0,
            approveByToDisplay:[],
            leaveTypeCode:"",
            leaveTypesToDisplay:[],
            checked:false,
            showMessageBox:false,
            title:'',
            displayMessage:''
        };
        this.setChecked=this.setChecked.bind(this);
        this.updateEmployeeLeave=this.updateEmployeeLeave.bind(this);
        this.leaveTypeCodeChange=this.leaveTypeCodeChange.bind(this);
        this.approvedByComboChange=this.approvedByComboChange.bind(this);
        this.clearControls=this.clearControls.bind(this);
    }

    componentDidMount()
    {
        var url= HelperMethods.GetServerIP() + 'api/leave_types';
        this.populateCombos('LeaveType',url);
        url= HelperMethods.GetServerIP() + 'api/employees';
        this.populateCombos('Employee',url);
        var application_id= localStorage.getItem('LeaveApplicationId');
        if(application_id > 0)
        {
            this.PopulateLeaveRecord(application_id);
        }
        else
        {
            var employeeId= localStorage.getItem('EmpIdLeave');
            this.PopulateEmployee(employeeId);
        }
    }

    async PopulateEmployee(employeeId)
    {
        this.setState({empId:employeeId});
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url= HelperMethods.GetServerIP() + 'api/employee/' + employeeId;
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        
        if(data!=null)
        {
            document.getElementById("EmpCode").innerHTML=data.emp_code;
            document.getElementById("EmpName").innerHTML=data.emp_first_name + ' ' + data?.emp_middle_name + ' ' + data.emp_last_name;
        }
    }

    async PopulateLeaveRecord(applicationId)
    {
        this.setState({leaveApplicationId : applicationId});
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url= HelperMethods.GetServerIP() + 'api/emp_leave/' + applicationId;
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        if(data!=null)
        {
            this.setState({empId : data.emp.emp_id});
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
            this.setState({approveById : data.approvedBy.emp_id});
            if(data.is_approved === "Y")
            {
                this.setState({checked : true});
            }
            else
            {
                this.setState({checked : false});
            }
            document.getElementById("Remarks").value=data.remarks;
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
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: err.message
            });
        }
    }

    clearControls()
    {
        this.setState({leaveApplicationId : 0});
        document.getElementById("ApplicationNo").value="";
        document.getElementById("AppDate").value="";
        document.getElementById("FromDate").value="";
        document.getElementById("ToDate").value="";
        document.getElementById("NoDays").innerHTML="";
        document.getElementById("ApplicationDetails").value="";
        this.setState({leaveTypeCode : ""});
        this.setState({approveById : 0});
        this.setState({checked : false});
        document.getElementById("Remarks").value="";

        document.getElementById("ApplicationNo").focus();
    }

    async validateControls()
    {
        if((this.state.empId ===0) || 
            (document.getElementById("EmpCode").innerHTML.trim().length === 0))
        {
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'No Employee have been selected for updating leave'
            });
            return false;
        }
        if(document.getElementById("ApplicationNo").value.trim().length === 0)
        {
            document.getElementById("ApplicationNo").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Application No cannot be blank'
            });
            return false;
        }
        if(document.getElementById("AppDate").value.trim().length === 0)
        {
            document.getElementById("AppDate").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Application Date should be in DD-MM-YYYY'
            });
            return false;
        }
        if(this.state.leaveTypeCode ==="")
        {
            document.getElementById("leaveTypesCombo").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Select Leave Type'
            });
            return false;
        }
        if(document.getElementById("FromDate").value.trim().length === 0)
        {
            document.getElementById("FromDate").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'From Date should be in DD-MM-YYYY'
            });
            return false;
        }
        if(document.getElementById("ToDate").value.trim().length === 0)
        {
            document.getElementById("ToDate").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'To Date should be in DD-MM-YYYY'
            });
            return false;
        }
        if(document.getElementById("AppDate").value > document.getElementById("FromDate").value)
        {
            document.getElementById("AppDate").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Application Date cannot be greater than From Date'
            });
            return false;
        }
        if(document.getElementById("AppDate").value > document.getElementById("ToDate").value)
        {
            document.getElementById("AppDate").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Application Date cannot be greater than To Date'
            });
            return false;
        }
        if(document.getElementById("FromDate").value > document.getElementById("ToDate").value)
        {
            document.getElementById("FromDate").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'From Date cannot be greater than To Date'
            });
            return false;
        }
        if(document.getElementById("ApplicationDetails").value.trim().length === 0)
        {
            document.getElementById("ApplicationDetails").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Application Details cannot be blank'
            });
            return false;
        }
        if(this.state.checked && this.state.approveById ===0)
        {
            document.getElementById("approvedByCombo").focus();
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: 'Select the approved By'
            });
            return false;
        }
        return true;
    }
    async updateEmployeeLeave()
    {
        await this.setState({
            showMessageBox:false,
            title:'',
            displayMessage:''
        });

        if(!await this.validateControls())
        {
            return;
        }
    
        let isApproved="N";
        if(this.state.checked) 
        {
            isApproved="Y";
        }
        
        /*var approved_ById=null;
        if(this.state.approveById>0)
        {
            approved_ById=this.state.approveById;
        }*/

        const requestOptions = 
        {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            body: JSON.stringify({
                "leave_application_id" : this.state.leaveApplicationId,
                "emp" : {"emp_id" : this.state.empId },
                "leave_application_no" : document.getElementById("ApplicationNo").value.trim(),
                "leave_application_date" : document.getElementById("AppDate").value,
                "leaveType" : {"leave_type_code" : this.state.leaveTypeCode},
                "from_date" : document.getElementById("FromDate").value,
                "to_date" : document.getElementById("ToDate").value,
                "leave_application_details" : document.getElementById("ApplicationDetails").value.trim(),
                "is_approved" : isApproved,
                "approvedBy": {"emp_id" : this.state.approveById },
                "remarks" : document.getElementById("Remarks").value.trim() })
            };
    
        let url= HelperMethods.GetServerIP() + 'api/save_emp_leave';
        
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            if (data.code === 0)
            {
                await this.setState({
                    showMessageBox:true,
                    title:'Save Information',
                    displayMessage: data.message
                });
            }
            else
            {
                await this.setState({
                    showMessageBox:true,
                    title:'Error Information',
                    displayMessage: data.message
                });
            }
        } catch(err) 
        { 
            await this.setState({
                showMessageBox:true,
                title:'Error Information',
                displayMessage: err.message
            });
        }
    }
    
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
    
    setChecked = () =>
    {
        this.setState(prevState => ({ checked: !prevState.checked }));
    };

    render()
    {
        const paperStyle={padding:20,height:'80vh',width:700,margin:"40px 100px",border: '5px solid brown'}
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
                        <td colspan="2"><TextField id="ApplicationDetails" multiline rows={4} variant='outlined' style={{width :'150%'}}></TextField></td>
                    </tr><br/>
                    <tr><td><label>Approved</label></td>
                        <td><input type="checkbox" checked={this.state.checked}
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
                        <td colspan="2"><TextField id="Remarks" variant='outlined' style={{width :'150%'}}></TextField></td>
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
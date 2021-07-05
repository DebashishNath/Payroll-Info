import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,TextField,MenuItem } from '@material-ui/core';
import moment from 'moment';

class EmployeeLeaveForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.setState({
            approveById:0,
            approveByToDisplay:[],
            leaveTypeCode:"",
            leaveTypesToDisplay:[],
            checked:false
        });
        this.setChecked=this.setChecked.bind(this);
        this.updateEmployeeOfficial=this.updateEmployeeOfficial.bind(this);
        this.leaveTypeCodeChange=this.leaveTypeCodeChange.bind(this);
    }

    componentDidMount()
    {
        alert(localStorage.getItem('LeaveApplicationId'));
        var url='http://192.168.43.241:8086/api/leave_types';
        this.populateCombos('LeaveType',url);
        url='http://192.168.43.241:8086/api/employees';
        this.populateCombos('Employee',url);
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

    setChecked(){}

    updateEmployeeOfficial(){}

    leaveTypeCodeChange(event) 
    {
        this.setState({
            leaveTypeCode : event.target.value
        });
    }
    

    render()
    {
        const paperStyle={padding:20,height:'90vh',width:600,margin:"10px auto"}
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
                        <td><label id ="AppNo"></label></td>
                        <td><label>App. Date</label></td>
                        <td>
                            <TextField id="AppDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                    </tr><br/>
                    <tr><td><label>Leave Type</label></td>
                        <td>
                        <Select id="leaveTypesCombo" value={this.state.leaveTypeCode} onChange={this.leaveTypeCodeChange}
                            style={{ border: '1px solid' ,width:'200px' }}>
                            {this.state.leaveTypesToDisplay}
                        </Select>
                        </td><td></td><td></td>
                    </tr><br/>
                    <tr><td><label>From Date</label></td>
                        <td>
                            <TextField id="FromDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                        <td><label>To Date</label></td>
                        <td>
                            <TextField id="ToDate" label="" type="date" defaultValue=""
                            InputLabelProps={{shrink: true,}} />
                        </td>
                    </tr><br/>
                    <tr><td><label>Is Approved</label></td>
                        <td><label><input type="checkbox" defaultChecked={this.state.checked}
                            onChange={() => this.setChecked()} />Approved</label></td>
                        <td><label>Approved By</label></td>
                        <td>
                        <Select id="approvedByCombo" value={this.state.approveById} onChange={this.approvedByComboChange}
                            style={{ border: '1px solid' ,width:'200px' }}>
                            {this.state.approveByToDisplay}
                        </Select>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                        onClick={() => { this.updateEmployeeOfficial() }}>Save</Button></td>
                    </tr>
                </Table>
                </Paper>
            </div>
        );
    }
}

export default EmployeeLeaveForm;
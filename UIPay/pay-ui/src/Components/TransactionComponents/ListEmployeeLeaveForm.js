import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,MenuItem } from '@material-ui/core';
import moment from 'moment';

class ListEmployeeLeaveForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state = {
            employeesToDisplay:[],
            empLeavesToDisplay:[],
            employeeId : 0
        }
        this.addEmpLeave=this.addEmpLeave.bind(this);
        this.doEditOfEmpLeave=this.doEditOfEmpLeave.bind(this);
        this.employeesComboChange = this.employeesComboChange.bind(this);
    }

    componentDidMount(){
        this.DisplayAllEmployees();
    }

    addEmpLeave()
    {
        localStorage.setItem('LeaveApplicationId',0);
        const { history } = this.props;
        if(history) history.push('/empleave');
    }

    doEditOfEmpLeave(leaveApplicationId)
    {
        localStorage.setItem('LeaveApplicationId',leaveApplicationId);
        const { history } = this.props;
        if(history) history.push('/empleave');
    }

    employeesComboChange(event) 
    {
        let empId=event.target.value;
        this.setState({
            employeeId : event.target.value
        });
        this.DisplayAllEmpLeaves(empId);
    }
    
    async DisplayAllEmployees()
    {
        let initialDataToDisplay = [];
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url='http://192.168.43.241:8086/api/employees';
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            if(data!=null && data.length>0)
            {
                for(var i=0;i<=data.length-1;i++)
                {
                    var emp_name= data[i].emp_first_name + " " + data[i]?.emp_middle_name + " " + data[i].emp_last_name;
                    initialDataToDisplay.push(<MenuItem value={data[i].emp_id}>{emp_name}</MenuItem>)
                }
                this.setState({ employeesToDisplay: initialDataToDisplay });
            }
        } catch(err) {
            alert(err.message);
        }
    }

    async DisplayAllEmpLeaves(empId)
    {
        let initialDataToDisplay = [];
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url='http://192.168.43.241:8086/api/leave_of_single_emp/' + empId;
        try 
        {
          const response = await fetch(url,requestOptions);
          var data = await response.json();
          if(data!=null && data.length>0)
          {
            initialDataToDisplay.push(<tr>
                 <th>Slno</th><th>Id</th><th>App. Date</th>
                 <th>From Date</th><th>To Date</th><th>Approve(Y/N)</th>
                 <th></th></tr>);
            for(var i=0;i<=data.length-1;i++)
            {
                let leaveApplicationId=data[i].leave_application_id;
                initialDataToDisplay.push(
                    <tr key={leaveApplicationId}>
                    <td>{i+1}</td>
                    <td>{leaveApplicationId}</td>
                    <td>{moment(data[i].leave_application_date).format('DD-MMM-YY')}</td>
                    <td>{moment(data[i].from_date).format('DD-MMM-YY')}</td>
                    <td>{moment(data[i].to_date).format('DD-MMM-YY')}</td>
                    <td>{data[i].is_approved}</td>
                    <td><Button color="primary" variant="contained" onClick={() => { this.doEditOfEmpLeave(leaveApplicationId) }}>Edit</Button></td>
                    </tr>);
            }
            this.setState({
                empLeavesToDisplay: initialDataToDisplay                
              });
          }
          else{
            alert("No Leaves taken");
          }
        } catch(err) {
            alert(err.message);
        }
    }

    render()
    {
        const paperStyle={padding:30,height:'80vh',width:800,margin:"40px 100px",border: '5px solid brown'}
        const btnStyle={margin:'8px 0'}
        const divStyle = {
            border: '5px solid green',
            height: '65vh',
            overflow: 'auto'
          };

        return (
            <Paper style={paperStyle} variant="outlined">
            <div>
                <Table>
                    <tr>
                    <td><label>Employee</label></td>
                    <td><Select id="employeesCombo" value={this.state.employeeId} onChange={this.employeesComboChange}
                        style={{ border: '1px solid' ,width:'200px' }}>
                        {this.state.employeesToDisplay}
                    </Select></td>
                    <td><Button color='primary' variant='contained' style={btnStyle} 
                        onClick={() => { this.addEmpLeave()}}>Add Employee Leave</Button></td>
                    </tr>
                </Table>
            </div>
            <div style={divStyle}>
                <Table border='1'>
                    {this.state.empLeavesToDisplay}
                </Table>
            </div>
            </Paper>
        );
    }
}

export default ListEmployeeLeaveForm;
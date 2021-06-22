import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,MenuItem } from '@material-ui/core';

class EmployeeSalaryStructureForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state = {
            employeesToDisplay:[],
            earnDedToDisplay:[],
            employeeId:0
        }
        this.employeesComboChange=this.employeesComboChange.bind(this);
    }

    componentDidMount(){
        this.DisplayAllEmployees();
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
        var url='http://192.168.43.241:8086/api/employees'
        try 
        {
          const response = await fetch(url,requestOptions);
          var data = await response.json();
          if(data!=null && data.length>0)
          {
            for(var i=0;i<=data.length-1;i++)
            {
                let emp_name=data[i].emp_code + " - " + data[i].emp_first_name + " " + data[i]?.emp_middle_name + " " + data[i].emp_last_name;
                initialDataToDisplay.push(<MenuItem value={data[i].emp_id}>{emp_name}</MenuItem>)
            }
            this.setState({ employeesToDisplay: initialDataToDisplay });
          }
        } catch(err) { alert(err.message); }
    }

    employeesComboChange(event)
    {
        this.setState({
            employeeId : event.target.value
          });
        
        this.displayEmpEarnDeduction(this.state.employeeId);
    }

    async displayEmpEarnDeduction(empId)
    {
        let initialDataToDisplay = [];
        
        const requestOptions = {
            crossDomain:true,
            method: 'GET',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
        };
        var url='http://192.168.43.241:8086/api/empsalstruct_findbyempid/' + empId;
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
                initialDataToDisplay.push(
                    <tr key={data[i].empEmpEarnDedIdentity.earn_ded_id}>
                    <td>{i+1}</td>
                    <td>{data[i].earnDedComponents.earn_ded_code}</td>
                    <td>{data[i].earnDedComponents.earn_ded_name}</td>
                    <td>{earnDedType}</td>
                    <td>{data[i].earn_ded_amount}</td>
                    <td><Button color="primary" variant="contained" onClick={() => { this.doEditOfEmpComponents(earnDedId) }}>Edit</Button></td>
                    </tr>);
            }
            this.setState({ earnDedToDisplay: initialDataToDisplay });
          }
          else{
            initialDataToDisplay.push("No Earning and Deduction Data for this employee");
          }
        } catch(err) {
            alert(err.message);
        }
    }

    render()
    {
        const paperStyle={padding:30,height:'50vh',width:600,margin:"10px auto",overflow:'auto'}
        return (
            <Paper style={paperStyle} variant="outlined">
                <div>
                    <Table>
                        <tr>
                            <td>Employee</td>
                            <td>
                                <Select id="employeesCombo" value={this.state.employeeId} onChange={this.employeesComboChange}
                                    style={{ border: '1px solid' ,width:'300px' }}>
                                    {this.state.employeesToDisplay}
                                </Select>
                            </td>
                        </tr>
                    </Table>
                </div><br/>
                <div>
                    <Table border='1'>
                        {this.state.earnDedToDisplay}
                    </Table>
                </div>
            </Paper>
        );
    }
}

export default EmployeeSalaryStructureForm;
import React, { PureComponent } from 'react';
import { Table, Button,Paper,Select,MenuItem,TextField } from '@material-ui/core';

class EmployeeSalaryStructureForm extends PureComponent {
    constructor(props) 
    {
        super(props);
        this.state = {
            employeesToDisplay:[],
            earnDedToDisplay:[],
            employeeStructId:0,
            earnDedComponentsToDisplay:[],
            earnDedId:0
        }
        this.employeesComboChange=this.employeesComboChange.bind(this);
        this.earnDedComponentsComboChange=this.earnDedComponentsComboChange.bind(this);
        this.displayOfEmpComponent=this.displayOfEmpComponent.bind(this);
    }

    componentDidMount(){
        var url='http://192.168.43.241:8086/api/employees'
        this.populateCombos('Employee',url);
        url='http://192.168.43.241:8086/api/earn_deductions'
        this.populateCombos('EarnDedComponent',url);
    }

    displayOfEmpComponent(earnDedId,earnDedAmount){
        document.getElementById("earnDedAmount").value=earnDedAmount;
        this.setState({ earnDedId : earnDedId });
    }

    async doUpdateEmpComponent(){

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
        } catch(err) { alert(err.message); }
    }

    employeesComboChange(event)
    {
        this.setState({
            employeeStructId : event.target.value
          });
        this.displayEmpEarnDeduction(event.target.value);
    }

    earnDedComponentsComboChange(event)
    {
        this.setState({
            earnDedId : event.target.value
          });
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
           }
          else
          {
            initialDataToDisplay.push("No Earning and Deduction Data for this employee");
          }
          this.setState({ earnDedToDisplay: initialDataToDisplay });
        } catch(err) {
            alert(err.message);
        }
    }

    render()
    {
        const paperStyle={padding:30,height:'70vh',width:600,margin:"10px auto",overflow:'auto'}
        return (
            <Paper style={paperStyle} variant="outlined">
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
                            <td>
                            <td><TextField id="earnDedAmount" variant='outlined' style ={{width: '50%'}}></TextField></td>
                            </td>
                            <td><Button color="primary" variant="contained" onClick={() => { this.doUpdateEmpComponent() }}>Update</Button></td>
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
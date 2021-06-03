import React, { Component } from 'react';
import { Table, Button,Paper } from '@material-ui/core';

class ListEmployeesForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            employeesToDisplay:[],
        }
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
            
            initialDataToDisplay.push(<tr>
                 <th>Slno</th><th className="hidden-xs">EmpId</th><th>Code</th><th>Name</th>
                 <th>Gender</th><th>Department</th><th>Designation</th>
                 <th></th></tr>);
            for(var i=0;i<=data.length-1;i++)
            {
                let gender='Male';
                if(data[i].gender === "F"){
                    gender='Female';
                }
                
                initialDataToDisplay.push(
                    <tr>
                    <td>{i+1}</td>
                    <td className="hidden-xs">{data[i].emp_id}</td>
                    <td>{data[i].emp_code}</td>
                    <td>{data[i].emp_first_name + ' ' + data[i]?.emp_middle_name + ' ' + data[i].emp_last_name}</td>
                    <td>{gender}</td>
                    <td>{data[i]?.department?.department_name}</td>
                    <td>{data[i]?.designation?.designation_name}</td>
                    <td><Button>Edit</Button></td>
                    </tr>);
            }
            this.setState({
                employeesToDisplay: initialDataToDisplay
              });
          }
          else{
            initialDataToDisplay.push("No Employees to display");
          }
        } catch(err) {
            alert(err.message);
        }
    }

    render()
    {
        return (
            <div>
                <Table>
                    {this.state.employeesToDisplay}
                </Table>
            </div>
        );
    }
}

export default ListEmployeesForm;
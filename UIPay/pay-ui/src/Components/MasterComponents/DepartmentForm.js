import React, { Component } from 'react';
import { Table, TextField , Button,Paper } from '@material-ui/core';

class DepartmentForm extends Component {
  constructor(props) 
  {
    super(props);
    this.addDepartment = this.addDepartment.bind(this);
  }

  async addDepartment() {
        
        const requestOptions = {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                       'Authorization' : 'Bearer ' +  localStorage.getItem('tokenValue') },
            body: JSON.stringify({
                "department_id" : "0",
                "department_code" : document.getElementById("departmentCode").value,
                "department_name" : document.getElementById("departmentName").value
              })
        };
      
      var url='http://192.168.43.241:8086/api/adddepartment';
      try 
      {
        const response = await fetch(url,requestOptions);
        var data = await response.json();
        document.getElementById('returnMessage').innerHTML = data.message;
        if (data.code === 0)
        {
          document.getElementById('returnMessage').style="color:green";
        }
        else
        {
          document.getElementById('returnMessage').style="color:red";
        }
      }
      catch(err) {
        alert(err.message);
      }
    }

    render() {
        const paperStyle={padding:20,height:'25vh',width:400,margin:"40px 100px"}
        const btnStyle={margin:'8px 0'}

        return (
        <div>
            <Paper style={paperStyle} variant="outlined">
            <label id = "returnMessage"></label>
            <Table>
              <tr><td><label>Department Code</label></td>
                  <td><TextField id="departmentCode" variant='outlined'></TextField></td>
              </tr>
              <br/>
              <tr><td><label>Department Name</label></td>
                  <td><TextField id="departmentName" variant='outlined'></TextField></td>
              </tr>
              <tr><td></td>
                  <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                    onClick={() => { this.addDepartment() }}>Save</Button></td>
              </tr>
            </Table>
            </Paper>
         </div>
        );
  }
}

export default DepartmentForm;
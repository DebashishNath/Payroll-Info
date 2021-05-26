import React, { Component } from 'react';
import { Table, TextField , Button,Paper,RadioGroup,FormControlLabel,Radio } from '@material-ui/core';

class EmployeePersonalForm extends Component{
  constructor(props) 
  {
    super(props);
    this.addEmployee = this.addEmployee.bind(this);
  }

  async addEmployee() 
  {

  }

  render()
  {
    const paperStyle={padding:20,height:'100vh',width:400,margin:"100px auto"}
    const btnStyle={margin:'8px 0'}

    return (
      <div>
          <Paper elevation={10} style={paperStyle}>
          <label id = "returnMessage"></label>
          <Table>
            <tr><td><label>Employee Code</label></td>
                <td><TextField id="employeeCode" placeholder='Enter Employee Code' 
                variant='outlined'></TextField></td>
            </tr>
            <br/>
            <tr><td><label>First Name</label></td>
                <td><TextField id="firstName" placeholder='Enter First Name' variant='outlined'></TextField></td>
            </tr>
            <tr><td><label>Middle Name</label></td>
                <td><TextField id="middleName" placeholder='Enter Middle Name' variant='outlined'></TextField></td>
            </tr>
            <tr><td><label>Last Name</label></td>
                <td><TextField id="lastName" placeholder='Enter Last Name' variant='outlined'></TextField></td>
            </tr>
            <br/>
            <tr>
              <td><label>Gender</label></td>
              <td>
                <RadioGroup aria-label="Gender" name="gender" row>
                <FormControlLabel value="male" control={<Radio />} label="Male" />
                <FormControlLabel value="female" control={<Radio />} label="Female" />
                </RadioGroup>
              </td>
            </tr>
            <tr>
            <td><label>Date Of Birth</label></td>
              <td>
                <TextField id="dob" label="" type="date" defaultValue=""
                  InputLabelProps={{shrink: true,}} />
              </td>
            </tr>
            <br/>
            <tr><td><label>Address</label></td>
                <td><TextField id="address1" placeholder='Enter Address Line 1' variant='outlined'></TextField></td>
            </tr>
            <tr><td></td>
                <td><TextField id="address2" placeholder='Enter Address Line 2' variant='outlined'></TextField></td>
            </tr>
            <tr><td><label>State</label></td>
                <td></td>
            </tr>
            <tr><td><label>District</label></td>
                <td></td>
            </tr>
            <tr><td><label>Pin</label></td>
                <td><TextField id="pin" placeholder='Enter Pin' variant='outlined'></TextField></td>
            </tr>
            <br/>
            <tr><td></td>
                <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                  onClick={() => { this.addEmployee() }}>Save</Button></td>
            </tr>
          </Table>
          </Paper>
       </div>
      );
  }
}

export default EmployeePersonalForm;
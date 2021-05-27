import React, { Component } from 'react';
import { Table, TextField , Button,Paper,RadioGroup,FormControlLabel,
        Radio,Select,MenuItem } from '@material-ui/core';

class EmployeePersonalForm extends Component{
  constructor(props) 
  {
    super(props);
    this.state = {
      statesToDisplay:[],
      districtsToDisplay:[]
    }
    this.addEmployee = this.addEmployee.bind(this);
  }

  componentDidMount(){
    var url='http://192.168.43.241:8086/api/states';
    this.populateCombos('State',url)

    url='http://192.168.43.241:8086/api/districts/1';
    this.populateCombos('District',url)
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
      if(data!=null && data.length>0){
       
        for(var i=0;i<=data.length-1;i++)
        {
          if(comboName === 'State')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].state_id}>{data[i].state_name}</MenuItem>)
          }
          if(comboName === 'District')
          {
            initialDataToDisplay.push(<MenuItem value={data[i].district_id}>{data[i].district_name}</MenuItem>)
          }
        }
        if(comboName === 'State')
        {
            this.setState({
              statesToDisplay: initialDataToDisplay
            });
        }
        if(comboName === 'District')
        {
            this.setState({
              districtsToDisplay: initialDataToDisplay
            });
        }
       }
    }
    catch(err) {
      alert(err.message);
    }
  }

  async addEmployee() 
  {

  }

  render()
  {
    const paperStyle={padding:20,height:'100vh',width:600,margin:"100px auto"}
    const btnStyle={margin:'8px 0'}
   
    return (
      <div>
          <Paper elevation={10} style={paperStyle}>
          <label id = "returnMessage"></label>
          <Table>
            <tr>
              <td><TextField id="employeeCode" label='Employee Code' variant='outlined' required ></TextField></td>
              <td></td><td></td>
            </tr>
            <br/>
            <tr>
                <td><TextField id="firstName" label='First Name' variant='outlined' required></TextField></td>
                <td><TextField id="middleName" label='Middle Name' variant='outlined'></TextField></td>
                <td><TextField id="lastName" label='Last Name' variant='outlined'></TextField></td>
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
                <td><TextField id="address1" variant='outlined'></TextField></td>
            </tr>
            <tr><td></td>
                <td><TextField id="address2" variant='outlined'></TextField></td>
            </tr>
            <tr><td><label>State</label></td>
                <td>
                  <Select>
                    {this.state.statesToDisplay}
                  </Select>
                </td>
            </tr>
            <tr><td><label>District</label></td>
              <td>
                <Select>
                {this.state.districtsToDisplay}
                </Select>
               </td>
            </tr>
            <tr><td><label>Pin</label></td>
                <td><TextField id="pin" variant='outlined'></TextField></td>
            </tr>
            <br/>
            <tr><td><label>Contact Number</label></td>
                <td><TextField id="contactNumber" variant='outlined'></TextField></td>
            </tr>
            <tr><td><label>Email</label></td>
                <td><TextField id="email" variant='outlined'></TextField></td>
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
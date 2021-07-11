import React, { Component } from 'react';
import { Table, TextField , Button,Paper,RadioGroup,FormControlLabel,
        Radio,Select,MenuItem } from '@material-ui/core';
import SaveIcon from '@material-ui/icons/Save';

class EmployeePersonalForm extends Component {
  constructor(props) 
  {
    super(props);
    this.state = {
      statesToDisplay:[],
      districtsToDisplay:[],
      stateId:0,
      districtId:0,
      gender:"M"
    }
    this.updateEmployeePersonal = this.updateEmployeePersonal.bind(this);
    this.statesComboChange = this.statesComboChange.bind(this);
    this.districtsComboChange = this.districtsComboChange.bind(this);
    this.onValueChange=this.onValueChange.bind(this);
  }

  componentDidMount(){
    document.getElementById("employeeCode").focus();
    var url='http://192.168.43.241:8086/api/states';
    
    this.populateCombos('State',url)
    let empId= localStorage.getItem('employeeId');
    document.getElementById("lblEmpId").value=empId;
    if(empId > 0)
    {
      this.populatePersonalEmpInfo(empId);
    }
  }

  async populatePersonalEmpInfo(empId)
  {
   
      document.getElementById("lblEmpId").value=empId;
      
      const requestOptions = {
        crossDomain:true,
        method: 'GET',
        headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
      };
      var url='http://192.168.43.241:8086/api/employee/' + empId;
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      if(data!=null)
      {
          document.getElementById("employeeCode").value = data.emp_code;
          document.getElementById("firstName").value = data.emp_first_name;
          document.getElementById("middleName").value = data.emp_middle_name;
          document.getElementById("lastName").value = data.emp_last_name;
          //"emp_image_path" : "",
          this.setState({ gender: data.gender });
          var dateFormat = require('dateformat');
          document.getElementById("dob").value = dateFormat(data.dob,'yyyy-mm-dd');
          document.getElementById("address1").value = data.address1;
          document.getElementById("address2").value = data.address2;
          //"location" : null,
          this.setState({ stateId : data.district.state.state_id });
          this.populateDistricts(data.district.state.state_id);
          this.setState({districtId : data.district.district_id });
          document.getElementById("pin").value = data.pin;
          document.getElementById("contactNumber").value = data.contact_number;
          document.getElementById("email").value = data.email;
      }
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

  statesComboChange(event) 
  {
    var empStateId=event.target.value;
    this.populateDistricts(empStateId);
  }
  
  populateDistricts(empStateId)
  {
    this.setState({
      districtsToDisplay: []
    });
    
    var url='http://192.168.43.241:8086/api/districts/' + empStateId;
    this.populateCombos('District',url)
    this.setState({stateId:empStateId});
  }

  districtsComboChange(event) 
  {
    this.setState({
      districtId : event.target.value
    });
  }

  onValueChange(event) {
    this.setState({
      gender: event.target.value
    });
  }

  validateControls()
  {
    if(document.getElementById("employeeCode").value.trim().length === 0)
    {
        alert("Employee code cannot be blank");
        document.getElementById("employeeCode").focus();
        return false;
    }
    if(document.getElementById("firstName").value.trim().length === 0)
    {
        alert("First name cannot be blank");
        document.getElementById("firstName").focus();
        return false;
    }
    if(document.getElementById("lastName").value.trim().length === 0)
    {
        alert("Last name cannot be blank");
        document.getElementById("lastName").focus();
        return false;
    }
    if(document.getElementById("dob").value.trim().length === 0)
    {
        alert("Date of birth should be in DD-MM-YYYY");
        document.getElementById("dob").focus();
        return false;
    }
    if(document.getElementById("address1").value.trim().length === 0)
    {
        alert("Address1 cannot be blank");
        document.getElementById("address1").focus();
        return false;
    }
    if(this.state.stateId === 0)
    {
        alert("State cannot be blank");
        document.getElementById("statesCombo").focus();
        return false;
    }
    if(this.state.districtId === 0)
    {
        alert("District cannot be blank");
        document.getElementById("districtsCombo").focus();
        return false;
    }
    if(document.getElementById("pin").value.trim().length === 0)
    {
        alert("Pin cannot be blank");
        document.getElementById("pin").focus();
        return false;
    }
    if(document.getElementById("contactNumber").value.trim().length === 0)
    {
        alert("Contact Numbers cannot be blank");
        document.getElementById("contactNumber").focus();
        return false;
    }
    if(document.getElementById("email").value.trim().length === 0)
    {
        alert("Email Ids cannot be blank");
        document.getElementById("email").focus();
        return false;
    }
    return true;
  }

  async updateEmployeePersonal() 
  {
    if(!this.validateControls())
    {
      return;
    }

    const requestOptions = 
    {
      crossDomain:true,
      method: 'POST',
      headers: { 'Content-Type': 'application/json',
                  'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
      body: JSON.stringify({
        "emp_id" : document.getElementById("lblEmpId").value,
	      "emp_code" : document.getElementById("employeeCode").value,
        "emp_first_name" : document.getElementById("firstName").value,
        "emp_middle_name" : document.getElementById("middleName").value,
        "emp_last_name" : document.getElementById("lastName").value,
        "emp_image_path" : "",
        "gender" : this.state.gender,
	      "dob" : document.getElementById("dob").value,
	      "address1" : document.getElementById("address1").value,
        "address2" : document.getElementById("address2").value,
        "location" : null,
        "district" : {"district_id" : this.state.districtId},
        "pin" : document.getElementById("pin").value,
	      "contact_number" : document.getElementById("contactNumber").value,
	      "email" : document.getElementById("email").value
	    })
    };
    
    let url='';
    if (document.getElementById("lblEmpId").value === '0')
    {
      url='http://192.168.43.241:8086/api/newemployee';
    }
    else
    {
      url='http://192.168.43.241:8086/api/modifyemployee';
    }
    
    try 
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      if (data.code === 0)
      {
        alert(data.message);
        localStorage.setItem('employeeId',document.getElementById("lblEmpId").value);
      }
      else
      {
        alert(data.message);
      }
    } catch(err) 
    { alert(err.message); }
  }

  render()
  {
    const paperStyle={padding:20,height:'105vh',width:600,margin:"10px auto",border: '5px solid green'}
    const btnStyle={margin:'8px 0'}
   
    return (
      <div>
        <Paper style={paperStyle} variant="outlined">
          <Table>
            <tr>
              <td><TextField id="employeeCode" label='Code' variant='outlined' required  style ={{width: '50%'}}></TextField></td>
              <td></td><td><label id="lblEmpId" value='0'></label></td>
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
                <RadioGroup aria-label="Gender" name="gender" value={this.state.gender} row>
                <FormControlLabel value="M" control={<Radio />} label="Male" onChange={this.onValueChange}/>
                <FormControlLabel value="F" control={<Radio />} label="Female" onChange={this.onValueChange}/>
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
                <td><TextField id="address1" multiline variant='outlined' style ={{width: '200%'}}></TextField></td>
            </tr><br/>
            <tr><td></td>
                <td><TextField id="address2" multiline variant='outlined' style ={{width: '200%'}}></TextField></td>
            </tr><br/>
            <tr><td><label>State</label></td>
                <td>
                  <Select id="statesCombo" value={this.state.stateId} onChange={this.statesComboChange}
                    style={{ border: '1px solid' ,width:'200px' }}>
                    {this.state.statesToDisplay}
                  </Select>
                </td>
            </tr>
            <tr>
              <td><label>District</label></td>
              <td>
                <Select id="districtsCombo" value={this.state.districtId} onChange={this.districtsComboChange}
                  style={{ border: '1px solid' ,width:'200px'  }}>
                  {this.state.districtsToDisplay}
                </Select>
               </td>
               <td>&emsp;&emsp;<TextField id="pin" variant='outlined' label='Pin' style ={{width: '60%'}}></TextField></td>
            </tr>
            <br/>
            <tr><td><label>Contact Numbers</label></td>
                <td><TextField id="contactNumber" variant='outlined' style ={{width: '200%'}}></TextField></td>
            </tr>
            <tr><td><label>Email ids</label></td>
                <td><TextField id="email" variant='outlined' style ={{width: '200%'}}></TextField></td>
            </tr>
            <br/>
            <tr><td></td>
                <td><Button type='submit' color='primary' variant='contained' style={btnStyle} 
                  size='small' startIcon={<SaveIcon />} onClick={() => { this.updateEmployeePersonal() }}>Save</Button></td>
            </tr>
          </Table>
        </Paper>
      </div>
    );
  }
}

export default EmployeePersonalForm;
import React, { Component } from 'react';
import { Table, TextField , Button,Paper,Select,MenuItem} from '@material-ui/core';

class AttendanceGenerationForm extends Component {
  constructor(props) 
  {
    super(props);
    this.state = {
      monthsToDisplay:[],
      monthId:0
    }
    this.monthsComboChange = this.monthsComboChange.bind(this);
    this.generateAttendance = this.generateAttendance.bind(this);
  }

  componentDidMount()
  {
    let initialDataToDisplay = [];

    initialDataToDisplay.push(<MenuItem value={1}>January</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={2}>February</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={3}>March</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={4}>April</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={5}>May</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={6}>June</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={7}>July</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={8}>August</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={9}>September</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={10}>October</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={11}>November</MenuItem>);
    initialDataToDisplay.push(<MenuItem value={12}>December</MenuItem>);

    this.setState({
      monthsToDisplay: initialDataToDisplay
    });
  }

  monthsComboChange(event) 
  {
    this.setState({
      monthId : event.target.value
    });
  }

  validateControls()
  {
    if(document.getElementById("year").value.trim().length === 0)
    {
      alert("Enter Year for attendance generation");
      document.getElementById("year").focus();
      return false;
    }
    if(this.state.monthId === 0)
    {
      alert("Select Month for attendance generation");
      document.getElementById("monthsCombo").focus();
      return false;
    }
    return true;
  }

  async generateAttendance() 
  {
    if(!this.validateControls())
    {
      return;
    }
    const requestOptions = {
        crossDomain:true,
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' +  localStorage.getItem('tokenValue') }
    };
    
    var payMonth=this.state.monthId;
    var payYear=document.getElementById("year").value;
    
    var url='http://192.168.43.241:8086/api/empattendance/' + payMonth + '/' + payYear 
    
    try 
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      
      if (data.code === 0)
      {
        alert(data.message);
      }
      else
      {
        alert(data.message);
      }
    }
    catch(err) {
      alert(err.message);
    }
  }

    render() {
        const paperStyle={padding:20,height:'25vh',width:400,margin:"40px 100px"}
      
      return (
        <div>
            <Paper style={paperStyle} variant="outlined">
              <Table>
                <tr>
                  <td>
                    <TextField id="year" label='Year' placeholder='Enter Year' variant='outlined' style={{width: '40%'}}></TextField>
                  </td>
                  <td>
                    <Select id="monthsCombo" value={this.state.value} onChange={this.monthsComboChange}
                      style={{ border: '1px solid',width:'150px' }}>
                      {this.state.monthsToDisplay}
                    </Select>
                  </td>
                </tr>
                <tr>
                  <br/>
                  <div><Button type='submit' color='primary' variant='contained'  
                        onClick={() => { this.generateAttendance() }}>Generate Attendance</Button>
                  </div>
                </tr>
              </Table>
            </Paper>
         </div>
        );
  }
}

export default AttendanceGenerationForm;
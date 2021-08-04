import React, { Component } from 'react';
import { Table, TextField , Button,Paper,Select,MenuItem} from '@material-ui/core';
import ProgressBar from "./progress-bar.component";
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class AttendanceGenerationForm extends Component {
  constructor(props) 
  {
    super(props);
    this.state = {
      monthsToDisplay:[],
      monthId:0,
      disableButton:false,
      completed:0,
      showProgressBar:false,
      showMessageBox:false,
      title:'',
      displayMessage:''
    }
    this.monthsComboChange = this.monthsComboChange.bind(this);
    this.generateAttendance = this.generateAttendance.bind(this);
  }

  async componentDidMount()
  {
    this.PopulateMonths();
    document.getElementById("year").focus();
  }

  PopulateMonths()
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

  async showMessage(isShowMsgBox,messageBoxtitle,dispMessage,controlName) 
  {
    if(controlName.trim().length > 0)
    {
        document.getElementById(controlName).focus();
    }
    await this.setState({
        showMessageBox:isShowMsgBox,
        title: messageBoxtitle,
        displayMessage: dispMessage
    });
  }

  async validateControls()
  {
    await this.showMessage(false,'', '','');

    if(document.getElementById("year").value.trim().length === 0)
    {
      await this.showMessage(true,'Error Information', 'Enter Year for attendance generation','year');
      return false;
    }
    if(this.state.monthId === 0)
    {
      await this.showMessage(true,'Error Information', 'Select Month for attendance generation','monthsCombo');
      return false;
    }
    return true;
  }

  async generateAttendance() 
  {
    if(!await this.validateControls())
    {
      return;
    }

    await this.setState({ showProgressBar:true,
                    disableButton:true,
                    completed:0 });
    alert(this.state.completed);
    if (this.state.completed<=100)
    {
      setInterval(() => this.setState({completed : 
      setInterval(Math.floor(Math.random() * 100) + 1)}), 2000);
    }
    document.getElementById("lblMsg").innerHTML="Please wait....Attendance Generation is going on";
    document.getElementById("lblMsg").style.color = 'green';
    
    const requestOptions = {
        crossDomain:true,
        method: 'POST',
        headers: { 'Content-Type': 'application/json',
                    'Authorization' : 'Bearer ' +  localStorage.getItem('tokenValue') }
    };
    
    var payMonth=this.state.monthId;
    var payYear=document.getElementById("year").value;
    
    var url= HelperMethods.GetServerIP() + 'api/empattendance/' + payMonth + '/' + payYear 
    
    try 
    {
      const response = await fetch(url,requestOptions);
      var data = await response.json();
      
      if (data.code === 0)
      {
        await this.showMessage(true,'Save Information', data.message,'');
      }
      else
      {
        await this.showMessage(true,'Error Information', data.message,'');
      }
      
      this.setState({ showProgressBar:false,
        disableButton:false,
        completed:0 });
      
      document.getElementById("lblMsg").innerHTML="";
    }
    catch(err) 
    {
      await this.showMessage(true,'Error Information', err.message,'');
    }
  }

    render() {
        const paperStyle={padding:20,height:'25vh',width:400,margin:"40px 100px",border: '5px solid brown'}
      
      return (
        <div>
            {this.state.showMessageBox ?
              <div>
                  <MessageBoxForm title={this.state.title}>
                  {this.state.displayMessage}
                  </MessageBoxForm>
              </div> : null}
            <Paper style={paperStyle} variant="outlined">
              <Table>
                <tr>
                  <td>
                    <TextField id="year" label='Year' placeholder='Year' variant='outlined' style={{width: '40%'}}></TextField>
                  </td>
                  <td>
                    <Select id="monthsCombo" value={this.state.value} onChange={this.monthsComboChange}
                      style={{ border: '1px solid',width:'150px' }}>
                      {this.state.monthsToDisplay}
                    </Select>
                  </td>
                </tr>
                <br/>
                <tr>
                 <td colSpan="2">
                    <div><Button type='submit' color='primary' variant='contained' disabled={this.state.disableButton}
                          onClick={() => { this.generateAttendance() }}>Generate Attendance</Button>
                    </div>
                  </td>
                </tr>
                <br/>
                <tr>
                { this.state.showProgressBar ?
                  <td colSpan="2">
                    <ProgressBar id="pBar" bgcolor={"#6a1b9a"} completed={this.state.completed}/>
                  </td> :null }
                </tr>
                <tr>
                  <td colSpan="2">
                    <label id="lblMsg"></label>
                  </td>
                </tr>
              </Table>
            </Paper>
         </div>
        );
  }
}

export default AttendanceGenerationForm;
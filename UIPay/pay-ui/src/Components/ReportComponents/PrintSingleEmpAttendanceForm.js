import React, { Component } from 'react';
import { TextField, Table, Select , MenuItem, Button,Paper } from '@material-ui/core';
import moment from 'moment';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

class PrintSingleEmpAttendanceForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            monthsToDisplay:[],
            employeesToDisplay:[],
            attendanceData:[],
            monthId:0,
            employeeId:0,
            showAttendance: false,
            paperHeight:'15vh',
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.monthsComboChange=this.monthsComboChange.bind(this);
        this.employeesComboChange = this.employeesComboChange.bind(this);
        this.printSingleEmpAttendance=this.printSingleEmpAttendance.bind(this);
    }

    componentDidMount(){
        this.populateMonths();
        var url= HelperMethods.GetServerIP() +  'api/employees';
        this.populateCombos(url)
        document.getElementById("year").focus();
    }

    populateMonths()
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

    async populateCombos(url) 
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
            if(data!=null && data.length>0)
            {
                for(var i=0;i<=data.length-1;i++)
                {
                    var emp_name= data[i].emp_first_name + " " + data[i]?.emp_middle_name + " " + data[i].emp_last_name;
                    initialDataToDisplay.push(<MenuItem value={data[i].emp_id}>{emp_name}</MenuItem>)
                }
                this.setState({ employeesToDisplay: initialDataToDisplay });
            }
        } catch(err) 
        { 
            await this.showMessage(true,'Error Information',err.message,'');
        }
    }

    employeesComboChange(event) 
    {
        this.setState({
            employeeId : event.target.value
        });
    }

    async validateControls()
    {
        await this.showMessage(false,'','','');

        if(document.getElementById("year").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Enter Year for attendance','year');
            return false;
        }
        if(this.state.monthId === 0)
        {
            await this.showMessage(true,'Error Information','Select Month for attendance','monthsCombo');
            return false;
        }
        if(this.state.employeeId === 0)
        {
            await this.showMessage(true,'Error Information','Select Employee for attendance','employeesCombo');
            return false;
        }
        return true;
    }

    async printSingleEmpAttendance()
    {
        if(!await this.validateControls())
        {
            return;
        }

        let initialDataToDisplay=[];
        try
        {
            this.setState({ attendanceData :[] });   

            const requestOptions = 
            {
                crossDomain:true,
                method: 'GET',
                headers: { 'Content-Type': 'application/json',
                           'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') }
            };
            
            var empId=this.state.employeeId;
            var monthId=this.state.monthId;
            var year=document.getElementById("year").value;
            
            var url= HelperMethods.GetServerIP() + 'api/get_single_emp_attendance/'  + monthId + '/' + year + '/' + empId;
            
            const resp = await fetch(url,requestOptions);
            var data = await resp.json();
            if(data!=null && data.length > 0)
            {
                initialDataToDisplay.push(<tr>
                    <th>Slno</th>
                    <th>Date</th><th>Type</th>
                    <th>Hrs worked</th><th>Leave</th>
                    </tr>);
                for (var i = 0; i < data.length; i++)
                {
                    let leaveTypeName='';
                    let attendanceDate=moment(data[i].attendanceIdentity.attendance_date).format('DD-MM-YYYY');
                    try
                    {
                        leaveTypeName=data[i].leaveType.leave_type_name;
                    }catch(err){}
                    
                    initialDataToDisplay.push(<tr>
                        <td>{i+1}</td><td>{attendanceDate}</td>
                        <td>{data[i].attendanceType.attendance_type_code}</td>
                        <td>{data[i].no_hours_worked}</td>
                        <td>{leaveTypeName}</td>
                    </tr>)
                }
                this.setState({ 
                    paperHeight:'100vh',
                    showAttendance:true,
                    attendanceData: initialDataToDisplay });   
                }
            else
            {
                await this.setState({ 
                    paperHeight:'15vh',
                    showAttendance:false,
                    attendanceData: [] });  
                await this.showMessage(true,'Information','No records to display',''); 
            }
        } catch(err) 
        { 
            await this.setState({ 
                paperHeight:'20vh',
                showAttendance:false,
                attendanceData: [] });  
            await this.showMessage(true,'Error Information',err.message,''); 
        }
    }
    
    render() {
        const paperStyle={padding:20,height:this.state.paperHeight,width:450,margin:"40px 100px",border: '5px solid brown'}
        const divStyle = {
            border: '5px solid green',
            height: '80vh',
            overflow: 'auto'
          };

        return (
            <Paper style={paperStyle} variant="outlined">
                {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                        {this.state.displayMessage}
                        </MessageBoxForm>
                </div> : null}
                <Table>
                    <tr>
                    <td>
                        <TextField id="year" label='Year' placeholder='Year' variant='outlined' style ={{width: '30%'}} size="small"></TextField>
                    </td>
                    <td>
                        <Select id="monthsCombo" value={this.state.value} onChange={this.monthsComboChange}
                        style={{ border: '1px solid',width:'180px' }}>
                        {this.state.monthsToDisplay}
                        </Select>
                    </td>
                    </tr>
                    <br/>
                    <tr><td>
                        <Select id="employeesCombo" value={this.state.value} onChange={this.employeesComboChange}
                            style={{ border: '1px solid' ,width:'220px' }}>
                            {this.state.employeesToDisplay}
                        </Select>
                        </td>
                        <td><Button type='submit' color='primary' variant='contained' 
                            onClick={() => { this.printSingleEmpAttendance() }}>Print Attendance</Button>
                        </td>
                    </tr>
                </Table>
                <br/>
                { this.state.showAttendance
                    ?<div style={divStyle}>
                    <Table id='tableAttendance' border='1'>
                        {this.state.attendanceData}
                    </Table>
                    </div>
                      : null
                }
            </Paper>
        );
    }
}

export default PrintSingleEmpAttendanceForm;
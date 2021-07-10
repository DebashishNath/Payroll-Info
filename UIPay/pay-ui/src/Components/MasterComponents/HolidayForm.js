import React, { Component } from 'react';
import { TextField, Table, Select , MenuItem, Button,Paper } from '@material-ui/core';
import moment from 'moment';

class HolidayForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            showHolidays:false,
            monthsToDisplay:[],
            holidayRecords:[],
            monthId:0,
            holidayId:0,
            paperHeight:'30vh'
        }
        this.monthsComboChange=this.monthsComboChange.bind(this);
        this.doEditHolidayRecord=this.doEditHolidayRecord.bind(this);
        this.doUpdateHolidayRecord=this.doUpdateHolidayRecord.bind(this);
        this.clearControls=this.clearControls.bind(this);
    }

    componentDidMount()
    {
        this.populateMonths();
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
  
    async monthsComboChange(event) 
    {
        await this.setState( {monthId:event.target.value});
        this.showHolidaysMonthWise();
    }
    
    doUpdateHolidayRecord(){}

    clearControls(){}

    validateControls()
    {
        if(document.getElementById("year").value.trim().length === 0)
        {
            alert("Enter Year for displaying holidays");
            document.getElementById("year").focus();
            return false;
        }
        if(this.state.monthId === 0)
        {
            alert("Select Month for displaying holidays");
            document.getElementById("monthsCombo").focus();
            return false;
        }
    }

    async showHolidaysMonthWise()
    {
        try
        {
            var initialDataToDisplay=[];
            const requestOptions = {
                crossDomain:true,
                method: 'GET',
                headers: { 'Content-Type': 'application/json',
                           'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') }
            };
            var holidayYear=document.getElementById("year").value;
           
            var url='http://192.168.43.241:8086/api/holidays/' + this.state.monthId + '/' + holidayYear;
            const resp = await fetch(url,requestOptions);
            var data = await resp.json();
            
            if(data!=null && data.length > 0)
            {
                initialDataToDisplay.push(<tr>
                    <th>Slno</th>
                    <th>Id</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Date</th>
                    <th></th>
                    </tr>);
                for (var i = 0; i < data.length; i++)
                {
                    let id=data[i].holiday_Id;
                    let code=data[i].holiday_Code;
                    let holidayName=data[i].holiday_Name;
                    let holidayDate=data[i].holiday_Date;
                    let formatHolidayDate=moment(data[i].holiday_Date).format('DD-MM-YYYY');

                    initialDataToDisplay.push(
                        <tr key={id}>
                        <td>{i+1}</td>
                        <td>{id}</td>
                        <td>{code}</td>
                        <td>{holidayName}</td>
                        <td>{formatHolidayDate}</td>
                        <td><Button color="primary" variant="contained"  
                            onClick={() => { this.doEditHolidayRecord(id,code,holidayName,holidayDate) }}>Edit</Button></td>
                        </tr>);
                }
                this.setState({ 
                    showHolidays:true,
                    paperHeight : '75vh',
                    holidayRecords: initialDataToDisplay });  
                
                document.getElementById("year").focus();
            }
            else
            {
                this.setState({ 
                    showHolidays:false,
                    paperHeight : '30vh',
                    holidayRecords: [] });   
                alert('No records to display');
            }
        } catch(err) { alert(err.message); }
    }
    
    async doEditHolidayRecord(id,code,holidayName,holidayDate)
    {
        document.getElementById("holidayCode").value=code;
        document.getElementById("holidayName").value=holidayName;
        var dtFormat = require('dateformat');
        document.getElementById("holidayDate").value=dtFormat(holidayDate,'yyyy-mm-dd');
        await this.setState({holidayId:id});
    }

    render() {
        const paperStyle={padding:20,height:this.state.paperHeight,width:550,margin:"40px 100px",border: '5px solid brown'}
        const divStyle = {
            border: '5px solid green',
            height: '40vh',
            overflow: 'auto'
          };
        return (
        <div>
            <Paper style={paperStyle} variant="outlined">
                <Table>
                    <tr>
                    <td><label>Year</label></td>
                    <td>
                        <TextField id="year" variant='outlined' style ={{width: '60%'}}></TextField>
                    </td>
                    <td><label>Month</label></td>
                    <td>
                        <Select id="monthsCombo" value={this.state.monthId} onChange={this.monthsComboChange}
                        style={{ border: '1px solid',width:'140px' }}>
                        {this.state.monthsToDisplay}
                        </Select>
                    </td>
                    </tr><br/>
                    <tr>
                    <td><label>Holiday Code</label></td>
                    <td>
                        <TextField id="holidayCode" variant='outlined' style ={{width: '60%'}}></TextField>
                    </td>
                    <td><label>Holiday Name</label></td>
                    <td>
                        <TextField id="holidayName" variant='outlined'></TextField>
                    </td>
                    </tr><br/>
                    <tr>
                    <td><label>Holiday Date</label></td>
                    <td>
                        <TextField id="holidayDate" label="" type="date" defaultValue=""
                        InputLabelProps={{shrink: true,}} />
                    </td><td></td>
                        <td><Button color="primary" variant="contained" 
                            onClick={() => { this.doUpdateHolidayRecord() }}>Update</Button>
                        &nbsp;&nbsp;<Button color="primary" variant="contained" 
                            onClick={() => { this.clearControls() }}>Reset</Button></td>
                    </tr>
                </Table>
                <br/>
                { this.state.showHolidays
                  ? <div style={divStyle}>
                        <Table id='tableHolidays' border='1'>
                            {this.state.holidayRecords}
                        </Table>
                    </div> : null }
            </Paper>
         </div>
        );
    }
}

export default HolidayForm;
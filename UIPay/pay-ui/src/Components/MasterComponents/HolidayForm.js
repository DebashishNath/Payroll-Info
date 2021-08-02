import React, { Component } from 'react';
import { TextField, Table, Select , MenuItem, Button,Paper } from '@material-ui/core';
import moment from 'moment';
import SaveIcon from '@material-ui/icons/Save';
import ClearAllIcon from '@material-ui/icons/ClearAll';
import EditIcon from '@material-ui/icons/Edit';
import MessageBoxForm from '../CommonComponents/MessageBoxForm';
import HelperMethods from '../CommonComponents/HelperMethods';

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
            paperHeight:'30vh',
            showMessageBox:false,
            title:'',
            displayMessage:''
        }
        this.monthsComboChange=this.monthsComboChange.bind(this);
        this.doEditHolidayRecord=this.doEditHolidayRecord.bind(this);
        this.doUpdateHolidayRecord=this.doUpdateHolidayRecord.bind(this);
        this.doClearControls=this.doClearControls.bind(this);
    }

    async componentDidMount()
    {
        this.populateMonths();
        await document.getElementById("year").focus();
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
        await this.showMessage(false,'','','');
        if(document.getElementById("year").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Year cannot be blank','year');
            return;
        }
        await this.setState( {monthId:event.target.value});
        this.showHolidaysMonthWise();
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
        await this.showMessage(false,'','','');

        if(document.getElementById("year").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Year cannot be blank','year');
            return false;
        }
        if(this.state.monthId === 0)
        {
            await this.showMessage(true,'Error Information','Select month for display of holiday','monthsCombo');
            return false;
        }
        if(document.getElementById("holidayCode").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Holday Code cannot be blank','holidayCode');
            return false;
        }
        if(document.getElementById("holidayName").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Holday Name cannot be blank','holidayName');
            return false;
        }
        if(document.getElementById("holidayDate").value.trim().length === 0)
        {
            await this.showMessage(true,'Error Information','Holday Date cannot be blank','holidayDate');
            return false;
        }
        return true;
    }

    async doUpdateHolidayRecord()
    {
        if(!await this.validateControls())
        {
            return;
        }
        const requestOptions = {
            crossDomain:true,
            method: 'POST',
            headers: { 'Content-Type': 'application/json',
                        'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') },
            body: JSON.stringify({
                "holiday_id": this.state.holidayId,
                "holiday_code": document.getElementById("holidayCode").value,
                "holiday_name" : document.getElementById("holidayName").value,
                "holiday_date" : document.getElementById("holidayDate").value
              })
        };
        
        var url= HelperMethods.GetServerIP() + 'api/update_holiday';
        try 
        {
            const response = await fetch(url,requestOptions);
            var data = await response.json();
            
            if (data.code === 0)
            {
                this.showHolidaysMonthWise();
                await this.showMessage(true,'Save Information',data.message,'holidayCode');
                if(this.state.holidayId === 0)
                {
                    this.doClearControls();
                }
            }
            else
            {
                await this.showMessage(true,'Error Information',data.message,'');
            }
        }catch(err) 
        { 
            await this.showMessage(true,'Error Information',err.message,'');
        }
    }

    async doClearControls()
    {
        await this.setState({holidayId:0});
        document.getElementById("holidayCode").value="";
        document.getElementById("holidayName").value="";
        document.getElementById("holidayDate").value="";
        document.getElementById("holidayCode").focus();
    }

    async showHolidaysMonthWise()
    {
        await this.showMessage(false,'','','');
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
           
            var url=HelperMethods.GetServerIP() + 'api/holidays/' + this.state.monthId + '/' + holidayYear;
            const resp = await fetch(url,requestOptions);
            var data = await resp.json();
            
            if(data!=null && data.length > 0)
            {
                initialDataToDisplay.push(<tr>
                    <th>Slno</th>
                    <th style={{display:'none'}}>Id</th>
                    <th>Code</th>
                    <th>Name</th>
                    <th>Date</th>
                    <th></th>
                    </tr>);
                for (var i = 0; i < data.length; i++)
                {
                    let id=data[i].holiday_id;
                    let code=data[i].holiday_code;
                    let holidayName=data[i].holiday_name;
                    let holidayDate=data[i].holiday_date;
                    let formatHolidayDate=moment(data[i].holiday_date).format('DD-MM-YYYY');

                    initialDataToDisplay.push(
                        <tr key={id}>
                        <td>{i+1}</td>
                        <td style={{display:'none'}}>{id}</td>
                        <td>{code}</td>
                        <td>{holidayName}</td>
                        <td>{formatHolidayDate}</td>
                        <td><Button color="primary" variant="contained" size="small" startIcon={<EditIcon/>}
                            onClick={() => { this.doEditHolidayRecord(id,code,holidayName,holidayDate) }}>Edit</Button></td>
                        </tr>);
                }
                this.setState({ 
                    showHolidays:true,
                    paperHeight : '75vh',
                    holidayRecords: initialDataToDisplay });  
             }
            else
            {
                await this.setState({
                    showHolidays:false,
                    paperHeight : '30vh',
                    holidayRecords: []
                });
                await this.showMessage(true,'Information','No records to display','');
            }
            document.getElementById("holidayCode").focus();
        } catch(err) 
        { 
            await this.showMessage(true,'Error Information',err.message,'');
        }
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
                {this.state.showMessageBox ?
                    <div>
                        <MessageBoxForm title={this.state.title}>
                        {this.state.displayMessage}
                        </MessageBoxForm>
                    </div> : null}
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
                        <td><Button color="primary" variant="contained" size="small" startIcon={<SaveIcon/>}
                            onClick={() => { this.doUpdateHolidayRecord() }}>Save</Button>
                        &nbsp;&nbsp;<Button color="primary" variant="contained" size="small" startIcon={<ClearAllIcon/>}
                            onClick={() => { this.doClearControls() }}>Reset</Button></td>
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
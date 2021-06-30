import React, { Component } from 'react';
import { TextField, Table, Select , MenuItem, Button,Paper } from '@material-ui/core';

class PrintPaySlipForm extends Component {
    constructor(props) 
    {
        super(props);
        this.state = {
            monthsToDisplay:[],
            employeesToDisplay:[],
            paySlipData:[],
            displayMessage:'',
            monthId:0,
            employeeId:0
        }
        this.monthsComboChange=this.monthsComboChange.bind(this);
        this.employeesComboChange = this.employeesComboChange.bind(this);
        this.printPaySlip=this.printPaySlip.bind(this);
    }

    componentDidMount(){
        this.populateMonths();
        var url='http://192.168.43.241:8086/api/employees';
        this.populateCombos(url)
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
        } catch(err) { alert(err.message); }
    }

    employeesComboChange(event) 
    {
        this.setState({
            employeeId : event.target.value
        });
    }

    validateControls()
    {
        if(document.getElementById("year").value.trim().length === 0)
        {
            alert("Enter Year for Pay Slip printing");
            document.getElementById("year").focus();
            return false;
        }
        if(this.state.monthId === 0)
        {
            alert("Select Month for Pay Slip printing");
            document.getElementById("monthsCombo").focus();
            return false;
        }
        if(this.state.employeeId === 0)
        {
            alert("Select Employee for Pay Slip printing");
            document.getElementById("employeesCombo").focus();
            return false;
        }
        return true;
    }

    async printPaySlip()
    {
        if(!this.validateControls()) 
        {
            return;
        }
        try
        {
            let earnData = [],dedData = [],payData=[];
            let i = 0,total_earn_amount=0,total_ded_amount=0,net_amount=0;
            this.setState({ displayMessage :'' });
            this.setState({ paySlipData: [] });   

            const requestOptions = {
                crossDomain:true,
                method: 'GET',
                headers: { 'Content-Type': 'application/json',
                           'Authorization' : 'Bearer ' + localStorage.getItem('tokenValue') }
            };
            var userId=localStorage.getItem('userId');
            var payEmpId=this.state.employeeId;
            var payMonthId=this.state.monthId;
            var payYear=document.getElementById("year").value;
            
            var url='http://192.168.43.241:8086/api/printpayslip/' + userId + '/' + payMonthId + '/' + payYear + '/' + payEmpId;
            
            const resp = await fetch(url,requestOptions);
            var data = await resp.json();
            if(data!=null && data.lstPrintPaySlip!=null && data.lstPrintPaySlip.length > 0)
            {
                for (i = 0; i < data.lstPrintPaySlip.length; i++)
                {
                    if(i===0)
                    {
                        total_earn_amount=data.lstPrintPaySlip[i].total_earn_amount;
                        total_ded_amount=data.lstPrintPaySlip[i].total_ded_amount;
                        net_amount=data.lstPrintPaySlip[i].net_amount;
                    }
                    let earnDedType=data.lstPrintPaySlip[i].earn_ded_type;
                    if(earnDedType==='E')
                    {
                        earnData.push([data.lstPrintPaySlip[i].earn_ded_name,data.lstPrintPaySlip[i].earn_ded_amount]);
                    }
                    if(earnDedType==='D')
                    {
                        dedData.push([data.lstPrintPaySlip[i].earn_ded_name,data.lstPrintPaySlip[i].earn_ded_amount]);
                    }
                }
                let rows=0;
                if(earnData.length > dedData.length)
                { 
                    rows=earnData.length; 
                }
                else
                {
                    rows=dedData.length;
                }
                let diffRows=earnData.length - dedData.length;
                if(diffRows>0)
                {
                    for(i=0;i<diffRows;i++)
                    { dedData.push(['','']); }
                }
                else {
                    for(i=0;i<diffRows;i++)
                    { earnData.push(['','']); }
                }
                payData.push(<tr>
                    <th>Slno</th>
                    <th>Earning Components</th><th>Amount</th>
                    <th>Deduction Components</th><th>Amount</th>
                    </tr>);
                for(i=0;i<rows;i++)
                {
                    payData.push(<tr>
                        <td>{i+1}</td>
                        <td>{earnData[i][0]}</td>
                        <td>{earnData[i][1]}</td>
                        <td>{dedData[i][0]}</td>
                        <td>{dedData[i][1]}</td>
                        </tr>);
                }
                payData.push(<tr><td>&nbsp;</td><td></td><td></td><td></td><td></td></tr>);
                payData.push(<tr>
                    <td></td>
                    <td>Total Earn Amount</td>
                    <td>{total_earn_amount}</td>
                    <td>Total Ded Amount</td>
                    <td>{total_ded_amount}</td>
                    </tr>);
                
                payData.push(<tr>
                    <td></td><td></td><td></td>
                    <td>Net Amount</td><td>{net_amount}</td>
                    </tr>);
                this.setState({ paySlipData: payData });   
            }
            else
            {
                this.setState({ displayMessage :'No records to display' });
            }
        } catch(err) { alert(err.message); }
    }
    
    render() {
        const paperStyle={padding:20,height:'50vh',width:650,margin:"40px 100px"}
        
        return (
        <div>
            <Paper style={paperStyle} variant="outlined">
                <Table>
                    <tr>
                    <td>
                        <TextField id="year" label='Year' placeholder='Enter Year' variant='outlined' style ={{width: '20%'}}></TextField>
                    </td>
                    <td>
                        <Select id="monthsCombo" value={this.state.monthId} onChange={this.monthsComboChange}
                        style={{ border: '1px solid',width:'140px' }}>
                        {this.state.monthsToDisplay}
                        </Select>
                    </td>
                    </tr>
                    <br/>
                    <tr><td>
                        <Select id="employeesCombo" value={this.state.employeeId} onChange={this.employeesComboChange}
                            style={{ border: '1px solid' ,width:'220px' }}>
                            {this.state.employeesToDisplay}
                        </Select>
                        </td>
                        <td><Button type='submit' color='primary' variant='contained' 
                            onClick={() => { this.printPaySlip() }}>Print Pay Slip</Button>
                        </td>
                    </tr>
                </Table>
                <br/>
                <Table id='tablePaySlip' border='1'>
                    {this.state.paySlipData}
                </Table>
                <div>{this.state.displayMessage}</div>
            </Paper>
         </div>
        );
    }
}

export default PrintPaySlipForm;